# Getting Started

## Reference Documentation
For further reference, please consider the following sections:

* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#production-ready)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)

## Guide

- **Sleuth, Zipkin and RabbitMQ** are added for distributed tracing.
- Custom API routes are added under `apigateway.config.ApiGatewayConfiguration`. Check RouteLocator.
- Every request is logged using `apigateway.filter.LoggingFilter`. Check GlobalFilter interface and GatewayFilterChain.
