plugins {
    `kotlin-dsl`
}

group = "dev.mrz.bitriseclient.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.gradle.build)
    compileOnly(libs.kotlin.gradle.build)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "dev.mrz.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "dev.mrz.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "dev.mrz.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "dev.mrz.android.feature"
            implementationClass = "AndroidComposeFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "dev.mrz.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
