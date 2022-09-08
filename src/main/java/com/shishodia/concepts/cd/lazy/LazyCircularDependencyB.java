package com.shishodia.concepts.cd.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class LazyCircularDependencyB {

    private LazyCircularDependencyA circleDepA;

    /*
     * Use @Lazy.
     * A simple way to break the cycle is by telling Spring to initialize one of the beans lazily. 
     * So, instead of fully initializing the bean, it will create a proxy to inject it into the 
     * other bean. The injected bean will only be fully created when it’s first needed.
     */
    
    @Autowired
    public LazyCircularDependencyB(@Lazy LazyCircularDependencyA circleDepA) {
        this.circleDepA = circleDepA;
    }
    
}
