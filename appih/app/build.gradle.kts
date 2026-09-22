plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android { namespace = "com.example.appinstallhistory"; compileSdk = 35
    defaultConfig { applicationId = "com.example.appinstallhistory"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "1.0" }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.datastore:datastore-preferences:1.1.2")
    implementation("androidx.work:work-runtime-ktx:2.10.0")
    implementation("com.google.code.gson:gson:2.12.1")
}
