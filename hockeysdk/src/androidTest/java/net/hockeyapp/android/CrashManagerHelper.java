package net.hockeyapp.android;

import android.content.Context;

import net.hockeyapp.android.util.StacktraceFilenameFilter;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

public class CrashManagerHelper {

    public static void loadConstants(Context context) throws Exception {
        Constants.loadFromContext(context);
        Constants.DEVICE_IDENTIFIER.get();
    }

    public static void reset(Context context) {
        CrashManager.weakContext = new WeakReference<>(context);
        CrashManager.latch = new CountDownLatch(1);
        CrashManager.stackTracesCount = 0;
    }

    public static File cleanFiles(Context context) {
        File dir = context.getFilesDir();
        for (File f : dir.listFiles(new StacktraceFilenameFilter())) {
            //noinspection ResultOfMethodCallIgnored
            f.delete();
        }
        return dir;
    }
}
