package com.shishodia.concepts.di;

import org.springframework.stereotype.Service;

@Service("ClientServiceBImpl")
public class ClientServiceBImpl implements ClientService {

    @Override
    public String information() {
        return new String("ClientServiceBImpl > information");
    }

    @Override
    public String clientName() {
        return "ClientServiceBImpl";
    }
    
}
