package com.shishodia.microservice.basic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Either use @JsonIgnoreProperties or @JsonIgnore for static filtering.
 * In static filtering, properties are ignored across the board in the http response.
 */
@JsonIgnoreProperties(value = { "password", "code" })
public class ConfigsStaticFilter {

    private Integer id;
    private String name;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String code;

    public ConfigsStaticFilter() {
    }

    public ConfigsStaticFilter(Integer id, String name, String password, String code) {
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
        return "Configs [code=" + code + ", id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
