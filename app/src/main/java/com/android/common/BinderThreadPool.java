package com.android.common;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by wenjing.liu on 2021/5/13 in J1.
 *
 * @author wenjing.liu
 */
public class BinderThreadPool {

    /**
     * 获取当前进程中的所有Binder Thread
     *
     * @param className
     */
    public static void getAllBinderThread(String className) {
        Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
        Set<Thread> set = stacks.keySet();
        for (Thread key : set) {
            int pid = getProcessId(key.getName());
            if (isBinderThread(pid)) {
                String group = key.getThreadGroup().getName();
                Log.v("group = " + group + " , " + className + ", " + key.getName());
                // getProcPidInfo(pid, "cwd");
                // getProcPidInfo(pid, "fd");
                // getProcPidInfo(pid, "maps");
                // getProcPidInfo(pid, "root");
                // getProcPidInfo(pid, "stat");
                // getProcPidInfo(pid, "self");
                //getProcPidInfo(pid, "status");

            }
        }
    }

    /**
     * @param threadName Binder:6402_3
     * @return
     */
    private static int getProcessId(String threadName) {
        if (!threadName.startsWith("Binder")) {
            return 0;
        }
        int maohao = threadName.indexOf(":");
        int divider = threadName.indexOf("_");
        if (maohao < 0 || divider < 0) {
            return 0;
        }
        String pid = threadName.substring(maohao + 1, divider);
        if (TextUtils.isDigitsOnly(pid)) {
            return Integer.parseInt(pid);
        }
        return 0;
    }


    private static boolean isBinderThread(int id) {
        return id > 0;
    }


    private static String getProcessName(int pid) {
        return getProcPidInfo(pid, "cmdline");
    }


    private static String getProcPidInfo(int pid, String folder) {
        File file = new File("/proc/" + pid + "/" + folder);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String name;
            StringBuffer buffer = new StringBuffer();
            while ((name = reader.readLine()) != null) {
                buffer.append(name + "\n");
            }
            Log.d("正在读取" + folder + " : " + buffer);
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
        }
        return "";
    }
}
