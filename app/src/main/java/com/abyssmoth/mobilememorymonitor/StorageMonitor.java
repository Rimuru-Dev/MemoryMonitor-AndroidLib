package com.abyssmoth.mobilememorymonitor;

import android.os.Environment;
import android.os.StatFs;

public class StorageMonitor
{
    //
    // NOTE: Internal Storage ========================================================== //
    //
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

    public static float GetAvailableStoragePercentage()
    {
        long availableStorage = GetAvailableStorage();
        long totalStorage = GetTotalStorage();

        if (totalStorage == 0)
            return 0;

        return (availableStorage / (float) totalStorage) * 100;
    }

    //
    // NOTE: External Storage ========================================================== //
    //
    public static long GetAvailableExternalStorage()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

            return statFs.getAvailableBytes();
        }

        return Constants.EXTERNAL_STORAGE_NOT_AVAILABLE;
    }

    public static long GetTotalExternalStorage()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

            return statFs.getTotalBytes();
        }

        return Constants.EXTERNAL_STORAGE_NOT_AVAILABLE;
    }

    public static String GetExternalStorageType()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return Constants.STORAGE_TYPE_REMOVABLE;

        return Constants.STORAGE_TYPE_NOT_AVAILABLE;
    }

    public static float GetAvailableExternalStoragePercentage()
    {
        long availableExternalStorage = GetAvailableExternalStorage();
        long totalExternalStorage = GetTotalExternalStorage();

        if (totalExternalStorage == 0)
            return 0;

        return (availableExternalStorage / (float) totalExternalStorage) * 100;
    }
}