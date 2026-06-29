package com.shishodia.concepts.cd.postconstruct;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class PostConstructCircularDependencyB {

    private PostConstructCircularDependencyA circleDepA;
	
    private String message = "Hello, (PostConstruct) CircularDependencyB";

    public void setCircleDepA(PostConstructCircularDependencyA circleDepA) {
        this.circleDepA = circleDepA;
    }
	
    public String getMessage() {
        return message;
    }
    
}
