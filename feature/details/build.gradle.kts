plugins {
    id("jero.newsapi.android.feature")
    id("jero.newsapi.android.koin")
}

android {
    namespace = "com.example.feature.details"
}

dependencies {
    implementation(projects.core.viewmodel)
    implementation(libs.zxing.core)
    implementation(projects.utils)
    implementation(projects.core.database)
    implementation(projects.core.domain)
}