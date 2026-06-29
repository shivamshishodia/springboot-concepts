package com.shishodia.concepts.di;

import org.springframework.stereotype.Service;

@Service("ClientServiceCImpl")
public class ClientServiceCImpl implements ClientService {

    @Override
    public String information() {
        return new String("ClientServiceCImpl > information");
    }

    @Override
    public String clientName() {
        return "ClientServiceCImpl";
    }
    
}
