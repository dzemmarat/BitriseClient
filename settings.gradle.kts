rootProject.name = "BitriseClient"

pluginManagement {
    repositories {
        google()
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

File(rootProject.projectDir, "modules").walk()
        .filter { it.isBuildGradleScript() }
        .filter { it != rootProject.buildFile }
        .mapNotNull { it.parentFile }
        .forEach { moduleDir ->
            val moduleName = ":${moduleDir.name}"
            include(moduleName)
            project(moduleName).projectDir = moduleDir
        }

fun File.isBuildGradleScript(): Boolean =
        isFile && name.matches("build\\.gradle(\\.kts)?".toRegex())
