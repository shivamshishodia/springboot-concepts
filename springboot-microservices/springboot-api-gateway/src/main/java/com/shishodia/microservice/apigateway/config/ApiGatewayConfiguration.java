package com.shishodia.microservice.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// By default the route created by API gateway will be of type `http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR`
// You can create your own custom routes using the RouteBuilder given below.
// You can also filter out headers, params, etc. for Security.
@Configuration
public class ApiGatewayConfiguration {

    /*
     * We want the API gateway to route `http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR`
     * to `http://localhost:8765/currency-exchange/from/USD/to/INR` to access `http://localhost:8000/currency-exchange/from/USD/to/INR`.
     * Note that we are removing `currency-exchange` from the route with port 8765 and making it consistent with last two routes. 
     * Also, everything in the last routes remain the same except the ports 8765 (API gateway) and 8000 (Original service).  
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder
            .routes()
            // Route `http://localhost:8765/currency-exchange/**` to `http://localhost:8765/currency-exchange/from/USD/to/INR`
            // `lb` is used to load balance 
            .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
            .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
            .route(p -> p.path("/currency-conversion-rest/**").uri("lb://currency-conversion"))
            // You can create your own custom route to be re-directed to some other URI
            .route(p -> p.path("/currency-conversion-od/**")
                .filters(f -> f.rewritePath("/currency-conversion-od/(?<segment>.*)", "/currency-conversion/${segment}"))
                .uri("lb://currency-conversion"))
            .build();
    }
    
}
