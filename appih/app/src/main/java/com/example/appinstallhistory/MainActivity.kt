package com.example.appinstallhistory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { App() } }
}

@Composable fun App() {
    val context = androidx.compose.ui.platform.LocalContext.current
    var records by remember { mutableStateOf(AppScanner.scan(context)) }
    var query by remember { mutableStateOf("") }
    val fmt = remember { SimpleDateFormat("dd MMM yyyy, hh:mm:ss a", Locale.getDefault()) }
    val filtered = records.filter { it.appName.contains(query, true) || it.packageName.contains(query, true) }
    MaterialTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("App Install History") }) }) { pad ->
            Column(Modifier.padding(pad).padding(12.dp)) {
                OutlinedTextField(query, { query = it }, Modifier.fillMaxWidth(), label = { Text("Search apps") }, singleLine = true)
                Spacer(Modifier.height(10.dp))
                Button(onClick = { records = AppScanner.scan(context) }) { Text("Refresh") }
                Spacer(Modifier.height(8.dp))
                Text("${filtered.size} apps", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(8.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filtered, key = { it.packageName }) { r ->
                        Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(14.dp)) {
                            Text(r.appName, style = MaterialTheme.typography.titleMedium)
                            Text(r.packageName, style = MaterialTheme.typography.bodySmall)
                            Text("Installed: ${fmt.format(Date(r.installTime))}")
                            Text("Last updated: ${fmt.format(Date(r.updateTime))}")
                            Text("Source: ${sourceName(r.installer)}")
                        } }
                    }
                }
            }
        }
    }
}

fun sourceName(p: String?): String = when (p) {
    "com.android.vending" -> "Google Play Store"
    "com.google.android.packageinstaller", "com.android.packageinstaller" -> "Package Installer / APK"
    null -> "Unknown"
    else -> p
}
