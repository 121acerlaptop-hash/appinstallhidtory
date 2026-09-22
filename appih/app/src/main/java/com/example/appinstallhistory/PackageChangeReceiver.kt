package com.example.appinstallhistory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PackageChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_PACKAGE_ADDED) return
        val pkg = intent.data?.schemeSpecificPart ?: return
        val record = AppScanner.scan(context).firstOrNull { it.packageName == pkg } ?: return
        val store = HistoryStore(context)
        val map = store.get()
        if (!map.containsKey(pkg)) { map[pkg] = record; store.save(map) }
    }
}
