plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "svc-kotlin-playground"
include("context:pokemon")
findProject(":context:pokemon")?.name = "pokemon"
include("spring-base")
include("lib:spring-base")
findProject(":lib:spring-base")?.name = "spring-base"
include("lib:logging")
findProject(":lib:logging")?.name = "logging"
include("kernel")
