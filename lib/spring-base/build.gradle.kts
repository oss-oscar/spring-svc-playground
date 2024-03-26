plugins {
    kotlin("jvm")
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring boot dependencies
    implementation(libs.springboot.web)
    implementation(libs.springboot.actuator)
    implementation(libs.springboot.cache)
    implementation(libs.springboot.data.redis)
}

kotlin {
    jvmToolchain(17)
}