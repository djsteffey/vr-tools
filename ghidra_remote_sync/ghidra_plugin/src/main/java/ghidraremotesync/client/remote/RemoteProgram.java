package ghidraremotesync.client.remote;

import db.Transaction;
import ghidra.framework.data.DomainObjectAdapter;
import ghidra.framework.data.DomainObjectFileListener;
import ghidra.framework.model.*;
import ghidra.framework.options.Options;
import ghidra.framework.store.LockException;
import ghidra.program.database.IntRangeMap;
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
import ghidra.util.task.TaskMonitor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteProgram implements Program, ChangeManager {
    // variables
    private DomainFile m_domainFile;
    private Set<Object> m_concurrentConsumerSet;

    private Listing m_listing;
    private AddressMap m_addressMap;
    private ProgramBasedDataTypeManager m_programBasedDataTypeManager;
    private FunctionManager m_functionManager;
    private ProgramUserData m_programUserData;
    private SymbolTable m_symbolTable;
    private ExternalManager m_externalManager;
    private EquateTable m_equateTable;
    private Memory m_memory;
    private ReferenceManager m_referenceManager;
    private BookmarkManager m_bookmarkManager;


    // methods
    public RemoteProgram(){
        this.m_domainFile = new RemoteDomainFile();
        this.m_concurrentConsumerSet = ConcurrentHashMap.newKeySet();


        this.m_listing = new RemoteListing();
        this.m_addressMap = new RemoteAddressMap();
        this.m_programBasedDataTypeManager = new RemoteProgramBasedDataTypeManager();
        this.m_functionManager = new RemoteFunctionManager();
        this.m_programUserData = new RemoteProgramUserData();
        this.m_symbolTable = new RemoteSymbolTable();
        this.m_externalManager = new RemoteExternalManager();
        this.m_equateTable = new RemoteEquateTable();
        this.m_memory = new RemoteMemory();
        this.m_referenceManager = new RemoteReferenceManager();
        this.m_bookmarkManager = new RemoteBookmarkManager();
    }

    @Override
    public boolean isChanged() {
        return false;
    }

    @Override
    public void setTemporary(boolean b) {
        int y = 0;
    }

    @Override
    public boolean isTemporary() {
        return false;
    }

    @Override
    public boolean isChangeable() {
        return false;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public void save(String s, TaskMonitor taskMonitor) throws IOException, CancelledException {
        int y = 0;
    }

    @Override
    public void saveToPackedFile(File file, TaskMonitor taskMonitor) throws IOException, CancelledException {
        int y = 0;
    }

    @Override
    public void release(Object o) {
        int y = 0;
    }

    @Override
    public void addListener(DomainObjectListener domainObjectListener) {
        int y = 0;
    }

    @Override
    public void removeListener(DomainObjectListener domainObjectListener) {
        int y = 0;
    }

    @Override
    public void addCloseListener(DomainObjectClosedListener domainObjectClosedListener) {
        int y = 0;
    }

    @Override
    public void removeCloseListener(DomainObjectClosedListener domainObjectClosedListener) {
        int y = 0;
    }

    @Override
    public void addDomainFileListener(DomainObjectFileListener domainObjectFileListener) {
        int y = 0;
    }

    @Override
    public void removeDomainFileListener(DomainObjectFileListener domainObjectFileListener) {
        int y = 0;
    }

    @Override
    public EventQueueID createPrivateEventQueue(DomainObjectListener domainObjectListener, int i) {
        return null;
    }

    @Override
    public boolean removePrivateEventQueue(EventQueueID eventQueueID) {
        return false;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String s) {
        int y = 0;
    }

    @Override
    public DomainFile getDomainFile() {
        return this.m_domainFile;
    }

    @Override
    public boolean addConsumer(Object o) {
        this.m_concurrentConsumerSet.add(o);
        return true;
    }

    @Override
    public List<Object> getConsumerList() {
        return List.of();
    }

    @Override
    public boolean isUsedBy(Object o) {
        return false;
    }

    @Override
    public void setEventsEnabled(boolean b) {
        int y = 0;
    }

    @Override
    public boolean isSendingEvents() {
        return false;
    }

    @Override
    public void flushEvents() {
        int y = 0;
    }

    @Override
    public void flushPrivateEventQueue(EventQueueID eventQueueID) {
        int y = 0;
    }

    @Override
    public boolean canLock() {
        return false;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public boolean lock(String s) {
        return false;
    }

    @Override
    public void forceLock(boolean b, String s) {
        int y = 0;
    }

    @Override
    public void unlock() {
        int y = 0;
    }

    @Override
    public List<String> getOptionsNames() {
        return List.of();
    }

    @Override
    public Options getOptions(String s) {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean hasExclusiveAccess() {
        return false;
    }

    @Override
    public Map<String, String> getMetadata() {
        return Map.of();
    }

    @Override
    public long getModificationNumber() {
        return 0;
    }

    @Override
    public Transaction openTransaction(String s) throws IllegalStateException {
        return null;
    }

    @Override
    public int startTransaction(String s) {
        return 0;
    }

    @Override
    public int startTransaction(String s, AbortedTransactionListener abortedTransactionListener) {
        return 0;
    }

    @Override
    public void endTransaction(int i, boolean b) {
        int y = 0;
    }

    @Override
    public TransactionInfo getCurrentTransactionInfo() {
        return null;
    }

    @Override
    public boolean hasTerminatedTransaction() {
        return false;
    }

    @Override
    public DomainObject[] getSynchronizedDomainObjects() {
        return new DomainObject[0];
    }

    @Override
    public void addSynchronizedDomainObject(DomainObject domainObject) throws LockException {
        int y = 0;
    }

    @Override
    public void releaseSynchronizedDomainObject() throws LockException {
        int y = 0;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public boolean canRedo() {
        return false;
    }

    @Override
    public void clearUndo() {
        int y = 0;
    }

    @Override
    public void undo() throws IOException {
        int y = 0;
    }

    @Override
    public void redo() throws IOException {
        int y = 0;
    }

    @Override
    public String getUndoName() {
        return "";
    }

    @Override
    public String getRedoName() {
        return "";
    }

    @Override
    public List<String> getAllUndoNames() {
        return List.of();
    }

    @Override
    public List<String> getAllRedoNames() {
        return List.of();
    }

    @Override
    public void addTransactionListener(TransactionListener transactionListener) {
        int y = 0;
    }

    @Override
    public void removeTransactionListener(TransactionListener transactionListener) {
        int y = 0;
    }

    @Override
    public Listing getListing() {
        return null;
    }

    @Override
    @SuppressWarnings({"deprecated", "removed"})
    public AddressMap getAddressMap() {
        return null;
    }

    @Override
    public ProgramBasedDataTypeManager getDataTypeManager() {
        return null;
    }

    @Override
    public FunctionManager getFunctionManager() {
        return null;
    }

    @Override
    public ProgramUserData getProgramUserData() {
        return null;
    }

    @Override
    public SymbolTable getSymbolTable() {
        return null;
    }

    @Override
    public ExternalManager getExternalManager() {
        return null;
    }

    @Override
    public EquateTable getEquateTable() {
        return null;
    }

    @Override
    public Memory getMemory() {
        return null;
    }

    @Override
    public ReferenceManager getReferenceManager() {
        return null;
    }

    @Override
    public BookmarkManager getBookmarkManager() {
        return null;
    }

    @Override
    public int getDefaultPointerSize() {
        return 0;
    }

    @Override
    public String getCompiler() {
        return "";
    }

    @Override
    public void setCompiler(String s) {
        int y = 0;
    }

    @Override
    public CategoryPath getPreferredRootNamespaceCategoryPath() {
        return null;
    }

    @Override
    public void setPreferredRootNamespaceCategoryPath(String s) {
        int y = 0;
    }

    @Override
    public String getExecutablePath() {
        return "";
    }

    @Override
    public void setExecutablePath(String s) {
        int y = 0;
    }

    @Override
    public String getExecutableFormat() {
        return "";
    }

    @Override
    public void setExecutableFormat(String s) {
        int y = 0;
    }

    @Override
    public String getExecutableMD5() {
        return "";
    }

    @Override
    public void setExecutableMD5(String s) {
        int y = 0;
    }

    @Override
    public void setExecutableSHA256(String s) {
        int y = 0;
    }

    @Override
    public String getExecutableSHA256() {
        return "";
    }

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public RelocationTable getRelocationTable() {
        return null;
    }

    @Override
    public Language getLanguage() {
        return null;
    }

    @Override
    public CompilerSpec getCompilerSpec() {
        return null;
    }

    @Override
    public LanguageID getLanguageID() {
        return null;
    }

    @Override
    public PropertyMapManager getUsrPropertyManager() {
        return null;
    }

    @Override
    public ProgramContext getProgramContext() {
        return null;
    }

    @Override
    public Address getMinAddress() {
        return null;
    }

    @Override
    public Address getMaxAddress() {
        return null;
    }

    @Override
    public ProgramChangeSet getChanges() {
        return null;
    }

    @Override
    public AddressFactory getAddressFactory() {
        return null;
    }

    @Override
    public Address[] parseAddress(String s) {
        return new Address[0];
    }

    @Override
    public Address[] parseAddress(String s, boolean b) {
        return new Address[0];
    }

    @Override
    public ProgramOverlayAddressSpace createOverlaySpace(String s, AddressSpace addressSpace) throws IllegalStateException, DuplicateNameException, InvalidNameException, LockException {
        return null;
    }

    @Override
    public void renameOverlaySpace(String s, String s1) throws NotFoundException, InvalidNameException, DuplicateNameException, LockException {
        int y = 0;
    }

    @Override
    public boolean removeOverlaySpace(String s) throws LockException, NotFoundException {
        return false;
    }

    @Override
    public Register getRegister(String s) {
        return null;
    }

    @Override
    public Register getRegister(Address address) {
        return null;
    }

    @Override
    public Register[] getRegisters(Address address) {
        return new Register[0];
    }

    @Override
    public Register getRegister(Address address, int i) {
        return null;
    }

    @Override
    public Register getRegister(Varnode varnode) {
        return null;
    }

    @Override
    public Address getImageBase() {
        return null;
    }

    @Override
    public void setImageBase(Address address, boolean b) throws AddressOverflowException, LockException, IllegalStateException {
        int y = 0;
    }

    @Override
    public void restoreImageBase() {
        int y = 0;
    }

    @Override
    public void setLanguage(Language language, CompilerSpecID compilerSpecID, boolean b, TaskMonitor taskMonitor) throws IllegalStateException, IncompatibleLanguageException, LockException {
        int y = 0;
    }

    @Override
    public Namespace getGlobalNamespace() {
        return null;
    }

    @Override
    public AddressSetPropertyMap createAddressSetPropertyMap(String s) throws DuplicateNameException {
        return null;
    }

    @Override
    public IntRangeMap createIntRangeMap(String s) throws DuplicateNameException {
        return null;
    }

    @Override
    public AddressSetPropertyMap getAddressSetPropertyMap(String s) {
        return null;
    }

    @Override
    public IntRangeMap getIntRangeMap(String s) {
        return null;
    }

    @Override
    public void deleteAddressSetPropertyMap(String s) {
        int y = 0;
    }

    @Override
    public void deleteIntRangeMap(String s) {
        int y = 0;
    }

    @Override
    public long getUniqueProgramID() {
        return 0;
    }

    @Override
    public void setChanged(ProgramEvent programEvent, Object o, Object o1) {
        int y = 0;
    }

    @Override
    public void setRegisterValuesChanged(Register register, Address address, Address address1) {
        int y = 0;
    }

    @Override
    public void setChanged(ProgramEvent programEvent, Address address, Address address1, Object o, Object o1) {
        int y = 0;
    }

    @Override
    public void setObjChanged(ProgramEvent programEvent, Object o, Object o1, Object o2) {
        int y = 0;
    }

    @Override
    public void setObjChanged(ProgramEvent programEvent, Address address, Object o, Object o1, Object o2) {
        int y = 0;
    }

    @Override
    public void setPropertyChanged(String s, Address address, Object o, Object o1) {
        int y = 0;
    }

    @Override
    public void setPropertyRangeRemoved(String s, Address address, Address address1) {
        int y = 0;
    }
}
