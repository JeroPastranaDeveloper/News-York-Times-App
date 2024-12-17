plugins {
    alias(libs.plugins.kotlinx.serialization)
    id("jero.newsapi.android.library")
    id("jero.newsapi.android.library.compose")
    id("jero.newsapi.android.koin")
}

android {
    namespace = "com.example.core.navigation"
}

dependencies {

    implementation(projects.core.model)

    implementation(libs.androidx.core)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}