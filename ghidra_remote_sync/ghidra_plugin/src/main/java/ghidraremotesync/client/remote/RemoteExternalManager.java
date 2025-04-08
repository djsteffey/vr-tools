package ghidraremotesync.client.remote;

import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Library;
import ghidra.program.model.symbol.*;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;

import java.util.List;

public class RemoteExternalManager implements ExternalManager {
    @Override
    public String[] getExternalLibraryNames() {
        return new String[0];
    }

    @Override
    public Library getExternalLibrary(String s) {
        return null;
    }

    @Override
    public boolean removeExternalLibrary(String s) {
        return false;
    }

    @Override
    public String getExternalLibraryPath(String s) {
        return "";
    }

    @Override
    public void setExternalPath(String s, String s1, boolean b) throws InvalidInputException {

    }

    @Override
    public void updateExternalLibraryName(String s, String s1, SourceType sourceType) throws DuplicateNameException, InvalidInputException {

    }

    @Override
    public ExternalLocationIterator getExternalLocations(String s) {
        return null;
    }

    @Override
    public ExternalLocationIterator getExternalLocations(Address address) {
        return null;
    }

    @Override
    public ExternalLocation getExternalLocation(String s, String s1) {
        return null;
    }

    @Override
    public ExternalLocation getExternalLocation(Namespace namespace, String s) {
        return null;
    }

    @Override
    public List<ExternalLocation> getExternalLocations(String s, String s1) {
        return List.of();
    }

    @Override
    public List<ExternalLocation> getExternalLocations(Namespace namespace, String s) {
        return List.of();
    }

    @Override
    public ExternalLocation getUniqueExternalLocation(String s, String s1) {
        return null;
    }

    @Override
    public ExternalLocation getUniqueExternalLocation(Namespace namespace, String s) {
        return null;
    }

    @Override
    public ExternalLocation getExternalLocation(Symbol symbol) {
        return null;
    }

    @Override
    public boolean contains(String s) {
        return false;
    }

    @Override
    public Library addExternalLibraryName(String s, SourceType sourceType) throws InvalidInputException, DuplicateNameException {
        return null;
    }

    @Override
    public ExternalLocation addExtLocation(String s, String s1, Address address, SourceType sourceType) throws InvalidInputException, DuplicateNameException {
        return null;
    }

    @Override
    public ExternalLocation addExtLocation(Namespace namespace, String s, Address address, SourceType sourceType) throws InvalidInputException {
        return null;
    }

    @Override
    public ExternalLocation addExtLocation(Namespace namespace, String s, Address address, SourceType sourceType, boolean b) throws InvalidInputException {
        return null;
    }

    @Override
    public ExternalLocation addExtFunction(String s, String s1, Address address, SourceType sourceType) throws InvalidInputException, DuplicateNameException {
        return null;
    }

    @Override
    public ExternalLocation addExtFunction(Namespace namespace, String s, Address address, SourceType sourceType) throws InvalidInputException {
        return null;
    }

    @Override
    public ExternalLocation addExtFunction(Namespace namespace, String s, Address address, SourceType sourceType, boolean b) throws InvalidInputException {
        return null;
    }
}
