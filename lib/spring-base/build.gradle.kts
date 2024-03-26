plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependencyManagement)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kernel"))

    // Spring boot dependencies
    api(libs.springboot.web)
    api(libs.springboot.actuator)
    api(libs.springboot.cache)
    api(libs.springboot.data.redis)

    // Spring doc Open API Spec
    api(libs.springdoc.openapi)
}

kotlin {
    jvmToolchain(17)
}