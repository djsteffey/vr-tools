package ghidraremotesync.client.remote;

import ghidra.program.database.map.AddressMap;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressFactory;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.address.KeyRange;

import java.util.List;

public class RemoteAddressMap implements AddressMap {
    @Override
    public long getKey(Address address, boolean b) {
        return 0;
    }

    @Override
    public long getAbsoluteEncoding(Address address, boolean b) {
        return 0;
    }

    @Override
    public int findKeyRange(List<KeyRange> list, Address address) {
        return 0;
    }

    @Override
    public List<KeyRange> getKeyRanges(Address address, Address address1, boolean b) {
        return List.of();
    }

    @Override
    public List<KeyRange> getKeyRanges(AddressSetView addressSetView, boolean b) {
        return List.of();
    }

    @Override
    public Address decodeAddress(long l) {
        return null;
    }

    @Override
    public AddressFactory getAddressFactory() {
        return null;
    }

    @Override
    public List<KeyRange> getKeyRanges(Address address, Address address1, boolean b, boolean b1) {
        return List.of();
    }

    @Override
    public List<KeyRange> getKeyRanges(AddressSetView addressSetView, boolean b, boolean b1) {
        return List.of();
    }

    @Override
    public AddressMap getOldAddressMap() {
        return null;
    }

    @Override
    public boolean isUpgraded() {
        return false;
    }

    @Override
    public Address getImageBase() {
        return null;
    }
}
