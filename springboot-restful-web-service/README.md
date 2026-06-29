# Basic Spring Boot RESTful Web Service

A basic Spring Boot RESTful web service (This is for basic concepts. Codebase is not production ready). Please read all the comments properly. May start building a boiler-plate on top of this.

## Project Details

- **Specialization** Common exception handler for all controllers `basic.exceptions.CustomResponseExceptionHandler`.

- **Request Validations** Handled inside beans `basic.models.User` with `@Valid` at the controller level. Refer `basic.exceptions.CustomResponseExceptionHandler.handleMethodArgumentNotValid`. Dependency required `spring-boot-starter-validation`.

- **HATEOAS** Allows you to add reference links with the response without hard-coding URIs. See `basic.resources.UserResourcesImpl.fetchOneUser` response having link to `basic.resources.UserResourcesImpl.fetchUsers` method. URIs are picked from the mappings automatically. Dependency required `spring-boot-starter-hateoas`.

- **Internationalization** Refer `basic.resources.InternationalizedResources.internationalized` and `basic.resources.InternationalizedResources.internationalizedContext` and all the `messages` property files. Make sure to include `Accept-Language` in request header.

- **Content Negotiation** While making the call ensure to add `Accept: application/xml` and it will return XML instead of JSON. Dependency required `com.fasterxml.jackson.dataformat`.

- **Swagger** Refer [springdoc-openapi](https://springdoc.org/) and include dependency `org.springdoc`. Swagger UI located at [swagger-ui](http://localhost:8080/swagger-ui.html). Swagger docs are located at [api-docs](http://localhost:8080/v3/api-docs). Provides server info, paths, schemas and components. For migration from Springfox Swagger 2 to Springdoc Open API look [stackoverflow](https://stackoverflow.com/questions/59291371/migrating-from-springfox-swagger-2-to-springdoc-open-api).

- **Actuator** Provides production read metrics, heap dumps, jvm, loggers, thread dumps, mapping, etc. Refer properties for wildcard web exposure. [actuator-docs](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html). Dependency added `spring-boot-starter-actuator`. [actuator-endpoint](http://localhost:8080/actuator)

- **HAL Explorer** Bundles features of Swagger UI and visualizes raw JSON such as `/actuator`. Dependency added `spring-data-rest-hal-explorer`. Just go to [localhost](localhost:8080).

- **Static filtering** To ignore particular data members use @JsonIgnore or @JsonIgnoreProperties. Refer `basic.models.ConfigsStaticFilter` and `basic.resources.FilteredResources`.

- **Dynamic Filtering** Filters are applied when the beans are fetched. Refer `basic.models.ConfigsDynamicFilter` and `basic.resources.FilteredResources`.

- **Versioning** Different companies use different approaches. All are discussed under the `versioning` package.

- **Basic Authentication** Dependency required `spring-boot-starter-security`. Copy of the password displayed in the terminal when the service starts. Currently, the user and password is listed under property file. Make a request using this password and user as `user` under authorization > basic auth.

- **H2 database** Enable h2-console under property file and go to [h2-console](http://localhost:8080/h2-console). Refer `data.sql` under resource file. JDBC url should be set to `jdbc:h2:mem:testdb`.
