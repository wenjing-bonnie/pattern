package com.android.common;

/**
 * Created by wenjing.liu on 2021/4/8 in J1.
 *
 * @author wenjing.liu
 */
public class Log {

    public static void d(String info) {
        android.util.Log.d(generateTag(), info);
    }

    public static void v(String info) {
        android.util.Log.v(generateTag(), info);
    }

    public static void w(String info) {
        android.util.Log.w(generateTag(), info);
    }

    private static String generateTag() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        return tag;
    }
}
