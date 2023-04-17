import org.gradle.api.JavaVersion

object AppSettings {
    const val ID = "dev.mrz.bitriseClient"
    const val VERSION_NAME = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    const val VERSION_CODE = 1
    const val COMPILE_SDK = 33
    const val MIN_SDK = 33
    val JAVA_VERSION = JavaVersion.VERSION_17 // TODO: Find another way. Now need to set
    // in build gradle of project and build src!
}
