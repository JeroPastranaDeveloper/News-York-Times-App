plugins {
    id("jero.newsapi.android.library")
}

android {
    namespace = "com.example.core.domain"
}

dependencies {
    // core modules
    api(projects.core.model)
    implementation(projects.core.network)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)

    // network
    implementation(libs.sandwich)
}