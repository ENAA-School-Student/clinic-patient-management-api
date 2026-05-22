CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    user_type VARCHAR(50),
    nom VARCHAR(255),
    prenom VARCHAR(255),
    telephone VARCHAR(255),
    date_naissance DATE,
    specialite VARCHAR(255)
);

CREATE TABLE dossier_medical (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diagnostic TEXT,
    observations TEXT,
    date_creation DATETIME,
    patient_id BIGINT UNIQUE,
    CONSTRAINT fk_dossier_patient FOREIGN KEY (patient_id) REFERENCES users(id)
);

CREATE TABLE rendez_vous (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_rendez_vous DATETIME,
    statut VARCHAR(255),
    patient_id BIGINT,
    medecin_id BIGINT,
    CONSTRAINT fk_rdv_patient FOREIGN KEY (patient_id) REFERENCES users(id),
    CONSTRAINT fk_rdv_medecin FOREIGN KEY (medecin_id) REFERENCES users(id)
);
