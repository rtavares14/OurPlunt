package com.myplunt.greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingServiceTest {

    private final GreetingService greetingService = new GreetingService();

    @Test
    void getGreeting_returnsHello() {
        assertThat(greetingService.getGreeting()).isEqualTo("Hello!");
    }
}