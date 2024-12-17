plugins {
    id("jero.newsapi.android.library")
}

android {
    namespace = "com.example.core.viewmodel"
}

dependencies {
    api(libs.androidx.lifecycle.viewModelCompose)
}