plugins {
    id("jero.newsapi.android.library")
}

android {
    namespace = "com.example.core.test"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
}