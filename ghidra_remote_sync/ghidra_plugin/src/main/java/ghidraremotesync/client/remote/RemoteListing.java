package ghidraremotesync.client.remote;

import ghidra.program.database.function.OverlappingFunctionException;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressIterator;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.data.DataType;
import ghidra.program.model.data.DataTypeManager;
import ghidra.program.model.lang.InstructionPrototype;
import ghidra.program.model.lang.InstructionSet;
import ghidra.program.model.lang.ProcessorContextView;
import ghidra.program.model.listing.*;
import ghidra.program.model.mem.MemBuffer;
import ghidra.program.model.symbol.Namespace;
import ghidra.program.model.symbol.SourceType;
import ghidra.program.model.util.CodeUnitInsertionException;
import ghidra.program.model.util.PropertyMap;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;
import ghidra.util.task.TaskMonitor;

import java.util.Iterator;
import java.util.List;

public class RemoteListing implements Listing {
    @Override
    public CodeUnit getCodeUnitAt(Address address) {
        return null;
    }

    @Override
    public CodeUnit getCodeUnitContaining(Address address) {
        return null;
    }

    @Override
    public CodeUnit getCodeUnitAfter(Address address) {
        return null;
    }

    @Override
    public CodeUnit getCodeUnitBefore(Address address) {
        return null;
    }

    @Override
    public CodeUnitIterator getCodeUnitIterator(String s, boolean b) {
        return null;
    }

    @Override
    public CodeUnitIterator getCodeUnitIterator(String s, Address address, boolean b) {
        return null;
    }

