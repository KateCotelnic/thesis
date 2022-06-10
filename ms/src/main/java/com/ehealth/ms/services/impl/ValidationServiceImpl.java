package com.ehealth.ms.services.impl;

import com.ehealth.ms.exceptions.InvalidEmailPasswordException;
import com.ehealth.ms.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void verifyEmail(String email) {
        if (Objects.isNull(email) || !email.matches("^(?![.])[A-z0-9.]{5,35}@[A-z0-9.]{1,10}\\.[A-z0-9.]{1,11}$")) {
            throw new InvalidEmailPasswordException("Email is not valid.");
        }
    }

    @Override
    public void verifyPassword(String password) {
        if (Objects.isNull(password) || !password.matches("^[A-z0-9'~!@#$%^&*()_+\\-=?.,;:'\\/\\\"|\\{\\}<>\\[\\]]{5,10}$")) {
            throw new InvalidEmailPasswordException("Password is not valid.");
        }
    }
}
