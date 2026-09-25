package com.myplunt.greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    
    public String getGreeting() {
        return "Hello!";
    }
}