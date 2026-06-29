# Getting Started

## Setup

- **Config Client** dependency added.
- **Eureka Client** registers this service on naming server running on 8761 port. 
- **Openfeign** dependency added. @EnableFeignClients and @FeignClient annotations added.
- Check property files for git URI.
- This service will run at multiple ports i.e. 8100, 8101, 8102. to demonstrate load balancing across multiple instances.
- Currency Conversion microservice will talk to load balancer which in turn will route requests to various ports of Currency Exchange Service.
- This microservice maintains it's own H2 in-memory database to store currency exchange rates [h2-console](http://localhost:8000/h2-console).

## URLs

- Currency Exchange Service: [/currency-exchange/from/USD/to/INR](http://localhost:8000/currency-exchange/from/USD/to/INR)
- Currency Conversion Service: [/currency-conversion/from/USD/to/INR/quantity/10](http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10)
- **Sleuth, Zipkin and RabbitMQ** are added for distributed tracing.
