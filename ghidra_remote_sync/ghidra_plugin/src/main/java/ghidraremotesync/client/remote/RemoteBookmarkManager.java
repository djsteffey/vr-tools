package ghidraremotesync.client.remote;

import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.listing.Bookmark;
import ghidra.program.model.listing.BookmarkManager;
import ghidra.program.model.listing.BookmarkType;
import ghidra.program.model.listing.Program;
import ghidra.util.exception.CancelledException;
import ghidra.util.task.TaskMonitor;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class RemoteBookmarkManager implements BookmarkManager {
    @Override
    public BookmarkType defineType(String s, Icon icon, Color color, int i) {
        return null;
    }

    @Override
    public BookmarkType[] getBookmarkTypes() {
        return new BookmarkType[0];
    }

    @Override
    public BookmarkType getBookmarkType(String s) {
        return null;
    }

    @Override
    public String[] getCategories(String s) {
        return new String[0];
    }

    @Override
    public Bookmark setBookmark(Address address, String s, String s1, String s2) {
        return null;
    }

    @Override
    public Bookmark getBookmark(Address address, String s, String s1) {
        return null;
    }

    @Override
    public void removeBookmark(Bookmark bookmark) {

    }

    @Override
    public void removeBookmarks(String s) {

    }

    @Override
    public void removeBookmarks(String s, String s1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void removeBookmarks(AddressSetView addressSetView, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void removeBookmarks(AddressSetView addressSetView, String s, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public void removeBookmarks(AddressSetView addressSetView, String s, String s1, TaskMonitor taskMonitor) throws CancelledException {

    }

    @Override
    public Bookmark[] getBookmarks(Address address, String s) {
        return new Bookmark[0];
    }

    @Override
    public Bookmark[] getBookmarks(Address address) {
        return new Bookmark[0];
    }

    @Override
    public AddressSetView getBookmarkAddresses(String s) {
        return null;
    }

    @Override
    public Iterator<Bookmark> getBookmarksIterator(String s) {
        return null;
    }

    @Override
    public Iterator<Bookmark> getBookmarksIterator() {
        return null;
    }

    @Override
    public Iterator<Bookmark> getBookmarksIterator(Address address, boolean b) {
        return null;
    }

    @Override
    public Bookmark getBookmark(long l) {
        return null;
    }

    @Override
    public boolean hasBookmarks(String s) {
        return false;
    }

    @Override
    public int getBookmarkCount(String s) {
        return 0;
    }

    @Override
    public int getBookmarkCount() {
        return 0;
    }

    @Override
    public Program getProgram() {
        return null;
    }
}
