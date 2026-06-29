package com.shishodia.concepts.di;

import org.springframework.http.ResponseEntity;

public interface ClientController {

    public ResponseEntity<Object> informationAll();
    public ResponseEntity<Object> informationPrimary();
    public ResponseEntity<Object> informationClientServiceA();
    public ResponseEntity<Object> informationClientServiceB();
    public ResponseEntity<Object> informationClientServiceC();
    public ResponseEntity<Object> informationAutoClientServiceA();
    public ResponseEntity<Object> informationAutoClientServiceB();
    public ResponseEntity<Object> informationAutoClientServiceC();
    
}
