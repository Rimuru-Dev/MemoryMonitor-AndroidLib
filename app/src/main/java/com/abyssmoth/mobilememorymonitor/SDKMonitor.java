package com.abyssmoth.mobilememorymonitor;

public class SDKMonitor
{
    public static int HandleFeatureSupport(int requiredApiLevel)
    {
        if (ReadSDKVersion() < requiredApiLevel)
            return Constants.ERROR_LOW_API;

        return Constants.FEATURE_SUPPORTED;
    }

    public static int ReadSDKVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }
}
