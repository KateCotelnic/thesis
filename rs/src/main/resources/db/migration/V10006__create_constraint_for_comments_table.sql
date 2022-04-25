ALTER TABLE comments
    ADD CONSTRAINT PK_comment_id PRIMARY KEY (comment_id);

ALTER TABLE comments
    ADD CONSTRAINT FK_comment_doctor FOREIGN KEY (doctor_email) REFERENCES users (email);

ALTER TABLE comments
    ADD CONSTRAINT FK_comment_patient FOREIGN KEY (patient_email) REFERENCES users (email);