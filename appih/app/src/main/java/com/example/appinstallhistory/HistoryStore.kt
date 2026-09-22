package com.example.appinstallhistory

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryStore(context: Context) {
    private val prefs = context.getSharedPreferences("history", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun get(): MutableMap<String, AppRecord> = try {
        val type = object : TypeToken<MutableMap<String, AppRecord>>() {}.type
        gson.fromJson(prefs.getString("apps", "{}"), type) ?: mutableMapOf()
    } catch (_: Exception) { mutableMapOf() }
    fun save(map: Map<String, AppRecord>) { prefs.edit().putString("apps", gson.toJson(map)).apply() }
}