    @Override
    public CodeUnitIterator getCodeUnitIterator(String s, AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public CodeUnitIterator getCommentCodeUnitIterator(int i, AddressSetView addressSetView) {
        return null;
    }

    @Override
    public AddressIterator getCommentAddressIterator(int i, AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public AddressIterator getCommentAddressIterator(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public String getComment(int i, Address address) {
        return "";
    }

    @Override
    public void setComment(Address address, int i, String s) {

    }

    @Override
    public CodeUnitIterator getCodeUnits(boolean b) {
        return null;
    }

    @Override
    public CodeUnitIterator getCodeUnits(Address address, boolean b) {
        return null;
    }

    @Override
    public CodeUnitIterator getCodeUnits(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public Instruction getInstructionAt(Address address) {
        return null;
    }

    @Override
    public Instruction getInstructionContaining(Address address) {
        return null;
    }

    @Override
    public Instruction getInstructionAfter(Address address) {
        return null;
    }

    @Override
    public Instruction getInstructionBefore(Address address) {
        return null;
    }

    @Override
    public InstructionIterator getInstructions(boolean b) {
        return null;
    }

    @Override
    public InstructionIterator getInstructions(Address address, boolean b) {
        return null;
    }

    @Override
    public InstructionIterator getInstructions(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public Data getDataAt(Address address) {
        return null;
    }

    @Override
    public Data getDataContaining(Address address) {
        return null;
    }

    @Override
    public Data getDataAfter(Address address) {
        return null;
    }

    @Override
    public Data getDataBefore(Address address) {
        return null;
    }

    @Override
    public DataIterator getData(boolean b) {
        return null;
    }

    @Override
    public DataIterator getData(Address address, boolean b) {
        return null;
    }

    @Override
    public DataIterator getData(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public Data getDefinedDataAt(Address address) {
        return null;
    }

    @Override
    public Data getDefinedDataContaining(Address address) {
        return null;
    }

    @Override
    public Data getDefinedDataAfter(Address address) {
        return null;
    }

    @Override
    public Data getDefinedDataBefore(Address address) {
        return null;
    }

    @Override
    public DataIterator getDefinedData(boolean b) {
        return null;
    }

    @Override
    public DataIterator getDefinedData(Address address, boolean b) {
        return null;
    }

    @Override
    public DataIterator getDefinedData(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public Data getUndefinedDataAt(Address address) {
        return null;
    }

    @Override
    public Data getUndefinedDataAfter(Address address, TaskMonitor taskMonitor) {
        return null;
    }

    @Override
    public Data getFirstUndefinedData(AddressSetView addressSetView, TaskMonitor taskMonitor) {
        return null;
    }

    @Override
    public Data getUndefinedDataBefore(Address address, TaskMonitor taskMonitor) {
        return null;
    }

    @Override
    public AddressSetView getUndefinedRanges(AddressSetView addressSetView, boolean b, TaskMonitor taskMonitor) throws CancelledException {
        return null;
    }

    @Override
    public CodeUnit getDefinedCodeUnitAfter(Address address) {
        return null;
    }

    @Override
    public CodeUnit getDefinedCodeUnitBefore(Address address) {
        return null;
    }

    @Override
    public DataIterator getCompositeData(boolean b) {
        return null;
    }

    @Override
    public DataIterator getCompositeData(Address address, boolean b) {
        return null;
    }

    @Override
    public DataIterator getCompositeData(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public Iterator<String> getUserDefinedProperties() {
        return null;
    }

    @Override
    public void removeUserDefinedProperty(String s) {

    }

    @Override
    public PropertyMap getPropertyMap(String s) {
        return null;
    }

    @Override
    public Instruction createInstruction(Address address, InstructionPrototype instructionPrototype, MemBuffer memBuffer, ProcessorContextView processorContextView, int i) throws CodeUnitInsertionException {
        return null;
    }

    @Override
    public AddressSetView addInstructions(InstructionSet instructionSet, boolean b) throws CodeUnitInsertionException {
        return null;
    }

    @Override
    public Data createData(Address address, DataType dataType, int i) throws CodeUnitInsertionException {
        return null;
    }

    @Override
    public Data createData(Address address, DataType dataType) throws CodeUnitInsertionException {
        return null;
    }

    @Override
    public void clearCodeUnits(Address address, Address address1, boolean b) {

    }

    @Override
    public void clearCodeUnits(Address address, Address address1, boolean b, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public boolean isUndefined(Address address, Address address1) {
        return false;
    }

    @Override
    public void clearComments(Address address, Address address1) {

    }

    @Override
    public void clearProperties(Address address, Address address1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void clearAll(boolean b, TaskMonitor taskMonitor) {

    }

    @Override
    public ProgramFragment getFragment(String s, Address address) {
        return null;
    }

    @Override
    public ProgramModule getModule(String s, String s1) {
        return null;
    }

    @Override
    public ProgramFragment getFragment(String s, String s1) {
        return null;
    }

    @Override
    public ProgramModule createRootModule(String s) throws DuplicateNameException {
        return null;
    }

    @Override
    public ProgramModule getRootModule(String s) {
        return null;
    }

    @Override
    public ProgramModule getRootModule(long l) {
        return null;
    }

    @Override
    public ProgramModule getDefaultRootModule() {
        return null;
    }

    @Override
    public String[] getTreeNames() {
        return new String[0];
    }

    @Override
    public boolean removeTree(String s) {
        return false;
    }

    @Override
    public void renameTree(String s, String s1) throws DuplicateNameException {

    }

    @Override
    public long getNumCodeUnits() {
        return 0;
    }

    @Override
    public long getNumDefinedData() {
        return 0;
    }

    @Override
    public long getNumInstructions() {
        return 0;
    }

    @Override
    public DataTypeManager getDataTypeManager() {
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
    public void removeFunction(Address address) {

    }

    @Override
    public Function getFunctionAt(Address address) {
        return null;
    }

    @Override
    public List<Function> getGlobalFunctions(String s) {
        return List.of();
    }

    @Override
    public List<Function> getFunctions(String s, String s1) {
        return List.of();
    }

    @Override
    public Function getFunctionContaining(Address address) {
        return null;
    }

    @Override
    public FunctionIterator getExternalFunctions() {
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
    public boolean isInFunction(Address address) {
        return false;
    }

    @Override
    public CommentHistory[] getCommentHistory(Address address, int i) {
        return new CommentHistory[0];
    }
}
