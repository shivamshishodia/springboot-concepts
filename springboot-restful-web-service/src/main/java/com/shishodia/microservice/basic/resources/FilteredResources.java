package com.shishodia.microservice.basic.resources;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.shishodia.microservice.basic.models.ConfigsStaticFilter;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "filters/")
public class FilteredResources {

    @GetMapping(path = "static-filter")
    public List<ConfigsStaticFilter> staticFilter() {
        return Arrays.asList(
            new ConfigsStaticFilter(1, "Adam", "123", "XYZ"),
            new ConfigsStaticFilter(2, "Ben", "456", "MNO"),
            new ConfigsStaticFilter(3, "Calvin", "890", "ABC")
        );
    }

    @GetMapping(path = "dynamic-filter")
    public MappingJacksonValue dynamicFilter() {

        List<ConfigsStaticFilter> configValues =  Arrays.asList(
            new ConfigsStaticFilter(1, "Adam", "123", "XYZ"),
            new ConfigsStaticFilter(2, "Ben", "456", "MNO"),
            new ConfigsStaticFilter(3, "Calvin", "890", "ABC")
        );

        // Provide the values to filter
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        // This filter is also added at basic.models.ConfigsDynamicFilter
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeConfigsFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(configValues);
        mapping.setFilters(filters);

        return mapping;

    }
    
}
