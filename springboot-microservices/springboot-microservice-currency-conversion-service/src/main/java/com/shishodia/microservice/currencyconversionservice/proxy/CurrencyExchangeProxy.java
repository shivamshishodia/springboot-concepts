package com.shishodia.microservice.currencyconversionservice.proxy;

import com.shishodia.microservice.currencyconversionservice.bean.CurrencyConversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * `url` parameter is required when you are not using Eureka Server.
 * Note that when you specify the `url`, you are also specifying the port (or the instance)
 * which is not good when you want to load balance requests. When you use only `name`, 
 * the service asks the Eureka Server for the instance (or the port) it should query to.
 */
// @FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    
    // The below mapping is copied as is from `currency-exchange` microservice.
    // CurrencyConversion data members are super set of CurrencyExchange object.
    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
