CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    email VARCHAR(255),
    telephone VARCHAR(255),
    date_naissance DATE
);

CREATE TABLE medecin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    specialite VARCHAR(255),
    email VARCHAR(255),
    telephone VARCHAR(255)
);

CREATE TABLE dossier_medical (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diagnostic TEXT,
    observations TEXT,
    date_creation DATETIME,
    patient_id BIGINT UNIQUE,
    CONSTRAINT fk_dossier_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE rendez_vous (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_rendez_vous DATETIME,
    statut VARCHAR(255),
    patient_id BIGINT,
    medecin_id BIGINT,
    CONSTRAINT fk_rdv_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_rdv_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);
