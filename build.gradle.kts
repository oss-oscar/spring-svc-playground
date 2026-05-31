import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependencyManagement)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.jib)
}

group = "oscar.c.pozas"
version = "0.0.1-SNAPSHOT"

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
    implementation(project(":context:player"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Spring cloud dependencies
    implementation(libs.springcloud.openFeign)

    // Jackson parser
    implementation(libs.jackson.kotlin)
    testImplementation(libs.jackson.kotlin)

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
    testImplementation("com.h2database:h2:2.2.224")

    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.0.0")
    implementation("org.mongodb:bson-kotlinx:5.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("io.github.serpro69:kotlin-faker:1.6.0")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.named<KotlinCompilationTask<*>>("compileKotlin").configure {
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
