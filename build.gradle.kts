import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependencyManagement)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.jib)
}

kotlin {
    jvmToolchain(17)
}

group = "oscar.c.pozas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // Shared kernel
    implementation(project(":kernel"))

    // Libs
    implementation(project(":lib:spring-base"))

    // Contexts
    implementation(project(":context:pokemon"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")

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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
