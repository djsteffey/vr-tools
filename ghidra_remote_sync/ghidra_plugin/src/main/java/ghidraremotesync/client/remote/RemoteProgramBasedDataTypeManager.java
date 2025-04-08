package ghidraremotesync.client.remote;

import db.Transaction;
import ghidra.docking.settings.SettingsDefinition;
import ghidra.framework.model.DomainFile;
import ghidra.program.database.map.AddressMap;
import ghidra.program.model.address.Address;
import ghidra.program.model.data.*;
import ghidra.program.model.lang.ProgramArchitecture;
import ghidra.program.model.lang.PrototypeModel;
import ghidra.program.model.listing.Data;
import ghidra.program.model.listing.Program;
import ghidra.util.InvalidNameException;
import ghidra.util.UniversalID;
import ghidra.util.exception.CancelledException;
import ghidra.util.task.TaskMonitor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RemoteProgramBasedDataTypeManager implements ProgramBasedDataTypeManager {
    @Override
    public Program getProgram() {
        return null;
    }

    @Override
    public boolean isChangeAllowed(Data data, SettingsDefinition settingsDefinition) {
        return false;
    }

    @Override
    public boolean setLongSettingsValue(Data data, String s, long l) {
        return false;
    }

    @Override
    public boolean setStringSettingsValue(Data data, String s, String s1) {
        return false;
    }

    @Override
    public boolean setSettings(Data data, String s, Object o) {
        return false;
    }

    @Override
    public Long getLongSettingsValue(Data data, String s) {
        return 0L;
    }

    @Override
    public String getStringSettingsValue(Data data, String s) {
        return "";
    }

    @Override
    public Object getSettings(Data data, String s) {
        return null;
    }

    @Override
    public boolean clearSetting(Data data, String s) {
        return false;
    }

    @Override
    public void clearAllSettings(Data data) {

    }

    @Override
    public String[] getInstanceSettingsNames(Data data) {
        return new String[0];
    }

    @Override
    public boolean isEmptySetting(Data data) {
        return false;
    }

    @Override
    public void moveAddressRange(Address address, Address address1, long l, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void deleteAddressRange(Address address, Address address1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public DomainFile getDomainFile() {
        return null;
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    public UniversalID getUniversalID() {
        return null;
    }

    @Override
    public ProgramArchitecture getProgramArchitecture() {
        return null;
    }

    @Override
    public String getProgramArchitectureSummary() {
        return "";
    }

    @Override
    public boolean containsCategory(CategoryPath categoryPath) {
        return false;
    }

    @Override
    public String getUniqueName(CategoryPath categoryPath, String s) {
        return "";
    }

    @Override
    public DataType resolve(DataType dataType, DataTypeConflictHandler dataTypeConflictHandler) {
        return null;
    }

    @Override
    public DataType addDataType(DataType dataType, DataTypeConflictHandler dataTypeConflictHandler) {
        return null;
    }

    @Override
    public void addDataTypes(Collection<DataType> collection, DataTypeConflictHandler dataTypeConflictHandler, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public Iterator<DataType> getAllDataTypes() {
        return null;
    }

    @Override
    public void getAllDataTypes(List<DataType> list) {

    }

    @Override
    public Iterator<Structure> getAllStructures() {
        return null;
    }

    @Override
    public Iterator<Composite> getAllComposites() {
        return null;
    }

    @Override
    public Iterator<FunctionDefinition> getAllFunctionDefinitions() {
        return null;
    }

    @Override
    public void findDataTypes(String s, List<DataType> list) {

    }

    @Override
    public void findDataTypes(String s, List<DataType> list, boolean b, TaskMonitor taskMonitor) {

    }

    @Override
    public DataType replaceDataType(DataType dataType, DataType dataType1, boolean b) throws DataTypeDependencyException {
        return null;
    }

    @Override
    public DataType getDataType(String s) {
        return null;
    }

    @Override
    public DataType findDataType(String s) {
        return null;
    }

    @Override
    public DataType getDataType(DataTypePath dataTypePath) {
        return null;
    }

    @Override
    public long getResolvedID(DataType dataType) {
        return 0;
    }

    @Override
    public long getID(DataType dataType) {
        return 0;
    }

    @Override
    public DataType getDataType(long l) {
        return null;
    }

    @Override
    public Category getCategory(long l) {
        return null;
    }

    @Override
    public Category getCategory(CategoryPath categoryPath) {
        return null;
    }

    @Override
    public void addDataTypeManagerListener(DataTypeManagerChangeListener dataTypeManagerChangeListener) {

    }

    @Override
    public void removeDataTypeManagerListener(DataTypeManagerChangeListener dataTypeManagerChangeListener) {

    }

    @Override
    public void addInvalidatedListener(InvalidatedListener invalidatedListener) {

    }

    @Override
    public void removeInvalidatedListener(InvalidatedListener invalidatedListener) {

    }

    @Override
    public boolean remove(DataType dataType, TaskMonitor taskMonitor) {
        return false;
    }

    @Override
    public boolean contains(DataType dataType) {
        return false;
    }

    @Override
    public Category createCategory(CategoryPath categoryPath) {
        return null;
    }

    @Override
    public DataType getDataType(CategoryPath categoryPath, String s) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String s) throws InvalidNameException {

    }

    @Override
    public boolean isUpdatable() {
        return false;
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
    public void endTransaction(int i, boolean b) {

    }

    @Override
    public void flushEvents() {

    }

    @Override
    public void close() {

    }

    @Override
    public Pointer getPointer(DataType dataType) {
        return null;
    }

    @Override
    public Pointer getPointer(DataType dataType, int i) {
        return null;
    }

    @Override
    public Category getRootCategory() {
        return null;
    }

    @Override
    public boolean isFavorite(DataType dataType) {
        return false;
    }

    @Override
    public void setFavorite(DataType dataType, boolean b) {

    }

    @Override
    public List<DataType> getFavorites() {
        return List.of();
    }

    @Override
    public int getCategoryCount() {
        return 0;
    }

    @Override
    public int getDataTypeCount(boolean b) {
        return 0;
    }

    @Override
    public void findEnumValueNames(long l, Set<String> set) {

    }

    @Override
    public DataType getDataType(SourceArchive sourceArchive, UniversalID universalID) {
        return null;
    }

    @Override
    public DataType findDataTypeForID(UniversalID universalID) {
        return null;
    }

    @Override
    public long getLastChangeTimeForMyManager() {
        return 0;
    }

    @Override
    public SourceArchive getSourceArchive(UniversalID universalID) {
        return null;
    }

    @Override
    public ArchiveType getType() {
        return null;
    }

    @Override
    public List<DataType> getDataTypes(SourceArchive sourceArchive) {
        return List.of();
    }

    @Override
    public SourceArchive getLocalSourceArchive() {
        return null;
    }

    @Override
    public void associateDataTypeWithArchive(DataType dataType, SourceArchive sourceArchive) {

    }

    @Override
    public void disassociate(DataType dataType) {

    }

    @Override
    public boolean updateSourceArchiveName(String s, String s1) {
        return false;
    }

    @Override
    public boolean updateSourceArchiveName(UniversalID universalID, String s) {
        return false;
    }

    @Override
    public DataOrganization getDataOrganization() {
        return null;
    }

    @Override
    public AddressMap getAddressMap() {
        return null;
    }

    @Override
    public List<SourceArchive> getSourceArchives() {
        return List.of();
    }

    @Override
    public void removeSourceArchive(SourceArchive sourceArchive) {

    }

    @Override
    public SourceArchive resolveSourceArchive(SourceArchive sourceArchive) {
        return null;
    }

    @Override
    public Set<DataType> getDataTypesContaining(DataType dataType) {
        return Set.of();
    }

    @Override
    public boolean allowsDefaultBuiltInSettings() {
        return false;
    }

    @Override
    public boolean allowsDefaultComponentSettings() {
        return false;
    }

    @Override
    public Collection<String> getKnownCallingConventionNames() {
        return List.of();
    }

    @Override
    public Collection<String> getDefinedCallingConventionNames() {
        return List.of();
    }

    @Override
    public PrototypeModel getDefaultCallingConvention() {
        return null;
    }

    @Override
    public PrototypeModel getCallingConvention(String s) {
        return null;
    }
}
