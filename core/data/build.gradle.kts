plugins {
    id("jero.newsapi.android.library")
    id("jero.newsapi.android.koin")
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.example.core.data"
    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    // core modules
    api(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.domain)

    // kotlinx
    api(libs.kotlinx.immutable.collection)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)

    // network
    implementation(libs.sandwich)
    implementation(projects.core.database)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
}