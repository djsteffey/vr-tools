package ghidraremotesync.client.remote;

import ghidra.framework.data.CheckinHandler;
import ghidra.framework.model.*;
import ghidra.framework.store.ItemCheckoutStatus;
import ghidra.framework.store.Version;
import ghidra.util.InvalidNameException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.VersionException;
import ghidra.util.task.TaskMonitor;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RemoteDomainFile implements DomainFile {
    // variables


    // methods
    public RemoteDomainFile(){
        int y = 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public String getFileID() {
        return "";
    }

    @Override
    public DomainFile setName(String s) throws InvalidNameException, IOException {
        return null;
    }

    @Override
    public String getPathname() {
        return "";
    }

    @Override
    public URL getSharedProjectURL(String s) {
        return null;
    }

    @Override
    public URL getLocalProjectURL(String s) {
        return null;
    }

    @Override
    public ProjectLocator getProjectLocator() {
        return null;
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public Class<? extends DomainObject> getDomainObjectClass() {
        return RemoteProgram.class;
    }

    @Override
    public DomainFolder getParent() {
        return null;
    }

    @Override
    public ChangeSet getChangesByOthersSinceCheckout() throws VersionException, IOException {
        return null;
    }

    @Override
    public DomainObject getDomainObject(Object o, boolean b, boolean b1, TaskMonitor taskMonitor) throws VersionException, IOException, CancelledException {
        return null;
    }

    @Override
    public DomainObject getOpenedDomainObject(Object o) {
        return null;
    }

    @Override
    public DomainObject getReadOnlyDomainObject(Object o, int i, TaskMonitor taskMonitor) throws VersionException, IOException, CancelledException {
        return null;
    }

    @Override
    public DomainObject getImmutableDomainObject(Object o, int i, TaskMonitor taskMonitor) throws VersionException, IOException, CancelledException {
        return null;
    }

    @Override
    public void save(TaskMonitor taskMonitor) throws IOException, CancelledException {
        int y = 0;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public boolean canRecover() {
        return false;
    }

    @Override
    public boolean takeRecoverySnapshot() throws IOException {
        return false;
    }

    @Override
    public boolean isInWritableProject() {
        return false;
    }

    @Override
    public long getLastModifiedTime() {
        return 0;
    }

    @Override
    public Icon getIcon(boolean b) {
        return null;
    }

    @Override
    public boolean isCheckedOut() {
        return false;
    }

    @Override
    public boolean isCheckedOutExclusive() {
        return false;
    }

    @Override
    public boolean modifiedSinceCheckout() {
        return false;
    }

    @Override
    public boolean canCheckout() {
        return false;
    }

    @Override
    public boolean canCheckin() {
        return false;
    }

    @Override
    public boolean canMerge() {
        return false;
    }

    @Override
    public boolean canAddToRepository() {
        return false;
    }

    @Override
    public void setReadOnly(boolean b) throws IOException {
        int y = 0;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public boolean isVersioned() {
        return false;
    }

    @Override
    public boolean isHijacked() {
        return false;
    }

    @Override
    public int getLatestVersion() {
        return 0;
    }

    @Override
    public boolean isLatestVersion() {
        return false;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Version[] getVersionHistory() throws IOException {
        return new Version[0];
    }

    @Override
    public void addToVersionControl(String s, boolean b, TaskMonitor taskMonitor) throws IOException, CancelledException {
        int y = 0;
    }

    @Override
    public boolean checkout(boolean b, TaskMonitor taskMonitor) throws IOException, CancelledException {
        return false;
    }

    @Override
    public void checkin(CheckinHandler checkinHandler, TaskMonitor taskMonitor) throws IOException, VersionException, CancelledException {
        int y = 0;
    }

    @Override
    public void merge(boolean b, TaskMonitor taskMonitor) throws IOException, VersionException, CancelledException {
        int y = 0;
    }

    @Override
    public void undoCheckout(boolean b) throws IOException {
        int y = 0;
    }

    @Override
    public void undoCheckout(boolean b, boolean b1) throws IOException {
        int y = 0;
    }

    @Override
    public void terminateCheckout(long l) throws IOException {
        int y = 0;
    }

    @Override
    public ItemCheckoutStatus[] getCheckouts() throws IOException {
        return new ItemCheckoutStatus[0];
    }

    @Override
    public ItemCheckoutStatus getCheckoutStatus() throws IOException {
        return null;
    }

    @Override
    public void delete() throws IOException {
        int y = 0;
    }

    @Override
    public void delete(int i) throws IOException {
        int y = 0;
    }

    @Override
    public DomainFile moveTo(DomainFolder domainFolder) throws IOException {
        return null;
    }

    @Override
    public DomainFile copyTo(DomainFolder domainFolder, TaskMonitor taskMonitor) throws IOException, CancelledException {
        return null;
    }

    @Override
    public DomainFile copyVersionTo(int i, DomainFolder domainFolder, TaskMonitor taskMonitor) throws IOException, CancelledException {
        return null;
    }

    @Override
    public DomainFile copyToAsLink(DomainFolder domainFolder) throws IOException {
        return null;
    }

    @Override
    public boolean isLinkingSupported() {
        return false;
    }

    @Override
    public List<?> getConsumers() {
        return List.of();
    }

    @Override
    public boolean isChanged() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public void packFile(File file, TaskMonitor taskMonitor) throws IOException, CancelledException {
        int y = 0;
    }

    @Override
    public Map<String, String> getMetadata() {
        return Map.of();
    }

    @Override
    public long length() throws IOException {
        return 0;
    }

    @Override
    public boolean isLinkFile() {
        return false;
    }

    @Override
    public DomainFolder followLink() {
        return null;
    }

    @Override
    public int compareTo(@NotNull DomainFile o) {
        return 0;
    }
}
