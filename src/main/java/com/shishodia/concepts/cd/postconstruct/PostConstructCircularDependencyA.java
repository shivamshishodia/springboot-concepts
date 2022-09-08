package com.shishodia.concepts.cd.postconstruct;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstructCircularDependencyA {

    /*
     * Use @PostConstruct and @Autowired.
     * Another way to break the cycle is by injecting a dependency using @Autowired 
     * on one of the beans and then using a method annotated with @PostConstruct to 
     * set the other dependency.
     */

    @Autowired
    private PostConstructCircularDependencyB circleDepB;

    @PostConstruct
    public void init() {
        circleDepB.setCircleDepA(this);
    }

    public PostConstructCircularDependencyB getCircleB() {
        return circleDepB;
    }
    
}
