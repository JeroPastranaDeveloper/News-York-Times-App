import java.util.Properties

plugins {
    id("jero.newsapi.android.library")
    id("jero.newsapi.android.koin")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { localProperties.load(it) }
        }

        val apiKey = localProperties["API_KEY"] as? String ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.android)

    // Network
    implementation(libs.sandwich)
    implementation(platform(libs.retrofit.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.retrofitBundle)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)
    implementation(projects.utils)
}
