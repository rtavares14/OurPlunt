package com.myplunt.greeting;

import com.myplunt.greeting.dto.GreetingRequest;
import com.myplunt.greeting.dto.GreetingResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

    @Mock
    private GreetingRepository greetingRepository;

    @Test
    void getGreeting_returnsMessageFromRepository() {
        when(greetingRepository.findAll()).thenReturn(List.of(new Greeting("Hello!")));

        GreetingService greetingService = new GreetingService(greetingRepository);

        assertThat(greetingService.getGreeting().message()).isEqualTo("Hello!");
    }

    @Test
    void getGreeting_throwsWhenNoneFound() {
        when(greetingRepository.findAll()).thenReturn(List.of());

        GreetingService greetingService = new GreetingService(greetingRepository);

        assertThatThrownBy(greetingService::getGreeting)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void createGreeting_savesAndReturnsMessage() {
        when(greetingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        GreetingService greetingService = new GreetingService(greetingRepository);

        GreetingResponse response = greetingService.createGreeting(new GreetingRequest("Hi!"));

        assertThat(response.message()).isEqualTo("Hi!");
    }
}
