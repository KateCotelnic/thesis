INSERT INTO comments ( comment_id,
                       body,
                       rating,
                       date,
                       patient_email,
                       doctor_email)
VALUES (nextval('ehealth_sequence'),
        'I liked the consultation, it went quickly and efficiently.',
        5, '2021-11-28', 'jeffrey@gmail.com', 'catalin.schiopu@isa.utm.md');

INSERT INTO comments ( comment_id,
                       rating,
                       date,
                       patient_email,
                       doctor_email)
VALUES (nextval('ehealth_sequence'),
        4, '2020-03-12', 'ecaterina.cotelnic@isa.utm.md', 'catalin.schiopu@isa.utm.md');