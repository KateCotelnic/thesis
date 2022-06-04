CREATE TABLE appointments
(
    appointment_id   bigint NOT NULL,
    doctor_email     varchar(255) NOT NULL,
    patient_email         varchar(255) NOT NULL,
    hospital_name    varchar(255) NOT NULL,
    start_date         timestamp NOT NULL,
    end_date         timestamp NOT NULL,
    status      varchar(255) NOT NULL,
    sent_notification  boolean default false
);