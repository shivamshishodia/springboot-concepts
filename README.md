# Spring Boot Concepts

This repository is a consolidated Spring Boot examples monorepo. It groups
multiple standalone Spring Boot projects that cover core concepts, REST APIs,
GitHub OAuth, Quartz scheduling, RabbitMQ, Kafka, and Spring Cloud
microservices.

Each application is still an independent Maven project. There is currently no
root aggregator `pom.xml`, so build and test commands should be run from the
directory that contains the relevant `pom.xml`.

## Repository Layout

| Directory | Contents |
| --- | --- |
| [`springboot-general`](springboot-general) | General Spring Boot concepts, dependency injection examples, and circular dependency examples. This was the original root application. |
| [`springboot-restful-web-service`](springboot-restful-web-service) | RESTful web service examples, validation, exception handling, HATEOAS, i18n, content negotiation, Swagger/OpenAPI, Actuator, filtering, versioning, security, and H2. |
| [`springboot-security-oauth-github`](springboot-security-oauth-github) | Spring Security OAuth login with GitHub, token handling, request filtering, and Thunder Client request examples. |
| [`springboot-quartz`](springboot-quartz) | Quartz-based email scheduler using Spring Boot, H2, JDBC-backed Quartz tables, and mail configuration. |
| [`springboot-rabbitmq`](springboot-rabbitmq) | RabbitMQ producer and consumer applications, exchanges, queues, dead-letter handling, retries, and publish confirms. |
| [`springboot-kafka`](springboot-kafka) | Kafka producer and consumer applications for library events, plus local Kafka broker configuration files. |
| [`springboot-microservices`](springboot-microservices) | Spring Cloud microservices, including config server, Eureka naming server, API gateway, limits service, currency exchange, currency conversion, local config repo, and Docker Compose setup. |

## Migration Notes

The projects were lifted into this mother repository as source code only.
Repository metadata and generated artifacts from the original repositories were
not moved.

Intentionally excluded:

- Child `.git` directories and independent Git histories.
- `target/` build outputs.
- IDE metadata and other ignored files.
- Child `.gitignore` files where the root `.gitignore` now covers the shared
  ignore rules.

Project-specific `README.md` files were retained inside the migrated
directories for detailed setup, endpoints, ports, and local run instructions.

## Prerequisites

- Java 11 or newer.
- Maven 3.x.
- Docker, where needed for supporting services such as Zipkin or microservice
  Docker Compose workflows.
- RabbitMQ, when running the RabbitMQ producer and consumer projects locally.
- Kafka and Zookeeper, when running the Kafka producer and consumer projects
  locally.
- Reachable Maven repositories or mirrors. If your local Maven settings route
  dependencies through a corporate mirror, make sure that mirror is available.

## Build And Test

Run Maven from the directory of the project you want to build:

```sh
cd springboot-general
mvn test
```

For nested projects, run the same command from the nested Maven module:

```sh
cd springboot-kafka/library-events-producer
mvn test
```

Useful command to list all Maven projects:

```sh
find . -path '*/target' -prune -o -name pom.xml -print
```

## Project Documentation

Start with the root layout above, then use the child README files for deeper
details:

- [`springboot-security-oauth-github/README.md`](springboot-security-oauth-github/README.md)
- [`springboot-quartz/README.md`](springboot-quartz/README.md)
- [`springboot-restful-web-service/README.md`](springboot-restful-web-service/README.md)
- [`springboot-rabbitmq/README.md`](springboot-rabbitmq/README.md)
- [`springboot-kafka/README.md`](springboot-kafka/README.md)
- [`springboot-microservices/README.md`](springboot-microservices/README.md)
