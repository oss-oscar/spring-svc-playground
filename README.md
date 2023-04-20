Playground where to test different configurations for a service made with Spring boot.

## Running the project

### From CLI:

The repository provides a compiled version of [Gradle build tool](https://gradle.org) called `gradlew` (for unix) and `gradle.bat` (for windows users).

Before starting the project it's necessary to run the [`docker-compose.yml`](https://github.com/oscarcpozas/springboot-svc-playground/blob/master/docker-compose.yml) that defines the external dependencies:

```shell
docker-compose up
```

Then, from the root directory:

```shell
./gradlew bootRun
```

> **Note:** It may be necessary to give exec permissions to the gradlew script: `chmod +x gradlew`

### From Docker:

A pre-compiled version is provided in DockerHub registry:

```shell
docker run -d -p 8080:8080 --name code-challenge oscarcpozas/svc-kotlin-playground:latest
```

> **Note:** All the image versions are build by the CI pipeline

## Core decisions

This solution is made in Kotlin with Spring Boot 3. It's based in a CLEAN Architecture:

### 1. The architecture

TODO()

### 2. Dependencies management

All project dependencies are collected in a [TOML](https://toml.io/en) file trying to be a reference point, this is especially useful when it is a multi-module project.

### 2. Testing strategy

TODO()

## CI / CD

This project has a series of [Github Actions workflows/pipelines](https://github.com/oscarcpozas/springboot-svc-playground/tree/master/.github/workflows) to perform a series of checks on each development.

In addition, for each new commit in the master branch, an image of the service is generated and published in
the [DockerHub registry](https://hub.docker.com/repository/docker/oscarcpozas/svc-kotlin-playground/general).

After evaluating the different options that [Spring mentions in the official documentation](https://spring.io/guides/topicals/spring-boot-docker)
to generate the Docker image of the service, I've chosen to use the [Google Jib plugin](https://github.com/GoogleContainerTools/jib) that brings a number of optimizations.

## Miscellanea

- [OpenFeign](https://github.com/OpenFeign/feign) is used as a HTTP Client, it's inspired on Retrofit
- API documentation is available under Swagger UI (OpenAPI) checking `http://localhost:8080/swagger-ui`
- Version control strategy uses [Trunk based development](https://trunkbaseddevelopment.com) in combination with [conventional commits messages](https://www.conventionalcommits.org)
