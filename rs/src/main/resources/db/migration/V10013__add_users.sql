INSERT INTO users (email,
                     password,
                     role,
                     is_enable,
                     first_name,
                     last_name,
                     phone_number)
VALUES ('katie@email.com', '$2a$12$6OxG/l4ROLHeUXyvfrvRge9/w8PHt9gZZjY37uL15bUXeB/Jpvss.',
        'ADMIN', true, 'Catherine', 'Cot', '049584732'),
       ('jeffrey@email.com', '$2a$12$20kvXa6CZ5/JHuib0kIeOevbCFAF4mZJR7W6PiEbBQDqhbKrLpPOi',
        'PATIENT', false, 'Jeffrey', 'Jef', '304874032'),
       ('emily@email.com', '$2a$12$fVQwXxgEV.abCSLpjN7V6uXV4Qm4cvRqANCXmnpwXZ5dTixXrBNBe',
        'PATIENT', true, 'Emily', 'Muller', '3215153425'),
       ('catal@email.com', '$2a$12$C5gDWeIRojjxAjPgG1XacugzdAb6HrJeHzFqIIM96nicRF9ISOQdy',
        'PATIENT', true, 'Catalin', 'Kelly', '4932754930');

INSERT INTO users (email,
                   password,
                   role,
                   is_enable,
                   first_name,
                   last_name,
                   phone_number,
                   speciality,
                   price,
                   grade,
                   experience,
                   description,
                   classification,
                   rating)
VALUES ('polly@email.com', '$2a$12$s39m9a4.s8NXuIZ0GzmkrugfSEF3FBpMJKusthZwohRgy1f1pcKVu',
        'DOCTOR', true, 'Polina', 'Murphy', '0948573035', 'PSYCHIATRIST', 300, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0);

INSERT INTO users (email,
                   password,
                   role,
                   is_enable,
                   first_name,
                   last_name,
                   phone_number,
                   speciality,
                   price,
                   grade,
                   experience,
                   classification,
                   rating)

VALUES ('tommy@email.com', '$2a$12$RbRBKNQayrE6vqlfSXFJSuRULF8wyRtCG5x1UX26jamq7FrCMJLXu',
        'DOCTOR', true, 'Tom', 'Reyes', '05948386785', 'THERAPIST', 200, 'HIGHEST', 12, 'FAMILY', 4.5);