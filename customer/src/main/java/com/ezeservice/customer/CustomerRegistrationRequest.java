package com.ezeservice.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}