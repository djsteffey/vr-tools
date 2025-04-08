package ghidraremotesync.client.remote;

import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressIterator;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.lang.Register;
import ghidra.program.model.listing.Variable;
import ghidra.program.model.symbol.*;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;

public class RemoteReferenceManager implements ReferenceManager {
    @Override
    public Reference addReference(Reference reference) {
        return null;
    }

    @Override
    public Reference addStackReference(Address address, int i, int i1, RefType refType, SourceType sourceType) {
        return null;
    }

    @Override
    public Reference addRegisterReference(Address address, int i, Register register, RefType refType, SourceType sourceType) {
        return null;
    }

    @Override
    public Reference addMemoryReference(Address address, Address address1, RefType refType, SourceType sourceType, int i) {
        return null;
    }

    @Override
    public Reference addOffsetMemReference(Address address, Address address1, boolean b, long l, RefType refType, SourceType sourceType, int i) {
        return null;
    }

    @Override
    public Reference addShiftedMemReference(Address address, Address address1, int i, RefType refType, SourceType sourceType, int i1) {
        return null;
    }

    @Override
    public Reference addExternalReference(Address address, String s, String s1, Address address1, SourceType sourceType, int i, RefType refType) throws InvalidInputException, DuplicateNameException {
        return null;
    }

    @Override
    public Reference addExternalReference(Address address, Namespace namespace, String s, Address address1, SourceType sourceType, int i, RefType refType) throws InvalidInputException, DuplicateNameException {
        return null;
    }

    @Override
    public Reference addExternalReference(Address address, int i, ExternalLocation externalLocation, SourceType sourceType, RefType refType) throws InvalidInputException {
        return null;
    }

    @Override
    public void removeAllReferencesFrom(Address address, Address address1) {

    }

    @Override
    public void removeAllReferencesFrom(Address address) {

    }

    @Override
    public void removeAllReferencesTo(Address address) {

    }

    @Override
    public Reference[] getReferencesTo(Variable variable) {
        return new Reference[0];
    }

    @Override
    public Variable getReferencedVariable(Reference reference) {
        return null;
    }

    @Override
    public void setPrimary(Reference reference, boolean b) {

    }

    @Override
    public boolean hasFlowReferencesFrom(Address address) {
        return false;
    }

    @Override
    public Reference[] getFlowReferencesFrom(Address address) {
        return new Reference[0];
    }

    @Override
    public ReferenceIterator getExternalReferences() {
        return null;
    }

    @Override
    public ReferenceIterator getReferencesTo(Address address) {
        return null;
    }

    @Override
    public ReferenceIterator getReferenceIterator(Address address) {
        return null;
    }

    @Override
    public Reference getReference(Address address, Address address1, int i) {
        return null;
    }

    @Override
    public Reference[] getReferencesFrom(Address address) {
        return new Reference[0];
    }

    @Override
    public Reference[] getReferencesFrom(Address address, int i) {
        return new Reference[0];
    }

    @Override
    public boolean hasReferencesFrom(Address address, int i) {
        return false;
    }

    @Override
    public boolean hasReferencesFrom(Address address) {
        return false;
    }

    @Override
    public Reference getPrimaryReferenceFrom(Address address, int i) {
        return null;
    }

    @Override
    public AddressIterator getReferenceSourceIterator(Address address, boolean b) {
        return null;
    }

    @Override
    public AddressIterator getReferenceSourceIterator(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public AddressIterator getReferenceDestinationIterator(Address address, boolean b) {
        return null;
    }

    @Override
    public AddressIterator getReferenceDestinationIterator(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public int getReferenceCountTo(Address address) {
        return 0;
    }

    @Override
    public int getReferenceCountFrom(Address address) {
        return 0;
    }

    @Override
    public int getReferenceDestinationCount() {
        return 0;
    }

    @Override
    public int getReferenceSourceCount() {
        return 0;
    }

    @Override
    public boolean hasReferencesTo(Address address) {
        return false;
    }

    @Override
    public Reference updateRefType(Reference reference, RefType refType) {
        return null;
    }

    @Override
    public void setAssociation(Symbol symbol, Reference reference) {

    }

    @Override
    public void removeAssociation(Reference reference) {

    }

    @Override
    public void delete(Reference reference) {

    }

    @Override
    public byte getReferenceLevel(Address address) {
        return 0;
    }
}
