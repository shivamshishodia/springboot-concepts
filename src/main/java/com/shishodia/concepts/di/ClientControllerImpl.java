package com.shishodia.concepts.di;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/")
public class ClientControllerImpl implements ClientController {

    /** -------------- using @Primary --------------- */

    /**
     * If none of the three service implementations (ClientServiceAImpl, ClientServiceBImpl and 
     * ClientServiceCImpl) have @Primary then the below error will be thrown.
     * Consider marking one of the beans as @Primary, updating the consumer to accept multiple 
     * beans, or using @Qualifier to identify the bean that should be consumed.
     */
    
    @Autowired
    private ClientService primaryClientService; // ClientServiceAImpl is marked @Primary.

    @Override
    @GetMapping(path = "/information/primary")
    public ResponseEntity<Object> informationPrimary() {
        return new ResponseEntity<Object>(primaryClientService.information(), HttpStatus.OK);
    }

    /** -------------- using iteration and clientName() -------------- */

    @Autowired
    Collection<ClientService> allClientServices;

    @Override
    @GetMapping(path = "/information/all")
    public ResponseEntity<Object> informationAll() {
        // This will call ClientServiceBImpl.
        String clientServiceName = "ClientServiceBImpl";
        for (ClientService s : allClientServices) {
            if (clientServiceName.equals(s.clientName())) {
                return new ResponseEntity<Object>(s.information(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Object>("None service implementation selected.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /** -------------- using @Autowired in constructor -------------- */

    private ClientService clientServiceA;
    private ClientService clientServiceB;
    private ClientService clientServiceC;

    @Autowired 
    public ClientControllerImpl(
        @Qualifier("ClientServiceAImpl") ClientService clientServiceA,
        @Qualifier("ClientServiceBImpl") ClientService clientServiceB,
        @Qualifier("ClientServiceCImpl") ClientService clientServiceC
    ) {
        this.clientServiceA = clientServiceA;
        this.clientServiceB = clientServiceB;
        this.clientServiceC = clientServiceC;
    }

    @Override
    @GetMapping(path = "/information/a")
    public ResponseEntity<Object> informationClientServiceA() {
        return new ResponseEntity<Object>(clientServiceA.information(), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/information/b")
    public ResponseEntity<Object> informationClientServiceB() {
        return new ResponseEntity<Object>(clientServiceB.information(), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/information/c")
    public ResponseEntity<Object> informationClientServiceC() {
        return new ResponseEntity<Object>(clientServiceC.information(), HttpStatus.OK);
    }

    /** -------------- using @Qualifier referring @Service("ServiceName") -------------- */

    @Autowired
    @Qualifier(value = "ClientServiceAImpl")
    private ClientService autoClientServiceA;

    @Autowired
    @Qualifier(value = "ClientServiceBImpl")
    private ClientService autoClientServiceB;

    @Autowired
    @Qualifier(value = "ClientServiceCImpl")
    private ClientService autoClientServiceC;

    @Override
    @GetMapping(path = "/information/auto/a")
    public ResponseEntity<Object> informationAutoClientServiceA() {
        return new ResponseEntity<Object>(autoClientServiceA.information(), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/information/auto/b")
    public ResponseEntity<Object> informationAutoClientServiceB() {
        return new ResponseEntity<Object>(autoClientServiceB.information(), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/information/auto/c")
    public ResponseEntity<Object> informationAutoClientServiceC() {
        return new ResponseEntity<Object>(autoClientServiceC.information(), HttpStatus.OK);
    }

}
