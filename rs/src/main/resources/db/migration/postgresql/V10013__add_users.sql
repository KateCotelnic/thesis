INSERT INTO users (email,
                     password,
                     role,
                     is_enable,
                     first_name,
                     last_name,
                     phone_number,
                     age)
VALUES ('kotkatea@gmail.com', '$2a$12$6OxG/l4ROLHeUXyvfrvRge9/w8PHt9gZZjY37uL15bUXeB/Jpvss.',
        'ADMIN', true, 'Catherine', 'Cot', '069485739', 27),
       ('jeffrey@gmail.com', '$2a$12$20kvXa6CZ5/JHuib0kIeOevbCFAF4mZJR7W6PiEbBQDqhbKrLpPOi',
        'PATIENT', false, 'Jeffrey', 'Jef', '069746375', 25),
       ('ecaterina.cotelnic@isa.utm.md', '$2a$12$fVQwXxgEV.abCSLpjN7V6uXV4Qm4cvRqANCXmnpwXZ5dTixXrBNBe',
        'PATIENT', true, 'Ecaterina', 'Cotelnic', '069857483', 16),
       ('johan@gmail.com', '$2a$12$NW2di6HX.M9u0lvJvRTsCe65amVdLIgx5wn/2uldXuak8BbTEw8oO',
        'PATIENT', true, 'Johan', 'Doyle', '069058694', 37),
       ('tomkel@gmail.com', '$2a$12$B4IVy0E2ORKQ6KXpxYHG7eFwji4pSIVfa61ebP/xPzG1ZIUF0F0nm',
        'PATIENT', true, 'Tom', 'Keller', '069495860', 48);

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
VALUES ('polina@gmail.com', '$2a$12$s39m9a4.s8NXuIZ0GzmkrugfSEF3FBpMJKusthZwohRgy1f1pcKVu',
        'DOCTOR', true, 'Polina', 'Murphy', '068493784', 'PSYCHIATRIST', 300, 'SECOND', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('catalin.schiopu@isa.utm.md', '$2a$12$s39m9a4.s8NXuIZ0GzmkrugfSEF3FBpMJKusthZwohRgy1f1pcKVu',
        'DOCTOR', true, 'Catalin', 'Schiopu', '069857403', 'PSYCHIATRIST', 330, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 4.5),
       ('rose.fr@gmail.com', '$2a$12$KXIXyz4J.z6g9FAjPo0One6LED0DysQnh4Z2Y3Wis0EiuOCq1G6hm',
        'DOCTOR', true, 'Rose', 'Francis', '068645986', 'MAMMOLOGIST', 400, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('barry@gmail.com', '$2a$12$jwScBLWwTTRri6vKtZjwLu6gZt174i4VDjwkl5s1x5uYIIp8CqD/6',
        'DOCTOR', true, 'Barry', 'Moody', '069857463', 'ONCOLOGIST', 200, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('esmae@gmail.com', '$2a$12$okWMvzWvlSUgu9KAVeL65O22vmPrm4gelmBwAz0BrQCiZOIXTrAb6',
        'DOCTOR', true, 'Esmae', 'Thompson', '069253640', 'HEMATOLOGIST', 250, 'HIGHEST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('hopperlacy@gmail.com', '$2a$12$fTxW.KAUdFd6gFscIi4FZelBDqoz.VsFMAniaBV53D07PvCFD.H7a',
        'DOCTOR', true, 'Lacy', 'Hopper', '069857102', 'PLASTIC_SURGEON', 500, 'SECOND', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('cherish@gmail.com', '$2a$12$MMqoNe1nyETxL2oN2wDzkeSIdpEYoK4//6iWzOO.xwSBOtFax.w/G',
        'DOCTOR', true, 'Cherish', 'Avila', '069840583', 'PEDIATRICIAN', 100, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('payton@gmail.com', '$2a$12$OSWQlBAjc1L7cz0D.GJukuVl6wLGYEMVk0ByfSk8Ks4VBzGJrTSJG',
        'DOCTOR', true, 'Payton', 'Bell', '069850384', 'PEDIATRICIAN', 120, 'HIGHEST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('kyandev@gmail.com', '$2a$12$i97sLzulJ6XkgcJXC5yQWuuTRKbaar.bai.5XYy11CqXte8coESmi',
        'DOCTOR', true, 'Kyan', 'Devlin', '069482029', 'RADIOLOGIST', 300, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('celeste@gmail.com', '$2a$12$omnQrF4GxFrOHtil/JVo8eJBdZTEdFch3ychKsa6Vj08qtfLlDik2',
        'DOCTOR', true, 'Celeste', 'Avery', '069302856', 'SEXOLOGIST', 300, 'HIGHEST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('holli@gmail.com', '$2a$12$Co/sU8zYDkgYJqqBb3RgWO6W9emqoGYQYnVxiXhsjYm0dyneRxR1S',
        'DOCTOR', true, 'Holli', 'Wheatley', '069887409', 'DENTIST', 500, 'HIGHEST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('bonnie@gmail.com', '$2a$12$0R2BrfRROGYn1ExXQRM9aOG7ue27wfsOB0QPHasHH6HGZ/L2ym2hW',
        'DOCTOR', true, 'Bonnie', 'Boyce', '068765498', 'UROLOGIST', 200, 'SECOND', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('felicity@gmail.com', '$2a$12$1OMgBbMkuvDRbPxsYJO0eufBL.O3sAz9RK/UbanCj9JxO8wDS05G6',
        'DOCTOR', true, 'Felicity', 'Hurley', '069890945', 'ENDOCRINOLOGIST', 230, 'FIRST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('rosalind@gmail.com', '$2a$12$vkypFYoL9iz6C18UDM/qW.5SDoPFvafT.Bs0CnTTlOwv10m9qwuga',
        'DOCTOR', true, 'Rosalind', 'Dillard', '069895432', 'ONCOLOGIST', 300, 'HIGHEST', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
        'ADULT', 0),
       ('zakifor@gmail.com', '$2a$12$PbHD4UNoKQmizClafMs2Z.5WDfHG2YlkyAse5PZdSgnh4nMr6vGwm',
        'DOCTOR', true, 'Zaki', 'Foreman', '069543209', 'PULMONOLOGIST', 300, 'SECOND', 3, 'Consultation, examination, diagnosis, prescription treatment. Consult in the language: Romanian, Russian.',
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
        'DOCTOR', true, 'Tom', 'Reyes', '079857463', 'THERAPIST', 100, 'HIGHEST', 12, 'FAMILY', 0);