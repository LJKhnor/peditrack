-- Table : Patient
CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    num_tel VARCHAR(15),
    birthdate DATE,
    person_of_contact VARCHAR(200),
    person_of_contact_phone_number VARCHAR(15),
    mail VARCHAR(100),
    referenced_by VARCHAR(200),
    doctor VARCHAR(200),
    comments TEXT,
    address VARCHAR(255),
    locality VARCHAR(100),
    postal_code VARCHAR(10),
    city VARCHAR(25),
    mutual VARCHAR(50)
);
CREATE UNIQUE INDEX idx_patient_id on patient(id);

-- Table : Health
CREATE TABLE IF NOT EXISTS health (
    id SERIAL PRIMARY KEY,
    id_patient INT,
    group_type VARCHAR(50) ,
    diabete_type VARCHAR(50) ,
    date_consultation DATE DEFAULT CURRENT_DATE,
    is_with_heart_disorder BOOLEAN,
    is_with_bleeding_disorder BOOLEAN,
    is_with_thyroid_disorder BOOLEAN,
    has_hip_prothesis BOOLEAN,
    has_knee_prothesis BOOLEAN,
    has_recent_diseases BOOLEAN,
    has_recent_operation BOOLEAN,
    allergies TEXT,
    drugs TEXT,
    skin TEXT,
    feet TEXT,
    sweating TEXT,
    footnotes TEXT,
    circulation TEXT,
    dermatosis TEXT,
    foot_deformity TEXT,
    nail_disease TEXT,
    shoes_condition TEXT,
    cares TEXT,
    products_used TEXT,
    materials_used TEXT,
    possible_injuries TEXT,
    advice_given TEXT,
    FOREIGN KEY (id_patient) REFERENCES Patient(id) ON DELETE CASCADE
);

-- Table: User
CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    firstname VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

