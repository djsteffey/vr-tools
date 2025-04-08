package ghidraremotesync.client.remote;

import ghidra.framework.data.OpenMode;
import ghidra.program.database.ProgramDB;
import ghidra.program.database.function.OverlappingFunctionException;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.lang.PrototypeModel;
import ghidra.program.model.listing.*;
import ghidra.program.model.symbol.Namespace;
import ghidra.program.model.symbol.SourceType;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.InvalidInputException;
import ghidra.util.task.TaskMonitor;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RemoteFunctionManager implements FunctionManager {
    @Override
    public Program getProgram() {
        return null;
    }

    @Override
    public Collection<String> getCallingConventionNames() {
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

    @Override
    public Function createFunction(String s, Address address, AddressSetView addressSetView, SourceType sourceType) throws InvalidInputException, OverlappingFunctionException {
        return null;
    }

    @Override
    public Function createFunction(String s, Namespace namespace, Address address, AddressSetView addressSetView, SourceType sourceType) throws InvalidInputException, OverlappingFunctionException {
        return null;
    }

    @Override
    public Function createThunkFunction(String s, Namespace namespace, Address address, AddressSetView addressSetView, Function function, SourceType sourceType) throws OverlappingFunctionException {
        return null;
    }

    @Override
    public int getFunctionCount() {
        return 0;
    }

    @Override
    public boolean removeFunction(Address address) {
        return false;
    }

    @Override
    public Function getFunctionAt(Address address) {
        return null;
    }

    @Override
    public Function getReferencedFunction(Address address) {
        return null;
    }

    @Override
    public Function getFunctionContaining(Address address) {
        return null;
    }

    @Override
    public FunctionIterator getFunctions(boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getFunctions(Address address, boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getFunctions(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getFunctionsNoStubs(boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getFunctionsNoStubs(Address address, boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getFunctionsNoStubs(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public FunctionIterator getExternalFunctions() {
        return null;
    }

    @Override
    public boolean isInFunction(Address address) {
        return false;
    }

    @Override
    public Iterator<Function> getFunctionsOverlapping(AddressSetView addressSetView) {
        return null;
    }

    @Override
    public Variable getReferencedVariable(Address address, Address address1, int i, boolean b) {
        return null;
    }

    @Override
    public Function getFunction(long l) {
        return null;
    }

    @Override
    public FunctionTagManager getFunctionTagManager() {
        return null;
    }

    @Override
    public void setProgram(ProgramDB programDB) {

    }

    @Override
    public void programReady(OpenMode openMode, int i, TaskMonitor taskMonitor) throws IOException, CancelledException {

    }

    @Override
    public void invalidateCache(boolean b) {

    }

    @Override
    public void deleteAddressRange(Address address, Address address1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void moveAddressRange(Address address, Address address1, long l, TaskMonitor taskMonitor) throws CancelledException {

    }
}
