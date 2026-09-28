package com.myplunt.greeting;

import com.myplunt.greeting.dto.GreetingRequest;
import com.myplunt.greeting.dto.GreetingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(GreetingController.class)
@AutoConfigureRestTestClient
class GreetingControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private GreetingService greetingService;

    @Test
    void getGreeting_returnsHello() {
        when(greetingService.getGreeting()).thenReturn(new GreetingResponse(1L, "Hello!"));

        restTestClient.get().uri("/v1/greeting")
                .exchange()
                .expectBody(GreetingResponse.class)
                .isEqualTo(new GreetingResponse(1L, "Hello!"));
    }

    @Test
    void createGreeting_returnsCreatedGreeting() {
        when(greetingService.createGreeting(any())).thenReturn(new GreetingResponse(2L, "Hi!"));

        restTestClient.post().uri("/v1/greeting")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GreetingRequest("Hi!"))
                .exchange()
                .expectBody(GreetingResponse.class)
                .isEqualTo(new GreetingResponse(2L, "Hi!"));
    }

    @Test
    void createGreeting_rejectsBlankMessage() {
        restTestClient.post().uri("/v1/greeting")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GreetingRequest(" "))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
