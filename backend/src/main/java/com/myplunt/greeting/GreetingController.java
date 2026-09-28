package com.myplunt.greeting;

import com.myplunt.constants.ApiPaths;
import com.myplunt.greeting.dto.GreetingRequest;
import com.myplunt.greeting.dto.GreetingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(ApiPaths.Greeting.BASE)
    public GreetingResponse getGreeting() {
        return greetingService.getGreeting();
    }

    @PostMapping(ApiPaths.Greeting.BASE)
    @ResponseStatus(HttpStatus.CREATED)
    public GreetingResponse createGreeting(@Valid @RequestBody GreetingRequest request) {
        return greetingService.createGreeting(request);
    }
}
