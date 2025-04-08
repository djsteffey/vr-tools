package ghidraremotesync.client.remote;

import ghidra.framework.store.LockException;
import ghidra.program.database.mem.AddressSourceInfo;
import ghidra.program.database.mem.ByteMappingScheme;
import ghidra.program.database.mem.FileBytes;
import ghidra.program.model.address.*;
import ghidra.program.model.listing.Program;
import ghidra.program.model.mem.*;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.NotFoundException;
import ghidra.util.task.TaskMonitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class RemoteMemory implements Memory {
    @Override
    public Program getProgram() {
        return null;
    }

    @Override
    public AddressSetView getLoadedAndInitializedAddressSet() {
        return null;
    }

    @Override
    public AddressSetView getAllInitializedAddressSet() {
        return null;
    }

    @Override
    public AddressSetView getInitializedAddressSet() {
        return null;
    }

    @Override
    public AddressSetView getExecuteSet() {
        return null;
    }

    @Override
    public boolean isBigEndian() {
        return false;
    }

    @Override
    public void setLiveMemoryHandler(LiveMemoryHandler liveMemoryHandler) {

    }

    @Override
    public LiveMemoryHandler getLiveMemoryHandler() {
        return null;
    }

    @Override
    public MemoryBlock createInitializedBlock(String s, Address address, InputStream inputStream, long l, TaskMonitor taskMonitor, boolean b) throws LockException, MemoryConflictException, AddressOverflowException, CancelledException, IllegalArgumentException {
        return null;
    }

    @Override
    public MemoryBlock createInitializedBlock(String s, Address address, long l, byte b, TaskMonitor taskMonitor, boolean b1) throws LockException, IllegalArgumentException, MemoryConflictException, AddressOverflowException, CancelledException {
        return null;
    }

    @Override
    public MemoryBlock createInitializedBlock(String s, Address address, FileBytes fileBytes, long l, long l1, boolean b) throws LockException, IllegalArgumentException, MemoryConflictException, AddressOverflowException {
        return null;
    }

    @Override
    public MemoryBlock createUninitializedBlock(String s, Address address, long l, boolean b) throws LockException, IllegalArgumentException, MemoryConflictException, AddressOverflowException {
        return null;
    }

    @Override
    public MemoryBlock createBitMappedBlock(String s, Address address, Address address1, long l, boolean b) throws LockException, MemoryConflictException, AddressOverflowException, IllegalArgumentException {
        return null;
    }

    @Override
    public MemoryBlock createByteMappedBlock(String s, Address address, Address address1, long l, ByteMappingScheme byteMappingScheme, boolean b) throws LockException, MemoryConflictException, AddressOverflowException, IllegalArgumentException {
        return null;
    }

    @Override
    public MemoryBlock createBlock(MemoryBlock memoryBlock, String s, Address address, long l) throws LockException, IllegalArgumentException, MemoryConflictException, AddressOverflowException {
        return null;
    }

    @Override
    public void removeBlock(MemoryBlock memoryBlock, TaskMonitor taskMonitor) throws LockException {

    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public MemoryBlock getBlock(Address address) {
        return null;
    }

    @Override
    public MemoryBlock getBlock(String s) {
        return null;
    }

    @Override
    public MemoryBlock[] getBlocks() {
        return new MemoryBlock[0];
    }

    @Override
    public void moveBlock(MemoryBlock memoryBlock, Address address, TaskMonitor taskMonitor) throws LockException, MemoryBlockException, MemoryConflictException, AddressOverflowException, NotFoundException {

    }

    @Override
    public void split(MemoryBlock memoryBlock, Address address) throws MemoryBlockException, LockException, NotFoundException {

    }

    @Override
    public MemoryBlock join(MemoryBlock memoryBlock, MemoryBlock memoryBlock1) throws LockException, MemoryBlockException, NotFoundException {
        return null;
    }

    @Override
    public MemoryBlock convertToInitialized(MemoryBlock memoryBlock, byte b) throws LockException, MemoryBlockException, NotFoundException {
        return null;
    }

    @Override
    public MemoryBlock convertToUninitialized(MemoryBlock memoryBlock) throws MemoryBlockException, NotFoundException, LockException {
        return null;
    }

    @Override
    public Address findBytes(Address address, byte[] bytes, byte[] bytes1, boolean b, TaskMonitor taskMonitor) {
        return null;
    }

    @Override
    public Address findBytes(Address address, Address address1, byte[] bytes, byte[] bytes1, boolean b, TaskMonitor taskMonitor) {
        return null;
    }

    @Override
    public byte getByte(Address address) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getBytes(Address address, byte[] bytes) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getBytes(Address address, byte[] bytes, int i, int i1) throws MemoryAccessException {
        return 0;
    }

    @Override
    public short getShort(Address address) throws MemoryAccessException {
        return 0;
    }

    @Override
    public short getShort(Address address, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getShorts(Address address, short[] shorts) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getShorts(Address address, short[] shorts, int i, int i1) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getShorts(Address address, short[] shorts, int i, int i1, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getInt(Address address) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getInt(Address address, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getInts(Address address, int[] ints) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getInts(Address address, int[] ints, int i, int i1) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getInts(Address address, int[] ints, int i, int i1, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public long getLong(Address address) throws MemoryAccessException {
        return 0;
    }

    @Override
    public long getLong(Address address, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getLongs(Address address, long[] longs) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getLongs(Address address, long[] longs, int i, int i1) throws MemoryAccessException {
        return 0;
    }

    @Override
    public int getLongs(Address address, long[] longs, int i, int i1, boolean b) throws MemoryAccessException {
        return 0;
    }

    @Override
    public void setByte(Address address, byte b) throws MemoryAccessException {

    }

    @Override
    public void setBytes(Address address, byte[] bytes) throws MemoryAccessException {

    }

    @Override
    public void setBytes(Address address, byte[] bytes, int i, int i1) throws MemoryAccessException {

    }

    @Override
    public void setShort(Address address, short i) throws MemoryAccessException {

    }

    @Override
    public void setShort(Address address, short i, boolean b) throws MemoryAccessException {

    }

    @Override
    public void setInt(Address address, int i) throws MemoryAccessException {

    }

    @Override
    public void setInt(Address address, int i, boolean b) throws MemoryAccessException {

    }

    @Override
    public void setLong(Address address, long l) throws MemoryAccessException {

    }

    @Override
    public void setLong(Address address, long l, boolean b) throws MemoryAccessException {

    }

    @Override
    public FileBytes createFileBytes(String s, long l, long l1, InputStream inputStream, TaskMonitor taskMonitor) throws IOException, CancelledException {
        return null;
    }

    @Override
    public List<FileBytes> getAllFileBytes() {
        return List.of();
    }

    @Override
    public boolean deleteFileBytes(FileBytes fileBytes) throws IOException {
        return false;
    }

    @Override
    public AddressSourceInfo getAddressSourceInfo(Address address) {
        return null;
    }

    @Override
    public boolean contains(Address address) {
        return false;
    }

    @Override
    public boolean contains(Address address, Address address1) {
        return false;
    }

    @Override
    public boolean contains(AddressSetView addressSetView) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Address getMinAddress() {
        return null;
    }

    @Override
    public Address getMaxAddress() {
        return null;
    }

    @Override
    public int getNumAddressRanges() {
        return 0;
    }

    @Override
    public AddressRangeIterator getAddressRanges() {
        return null;
    }

    @Override
    public AddressRangeIterator getAddressRanges(boolean b) {
        return null;
    }

    @Override
    public AddressRangeIterator getAddressRanges(Address address, boolean b) {
        return null;
    }

    @Override
    public Iterator<AddressRange> iterator() {
        return null;
    }

    @Override
    public Iterator<AddressRange> iterator(boolean b) {
        return null;
    }

    @Override
    public Iterator<AddressRange> iterator(Address address, boolean b) {
        return null;
    }

    @Override
    public long getNumAddresses() {
        return 0;
    }

    @Override
    public AddressIterator getAddresses(boolean b) {
        return null;
    }

    @Override
    public AddressIterator getAddresses(Address address, boolean b) {
        return null;
    }

    @Override
    public boolean intersects(AddressSetView addressSetView) {
        return false;
    }

    @Override
    public boolean intersects(Address address, Address address1) {
        return false;
    }

    @Override
    public AddressSet intersect(AddressSetView addressSetView) {
        return null;
    }

    @Override
    public AddressSet intersectRange(Address address, Address address1) {
        return null;
    }

    @Override
    public AddressSet union(AddressSetView addressSetView) {
        return null;
    }

    @Override
    public AddressSet subtract(AddressSetView addressSetView) {
        return null;
    }

    @Override
    public AddressSet xor(AddressSetView addressSetView) {
        return null;
    }

    @Override
    public boolean hasSameAddresses(AddressSetView addressSetView) {
        return false;
    }

    @Override
    public AddressRange getFirstRange() {
        return null;
    }

    @Override
    public AddressRange getLastRange() {
        return null;
    }

    @Override
    public AddressRange getRangeContaining(Address address) {
        return null;
    }

    @Override
    public Address findFirstAddressInCommon(AddressSetView addressSetView) {
        return null;
    }
}
