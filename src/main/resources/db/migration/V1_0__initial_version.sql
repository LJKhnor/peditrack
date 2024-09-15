create table if not exists "user"(
    id serial primary key,
    name varchar(80) not null,
    password varchar(80) not null,
    email varchar(255) unique not null
);

create table if not exists person(
    id serial primary key,
    name varchar(80) not null,
    first_name varchar(80) not null,
    num_tel varchar(15) null
);

create table if not exists patient(
    id serial primary key,
    name varchar(80) not null,
    first_name varchar(80) not null,
    num_tel varchar(15) null,
    birthdate date null,
    person_of_contact_id int8 not null,
    referenced_by_id int8 not null,
    doctor_id int8 not null,
    mail varchar(80) null,
    comments varchar(160) null,
    address varchar(160) null,
    locality varchar(80) null,
    postal_code int8 null,
    constraint fk_person_person_of_contact foreign key(person_of_contact_id) references person(id),
    constraint fk_person_referenced_by foreign key(referenced_by_id) references person(id),
    constraint fk_person_doctor foreign key(doctor_id) references person(id)
);