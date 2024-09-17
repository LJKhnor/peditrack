--create table if not exists "user"(
--    id serial primary key,
--    name varchar(80) not null,
--    password varchar(80) not null,
--    email varchar(255) unique not null
--);
--
--create table if not exists person(
--    id serial primary key,
--    name varchar(80) not null,
--    first_name varchar(80) not null,
--    num_tel varchar(15) null
--);
--
--create table if not exists patient(
--    id serial primary key,
--    name varchar(80) not null,
--    first_name varchar(80) not null,
--    num_tel varchar(15) null,
--    birthdate date null,
--    person_of_contact_id int8 not null,
--    referenced_by_id int8 not null,
--    doctor_id int8 not null,
--    mail varchar(80) null,
--    comments varchar(160) null,
--    address varchar(160) null,
--    locality varchar(80) null,
--    postal_code int8 null,
--    constraint fk_person_person_of_contact foreign key(person_of_contact_id) references person(id),
--    constraint fk_person_referenced_by foreign key(referenced_by_id) references person(id),
--    constraint fk_person_doctor foreign key(doctor_id) references person(id)
--);

-- Table 2: Mutual
CREATE TABLE IF NOT EXISTS Mutual (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    is_refundable BOOLEAN
);
-- Table 3: Doctor
CREATE TABLE IF NOT EXISTS Doctor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    num_tel VARCHAR(15)
);

-- Table 4: Person (Person of Contact)
CREATE TABLE IF NOT EXISTS Person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    num_tel VARCHAR(15)
);

-- Table 1: Patient
CREATE TABLE IF NOT EXISTS Patient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    num_tel VARCHAR(15),
    birthdate DATE,
    person_of_contact_id INT,
    mail VARCHAR(100),
    referenced_by_id INT,
    doctor_id INT,
    comments TEXT,
    address VARCHAR(255),
    locality VARCHAR(100),
    postal_code VARCHAR(10),
    mutual_id INT,  -- FK to Mutual
    FOREIGN KEY (mutual_id) REFERENCES Mutual(id),
    FOREIGN KEY (person_of_contact_id) REFERENCES Person(id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id)
);

-- Table 5: Health
CREATE TABLE IF NOT EXISTS Health (
    id SERIAL PRIMARY KEY,
    health_group INT CHECK (health_group IN (0, 1, 2, 3)),
    diabetes INT CHECK (diabetes IN (1, 2)),
    is_with_heart_problems BOOLEAN,
    is_with_bleeding_disorder BOOLEAN,
    is_with_thyroid_disorder BOOLEAN,
    has_knee_prothesis BOOLEAN,
    has_hip_prothesis BOOLEAN,
    has_recent_diseases BOOLEAN,
    has_recent_operation BOOLEAN,
    allergies TEXT[],  -- Array of allergies
    medicines TEXT[],  -- Array of medicines
    skin TEXT,
    feet TEXT,
    sweating TEXT,
    footnotes TEXT,
    circulation TEXT,
    dermatosis TEXT,
    foot_deformity TEXT,
    nail_disease TEXT,
    shoes_condition TEXT
);
-- Table 6: Patient_Health (Joint table between Patient and Health)
CREATE TABLE IF NOT EXISTS Patient_Health (
    id_patient INT,
    id_health INT,
    date2 DATE,
    cares TEXT,
    products_used TEXT,
    materials_used TEXT,
    possible_injuries TEXT,
    advice_given TEXT,
    PRIMARY KEY (id_patient, id_health),
    FOREIGN KEY (id_patient) REFERENCES Patient(id),
    FOREIGN KEY (id_health) REFERENCES Health(id)
);

-- Table: User
CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);


-- Relationships
-- A patient can have 0 or 1 mutual, and a mutual can have 1 to many patients (already implemented via the mutual_id in the Patient table).
-- A patient can have multiple health conditions (handled by the Patient_Health joint table).
-- A patient can have one person of contact, and a person of contact can have many patients (circular reference handled via foreign key in Patient).
-- A patient can have multiple doctors and a doctor can have many patients (via doctor_id in Patient).

