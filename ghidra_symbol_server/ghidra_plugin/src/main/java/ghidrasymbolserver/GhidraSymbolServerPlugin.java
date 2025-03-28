package ghidrasymbolserver;

import docking.ActionContext;
import docking.action.DockingAction;
import docking.action.MenuData;
import ghidra.app.CorePluginPackage;
import ghidra.app.events.ProgramActivatedPluginEvent;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.ProgramPlugin;
import ghidra.app.services.ConsoleService;
import ghidra.app.services.GoToService;
import ghidra.app.services.ProgramManager;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.model.listing.Program;
import io.grpc.Server;
import io.grpc.ServerBuilder;

@PluginInfo(
        status = PluginStatus.STABLE,
        packageName = CorePluginPackage.NAME,
        category = PluginCategoryNames.COMMON,
        shortDescription = "Server symbol names and address over a gRPC connection",
        description = "Allows a client to query the ghidra program for symbols and address over a gRPC connection",
        servicesRequired = {
                ProgramManager.class
        },
        servicesProvided = {

        },
        eventsConsumed = {
                ProgramActivatedPluginEvent.class
        }

)
public class GhidraSymbolServerPlugin extends ProgramPlugin implements IMessageLogger {
    // variables
    private ConsoleService m_console;
    private DockingAction m_startServerAction;
    private DockingAction m_stopServerAction;
    private boolean m_isServerRunning;
    private GhidraSymbolServerService m_symbolServerService;
    private Server m_gRpcServer;

    // methods
    public GhidraSymbolServerPlugin(PluginTool tool) {
        super(tool);
        this.m_console = tool.getService(ConsoleService.class);
        this.m_startServerAction = null;
        this.m_stopServerAction = null;
        this.m_isServerRunning = false;
        this.m_symbolServerService = null;
        this.m_gRpcServer = null;
        this.createActions(tool);
    }

    @Override
    protected void programActivated(Program program){
        this.m_console = tool.getService(ConsoleService.class);
        if (this.m_symbolServerService != null){
            this.m_symbolServerService.setProgram(program);
        }
    }

    private void createActions(PluginTool tool) {
        // start menu action
        this.m_startServerAction = new DockingAction("Start Server", this.getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                GhidraSymbolServerPlugin.this.startServer();
            }
        };
        this.m_startServerAction.setEnabled(true);
        this.m_startServerAction.setMenuBarData(new MenuData(new String[] { "Ghidra Symbol Server", "Start Server" }, "Ghidra Symbol Server"));
        tool.addAction(this.m_startServerAction);

        // stop menu action
        this.m_stopServerAction = new DockingAction("Start Server", this.getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                GhidraSymbolServerPlugin.this.stopServer();
            }
        };
        this.m_stopServerAction.setEnabled(false);
        this.m_stopServerAction.setMenuBarData(new MenuData(new String[] { "Ghidra Symbol Server", "Stop Server" }, "Ghidra Symbol Server"));
        tool.addAction(this.m_stopServerAction);
    }

    private void startServer(){
        this.addMessage("starting server");

        // check if already running
        if (this.m_isServerRunning){
            // ignore
            this.addMessage("server is already running");
            return;
        }

        // clear action
        this.m_startServerAction.setEnabled(false);

        // create service
        this.m_symbolServerService = new GhidraSymbolServerService(
                this.getCurrentProgram(),
                this,
                this.getTool().getService(GoToService.class)
        );

        // create server
        this.m_gRpcServer = ServerBuilder.forPort(50051)
                .addService(this.m_symbolServerService)
                .build();

        // start server
        try {
            this.m_gRpcServer.start();
        }
        catch (Exception ex){
            this.serverStartFailed(ex);
            return;
        }

        // mark as running
        this.m_isServerRunning = true;

        // enable stop action
        this.m_stopServerAction.setEnabled(true);

        // done
        this.addMessage("server is now running");
    }

    private void stopServer(){
        this.addMessage("stopping server");

        // check if not running
        if (this.m_isServerRunning == false){
            // ignore
            this.addMessage("server is already stopped");
            return;
        }

        // clear action
        this.m_stopServerAction.setEnabled(false);

        // stop server and service
        this.m_gRpcServer.shutdownNow();
        this.m_gRpcServer = null;
        this.m_symbolServerService = null;


        // mask as not running
        this.m_isServerRunning = false;

        // enable start action
        this.m_startServerAction.setEnabled(true);

        // done
        this.addMessage("server has started");
    }

    private void serverStartFailed(Exception ex){
        // pop message box
        this.addMessage("server was unable to start: " + ex.toString());

        // reset vars and ui
        this.m_startServerAction.setEnabled(true);
        this.m_stopServerAction.setEnabled(false);
        this.m_isServerRunning = false;
        this.m_gRpcServer = null;
    }

    @Override
    public void addMessage(String message) {
        if (this.m_console != null){
            this.m_console.addMessage("GhidraSymbolServer", message);
        }
    }
}
