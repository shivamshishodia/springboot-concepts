package com.shishodia.concepts.cd.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class LazyCircularDependencyA {

    private LazyCircularDependencyB circleDepB;

    @Autowired
    public LazyCircularDependencyA(LazyCircularDependencyB circleDepB) {
        this.circleDepB = circleDepB;
    }
    
}
