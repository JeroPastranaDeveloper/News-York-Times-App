enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "news-api"
include(":app")

include(":core")
include(":core:designsystem")
include(":core:data")
include(":core:navigation")
include(":core:model")

include(":feature")
include(":feature:home")
include(":feature:details")
include(":core:network")
include(":core:database")
include(":core:viewmodel")
include(":utils")
include(":core:domain")
include(":core:test")
