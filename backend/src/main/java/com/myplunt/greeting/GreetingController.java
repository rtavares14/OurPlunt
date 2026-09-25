package com.myplunt.greeting;

import com.myplunt.constants.ApiPaths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(ApiPaths.Greeting.BASE)
    public String getGreeting() {
        return greetingService.getGreeting();
    }
}