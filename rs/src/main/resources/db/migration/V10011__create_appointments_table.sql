CREATE TABLE appointments
(
    appointment_id   bigint NOT NULL,
    doctor_email     varchar(255) NOT NULL,
    patient_email         varchar(255) NOT NULL,
    hospital_name    varchar(255) NOT NULL,
    date         timestamp NOT NULL,
    duration    int NOT NULL,
    status      varchar(255) NOT NULL
);