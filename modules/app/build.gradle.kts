plugins {
    id("dev.mrz.android.application")
    id("dev.mrz.android.application.compose")
    alias(libs.plugins.ksp)
}

android {
    namespace = AppSettings.ID
    compileSdk = AppSettings.COMPILE_SDK

    defaultConfig {
        applicationId = AppSettings.ID
        versionCode = AppSettings.VERSION_CODE
        versionName = AppSettings.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        @Suppress("UNUSED_VARIABLE") val debug by getting {
            versionNameSuffix = ".debug"
        }
        @Suppress("UNUSED_VARIABLE") val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/LICENSE-notice.md")
            excludes.add("META-INF/LICENSE.md")
        }
    }
}

dependencies {
    // AndroidX
    implementation(libs.bundles.androidx)
    implementation(libs.android.core.splash)

    // Accompanist
    implementation(libs.bundles.accompanist)

    // Koin
    implementation(libs.bundles.koin)

    // Elmslie
    implementation(libs.bundles.elmslie)

    // TEST
    testImplementation(libs.bundles.test)
    debugImplementation(libs.compose.debug.test.manifest)

    // Navigator
    implementation(libs.bundles.navigator)
}