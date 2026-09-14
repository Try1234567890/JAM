package com.github.trfiles.os;

import com.github.trfiles.os.android.AndroidOS;
import com.github.trfiles.os.linux.LinuxOS;
import com.github.trfiles.os.mac.MacOS;
import com.github.trfiles.os.windows.WindowsOS;

import java.util.Locale;

final class OperatingSystemFactory {

    private OperatingSystemFactory() {}

    public static OperatingSystem currentOS() {
        if (isAndroid()) return AndroidOS.get();

        String osName = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (osName.contains("win")) {
            return WindowsOS.get();
        } else if (osName.contains("mac") || osName.contains("darwin")) {
            return MacOS.get();
        }

        return LinuxOS.get();
    }

    private static boolean isAndroid() {
        String vendor = System.getProperty("java.vendor", "").toLowerCase(Locale.ENGLISH);
        String runtime = System.getProperty("java.runtime.name", "").toLowerCase(Locale.ENGLISH);
        
        return vendor.contains("android") || runtime.contains("android");
    }
}