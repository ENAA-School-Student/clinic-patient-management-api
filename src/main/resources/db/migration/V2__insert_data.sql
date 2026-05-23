INSERT INTO users (username, email, password, role, user_type, nom, prenom, telephone, date_naissance)
VALUES
('jean.dupont', 'jean.dupont@email.com', 'changeme', 'PATIENT', 'PATIENT', 'Dupont', 'Jean', '0123456789', '1985-05-15'),
('alice.martin', 'alice.martin@email.com', 'changeme', 'PATIENT', 'PATIENT', 'Martin', 'Alice', '0987654321', '1992-10-20');

INSERT INTO users (username, email, password, role, user_type, nom, specialite, telephone)
VALUES
('dr.durand', 'durand@healthcare.com', 'changeme', 'MEDECIN', 'MEDECIN', 'Dr. Durand', 'Cardiologue', '0147258369'),
('dr.lefebvre', 'lefebvre@healthcare.com', 'changeme', 'MEDECIN', 'MEDECIN', 'Dr. Lefebvre', 'Generaliste', '0369258147');

INSERT INTO dossier_medical (diagnostic, observations, date_creation, patient_id)
VALUES
('Hypertension legere', 'Suivi regulier recommande', NOW(), 1),
('Bilan de sante annuel', 'Tout est normal', NOW(), 2);

INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id)
VALUES
('2024-06-01 10:00:00', 'CONFIRME', 1, 3),
('2024-06-05 14:30:00', 'PLANIFIE', 2, 4);
