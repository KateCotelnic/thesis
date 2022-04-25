CREATE TABLE users
(
    email   varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,
    role         varchar(255) NOT NULL,
    is_enable    boolean NOT NULL,
    first_name   varchar(255) NOT NULL,
    middle_name  varchar(255),
    last_name    varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    photo        varchar(255),
    speciality   varchar(255),
    price        integer DEFAULT(0),
    grade        varchar(255),
    experience   integer DEFAULT(0),
    description  varchar(255),
    classification varchar(255),
    rating       double precision DEFAULT(0)
);