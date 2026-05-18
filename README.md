
HealthCare - API REST Management System

Description de l'application
HealthCare est une API REST developpee sous Spring Boot 3 visant a numeriser et organiser le systeme medical d'une clinique. Cette application permet de gerer le cycle de vie complet des patients, des medecins (gestion CRUD), de planifier des rendez-vous et d'assurer le suivi via des dossiers medicaux dedies pour chaque patient.

Une couche de securite a ete recemment implementee afin de proteger les donnees sensibles des patients.

Architecture Securite (JWT Stateless)
Ce projet integre une authentification JWT pour proteger les differents endpoints de l'application. Seuls les utilisateurs enregistres et possedant un jeton valide peuvent interagir avec le systeme de sante.

Composants Principaux de Securite :
- Authentification avec JSON Web Tokens (JWT)
- Mode Stateless garantissant l'absence de session serveur locale
- Mots de passe chiffres en base de donnees via BCryptPasswordEncoder
- Filtre personnalise (JwtFilter) agissant en tant que Middleware pour verifier l'integrite du jeton a chaque requete
- Traduction de l'entite metier via CustomUserDetailsService

Endpoints Exposes pour l'Authentification :
L'API expose publiquement (Permit All) les endpoints suivants necessaires a l'obtention du token :
* POST /auth/register : Inscription d'un nouvel utilisateur (nom d'utilisateur, email, mot de passe).
* POST /auth/login : Connexion d'un utilisateur et renvoi du token JWT.

Tout le reste des endpoints (Patients, Medecins, Rendez-vous, Dossiers) necessite d'ajouter le token fourni dans l'entete HTTP (Authorization: Bearer <mon_token>).

Technologies
- Java 21 / Spring Boot 3
- Data : Spring Data JPA, Hibernate, MySQL, Flyway
- Securite : Spring Security, JJWT (JWT API)
- Conception : MapStruct (Mapper), OpenAPI(Swagger) pour la documentation

<img width="1443" height="963" alt="AjouterPatient" src="https://github.com/user-attachments/assets/121365b3-cc6a-4810-a113-8c04b6183cbc" />
<img width="1491" height="963" alt="creerUnDossier" src="https://github.com/user-attachments/assets/1baec891-1d4b-4e8a-95e7-108bd4661702" />
<img width="1407" height="1143" alt="SequenceDiagramAnnuler" src="https://github.com/user-attachments/assets/aebbff43-f8f1-4d31-8834-b344b14829a3" />
<img width="1720" height="2199" alt="UseCaseDiagramHealthCare" src="https://github.com/user-attachments/assets/bbe81336-8b3a-4344-b353-01850cc90c27" />
<img width="1371" height="843" alt="HealthCareClassdiagram" src="https://github.com/user-attachments/assets/b57445d3-f22d-4809-85ac-51ed8ab146bb" />

