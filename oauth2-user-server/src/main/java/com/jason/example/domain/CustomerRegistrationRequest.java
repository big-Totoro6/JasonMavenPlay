package com.jason.example.domain;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
