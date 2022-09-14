import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(apikeyPropertiesFile.inputStream())

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        applicationId = "ru.shurikus.movieapp"
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk
        versionCode = DefaultConfig.versionCode
        versionName = DefaultConfig.versionName

        // Properties
        buildConfigField("String", "API_KEY", apikeyProperties.getProperty("api.key"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = DefaultConfig.javaVersion
        targetCompatibility = DefaultConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = DefaultConfig.kotlinJvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.composeVersion
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-network"))
    implementation(project(":core-preferences"))
    implementation(project(":design-system"))
    implementation(project(":app-feature"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.lifecycleRuntimeCompose)
    implementation(Dependencies.lifecycleCompose)
    implementation(Dependencies.splashScreen)

    // Coroutines
    implementation(Dependencies.coroutinesLibraries)

    // DI
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)

    // Compose
    implementation(Dependencies.composeLibraries)
    debugImplementation(Dependencies.uiTooling)

    // Pluto
    debugImplementation(Dependencies.plutoDebug)
    debugImplementation(Dependencies.plutoNetworkDebug)
    debugImplementation(Dependencies.plutoLoggerDebug)
    debugImplementation(Dependencies.plutoExceptionDebug)
    releaseImplementation(Dependencies.plutoRelease)
    releaseImplementation(Dependencies.plutoNetworkRelease)
    releaseImplementation(Dependencies.plutoExceptionRelease)
    releaseImplementation(Dependencies.plutoLoggerRelease)

    // Test
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
}