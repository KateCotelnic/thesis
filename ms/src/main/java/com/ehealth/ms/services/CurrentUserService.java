package com.ehealth.ms.services;

public interface CurrentUserService {
    boolean verifyAdmin();
    boolean verifyDoctor();
    boolean verifyPatient();
}
