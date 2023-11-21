plugins {
    kotlin("kapt")
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.google.dagger:dagger:2.44")
    kapt("com.google.dagger:dagger-compiler:2.44")
}