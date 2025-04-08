package ghidraremotesync.client;

import db.Transaction;
import ghidra.app.plugin.core.progmgr.ProgramLocator;
import ghidra.app.util.task.OpenProgramRequest;
import ghidra.app.util.task.OpenProgramTask;
import ghidra.framework.data.DomainObjectFileListener;
import ghidra.framework.model.*;
import ghidra.framework.options.Options;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.store.LockException;
import ghidra.program.database.IntRangeMap;
import ghidra.program.database.ProgramDB;
import ghidra.program.database.ProgramOverlayAddressSpace;
import ghidra.program.database.map.AddressMap;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressFactory;
import ghidra.program.model.address.AddressOverflowException;
import ghidra.program.model.address.AddressSpace;
import ghidra.program.model.data.CategoryPath;
import ghidra.program.model.data.ProgramBasedDataTypeManager;
import ghidra.program.model.lang.*;
import ghidra.program.model.listing.*;
import ghidra.program.model.mem.Memory;
import ghidra.program.model.pcode.Varnode;
import ghidra.program.model.reloc.RelocationTable;
import ghidra.program.model.symbol.*;
import ghidra.program.model.util.AddressSetPropertyMap;
import ghidra.program.model.util.PropertyMapManager;
import ghidra.program.util.ChangeManager;
import ghidra.program.util.ProgramEvent;
import ghidra.util.InvalidNameException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.NotFoundException;
import ghidra.util.task.TaskLauncher;
import ghidra.util.task.TaskMonitor;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RemoteProgram_v2 implements Program, ChangeManager {
    // variables
    public ProgramDB m_real;


    // methods

    public RemoteProgram_v2(PluginTool tool){
        ProjectData pd = tool.getProject().getProjectData();
        DomainFolder folder = pd.getRootFolder();
        DomainFile df = folder.getFile("notepad++.exe");
        OpenProgramTask task = new OpenProgramTask(
                new ProgramLocator(
                        df
                ),
                this
        );
        new TaskLauncher(task, tool.getToolFrame());
        this.m_real = (ProgramDB) task.getOpenProgram().getProgram();
    }

    @Override
    public Listing getListing() {
        return this.m_real.getListing();
    }

    @Override
    @SuppressWarnings({"deprecazted", "removal"})
    public AddressMap getAddressMap() {
        return this.m_real.getAddressMap();
    }

    @Override
    public ProgramBasedDataTypeManager getDataTypeManager() {
        return this.m_real.getDataTypeManager();
    }

    @Override
    public FunctionManager getFunctionManager() {
        return this.m_real.getFunctionManager();
    }

    @Override
    public ProgramUserData getProgramUserData() {
        return this.m_real.getProgramUserData();
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this.m_real.getSymbolTable();
    }

    @Override
    public ExternalManager getExternalManager() {
        return this.m_real.getExternalManager();
    }

    @Override
    public EquateTable getEquateTable() {
        return this.m_real.getEquateTable();
    }

    @Override
    public Memory getMemory() {
        return this.m_real.getMemory();
    }

    @Override
    public ReferenceManager getReferenceManager() {
        return this.m_real.getReferenceManager();
    }

    @Override
    public BookmarkManager getBookmarkManager() {
        return this.m_real.getBookmarkManager();
    }

    @Override
    public int getDefaultPointerSize() {
        return this.m_real.getDefaultPointerSize();
    }

    @Override
    public String getCompiler() {
        return this.m_real.getCompiler();
    }

    @Override
    public void setCompiler(String s) {
        this.m_real.setCompiler(s);
    }

    @Override
    public CategoryPath getPreferredRootNamespaceCategoryPath() {
        return this.m_real.getPreferredRootNamespaceCategoryPath();
    }

    @Override
    public void setPreferredRootNamespaceCategoryPath(String s) {
        this.m_real.setPreferredRootNamespaceCategoryPath(s);
    }

    @Override
    public String getExecutablePath() {
        return this.m_real.getExecutablePath();
    }

    @Override
    public void setExecutablePath(String s) {
        this.m_real.setExecutablePath(s);
    }

    @Override
    public String getExecutableFormat() {
        return this.m_real.getExecutableFormat();
    }

    @Override
    public void setExecutableFormat(String s) {
        this.m_real.setExecutableFormat(s);
    }

    @Override
    public String getExecutableMD5() {
        return this.m_real.getExecutableMD5();
    }

    @Override
    public void setExecutableMD5(String s) {
        this.m_real.setExecutableMD5(s);
    }

    @Override
    public void setExecutableSHA256(String s) {
        this.m_real.setExecutableSHA256(s);
    }

    @Override
    public String getExecutableSHA256() {
        return this.m_real.getExecutableSHA256();
    }

    @Override
    public Date getCreationDate() {
        return this.m_real.getCreationDate();
    }

    @Override
    public RelocationTable getRelocationTable() {
        return this.m_real.getRelocationTable();
    }

    @Override
    public Language getLanguage() {
        return this.m_real.getLanguage();
    }

    @Override
    public CompilerSpec getCompilerSpec() {
        return this.m_real.getCompilerSpec();
    }

    @Override
    public LanguageID getLanguageID() {
        return this.m_real.getLanguageID();
    }

    @Override
    public PropertyMapManager getUsrPropertyManager() {
        return this.m_real.getUsrPropertyManager();
    }

    @Override
    public ProgramContext getProgramContext() {
        return this.m_real.getProgramContext();
    }

    @Override
    public Address getMinAddress() {
        return this.m_real.getMinAddress();
    }

    @Override
    public Address getMaxAddress() {
        return this.m_real.getMaxAddress();
    }

    @Override
    public ProgramChangeSet getChanges() {
        return this.m_real.getChanges();
    }

    @Override
    public AddressFactory getAddressFactory() {
        return this.m_real.getAddressFactory();
    }

    @Override
    public Address[] parseAddress(String s) {
        return this.m_real.parseAddress(s);
    }

    @Override
    public Address[] parseAddress(String s, boolean b) {
        return this.m_real.parseAddress(s, b);
    }

    @Override
    public ProgramOverlayAddressSpace createOverlaySpace(String s, AddressSpace addressSpace) throws IllegalStateException, DuplicateNameException, InvalidNameException, LockException {
        return this.m_real.createOverlaySpace(s, addressSpace);
    }

    @Override
    public void renameOverlaySpace(String s, String s1) throws NotFoundException, InvalidNameException, DuplicateNameException, LockException {
        this.m_real.renameOverlaySpace(s, s1);
    }

    @Override
    public boolean removeOverlaySpace(String s) throws LockException, NotFoundException {
        return this.m_real.removeOverlaySpace(s);
    }

    @Override
    public Register getRegister(String s) {
        return this.m_real.getRegister(s);
    }

    @Override
    public Register getRegister(Address address) {
        return this.m_real.getRegister(address);
    }

    @Override
    public Register[] getRegisters(Address address) {
        return this.m_real.getRegisters(address);
    }

    @Override
    public Register getRegister(Address address, int i) {
        return this.m_real.getRegister(address, i);
    }

    @Override
    public Register getRegister(Varnode varnode) {
        return this.m_real.getRegister(varnode);
    }

    @Override
    public Address getImageBase() {
        return this.m_real.getImageBase();
    }

    @Override
    public void setImageBase(Address address, boolean b) throws AddressOverflowException, LockException, IllegalStateException {
        this.m_real.setImageBase(address, b);
    }

    @Override
    public void restoreImageBase() {
        this.m_real.restoreImageBase();
    }

    @Override
    public void setLanguage(Language language, CompilerSpecID compilerSpecID, boolean b, TaskMonitor taskMonitor) throws IllegalStateException, IncompatibleLanguageException, LockException {
        this.m_real.setLanguage(language, compilerSpecID, b, taskMonitor);
    }

    @Override
    public Namespace getGlobalNamespace() {
        return this.m_real.getGlobalNamespace();
    }

    @Override
    public AddressSetPropertyMap createAddressSetPropertyMap(String s) throws DuplicateNameException {
        return this.m_real.createAddressSetPropertyMap(s);
    }

    @Override
    public IntRangeMap createIntRangeMap(String s) throws DuplicateNameException {
        return this.m_real.createIntRangeMap(s);
    }

    @Override
    public AddressSetPropertyMap getAddressSetPropertyMap(String s) {
        return this.m_real.getAddressSetPropertyMap(s);
    }

    @Override
    public IntRangeMap getIntRangeMap(String s) {
        return this.m_real.getIntRangeMap(s);
    }

    @Override
    public void deleteAddressSetPropertyMap(String s) {
        this.m_real.deleteAddressSetPropertyMap(s);
    }

    @Override
    public void deleteIntRangeMap(String s) {
        this.m_real.deleteIntRangeMap(s);
    }

    @Override
    public long getUniqueProgramID() {
        return this.m_real.getUniqueProgramID();
    }

    @Override
    public boolean isChanged() {
        return this.m_real.isChanged();
    }

    @Override
    public void setTemporary(boolean b) {
        this.m_real.setTemporary(b);
    }

    @Override
    public boolean isTemporary() {
        return this.m_real.isTemporary();
    }

    @Override
    public boolean isChangeable() {
        return this.m_real.isChangeable();
    }

    @Override
    public boolean canSave() {
        return this.m_real.canSave();
    }

    @Override
    public void save(String s, TaskMonitor taskMonitor) throws IOException, CancelledException {
        this.m_real.save(s, taskMonitor);
    }

    @Override
    public void saveToPackedFile(File file, TaskMonitor taskMonitor) throws IOException, CancelledException {
        this.m_real.saveToPackedFile(file, taskMonitor);
    }

    @Override
    public void release(Object o) {
        this.m_real.release(o);
    }

    @Override
    public void addListener(DomainObjectListener domainObjectListener) {
        this.m_real.addListener(domainObjectListener);
    }

    @Override
    public void removeListener(DomainObjectListener domainObjectListener) {
        this.m_real.removeListener(domainObjectListener);
    }

    @Override
    public void addCloseListener(DomainObjectClosedListener domainObjectClosedListener) {
        this.m_real.addCloseListener(domainObjectClosedListener);
    }

    @Override
    public void removeCloseListener(DomainObjectClosedListener domainObjectClosedListener) {
        this.m_real.removeCloseListener(domainObjectClosedListener);
    }

    @Override
    public void addDomainFileListener(DomainObjectFileListener domainObjectFileListener) {
        this.m_real.addDomainFileListener(domainObjectFileListener);
    }

    @Override
    public void removeDomainFileListener(DomainObjectFileListener domainObjectFileListener) {
        this.m_real.removeDomainFileListener(domainObjectFileListener);
    }

    @Override
    public EventQueueID createPrivateEventQueue(DomainObjectListener domainObjectListener, int i) {
        return this.m_real.createPrivateEventQueue(domainObjectListener, i);
    }

    @Override
    public boolean removePrivateEventQueue(EventQueueID eventQueueID) {
        return this.m_real.removePrivateEventQueue(eventQueueID);
    }

    @Override
    public String getDescription() {
        return this.m_real.getDescription();
    }

    @Override
    public String getName() {
        return this.m_real.getName();
    }

    @Override
    public void setName(String s) {
        this.m_real.setName(s);
    }

    @Override
    public DomainFile getDomainFile() {
        return this.m_real.getDomainFile();
    }

    @Override
    public boolean addConsumer(Object o) {
        return this.m_real.addConsumer(o);
    }

    @Override
    public List<Object> getConsumerList() {
        return this.m_real.getConsumerList();
    }

    @Override
    public boolean isUsedBy(Object o) {
        return this.m_real.isUsedBy(o);
    }

    @Override
    public void setEventsEnabled(boolean b) {
        this.m_real.setEventsEnabled(b);
    }

    @Override
    public boolean isSendingEvents() {
        return this.m_real.isSendingEvents();
    }

    @Override
    public void flushEvents() {
        this.m_real.flushEvents();
    }

    @Override
    public void flushPrivateEventQueue(EventQueueID eventQueueID) {
        this.m_real.flushPrivateEventQueue(eventQueueID);
    }

    @Override
    public boolean canLock() {
        return this.m_real.canLock();
    }

    @Override
    public boolean isLocked() {
        return this.m_real.isLocked();
    }

    @Override
    public boolean lock(String s) {
        return this.m_real.lock(s);
    }

    @Override
    public void forceLock(boolean b, String s) {
        this.m_real.forceLock(b, s);
    }

    @Override
    public void unlock() {
        this.m_real.unlock();
    }

    @Override
    public List<String> getOptionsNames() {
        return this.m_real.getOptionsNames();
    }

    @Override
    public Options getOptions(String s) {
        return this.m_real.getOptions(s);
    }

    @Override
    public boolean isClosed() {
        return this.m_real.isClosed();
    }

    @Override
    public boolean hasExclusiveAccess() {
        return this.m_real.hasExclusiveAccess();
    }

    @Override
    public Map<String, String> getMetadata() {
        return this.m_real.getMetadata();
    }

    @Override
    public long getModificationNumber() {
        return this.m_real.getModificationNumber();
    }

    @Override
    public Transaction openTransaction(String s) throws IllegalStateException {
        return this.m_real.openTransaction(s);
    }

    @Override
    public int startTransaction(String s) {
        return this.m_real.startTransaction(s);
    }

    @Override
    public int startTransaction(String s, AbortedTransactionListener abortedTransactionListener) {
        return this.m_real.startTransaction(s, abortedTransactionListener);
    }

    @Override
    public void endTransaction(int i, boolean b) {
        this.m_real.endTransaction(i, b);
    }

    @Override
    public TransactionInfo getCurrentTransactionInfo() {
        return this.m_real.getCurrentTransactionInfo();
    }

    @Override
    public boolean hasTerminatedTransaction() {
        return this.m_real.hasTerminatedTransaction();
    }

    @Override
    public DomainObject[] getSynchronizedDomainObjects() {
        return this.m_real.getSynchronizedDomainObjects();
    }

    @Override
    public void addSynchronizedDomainObject(DomainObject domainObject) throws LockException {
        this.m_real.addSynchronizedDomainObject(domainObject);
    }

    @Override
    public void releaseSynchronizedDomainObject() throws LockException {
        this.m_real.releaseSynchronizedDomainObject();
    }

    @Override
    public boolean canUndo() {
        return this.m_real.canUndo();
    }

    @Override
    public boolean canRedo() {
        return this.m_real.canRedo();
    }

    @Override
    public void clearUndo() {
        this.m_real.clearUndo();
    }

    @Override
    public void undo() throws IOException {
        this.m_real.undo();
    }

    @Override
    public void redo() throws IOException {
        this.m_real.redo();
    }

    @Override
    public String getUndoName() {
        return this.m_real.getUndoName();
    }

    @Override
    public String getRedoName() {
        return this.m_real.getRedoName();
    }

    @Override
    public List<String> getAllUndoNames() {
        return this.m_real.getAllUndoNames();
    }

    @Override
    public List<String> getAllRedoNames() {
        return this.m_real.getAllRedoNames();
    }

    @Override
    public void addTransactionListener(TransactionListener transactionListener) {
        this.m_real.addTransactionListener(transactionListener);
    }

    @Override
    public void removeTransactionListener(TransactionListener transactionListener) {
        this.m_real.removeTransactionListener(transactionListener);
    }

    @Override
    public void setChanged(ProgramEvent programEvent, Object o, Object o1) {
        this.m_real.setChanged(programEvent, o, o1);
    }

    @Override
    public void setRegisterValuesChanged(Register register, Address address, Address address1) {
        this.m_real.setRegisterValuesChanged(register, address, address1);
    }

    @Override
    public void setChanged(ProgramEvent programEvent, Address address, Address address1, Object o, Object o1) {
        this.m_real.setChanged(programEvent, address, address1, o, o1);
    }

    @Override
    public void setObjChanged(ProgramEvent programEvent, Object o, Object o1, Object o2) {
        this.m_real.setObjChanged(programEvent, o, o1, o2);
    }

    @Override
    public void setObjChanged(ProgramEvent programEvent, Address address, Object o, Object o1, Object o2) {
        this.m_real.setObjChanged(programEvent, address, o, o1, o2);
    }

    @Override
    public void setPropertyChanged(String s, Address address, Object o, Object o1) {
        this.m_real.setPropertyChanged(s, address, o, o1);
    }

    @Override
    public void setPropertyRangeRemoved(String s, Address address, Address address1) {
        this.m_real.setPropertyRangeRemoved(s, address, address1);
    }
}
