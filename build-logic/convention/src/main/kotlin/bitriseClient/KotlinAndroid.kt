package bitriseClient

import AppSettings
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.tools.r8.internal.kt
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    commonExtension.apply {
        compileSdk = AppSettings.COMPILE_SDK

        defaultConfig {
            minSdk = AppSettings.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = AppSettings.JAVA_VERSION
            targetCompatibility = AppSettings.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
            )

            jvmTarget = AppSettings.JAVA_VERSION.toString()
        }
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugar.jdk.libs").get())
        add("implementation", libs.findLibrary("coroutines.core").get())
        add("implementation", libs.findLibrary("coroutines.android").get())
        add("implementation", libs.findLibrary("immutable_collections").get())

        add("implementation", platform(libs.findLibrary("arrow.platform").get()))
        add("implementation", "io.arrow-kt:arrow-core")
        add("implementation", "io.arrow-kt:arrow-optics")

        add("testImplementation", libs.findLibrary("coroutines.test").get())
        add("testImplementation", libs.findLibrary("mockk").get())
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
