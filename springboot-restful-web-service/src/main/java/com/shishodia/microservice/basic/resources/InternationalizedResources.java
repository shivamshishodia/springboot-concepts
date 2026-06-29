package com.shishodia.microservice.basic.resources;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "messages/")
public class InternationalizedResources {

    @Autowired
    private MessageSource messageSource;

    /*
     * For Internationalization you can either go with 'Accept-Language' locale check
     * or you can pick the Locale from the LocaleContextHolder 
     * (See internationalizedLocaleContextHolder below)
     */
    @GetMapping(path = "internationalized")
    public String internationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        // Stored in messages.properties. Check definition of Locale (F12)
        /*
         * en: Good Morning
         * nl: Goedemorgen
         * fr: Bonjour
         * default: Default message. (when no match is found)
         */
        return messageSource.getMessage("good.morning.message", null, "Default message.", locale);
    }

    @GetMapping(path = "internationalizedContext")
    public String internationalizedLocaleContextHolder() {
        // Locale context automatically picks the locale in the request header 
        return messageSource.getMessage("good.morning.message", null, "Default message.", LocaleContextHolder.getLocale());   
    }
    
}
