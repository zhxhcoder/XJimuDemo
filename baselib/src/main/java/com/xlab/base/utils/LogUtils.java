package com.xlab.base.utils;

import android.util.Log;

/**
 * Created by zhxh on 2018/10/26
 */
public class LogUtils {

    /**
     * 日志输出级别 0为不输出
     */
    private static int logMode = 4;

    /**
     * 输出Error信息
     *
     * @param tag 异常信息标识
     * @param msg 异常信息
     */
    public static void e(String tag, String msg) {
        if (tag == null || msg == null) {
            return;
        }
        if (Log.ERROR <= logMode)
            Log.e(tag, msg);
    }

    /**
     * 输出警告信息
     *
     * @param tag 错误信息标识
     * @param msg 错误信息
     */
    public static void w(String tag, String msg) {
        if (tag == null || msg == null) {
            return;
        }
        if (Log.WARN <= logMode)
            Log.w(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (tag == null || msg == null) {
            return;
        }
        if (Log.INFO <= logMode)
            Log.i(tag, msg);
    }

    public static void i(String msg) {
        if (msg == null) {
            return;
        }
        if (Log.INFO <= logMode)
            Log.i("debuglog", msg);
    }


    /**
     * 输出debug信息
     *
     * @param tag 调试信息标识
     * @param msg 调试信息
     */
    public static void d(String tag, String msg) {
        if (tag == null || msg == null) {
            return;
        }
        if (Log.DEBUG <= logMode)
            Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (msg == null) {
            return;
        }
        if (Log.DEBUG <= logMode)
            Log.d("debuglog", msg);
    }

    public static void e(Object object, String msg) {
        if (msg == null) {
            return;
        }
        if (Log.DEBUG <= logMode)
            Log.e(object.getClass().getSimpleName(), msg);
    }

    public static void e(String msg) {
        if (msg == null) {
            return;
        }
        if (Log.DEBUG <= logMode)
            Log.e("debuglog", msg);
    }
}
