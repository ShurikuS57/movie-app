import org.gradle.api.JavaVersion

object DefaultConfig {
    const val versionCode     = 1
    const val versionName     = "0.0.1"

    const val compileSdk      = 33
    const val minSdk          = 24
    const val targetSdk       = 33

    val javaVersion           = JavaVersion.VERSION_11
    const val kotlinJvmTarget = "11"
}