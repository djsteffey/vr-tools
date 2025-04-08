package revsyncenhanced.ghidra;

import docking.ActionContext;
import docking.action.DockingAction;
import docking.action.MenuData;
import ghidra.app.CorePluginPackage;
import ghidra.app.events.ProgramActivatedPluginEvent;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.ProgramPlugin;
import ghidra.app.services.ConsoleService;
import ghidra.app.services.ProgramManager;
import ghidra.framework.model.DomainObjectChangedEvent;
import ghidra.framework.model.DomainObjectListener;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.*;
import ghidra.program.model.symbol.SourceType;
import ghidra.program.model.symbol.Symbol;
import ghidra.program.util.CommentChangeRecord;
import ghidra.program.util.FunctionChangeRecord;
import ghidra.program.util.ProgramChangeRecord;
import ghidra.program.util.ProgramEvent;

@PluginInfo(
        status = PluginStatus.STABLE,
        packageName = CorePluginPackage.NAME,
        category = PluginCategoryNames.COMMON,
        shortDescription = "Rev Sync Enhanced - Ghidra",
        description = "Syncs reverse engineering data with other remote users ",
        servicesRequired = {
                ProgramManager.class
        },
        servicesProvided = {

        },
        eventsConsumed = {
                ProgramActivatedPluginEvent.class
        },
        eventsProduced = {

        }
)
public class RevSyncEnhancedGhidraPlugin extends ProgramPlugin implements IMessageLogger, IRevSyncEnhancedBackend.IListener, DomainObjectListener {
    // variables
    private ConsoleService m_console;
    private DockingAction m_startSyncAction;
    private DockingAction m_stopSyncAction;
    private DockingAction m_settingsAction;
    private IRevSyncEnhancedBackend m_backend;

    // methods
    public RevSyncEnhancedGhidraPlugin(PluginTool tool) {
        super(tool);

        // handle to the console service for logging messages
        this.m_console = null;

        // actions for starting and stopping the sync
        this.m_startSyncAction = null;
        this.m_stopSyncAction = null;
        this.m_settingsAction = null;

        // create the start and stop actions
        this.createActions(tool);

        // no backend yet
        this.m_backend = null;
    }

    @Override
    protected void programActivated(Program program) {
        this.m_console = tool.getService(ConsoleService.class);
        this.getCurrentProgram().addListener(this);
        super.programActivated(program);
    }

    private void createActions(PluginTool tool) {
        // start menu action
        this.m_startSyncAction = new DockingAction("Start Sync", this.getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                RevSyncEnhancedGhidraPlugin.this.startSync();
            }
        };
        this.m_startSyncAction.setEnabled(true);
        this.m_startSyncAction.setMenuBarData(new MenuData(new String[] { "Rev Sync Enhanced", "Start Sync" }, "Rev Sync Enhanced"));
        tool.addAction(this.m_startSyncAction);

