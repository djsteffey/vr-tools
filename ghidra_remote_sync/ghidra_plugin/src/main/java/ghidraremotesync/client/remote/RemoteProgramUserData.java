package ghidraremotesync.client.remote;

import db.Transaction;
import ghidra.framework.options.Options;
import ghidra.program.model.listing.ProgramUserData;
import ghidra.program.model.util.*;
import ghidra.util.Saveable;
import ghidra.util.exception.PropertyTypeMismatchException;

import java.util.List;
import java.util.Set;

public class RemoteProgramUserData implements ProgramUserData {
    @Override
    public Transaction openTransaction() {
        return null;
    }

    @Override
    public int startTransaction() {
        return 0;
    }

    @Override
    public void endTransaction(int i) {

    }

    @Override
    public StringPropertyMap getStringProperty(String s, String s1, boolean b) throws PropertyTypeMismatchException {
        return null;
    }

    @Override
    public LongPropertyMap getLongProperty(String s, String s1, boolean b) throws PropertyTypeMismatchException {
        return null;
    }

    @Override
    public IntPropertyMap getIntProperty(String s, String s1, boolean b) throws PropertyTypeMismatchException {
        return null;
    }

    @Override
    public VoidPropertyMap getBooleanProperty(String s, String s1, boolean b) throws PropertyTypeMismatchException {
        return null;
    }

    @Override
    public <T extends Saveable> ObjectPropertyMap<T> getObjectProperty(String s, String s1, Class<T> aClass, boolean b) {
        return null;
    }

    @Override
    public List<PropertyMap<?>> getProperties(String s) {
        return List.of();
    }

    @Override
    public List<String> getPropertyOwners() {
        return List.of();
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
    public void setStringProperty(String s, String s1) {

    }

    @Override
    public String getStringProperty(String s, String s1) {
        return "";
    }

    @Override
    public String removeStringProperty(String s) {
        return "";
    }

    @Override
    public Set<String> getStringPropertyNames() {
        return Set.of();
    }
}
