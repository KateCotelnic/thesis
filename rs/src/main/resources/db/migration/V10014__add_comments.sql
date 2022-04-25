INSERT INTO comments ( comment_id,
                       body,
                       rating,
                       date,
                       patient_email,
                       doctor_email)
VALUES (nextval('ehealth_sequence'),
        'I liked the consultation, it went quickly and efficiently.',
        5, '2021-11-28', 'jeffrey@email.com', 'tommy@email.com');

INSERT INTO comments ( comment_id,
                       rating,
                       date,
                       patient_email,
                       doctor_email)
VALUES (nextval('ehealth_sequence'),
        4, '2020-03-12', 'emily@email.com', 'tommy@email.com');