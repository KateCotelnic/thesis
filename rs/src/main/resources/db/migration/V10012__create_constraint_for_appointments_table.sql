ALTER TABLE appointments
    ADD CONSTRAINT PK_appointment_id PRIMARY KEY (appointment_id);

ALTER TABLE appointments
    ADD CONSTRAINT FK_doctor FOREIGN KEY (doctor_email) REFERENCES users (email);

ALTER TABLE appointments
    ADD CONSTRAINT FK_patient FOREIGN KEY (patient_email) REFERENCES users (email);

ALTER TABLE appointments
    ADD CONSTRAINT FK_hospital FOREIGN KEY (hospital_name) REFERENCES hospitals (hospital_name);