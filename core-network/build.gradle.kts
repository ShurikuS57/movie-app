plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = DefaultConfig.javaVersion
        targetCompatibility = DefaultConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = DefaultConfig.kotlinJvmTarget
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-preferences"))

    // Network
    implementation(Dependencies.networkLibraries)

    // DI
    implementation(Dependencies.koin)

    // Pluto
    debugImplementation(Dependencies.plutoNetworkDebug)
    releaseImplementation(Dependencies.plutoNetworkRelease)

    // Paging
    implementation(Dependencies.pagingCompose)
}