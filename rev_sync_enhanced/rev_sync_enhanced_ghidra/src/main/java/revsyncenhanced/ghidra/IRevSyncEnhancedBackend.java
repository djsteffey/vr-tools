package revsyncenhanced.ghidra;

public interface IRevSyncEnhancedBackend {
    interface IListener{
        void onInfoMessage(String message);
        void onRemoteCommentChanged(long address, String comment);
        void onRemoteSymbolChangedGlobal(long address, String oldName, String newName);
        void onRemoteSymbolChangedLocal(String functionName, String oldName, String newName);
    }

    void stop();
    void publishLocalCommentChanged(long address, String comment);
    void publishLocalSymbolRenamedGlobal(long address, String oldName, String newName);
    void publishLocalSymbolRenamedLocal(String functionName, String oldName, String newName);
}
