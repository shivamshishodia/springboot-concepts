package com.shishodia.microservice.limitsservice.controller;

import com.shishodia.microservice.limitsservice.bean.Limits;
import com.shishodia.microservice.limitsservice.config.PropertyConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
public class LimitsController {

    @Autowired
    private PropertyConfig config;

    @GetMapping(path = "limits")
    public Limits retrieveLimit() {
        return new Limits(config.getMinimum(), config.getMaximum());
    }
    
}
