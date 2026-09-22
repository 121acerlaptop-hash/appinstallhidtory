package com.example.appinstallhistory

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

object AppScanner {
    fun scan(context: Context): List<AppRecord> {
        val pm = context.packageManager
        return pm.getInstalledPackages(PackageManager.PackageInfoFlags.of(0)).mapNotNull { p ->
            val ai = p.applicationInfo ?: return@mapNotNull null
            val label = pm.getApplicationLabel(ai).toString()
            val installer = try {
                if (Build.VERSION.SDK_INT >= 30) pm.getInstallSourceInfo(p.packageName).installingPackageName
                else @Suppress("DEPRECATION") pm.getInstallerPackageName(p.packageName)
            } catch (_: Exception) { null }
            AppRecord(p.packageName, label, p.firstInstallTime, p.lastUpdateTime, installer)
        }.sortedByDescending { it.installTime }
    }
}
