package revsyncenhanced.ghidra;

import ghidra.util.SystemUtilities;
import java.util.concurrent.Executors;

public class Utils {
    public static void runInThread(Runnable runnable){
        Executors.newSingleThreadExecutor().execute(runnable);
    }

    public static void runInSwingThread(Runnable runnable){
        SystemUtilities.runSwingNow(runnable);
    }
}
