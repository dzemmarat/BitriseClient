plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
    base
}

val detektFormatting = libs.detekt.formatting
val detektCompose = libs.detekt.rules.compose

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }
    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        baseline = rootProject.file("config/detekt/baseline.xml")
    }
    dependencies {
        detektPlugins(detektFormatting)
        detektPlugins(detektCompose)
    }
}