package com.ehealth.ms.services;

public interface ValidationService {
    void verifyEmail(String email);
    void verifyPassword(String password);
}
