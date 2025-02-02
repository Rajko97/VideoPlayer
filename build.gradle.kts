// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("androidx.navigation.safeargs") version "2.6.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

kotlin {
    detekt {
        config.from(files("detekt-config.yml"))
    }
}