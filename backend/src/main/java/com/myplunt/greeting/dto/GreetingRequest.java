package com.myplunt.greeting.dto;

import jakarta.validation.constraints.NotBlank;

public record GreetingRequest(@NotBlank String message) {
}
