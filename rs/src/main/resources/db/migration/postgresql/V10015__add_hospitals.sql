INSERT INTO hospitals (    hospital_name,
                            is_enable,
                           phone_number,
                           website,
                           city_area,
                           address)
VALUES ('Medpark International Hospital', true, '022 400 040', 'https://medpark.md/en',
        'CIOCANA', 'Strada Andrei Doga 24'),
       ('Sancos', true, '022 909 908', 'https://www.sancos.md/',
        'RISCANI', 'Bulevardul Moscova 16'),
       ('Allclinic', true, '022 200 000', 'http://allclinic.md/',
        'CENTRU', 'Strada Vasile Alecsandri 90/1'),
       ('Forum Clinic', true, '022 870 707', 'https://forumclinic.md/',
        'CENTRU', 'Strada Gheorghe Asachi 1'),
       ('Estetic Med Center', true, '0698 02 614', 'https://www.facebook.com/esteticmed.md/',
        'TELECENTRU', 'Strada Anestiade 3'),
       ('Tibetmed', true, '022 001 122', 'http://www.tibetmed.md/',
        'CENTRU', 'Vlaicu Pircalab St 30'),
       ('Expert Clinics', true, '0600 40 540', null,
        'BOTANICA', 'str. Tadeus Malinovschi 14/2'),
       ('OftalmoCorrect ProMed Family&Kids', true, '0799 90 382', 'https://pro-med.md/',
        'BUIUCANI', 'Strada Ion Creangă 24/1');

INSERT INTO hospitals (    hospital_name,
                            is_enable,
                           city_area,
                           address )
VALUES ('Repromed', true, 'BOTANICA', 'Strada Cuza Vodă 29/1');