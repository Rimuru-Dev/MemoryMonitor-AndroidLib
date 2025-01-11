package com.abyssmoth.mobilememorymonitor;

import android.content.Context;
import android.app.ActivityManager;

public class RAMMonitor
{
    public static long GetAvailableRAM(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }

    public static long GetTotalRAM(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.totalMem;
    }

    public static float GetAvailableRAMPercentage(Context context)
    {
        long availableRAM = GetAvailableRAM(context);
        long totalRAM = GetTotalRAM(context);

        if (totalRAM == 0)
            return 0;

        return (availableRAM / (float) totalRAM) * 100;
    }

    public static boolean IsLowRAM(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.lowMemory;
    }

    public static String SuggestMemoryCleanup(Context context)
    {
        if (IsLowRAM(context))
            return "Low RAM detected! Consider closing background apps.";

        return "RAM usage is within acceptable limits.";
    }
}
