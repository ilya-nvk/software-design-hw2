plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.21"
}

group = "com.ilyanvk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // serialization
    implementation("com.google.code.gson:gson:2.8.9")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    // dependency injection
    implementation("com.google.dagger:dagger:2.50")
    kapt("com.google.dagger:dagger-compiler:2.50")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}