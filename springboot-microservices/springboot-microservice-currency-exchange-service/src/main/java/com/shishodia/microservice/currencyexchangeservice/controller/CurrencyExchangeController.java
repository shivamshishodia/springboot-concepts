package com.shishodia.microservice.currencyexchangeservice.controller;

import com.shishodia.microservice.currencyexchangeservice.bean.CurrencyExchange;
import com.shishodia.microservice.currencyexchangeservice.data.CurrencyExchangeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class); 

    @Autowired
    private CurrencyExchangeRepository repository;

    // This service will run at multiple ports i.e. 8000, 8001, 8002. 
    // to demonstrate load balancing across multiple instances.
    @Autowired
    private Environment environment;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        // CurrencyExchange currencyExchange =  new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(65.00));

        logger.info("retrieveExchangeValue called with {} to {}", from, to);

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
    
}
