package com.shishodia.microservice.basic.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * In the first version, customers wanted only one string field to access name.
 * In the second version, some customer wanted two fields i.e. first name and last name.
 * Both are required to up.
 */
@RestController
@RequestMapping(value = "versioning/")
public class VersioningResource {

    /**
     * Summary
     * 1. URI based versioning (v1/person and v2/person).
     * 2. Request Parameter based versioning (params = "version=1/2" and @RequestParam).
     * 3. Header based versioning (headers = "X-API-VERSION=1/2" and @RequestHeader("X-API-VERSION"))
     * 4. Produces based versioning (produces = "application/vnd.company.app-v1/2+json")
     */

    /*
     * Different URIs for different versions (Twitter). 
     * i.e. "v1/person" and "v2/person".
     * Disadv.: URI pollution, Difficult documentation.
     */
    @GetMapping(value = "v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Shivam Shishodia");
    }

    @GetMapping(value = "v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Shivam", "Shishodia"));
    }

    /*
     * Request parameter based versioning (Amazon). 
     * Use "person/param?version=1/2".
     * Disadv.: URI pollution, Difficult documentation.
     */
    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 personParamV1(@RequestParam Integer version) {
        return new PersonV1("Shivam Shishodia");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 personParamV2(@RequestParam Integer version) {
        return new PersonV2(new Name("Shivam", "Shishodia"));
    }

    /*
     * Header based versioning (Microsoft). 
     * Use "person/header" and include "X-API-VERSION=1" in the request headers.
     * Disadv.: Caching, Misuse of HTTP headers (they were not meant to be used for versioning)
     * Disadv.: Not for normal user as headers need to configured. 
     * Adv.: Easy documentation.
     */
    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1(@RequestHeader("X-API-VERSION") Integer apiVersion) {
        return new PersonV1("Shivam Shishodia");
    }

    // TODO: Commented as Swagger UI breaks due to this
    // @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    // public PersonV2 personHeaderV2(@RequestHeader("X-API-VERSION") Integer apiVersion) {
    //     return new PersonV2(new Name("Shivam", "Shishodia"));
    // }

    /*
     * Produces based versioning. Also called Media type versioning (Github).
     * Use "person/produces" and include "Accept=application/vnd.company.app-v1/2+json" 
     * in the request headers.
     * Disadv.: Caching, Misuse of HTTP headers (they were not meant to be used for versioning)
     * Disadv.: Not for normal user as headers need to configured. 
     * Adv.: Easy documentation.
     */
    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProducesV1() {
        return new PersonV1("Shivam Shishodia");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personProducesV2() {
        return new PersonV2(new Name("Shivam", "Shishodia"));
    }
    
}
