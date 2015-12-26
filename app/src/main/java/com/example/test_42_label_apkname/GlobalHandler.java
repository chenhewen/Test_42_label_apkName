package com.example.test_42_label_apkname;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by Administrator on 2015/12/26.
 */
public class GlobalHandler {

    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    private static final HandlerThread HANDLER_THREAD = new HandlerThread("async-thread-for-handler");

    static {
        HANDLER_THREAD.start();
    }

    private static final Handler ASYNC_THREAD_HANDLER = new Handler(HANDLER_THREAD.getLooper());

    public static void postInMainThread(Runnable runnable) {
        MAIN_THREAD_HANDLER.post(runnable);
    }

    public static void postInMainThread(Runnable runnable, long delay) {
        MAIN_THREAD_HANDLER.postDelayed(runnable, delay);
    }

    public static void cancelMainThreadPost(Runnable runnable) {
        MAIN_THREAD_HANDLER.removeCallbacks(runnable);
    }

    public static void postInAsyncThread(Runnable runnable) {
        ASYNC_THREAD_HANDLER.post(runnable);
    }

    public static void postInAsyncThread(Runnable runnable, long delay) {
        ASYNC_THREAD_HANDLER.postDelayed(runnable, delay);
    }
}
