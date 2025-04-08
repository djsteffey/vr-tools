package ghidraremotesync.client.remote;

import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressIterator;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.GhidraClass;
import ghidra.program.model.listing.Library;
import ghidra.program.model.symbol.*;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;

import java.util.Iterator;
import java.util.List;

public class RemoteSymbolTable implements SymbolTable {
    @Override
    public Symbol createLabel(Address address, String s, SourceType sourceType) throws InvalidInputException {
        return null;
    }

    @Override
    public Symbol createLabel(Address address, String s, Namespace namespace, SourceType sourceType) throws InvalidInputException {
        return null;
    }

    @Override
    public boolean removeSymbolSpecial(Symbol symbol) {
        return false;
    }

    @Override
    public Symbol getSymbol(long l) {
        return null;
    }

    @Override
    public Symbol getSymbol(String s, Address address, Namespace namespace) {
        return null;
    }

    @Override
    public Symbol getGlobalSymbol(String s, Address address) {
        return null;
    }

    @Override
    public List<Symbol> getGlobalSymbols(String s) {
        return List.of();
    }

    @Override
    public List<Symbol> getLabelOrFunctionSymbols(String s, Namespace namespace) {
        return List.of();
    }

    @Override
    public Symbol getNamespaceSymbol(String s, Namespace namespace) {
        return null;
    }

    @Override
    public Symbol getLibrarySymbol(String s) {
        return null;
    }

    @Override
    public Symbol getClassSymbol(String s, Namespace namespace) {
        return null;
    }

    @Override
    public Symbol getParameterSymbol(String s, Namespace namespace) {
        return null;
    }

    @Override
    public Symbol getLocalVariableSymbol(String s, Namespace namespace) {
        return null;
    }

    @Override
    public List<Symbol> getSymbols(String s, Namespace namespace) {
        return List.of();
    }

    @Override
    public Symbol getVariableSymbol(String s, Function function) {
        return null;
    }

    @Override
    public Namespace getNamespace(String s, Namespace namespace) {
        return null;
    }

    @Override
    public SymbolIterator getSymbols(String s) {
        return null;
    }

    @Override
    public SymbolIterator getAllSymbols(boolean b) {
        return null;
    }

    @Override
    public Symbol getSymbol(Reference reference) {
        return null;
    }

    @Override
    public Symbol getPrimarySymbol(Address address) {
        return null;
    }

    @Override
    public Symbol[] getSymbols(Address address) {
        return new Symbol[0];
    }

    @Override
    public SymbolIterator getSymbolsAsIterator(Address address) {
        return null;
    }

    @Override
    public Symbol[] getUserSymbols(Address address) {
        return new Symbol[0];
    }

    @Override
    public SymbolIterator getSymbols(Namespace namespace) {
        return null;
    }

    @Override
    public SymbolIterator getSymbols(long l) {
        return null;
    }

    @Override
    public boolean hasSymbol(Address address) {
        return false;
    }

    @Override
    public long getDynamicSymbolID(Address address) {
        return 0;
    }

    @Override
    public SymbolIterator getSymbolIterator(String s, boolean b) {
        return null;
    }

    @Override
    public SymbolIterator getSymbols(AddressSetView addressSetView, SymbolType symbolType, boolean b) {
        return null;
    }

    @Override
    public SymbolIterator scanSymbolsByName(String s) {
        return null;
    }

    @Override
    public int getNumSymbols() {
        return 0;
    }

    @Override
    public SymbolIterator getSymbolIterator() {
        return null;
    }

    @Override
    public SymbolIterator getDefinedSymbols() {
        return null;
    }

    @Override
    public Symbol getExternalSymbol(String s) {
        return null;
    }

    @Override
    public SymbolIterator getExternalSymbols(String s) {
        return null;
    }

    @Override
    public SymbolIterator getExternalSymbols() {
        return null;
    }

    @Override
    public SymbolIterator getSymbolIterator(boolean b) {
        return null;
    }

    @Override
    public SymbolIterator getSymbolIterator(Address address, boolean b) {
        return null;
    }

    @Override
    public SymbolIterator getPrimarySymbolIterator(boolean b) {
        return null;
    }

    @Override
    public SymbolIterator getPrimarySymbolIterator(Address address, boolean b) {
        return null;
    }

    @Override
    public SymbolIterator getPrimarySymbolIterator(AddressSetView addressSetView, boolean b) {
        return null;
    }

    @Override
    public void addExternalEntryPoint(Address address) {

    }

    @Override
    public void removeExternalEntryPoint(Address address) {

    }

    @Override
    public boolean isExternalEntryPoint(Address address) {
        return false;
    }

    @Override
    public AddressIterator getExternalEntryPointIterator() {
        return null;
    }

    @Override
    public LabelHistory[] getLabelHistory(Address address) {
        return new LabelHistory[0];
    }

    @Override
    public Iterator<LabelHistory> getLabelHistory() {
        return null;
    }

    @Override
    public boolean hasLabelHistory(Address address) {
        return false;
    }

    @Override
    public Namespace getNamespace(Address address) {
        return null;
    }

    @Override
    public Iterator<GhidraClass> getClassNamespaces() {
        return null;
    }

    @Override
    public GhidraClass createClass(Namespace namespace, String s, SourceType sourceType) throws DuplicateNameException, InvalidInputException {
        return null;
    }

    @Override
    public SymbolIterator getChildren(Symbol symbol) {
        return null;
    }

    @Override
    public Library createExternalLibrary(String s, SourceType sourceType) throws DuplicateNameException, InvalidInputException {
        return null;
    }

    @Override
    public Namespace createNameSpace(Namespace namespace, String s, SourceType sourceType) throws DuplicateNameException, InvalidInputException {
        return null;
    }

    @Override
    public GhidraClass convertNamespaceToClass(Namespace namespace) {
        return null;
    }

    @Override
    public Namespace getOrCreateNameSpace(Namespace namespace, String s, SourceType sourceType) throws DuplicateNameException, InvalidInputException {
        return null;
    }
}
