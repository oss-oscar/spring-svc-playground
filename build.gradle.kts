import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    id("com.google.cloud.tools.jib") version "3.3.1"
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20"
}

group = "oscar.c.pozas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Spring boot dependencies
    implementation(libs.springboot.web)
    implementation(libs.springboot.actuator)
    implementation(libs.springboot.cache)
    implementation(libs.springboot.data.redis)

    // Spring cloud dependencies
    implementation(libs.springcloud.openFeign)

    // Spring doc Open API Spec
    implementation(libs.springdoc.openapi)

    // Jackson parser
    implementation(libs.jackson.kotlin)

    // Postgres SQL Driver
    implementation(libs.postgresql)

    // Exposed Kotlin SQL DSL
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)

    // Shedlock scheduler
    implementation(libs.shedlock.spring)
    implementation(libs.shedlock.redis)

    // Test dependencies
    testImplementation(libs.springboot.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
