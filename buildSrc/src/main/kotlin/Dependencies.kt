object Dependencies {
    // Gradle
    const val kotlin_version = "1.7.10"
    const val gradle_build = "7.2.2"
    const val detekt = "1.21.0"

    // UI
    private const val coreKtxVersion = "1.8.0"
    private const val appcompatVersion = "1.5.0"
    private const val materialVersion = "1.6.0"
    private const val splashScreenVersion = "1.0.0"

    const val coreKtx = "androidx.core:core-ktx:${coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${appcompatVersion}"
    const val material = "com.google.android.material:material:${materialVersion}"
    const val splashScreen = "androidx.core:core-splashscreen:${splashScreenVersion}"

    // Lifecycle
    private const val lifecycleComposeVersion = "2.6.0-alpha01"

    const val lifecycleCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${lifecycleComposeVersion}"
    const val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${lifecycleComposeVersion}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${lifecycleComposeVersion}"

    // Coroutines
    private const val coroutines_version = "1.6.4"

    private const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines_version}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines_version}"
    val coroutinesLibraries = listOf(coroutinesCore, coroutinesAndroid)

    // Compose
    const val composeVersion = "1.3.0-beta01"
    private const val composeActivityVersion = "1.5.1"
    private const val composeNavigationVersion = "2.5.1"
    private const val systemUiControllerVersion = "0.26.2-beta"
    private const val coilComposeVersion = "2.2.0"

    private const val composeUi = "androidx.compose.ui:ui:${composeVersion}"
    private const val composeMaterial = "androidx.compose.material:material:${composeVersion}"
    private const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${composeVersion}"
    private const val composeActivity =
        "androidx.activity:activity-compose:${composeActivityVersion}"
    private const val composeNavigation =
        "androidx.navigation:navigation-compose:${composeNavigationVersion}"
    private const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${systemUiControllerVersion}"
    private const val materialIcons =
        "androidx.compose.material:material-icons-extended:${composeVersion}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
    private const val coilCompose = "io.coil-kt:coil-compose:${coilComposeVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha16"
    const val swipeRefreshCompose = "com.google.accompanist:accompanist-swiperefresh:0.26.2-beta"

    val composeLibraries = listOf(
        composeUi, composeMaterial, composeUiPreview, composeActivity,
        composeNavigation, systemUiController, coilCompose, materialIcons,
    )

    // DI Koin
    private const val koin_version = "3.2.0"
    const val koin = "io.insert-koin:koin-android:$koin_version"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$koin_version"

    // Network
    private const val retrofit2Version = "2.9.0"
    private const val loggingInterceptorVersion = "4.10.0"

    private const val retrofit2 = "com.squareup.retrofit2:retrofit:${retrofit2Version}"
    private const val gson = "com.squareup.retrofit2:converter-gson:${retrofit2Version}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${loggingInterceptorVersion}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:4.10.0"
    val networkLibraries = listOf(retrofit2, gson, loggingInterceptor, okhttp)

    // Security
    private const val securityCryptoVersion = "1.1.0-alpha03"
    const val securityCrypto = "androidx.security:security-crypto:${securityCryptoVersion}"

    // Pluto
    private const val plutoVersion = "2.0.5"
    const val plutoDebug = "com.plutolib:pluto:${plutoVersion}"
    const val plutoRelease = "com.plutolib:pluto-no-op:${plutoVersion}"
    const val plutoNetworkDebug = "com.plutolib.plugins:network:${plutoVersion}"
    const val plutoNetworkRelease = "com.plutolib.plugins:network-no-op:${plutoVersion}"
    const val plutoExceptionDebug = "com.plutolib.plugins:exceptions:${plutoVersion}"
    const val plutoExceptionRelease = "com.plutolib.plugins:exceptions-no-op:${plutoVersion}"
    const val plutoLoggerDebug = "com.plutolib.plugins:logger:${plutoVersion}"
    const val plutoLoggerRelease = "com.plutolib.plugins:logger-no-op:${plutoVersion}"

    // Test
    private const val junitVersion = "4.13.2"
    private const val extJUnitVersion = "1.1.3"
    private const val espressoCoreVersion = "3.4.0"

    private const val junit = "junit:junit:${junitVersion}"
    private const val extJUnit = "androidx.test.ext:junit:${extJUnitVersion}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${espressoCoreVersion}"
    private const val jUnitCompose = "androidx.compose.ui:ui-test-junit4:${composeVersion}"

    val androidTestLibraries = listOf(extJUnit, espressoCore, jUnitCompose)
    val testLibraries = listOf(junit)
}
