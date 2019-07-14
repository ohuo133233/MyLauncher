
package com.example.launcher;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * 创建人：v_wangzhifeng01
 * 创建时间：2019-05-29
 * Log工具类
 * 提供了单例实现，和自己处理了自己的内存泄露问题
 * 提供重载方法，你可以选择填写TAG，也可以不填写TAG。
 * 还可以获取当前是在什么线程，行数等详细信息
 */

public class LogUtils {

    private static LogUtils mLogUtils;

    /**
     * 打印Log时的TAG值
     */
    private static String TAG = "TAG";

    /**
     * 打印获取的信息的TAG值，默认为TAG
     * 通过setDefaultTAG修改此值
     */
    private static String defaultTAG = "TAG";

    /**
     * Log开关，默认为打开
     */
    private static boolean mIsDebug = true;

    // 单例模式
    private LogUtils() {

    }

    public static LogUtils getInstance() {
        if (mLogUtils == null) {
            synchronized (LogUtils.class) {
                if (mLogUtils == null) {
                    mLogUtils = new LogUtils();
                }
            }
        }
        return mLogUtils;
    }

    // LogUtils 获取详细信息的方法区

    /**
     * 获取当前线程
     */
    public String getThread() {
        String thread = null;
        if (mIsDebug) {
            thread = Thread.currentThread().getName();
            Log.e(defaultTAG, thread);
        }
        return thread;
    }

    /**
     * 获取当前进程名字
     */
    public String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        Log.e(defaultTAG, "processName" + processName);
        context = null;
        manager = null;
        pid = 0;
        return processName;
    }

    // LogUtils 修改配置的方法区

    /**
     * 修改打印信息的TAG值
     *
     * @param tag 想要修改成的TAG值
     */
    public void setDefaultTAG(String tag) {
        if (tag != null) {
            defaultTAG = tag;
        }
    }

    public void setIsDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }
    // Log打印区

    /**
     * 开发时快速打印log
     *
     * @param message 打印的信息
     */
    public static void v(String message) {
        if (mIsDebug) {
            Log.v(defaultTAG, message);
        }

    }

    public static void v(String tag, String message) {
        if (mIsDebug) {
            if (tag != null) {
                TAG = tag;
            }
            Log.v(TAG, message);
        }

    }

    public static void d(String message) {
        if (mIsDebug) {
            Log.d(defaultTAG, message);
        }

    }

    public static void d(String tag, String message) {
        if (mIsDebug) {
            if (tag != null) {
                TAG = tag;
            }
            Log.d(TAG, message);
        }
    }

    public static void i(String message) {
        if (mIsDebug) {
            Log.i(defaultTAG, message);
        }

    }

    public static void i(String tag, String message) {
        if (mIsDebug) {
            if (tag != null) {
                TAG = tag;
            }
            Log.i(TAG, message);

        }

    }

    public static void w(String message) {
        if (mIsDebug) {
            Log.w(defaultTAG, message);
        }

    }

    public static void w(String tag, String message) {
        if (mIsDebug) {
            if (tag != null) {
                TAG = tag;
            }
            Log.w(TAG, message);
        }

    }

    public static void e(String message) {
        if (mIsDebug) {
            Log.e(defaultTAG, message);
        }

    }

    public static void e(String tag, String message) {
        if (mIsDebug) {
            if (tag != null) {
                TAG = tag;
            }
            Log.e(defaultTAG, message);
        }

    }

}
