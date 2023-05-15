import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 32
    const val targetSdk = 32
    const val buildTools = "30.0.3"

    val javaVersion = JavaVersion.VERSION_11
}
