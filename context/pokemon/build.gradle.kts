plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependencyManagement)
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
    testImplementation(libs.mockk)

    testImplementation(libs.restAssured.core)
    testImplementation(libs.restAssured.kotlin)

    testImplementation(libs.wiremock)

    testImplementation(libs.embeddedDatabase)
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}

