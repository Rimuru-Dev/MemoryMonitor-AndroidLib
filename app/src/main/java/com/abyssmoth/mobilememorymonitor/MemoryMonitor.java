package com.abyssmoth.mobilememorymonitor;

import android.content.Context;
import android.app.ActivityManager;

public class MemoryMonitor
{
    public static long GetAvailableRAM(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }
}