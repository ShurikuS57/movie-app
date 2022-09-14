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
    implementation(project(":design-system"))
    implementation(project(":core-network"))
    implementation(project(":core-preferences"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.lifecycleRuntimeCompose)
    implementation(Dependencies.lifecycleCompose)
    implementation(Dependencies.lifecycleViewModelSavedState)
    implementation(Dependencies.splashScreen)

    // Compose
    implementation(Dependencies.composeLibraries)
    debugImplementation(Dependencies.uiTooling)
    implementation(Dependencies.pagingCompose)
    implementation(Dependencies.swipeRefreshCompose)

    // Coroutines
    implementation(Dependencies.coroutinesLibraries)

    // DI
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)
}