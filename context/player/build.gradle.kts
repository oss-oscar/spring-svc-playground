plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependencyManagement)
    kotlin("plugin.serialization") version "2.1.20"
}

group = "oscar.c.pozas"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kernel"))

    implementation(project(":lib:spring-base"))

    // Spring cloud dependencies
    implementation(libs.springcloud.openFeign)

    // Jackson parser
    implementation(libs.jackson.kotlin)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

    // Postgres SQL Driver
    implementation(libs.postgresql)

    // Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Exposed Kotlin SQL DSL - to be removed after migration
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)

    // Shedlock scheduler
    implementation(libs.shedlock.spring)
    implementation(libs.shedlock.redis)

    // Test dependencies
    testImplementation(libs.springboot.test)
    testImplementation(libs.mockk)

    testImplementation(libs.restAssured.core)
    testImplementation(libs.restAssured.kotlin)

    testImplementation(libs.wiremock)

    testImplementation(libs.embeddedDatabase)

    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.+")

    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.2.0")
    implementation("org.mongodb:bson-kotlinx:5.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

tasks.test {
    useJUnitPlatform()
}
