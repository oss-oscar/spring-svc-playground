dependencyResolutionManagement {
    versionCatalogs {
        create("externalLibs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
