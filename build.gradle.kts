buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Dependencies.gradle_build}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.kotlin_version}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(Dependencies.detekt)
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Dependencies.detekt}")
    }

    detekt {
        toolVersion = Dependencies.detekt
        parallel = true
        autoCorrect = true
        config = files("$rootDir/detekt.yml")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = DefaultConfig.kotlinJvmTarget
}

tasks.register("build") {
    dependsOn(gradle.includedBuild("detekt-gradle-plugin").task(":build"))
}