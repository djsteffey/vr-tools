package ghidraremotesync.client.remote;

import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressIterator;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.symbol.Equate;
import ghidra.program.model.symbol.EquateTable;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;
import ghidra.util.task.TaskMonitor;

import java.util.Iterator;
import java.util.List;

public class RemoteEquateTable implements EquateTable {
    @Override
    public Equate createEquate(String s, long l) throws DuplicateNameException, InvalidInputException {
        return null;
    }

    @Override
    public boolean removeEquate(String s) {
        return false;
    }

    @Override
    public void deleteAddressRange(Address address, Address address1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public Equate getEquate(String s) {
        return null;
    }

    @Override
    public Equate getEquate(Address address, int i, long l) {
        return null;
    }

    @Override
    public List<Equate> getEquates(Address address, int i) {
        return List.of();
    }

    @Override
    public List<Equate> getEquates(Address address) {
        return List.of();
    }

    @Override
    public AddressIterator getEquateAddresses() {
        return null;
    }

    @Override
    public List<Equate> getEquates(long l) {
        return List.of();
    }

    @Override
    public Iterator<Equate> getEquates() {
        return null;
    }

    @Override
    public AddressIterator getEquateAddresses(Address address) {
        return null;
    }

    @Override
    public AddressIterator getEquateAddresses(AddressSetView addressSetView) {
        return null;
    }
}
