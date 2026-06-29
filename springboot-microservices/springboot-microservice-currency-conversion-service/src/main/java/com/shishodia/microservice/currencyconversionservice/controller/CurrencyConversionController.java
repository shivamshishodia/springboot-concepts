package com.shishodia.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import com.shishodia.microservice.currencyconversionservice.bean.CurrencyConversion;
import com.shishodia.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    // This is a crude way of calling another microservice using rest templates (we can use feign client instead)
    @GetMapping(path = "currency-conversion-rest/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionRestCall(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {
        // Preparing rest call.
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
            .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        
        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, 
            currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()), 
            currencyConversion.getEnvironment() + "rest");
    }

    // We can use feign client as given below.
    @GetMapping(path = "currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeignCall(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {
        // Use Feign proxy.
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, 
            currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()), 
            currencyConversion.getEnvironment() + "feign");
    }
    
}
