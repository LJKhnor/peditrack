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
    health_group INT CHECK (health_group IN (0, 1, 2, 3)),
    diabetes INT CHECK (diabetes IN (1, 2)),
    date_consultation DATE DEFAULT CURRENT_DATE,
    is_with_heart_problems BOOLEAN,
    is_with_bleeding_disorder BOOLEAN,
    is_with_thyroid_disorder BOOLEAN,
    has_knee_prothesis BOOLEAN,
    has_hip_prothesis BOOLEAN,
    has_recent_diseases BOOLEAN,
    has_recent_operation BOOLEAN,
    allergies TEXT[],
    medicines TEXT[],
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
    id_patient INT,
    FOREIGN KEY (id_patient) REFERENCES Patient(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX idx_health_patient on health(id_patient);
CREATE UNIQUE INDEX idx_health_date on health(date_consultation);

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

