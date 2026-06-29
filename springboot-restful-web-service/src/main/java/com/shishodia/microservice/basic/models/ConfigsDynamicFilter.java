package com.shishodia.microservice.basic.models;

import com.fasterxml.jackson.annotation.JsonFilter;

/*
 * @JsonFilter("SomeConfigsFilter") picked from basic.resources.FilteredResources.dynamicFilter.
 * In dynamic filtering, properties are ignored as per case basis by mapping values in the http response.
 * See: basic.resources.dynamicFilter.
 */
@JsonFilter("SomeConfigsFilter")
public class ConfigsDynamicFilter {

    private Integer id;
    private String name;
    private String password;
    private String code;

    public ConfigsDynamicFilter() {
    }

    public ConfigsDynamicFilter(Integer id, String name, String password, String code) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfigsDynamicFilter [code=" + code + ", id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
