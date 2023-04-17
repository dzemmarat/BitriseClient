package bitriseClient

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureFeature(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        dependencies {
            // AndroidX
            add("implementation", libs.findBundle("androidx").get())

            // Accompanist
            add("implementation", libs.findBundle("accompanist").get())

            // Koin
            add("implementation", libs.findBundle("koin").get())

            // Elmslie
            add("implementation", libs.findBundle("elmslie").get())

            // Navigator
            add("implementation", libs.findBundle("navigator").get())
        }
    }
}
