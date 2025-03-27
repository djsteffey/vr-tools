package ghidraremotesync.client;

import ghidra.framework.data.DBContentHandler;
import ghidra.framework.data.DomainObjectMergeManager;
import ghidra.framework.model.ChangeSet;
import ghidra.framework.model.DomainObject;
import ghidra.framework.store.FileSystem;
import ghidra.framework.store.FolderItem;
import ghidra.util.InvalidNameException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.VersionException;
import ghidra.util.task.TaskMonitor;
import javax.swing.*;
import java.io.IOException;

public class RemoteProgramDomainObjectContentHandler extends DBContentHandler<RemoteProgramDomainObject> {
    // constants
    private static final String CONTENT_TYPE = "RemoteProgram";

    // variables


    // methods
    public RemoteProgramDomainObjectContentHandler(){
        super();
    }

    @Override
    public long createFile(FileSystem fileSystem, FileSystem userFileSystem, String path, String name, DomainObject domainObject, TaskMonitor taskMonitor) throws IOException, InvalidNameException, CancelledException {
        // check that it is our domain object that we handle
        if (domainObject instanceof RemoteProgramDomainObject == false) {
            // not our type
            throw new IOException(
                    "Unsupported domain object: " + domainObject.getClass().getName()
            );
        }

        // create the file
        return createFile(
                (RemoteProgramDomainObject) domainObject,
                CONTENT_TYPE,
                fileSystem,
                path,
                name,
                taskMonitor
        );
    }

    @Override
    public RemoteProgramDomainObject getImmutableObject(FolderItem folderItem, Object o, int i, int i1, TaskMonitor taskMonitor) throws IOException, CancelledException, VersionException {
        return null;
    }

    @Override
    public RemoteProgramDomainObject getReadOnlyObject(FolderItem folderItem, int i, boolean b, Object o, TaskMonitor taskMonitor) throws IOException, VersionException, CancelledException {
        return null;
    }

    @Override
    public RemoteProgramDomainObject getDomainObject(FolderItem folderItem, FileSystem fileSystem, long l, boolean b, boolean b1, Object o, TaskMonitor taskMonitor) throws IOException, CancelledException, VersionException {
        return null;
    }

    @Override
    public ChangeSet getChangeSet(FolderItem folderItem, int i, int i1) throws VersionException, IOException {
        return null;
    }

    @Override
    public DomainObjectMergeManager getMergeManager(DomainObject domainObject, DomainObject domainObject1, DomainObject domainObject2, DomainObject domainObject3) {
        return null;
    }

    @Override
    public boolean isPrivateContentType() {
        return false;
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public String getContentTypeDisplayString() {
        return "Remote Program";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getDefaultToolName() {
        return "";
    }

    @Override
    public Class<RemoteProgramDomainObject> getDomainObjectClass() {
        return null;
    }
}

/*
    public static final String CONTENT_TYPE = "VersionTracking";

    private static final Icon ICON = new GIcon("icon.version.tracking.session.content.type");

    @Override
    public long createFile(FileSystem fs, FileSystem userfs, String path, String name,
                           DomainObject domainObject, TaskMonitor monitor)
            throws IOException, InvalidNameException, CancelledException {

        if (!(domainObject instanceof RemoteProgramDomainObject)) {
            throw new IOException(
                    "Unsupported domain object: " + domainObject.getClass().getName());
        }
        return createFile((RemoteProgramDomainObject) domainObject, CONTENT_TYPE, fs, path, name, monitor);
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public String getContentTypeDisplayString() {
        return "Version Tracking Session";
    }

    @Override
    public String getDefaultToolName() {
        return "Version Tracking";
    }

    private void checkContentAndExclusiveCheckout(FolderItem item) throws IOException {
        String contentType = item.getContentType();
        if (!contentType.equals(CONTENT_TYPE)) {
            throw new IOException("Unsupported content type: " + contentType);
        }

        // NOTE: item.isVersioned indicates that item is located on versioned filesystem
        // and is not checked-out, otheriwse assume item in local filesystem and must
        // ensure if any checkout is exclusive.
        if (item.isVersioned() || (item.isCheckedOut() && !item.isCheckedOutExclusive())) {
            throw new IOException(
                    "Unsupported VT Session use: session file must be checked-out exclusive");
        }
    }

    @Override
    public RemoteProgramDomainObject getDomainObject(FolderItem item, FileSystem userfs, long checkoutId,
                                                     boolean okToUpgrade, boolean okToRecover, Object consumer, TaskMonitor monitor)
            throws IOException, CancelledException, VersionException {

        checkContentAndExclusiveCheckout(item);

        if (item.isReadOnly()) {
            throw new ReadOnlyException("rsdo file is set read-only which prevents its use");
        }

        try {
            DatabaseItem dbItem = (DatabaseItem) item;
            BufferFile bf = dbItem.openForUpdate(checkoutId);
            DBHandle dbh = new DBHandle(bf, okToRecover, monitor);
            boolean success = false;
            try {
                // NOTE: Always open with DB upgrade enabled
                RemoteProgramDomainObject db = new RemoteProgramDomainObject(consumer);
                success = true;
                return db;
            }
            finally {
                if (!success) {
                    dbh.close();
                }
            }
        }
        catch (IOException | CancelledException e) {
            throw e;
        }
        catch (Throwable t) {
            Msg.error(this, "getDomainObject failed", t);
            String msg = t.getMessage();
            if (msg == null) {
                msg = t.toString();
            }
            throw new IOException("Open failed: " + msg, t);
        }

    }

    @Override
    public ChangeSet getChangeSet(FolderItem folderItem, int i, int i1) throws VersionException, IOException {
        return null;
    }

    @Override
    public Class<RemoteProgramDomainObject> getDomainObjectClass() {
        return RemoteProgramDomainObject.class;
    }

    @Override
    public Icon getIcon() {
        return ICON;
    }

    @Override
    public RemoteProgramDomainObject getImmutableObject(FolderItem item, Object consumer, int version,
                                                        int minChangeVersion, TaskMonitor monitor)
            throws IOException, CancelledException, VersionException {

        return getReadOnlyObject(item, -1, false, consumer, monitor);
    }

    @Override
    public DomainObjectMergeManager getMergeManager(DomainObject resultsObj, DomainObject sourceObj,
                                                    DomainObject originalObj, DomainObject latestObj) {

        return null;
    }

    @Override
    public RemoteProgramDomainObject getReadOnlyObject(FolderItem item, int version, boolean okToUpgrade,
                                                       Object consumer, TaskMonitor monitor)
            throws IOException, VersionException, CancelledException {

        checkContentAndExclusiveCheckout(item);

        throw new ReadOnlyException("VT Session does not support read-only use");
    }

    @Override
    public boolean isPrivateContentType() {
        return false;
    }

    @Override
    public boolean canResetDBSourceFile() {
        return true;
    }

    @Override
    public void resetDBSourceFile(FolderItem item, DomainObjectAdapterDB domainObj)
            throws IOException {
        if (!(item instanceof LocalDatabaseItem dbItem) ||
                !(domainObj instanceof RemoteProgramDomainObject vtSession)) {
            throw new IllegalArgumentException("LocalDatabaseItem and VTSessionDB required");
        }
        LocalManagedBufferFile bf = dbItem.openForUpdate(FolderItem.DEFAULT_CHECKOUT_ID);
        vtSession.getDBHandle().setDBVersionedSourceFile(bf);
    }

}*/