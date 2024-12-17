plugins {
    id("jero.newsapi.android.feature")
    id("jero.newsapi.android.koin")
}

android {
    namespace = "com.example.feature.home"
}

dependencies {
    implementation(libs.accompanist.systemuicontroller)

    implementation(projects.utils)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.viewmodel)

    implementation(projects.feature.details)
}
