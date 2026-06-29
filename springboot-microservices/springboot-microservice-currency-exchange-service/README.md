# Getting Started

## Setup

- **Config Client** dependency is already added.
- **Eureka Client** registers this service on naming server running on 8761 port. 
- **Resilience4j** is added for Circuit breaker stuff. Refer `CircuitBreakController` for more.
- **Sleuth, Zipkin and RabbitMQ** are added for distributed tracing.
- Check property files for git URI.
- This service will run at multiple ports i.e. 8000, 8001, 8002. to demonstrate load balancing across multiple instances.
- Currency Conversion microservice will talk to load balancer which in turn will route requests to various ports of Currency Exchange Service.
- This microservice maintains it's own H2 in-memory database to store currency exchange rates [h2-console](http://localhost:8000/h2-console).

## URLs

- Currency Exchange Service: [/currency-exchange/from/USD/to/INR](http://localhost:8000/currency-exchange/from/USD/to/INR)
- Currency Conversion Service: [/currency-conversion/from/USD/to/INR/quantity/10](http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10)

## Circuit Breaker URL

- **API with @Retry** http://localhost:8000/sample-api. Provides multiple tries and then fall backs to fallback method.
- **API with @CircuitBreaker** http://localhost:8000/sample-api-circuit-breaker. Provides fallback method when the too many failure requests occur. Refer https://resilience4j.readme.io/docs/circuitbreaker.
- **API with @RateLimiter** http://localhost:8000/sample-api-rate-limiter.
- **API with @Bulkhead** http://localhost:8000/sample-api-bulk-head.
