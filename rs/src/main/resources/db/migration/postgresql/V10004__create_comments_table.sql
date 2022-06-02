CREATE TABLE comments
(
    comment_id        bigint NOT NULL,
    body     varchar(255),
    rating       integer NOT NULL,
    date    date NOT NULL,
    patient_email   varchar(255) NOT NULL,
    doctor_email  varchar(255) NOT NULL
);