package com.shishodia.microservice.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    // We are going to Retry (sometimes the failing APIs do reply after some time).
    // We are using default configs, and a custom config included in the property.
    @GetMapping(path = "sample-api")
    // @Retry(name = "default") // This will retry 3 times.
    @Retry(name = "sample-api-retry-limit", fallbackMethod = "hardCodedResponse") // This will retry 5 times. This is set in property file.
    // Refer resilience4j.retry.instances.sample-api-retry-limit.maxRetryAttempts=5
    public String sampleApi() {
        // This logging is done to check the @Retry annotation. It will fail and print 
        // this log multipe times (depends on the @Retry name) during runtime.
        // Error is returned only when all the retries fail.
        logger.info("Sample API request received.");
        // We are purposely calling an API which does not exist and will fail.
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("https://localhost:9000/something-which-will-fail", String.class);
        return responseEntity.getBody();
    }

    // Here we are using circuit breaker
    // YOU NEED TO BOMBARD THIS API USING `watch`. 
    // After some point Circuit Breaker will re-direct to fallback and logs will no longer generate.
    @GetMapping(path = "sample-api-circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    public String sampleApiCircuitBreaker() {
        logger.info("Sample API request received.");
        // We are purposely calling an API which does not exist and will fail.
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("https://localhost:9000/something-which-will-fail", String.class);
        return responseEntity.getBody();
    }

    // Rate limiter. Property for `default` is set under property file.
    @GetMapping(path = "sample-api-rate-limiter")
    @RateLimiter(name = "default")
    public String sampleApiRateLimiter() {
        logger.info("Sample API request received.");
        return "sample-api-rate-limiter-message";
    }

    // Bulk head. Property for `default` is set under property file.
    @GetMapping(path = "sample-api-bulk-head")
    @Bulkhead(name = "default")
    public String sampleApiBulkHead() {
        logger.info("Sample API request received.");
        return "sample-api-bulk-head-message";
    }

    // Falback message when the rest call fails after multiple retires.
    public String hardCodedResponse(Exception ex) {
        return "fallback-response";
    }
    
}
