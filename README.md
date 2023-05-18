This project aims to be a guide or template for web services using the Sprinboot framework, a proof of concept where try to put attention to every decision made. The project is open to receive 
[contributions via PR](https://github.com/oscarcpozas/springboot-svc-playground/pulls) or [issues](https://github.com/oscarcpozas/springboot-svc-playground/issues) to discuss any aspect or decision.

The project connects to [PokeAPI](https://pokeapi.co) to retrieve data and manipulate it.


## Stack

|                   | Name                                | Version |
|-------------------|-------------------------------------|---------|
| **Language**      | Kotlin                              | 1.8.21  |
| **Build**         | Gradle                              | 8.1.1   |
| **Framework**     | Spring boot                         | 3.0.5   |
| **Code format**   | Ktlint Gradle                       | 11.3.1  |
| **Data base**     | PostgreSQL                          | 42.2.27 |
| **Cache service** | Redis                               | 5.2.0   |

## Running the project

### From CLI:

The repository provides a compiled version of [Gradle build tool](https://gradle.org) called `gradlew` (for unix) and `gradle.bat` (for windows users).

Before starting the project it's necessary to run the [`docker-compose.yml`](https://github.com/oscarcpozas/springboot-svc-playground/blob/main/docker-compose.yml) that defines the external dependencies:

```shell
docker-compose up
```

Then, from the root directory:

```shell
./gradlew bootRun
```

> **Note:** It may be necessary to give exec permissions to the gradlew script: `chmod +x gradlew`

### From CLI with Make:

In the root of the project there is a [`Makefile`](https://github.com/oscarcpozas/blob/main/Makefile) that simplifies the launching of some of the most common tasks:

- `make run`: Build and run the project, remember that it's necessary to have the `docker-compose up`
- `make test`: Pass all tests. Docker daemon it's necessary for acceptance tests
- `make styleCheck`: Checking that the styles defined for the code are being applied properly
- `make styleApply`: Checking the styles and fix errors
- `make imageBuild`: Build and push the container from the project (command points to my personal user and would require my credentials)

### From Docker:

A pre-compiled version is provided in [DockerHub registry](https://hub.docker.com/r/oscarcpozas/svc-kotlin-playground):

```shell
docker run -d -p 8080:8080 --name code-challenge oscarcpozas/svc-kotlin-playground:latest
```

> **Note:** All the image versions are build by the CI pipeline

## API documentation

API documentation is available under Swagger UI checking [`http://localhost:8080/swagger-ui`](http://localhost:8080/swagger-ui)

Also, open API spec can be accessed via [`http://localhost:8080/api-docs`](http://localhost:8080/api-docs)

## Core decisions

### 1. The architecture

Spring Boot is designed to work with a file organization or architecture based on [MVC](https://www.javatpoint.com/mvc-architecture-in-java),
but for this challenge I've preferred to go further by applying my own interpretation of the [CLEAN architecture proposed by Uncle Bob](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html),
which tries to take the best ideas from MVC, Hexagonal and Onion architectures.

The most common image to represent the layers that define this architecture is usually:

<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" width="600" />

But here is my own sketch of this project organization:

<img src="https://i.postimg.cc/6qPKywkZ/Untitled-2023-04-20-1357.png" />

The main benefits of using an architecture like this is the semantics it brings to the team, allowing to be clear where
everything is going to be found. In addition to simplifying the task of testing by allowing to isolate each layer.

In addition to the file organization proposed above, [SOLID principles](https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design) have been taken into account and other design
patterns have been applied based on the need to.

### 2. Dependencies management

[Gradle 7 introduces](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) the option to manage project dependencies using a [TOML](https://toml.io/en) file as a reference
point for all dependencies. This is especially useful when it is a multi-module project. And this is how I organize it in this project:

[Dependencies file reference](https://github.com/oscarcpozas/springboot-svc-playground/blob/main/gradle/libs.versions.toml)

### 2. Testing strategy

Despite what the [testing pyramid says](https://martinfowler.com/bliki/TestPyramid.html), I have preferred to base my strategy on 3 sets:

* On the one hand on the **presentation/app layer**, I do [acceptance testing to the REST controllers](https://github.com/oscarcpozas/fever-code-challenge/blob/main/src/test/kotlin/oscar/c/pozas/svc/app/controller/FeverPublicControllerAcceptanceTest.kt).
  This type of testing allows to check if the entire system is complying with the established requirements.

* In the **domain layer** I'm performing [unit tests on the use cases](https://github.com/oscarcpozas/fever-code-challenge/blob/main/src/test/kotlin/oscar/c/pozas/svc/domain/usecase/SearchEventsUseCaseTest.kt),
  being fast and abstracted from the dependencies with the Spring boot framework or third parties.

* Finally, in the **data layer**, I perform [integration tests of the different data sources](https://github.com/oscarcpozas/fever-code-challenge/blob/main/src/test/kotlin/oscar/c/pozas/svc/infrastructure/datasource/EventsApiDataSourceIntegrationTest.kt)
  to check their communication with these sources.

## CI / CD

This project has a series of [Github Actions workflows/pipelines](https://github.com/oscarcpozas/springboot-svc-playground/tree/main/.github/workflows) to perform a series of checks on each development.

In addition, for each new commit in the main branch, an image of the service is generated and published in
the [DockerHub registry](https://hub.docker.com/r/oscarcpozas/svc-kotlin-playground).

After evaluating the different options that [Spring mentions in the official documentation](https://spring.io/guides/topicals/spring-boot-docker)
to generate the Docker image of the service, I've chosen to use the [Google Jib plugin](https://github.com/GoogleContainerTools/jib) that brings a number of optimizations.

## Miscellanea

- [OpenFeign](https://github.com/OpenFeign/feign) is used as a HTTP Client, it's inspired on Retrofit
- Version control strategy uses [Trunk based development](https://trunkbaseddevelopment.com) in combination with [conventional commits messages](https://www.conventionalcommits.org)
- I've added an [interceptor that captures calls](https://github.com/oscarcpozas/blob/master/src/main/kotlin/oscar/c/pozas/svc/app/controller/interceptor/RequestTimeInterceptor.kt)
  to the service and the time it takes to answer
