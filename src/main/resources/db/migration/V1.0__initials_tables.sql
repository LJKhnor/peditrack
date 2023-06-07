create table patient if not exists (
    id int8 not null primary key,
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
    constraint fk_person foreign key(person_of_contact_id) references person(id),
    constraint fk_person foreign key(referenced_by_id) references person(id),
    constraint fk_person foreign key(doctor_id) references person(id)
);

create table person if not exists (
    id int8 not null primary key,
    name varchar(80) not null,
    first_name varchar(80) not null,
    num_tel varchar(15) null,
);