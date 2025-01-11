// Tasks:
// - Internal Storage
// - External Storage
// - Dynamic Storage
// - Check API Level

package com.abyssmoth.mobilememorymonitor;

import android.os.StatFs;
import android.os.Environment;
import android.content.Context;
import android.app.ActivityManager;

public class MemoryMonitor
{
    public static final int ERROR_LOW_API = -1;
    public static final int ERROR_UNSUPPORTED_FEATURE = -2;

    public static final int EXTERNAL_STORAGE_NOT_AVAILABLE = 0;
    public static final int FEATURE_SUPPORTED = 0;

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

    public static long GetAvailableStorage()
    {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());

        return statFs.getAvailableBytes();
    }

    public static long GetTotalStorage()
    {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());

        return statFs.getTotalBytes();
    }

    public static boolean IsLowRAM(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.lowMemory;
    }

    public static float GetAvailableRAMPercentage(Context context)
    {
        long availableRAM = GetAvailableRAM(context);
        long totalRAM = GetTotalRAM(context);

        if (totalRAM == 0)
            return 0;

        return (availableRAM / (float) totalRAM) * 100;
    }

    public static float GetAvailableStoragePercentage()
    {
        long availableStorage = GetAvailableStorage();
        long totalStorage = GetTotalStorage();

        if (totalStorage == 0)
            return 0;

        return (availableStorage / (float) totalStorage) * 100;
    }

    public static String SuggestMemoryCleanup(Context context)
    {
        if (IsLowRAM(context))
            return "Low RAM detected! Consider closing background apps.";

        return "RAM usage is within acceptable limits.";
    }

    public static long GetAvailableExternalStorage()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

            return statFs.getAvailableBytes();
        }

        return EXTERNAL_STORAGE_NOT_AVAILABLE;
    }

    public static long GetTotalExternalStorage()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

            return statFs.getTotalBytes();
        }

        return EXTERNAL_STORAGE_NOT_AVAILABLE;
    }

    public static float GetAvailableExternalStoragePercentage()
    {
        long availableExternalStorage = GetAvailableExternalStorage();
        long totalExternalStorage = GetTotalExternalStorage();

        if (totalExternalStorage == 0)
            return 0;

        return (availableExternalStorage / (float) totalExternalStorage) * 100;
    }

    public static String GetExternalStorageType()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return "Removable";

        return "Not Available";
    }

    public static int HandleFeatureSupport(Context context, int requiredApiLevel)
    {
        if (ReadSDKVersion() < requiredApiLevel)
            return ERROR_LOW_API;

        return FEATURE_SUPPORTED;
    }

    public static int ReadSDKVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }
}