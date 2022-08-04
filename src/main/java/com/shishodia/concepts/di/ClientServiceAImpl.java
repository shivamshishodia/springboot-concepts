package com.shishodia.concepts.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("ClientServiceAImpl")
public class ClientServiceAImpl implements ClientService {

    @Override
    public String information() {
        return new String("ClientServiceAImpl > information");
    }

    @Override
    public String clientName() {
        return "ClientServiceAImpl";
    }
    
}