        // stop menu action
        this.m_stopSyncAction = new DockingAction("Stop Sync", this.getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                RevSyncEnhancedGhidraPlugin.this.stopSync();
            }
        };
        this.m_stopSyncAction.setEnabled(false);
        this.m_stopSyncAction.setMenuBarData(new MenuData(new String[] { "Rev Sync Enhanced", "Stop Sync" }, "Rev Sync Enhanced"));
        tool.addAction(this.m_stopSyncAction);

        // settings menu action
        this.m_settingsAction = new DockingAction("Settings", this.getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                RevSyncEnhancedGhidraPlugin.this.settings();
            }
        };
        this.m_settingsAction.setEnabled(true);
        this.m_settingsAction.setMenuBarData(new MenuData(new String[] { "Rev Sync Enhanced", "Settings" }, "Rev Sync Enhanced"));
        tool.addAction(this.m_settingsAction);
    }

    private void startSync(){
        this.addMessage("starting sync");

        // disable actions
        this.m_startSyncAction.setEnabled(false);
        this.m_settingsAction.setEnabled(false);

        // load settings
        RevSyncEnhancedSettings settings = new RevSyncEnhancedSettings(this);
        settings.load();

        // backend service
        try {
            this.m_backend = new RevSyncBackendEnhancedRedisImpl(
                    settings,
                    this.getCurrentProgram().getExecutableMD5(),
                    this
            );
        }
        catch (Exception ex){
            // log
            this.addMessage("unable to create backend: " + ex.toString());

            // enable actions
            this.m_startSyncAction.setEnabled(true);
            this.m_settingsAction.setEnabled(true);

            // done
            return;
        }

        // enable stop action
        this.m_stopSyncAction.setEnabled(true);

        // done
        this.addMessage("sync is now running");
    }

    private void stopSync(){
        this.addMessage("stopping sync");

        // disable actions
        this.m_stopSyncAction.setEnabled(false);

        // stop backend service
        this.m_backend.stop();

        // enable actionS
        this.m_startSyncAction.setEnabled(true);
        this.m_settingsAction.setEnabled(true);

        // done
        this.addMessage("sync has stopped");
    }

    private void settings(){
        // show the settings dialog
        this.getTool().showDialog(new RevSyncEnhancedSettingsDialog(this));
    }

    // IMessageLogger
    @Override
    public void addMessage(String message) {
        if (this.m_console != null){
            this.m_console.addMessage("GhidraSymbolServer", message);
        }
    }

    // IRevSyncEnhancedBackend.IListener
    @Override
    public void onInfoMessage(String message) {
        this.addMessage(message);
    }

    @Override
    public void onRemoteCommentChanged(long address, String comment) {
        // convert the address
        Address ea = currentProgram.getImageBase().getNewAddress(address);

        // transact the program
        int transactionId = this.getCurrentProgram().startTransaction(this.getClass().getName());

        // set the comment
        this.getCurrentProgram().getListing().setComment(
                ea,
                CodeUnit.EOL_COMMENT,
                comment
        );

        // end transaction
        this.getCurrentProgram().endTransaction(transactionId, true);
    }

    @Override
    public void onRemoteSymbolChangedGlobal(long address, String oldName, String newName) {
        // convert the address
        Address ea = currentProgram.getImageBase().getNewAddress(address);

        // find the symbol
        Symbol symbol = this.getCurrentProgram().getSymbolTable().getPrimarySymbol(ea);

        // transact
        int transactionId = this.getCurrentProgram().startTransaction(this.getClass().getName());

        // change symbol
        if (symbol == null){
            try {
                this.getCurrentProgram().getSymbolTable().createLabel(ea, newName, SourceType.DEFAULT);
            }
            catch (Exception ex){
                this.addMessage("unable to create new symbol: " + ex);
            }
        }
        else{
            try {
                symbol.setName(newName, SourceType.DEFAULT);
            }
            catch (Exception ex){
                this.addMessage("unable to change symbol name: " + ex);
            }
        }

        // end transact
        this.getCurrentProgram().endTransaction(transactionId, true);
    }

    @Override
    public void onRemoteSymbolChangedLocal(String functionName, String oldName, String newName) {
        // get the function
        Function func = this.getCurrentProgram().getFunctionManager().getFunctionAt(
                this.getCurrentProgram().getAddressFactory().getDefaultAddressSpace().getAddress(0x14040ccbcL)
        );
    }

    // DomainObjectListener
    @Override
    public void domainObjectChanged(DomainObjectChangedEvent domainObjectChangedEvent) {
        // debug print
        for (int i = 0; i < domainObjectChangedEvent.numRecords(); ++i){
            this.addMessage("*** " + domainObjectChangedEvent.getChangeRecord(i).toString());
        }

        // save the number of records
        int numRecords = domainObjectChangedEvent.numRecords();

        // comments
        if (domainObjectChangedEvent.contains(ProgramEvent.COMMENT_CHANGED)){
            // should just be one record
            this.handleEventLocal_COMMENT_CHANGED(
                    (CommentChangeRecord) domainObjectChangedEvent.getChangeRecord(0)
            );
        }
        else if (domainObjectChangedEvent.contains(ProgramEvent.SYMBOL_RENAMED)){
            if (domainObjectChangedEvent.numRecords() == 1){
                // global symbol
                this.handleEventLocal_SYMBOL_RENAMED_global(
                        (ProgramChangeRecord) domainObjectChangedEvent.getChangeRecord(numRecords - 1)
                );
            }
            else{
                // function parameter or local variable
                this.handleEventLocal_SYMBOL_RENAMED_local(
                        (FunctionChangeRecord) domainObjectChangedEvent.getChangeRecord(numRecords - 2),
                        (ProgramChangeRecord) domainObjectChangedEvent.getChangeRecord(numRecords - 1)
                );
            }
        }
        // todo add structure stuff
        Function func = this.getCurrentProgram().getFunctionManager().getFunctionAt(
                this.getCurrentProgram().getAddressFactory().getDefaultAddressSpace().getAddress(0x14040ccbcL)
        );
        int y = 0;
    }

    private void handleEventLocal_COMMENT_CHANGED(CommentChangeRecord record){
        this.m_backend.publishLocalCommentChanged(
                record.getStart().getOffset(),
                (String) record.getNewValue()
        );
    }

    private void handleEventLocal_SYMBOL_RENAMED_global(ProgramChangeRecord pcr){
        this.m_backend.publishLocalSymbolRenamedGlobal(
                pcr.getStart().getOffset(),
                (String) pcr.getOldValue(),
                (String) pcr.getNewValue()
        );
    }

    private void handleEventLocal_SYMBOL_RENAMED_local(FunctionChangeRecord fcr, ProgramChangeRecord pcr){
        this.m_backend.publishLocalSymbolRenamedLocal(
                fcr.getFunction().getName(),
                (String) pcr.getOldValue(),
                (String) pcr.getNewValue()
        );
    }
}
