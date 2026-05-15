
INSERT INTO patient (nom, prenom, email, telephone, date_naissance) VALUES 
('Dupont', 'Jean', 'jean.dupont@email.com', '0123456789', '1985-05-15'),
('Martin', 'Alice', 'alice.martin@email.com', '0987654321', '1992-10-20');


INSERT INTO medecin (nom, specialite, email, telephone) VALUES 
('Dr. Durand', 'Cardiologue', 'durand@healthcare.com', '0147258369'),
('Dr. Lefebvre', 'Généraliste', 'lefebvre@healthcare.com', '0369258147');



INSERT INTO dossier_medical (diagnostic, observations, date_creation, patient_id) VALUES 
('Hypertension légère', 'Suivi régulier recommandé', NOW(), 1),
('Bilan de santé annuel', 'Tout est normal', NOW(), 2);


INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id) VALUES 
('2024-06-01 10:00:00', 'CONFIRME', 1, 1),
('2024-06-05 14:30:00', 'PLANIFIE', 2, 2);
