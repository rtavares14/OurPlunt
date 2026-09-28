package com.myplunt.greeting;

import com.myplunt.greeting.dto.GreetingRequest;
import com.myplunt.greeting.dto.GreetingResponse;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public GreetingResponse getGreeting() {
        return greetingRepository.findAll()
                .stream()
                .findFirst()
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalStateException("No greeting found"));
    }

    public GreetingResponse createGreeting(GreetingRequest request) {
        Greeting greeting = greetingRepository.save(new Greeting(request.message()));
        return toResponse(greeting);
    }

    private GreetingResponse toResponse(Greeting greeting) {
        return new GreetingResponse(greeting.getId(), greeting.getMessage());
    }
}
