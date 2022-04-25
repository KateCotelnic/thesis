ALTER TABLE hospitals_doctors
    ADD CONSTRAINT FK_doctor FOREIGN KEY (doctor_email) REFERENCES users (email);

ALTER TABLE hospitals_doctors
    ADD CONSTRAINT FK_hospital FOREIGN KEY (hospital_name) REFERENCES hospitals (hospital_name);