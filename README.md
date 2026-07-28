# 1. Nom du projet

** HealthCare – API REST Management System

---

# 2. Présentation du projet

Ce projet est une **API REST** qui permet de numériser et d'organiser le système médical d'une clinique, en gérant patients, médecins, rendez-vous et dossiers médicaux.

Il s'adresse principalement au **personnel médical et administratif d'une clinique** (médecins, secrétariat, administrateurs) ayant besoin d'un accès rapide, organisé et sécurisé aux informations des patients.

Son objectif principal est de **centraliser et sécuriser la gestion du cycle de vie complet des patients et des médecins**, tout en facilitant la planification des rendez-vous et le suivi médical via des dossiers dédiés.

---

# 3. Problématique

Le problème identifié est que **les cliniques gèrent souvent les données des patients, des médecins et des rendez-vous de manière dispersée ou peu sécurisée, ce qui ralentit le travail administratif et expose des données médicales sensibles.**

La solution proposée permet de **centraliser ces informations dans une API unique, protégée par authentification, où chaque donnée n'est accessible qu'aux utilisateurs autorisés et authentifiés.**

---

# 4. Fonctionnalités principales

- Inscrire un nouvel utilisateur et lui attribuer un accès sécurisé
- Authentifier un utilisateur et générer un token JWT
- Gérer le cycle de vie complet des patients (créer, consulter, modifier, supprimer)
- Gérer le cycle de vie complet des médecins (créer, consulter, modifier, supprimer)
- Planifier et suivre les rendez-vous médicaux
- Consulter et mettre à jour les dossiers médicaux de chaque patient

---

# 5. Technologies utilisées

| Technologie | Utilisation dans le projet |
|-------------|----------------------------|
| Java 21 / Spring Boot 3 | Développement du backend et de la logique métier de l'API |
| Spring Data JPA & Hibernate | Gestion de la persistance et des interactions avec la base de données |
| MySQL | Stockage des données (patients, médecins, rendez-vous, dossiers médicaux) |
| Flyway | Gestion des migrations et du versionnement de la base de données |
| Spring Security & JJWT | Authentification et sécurisation des endpoints via JWT |
| MapStruct | Conversion entre les entités et les objets de transfert de données (DTO) |
| OpenAPI (Swagger) | Documentation interactive des endpoints de l'API |

---

# 6. Sécurité — Architecture JWT Stateless

Ce projet intègre une authentification JWT pour protéger les différents endpoints de l'application. Seuls les utilisateurs enregistrés et possédant un jeton valide peuvent interagir avec le système.

**Composants principaux de sécurité :**

- Authentification avec JSON Web Tokens (JWT)
- Mode stateless garantissant l'absence de session serveur locale
- Mots de passe chiffrés en base de données via `BCryptPasswordEncoder`
- Filtre personnalisé (`JwtFilter`) agissant comme middleware pour vérifier l'intégrité du jeton à chaque requête
- Traduction de l'entité métier via `CustomUserDetailsService`

**Endpoints exposés pour l'authentification :**

L'API expose publiquement (Permit All) les endpoints suivants, nécessaires à l'obtention du token :

- `POST /auth/register` : inscription d'un nouvel utilisateur (nom d'utilisateur, email, mot de passe)
- `POST /auth/login` : connexion d'un utilisateur et renvoi du token JWT

Tous les autres endpoints (Patients, Médecins, Rendez-vous, Dossiers) nécessitent d'ajouter le token fourni dans l'en-tête HTTP :

```
Authorization: Bearer <mon_token>
```

---

# 7. Installation et lancement

## 7.1 Prérequis

- Java 21
- Maven (ou le wrapper `mvnw` fourni dans le projet)
- Git
- MySQL
- Un IDE (IntelliJ, VS Code, etc.)

## 7.2 Cloner le dépôt

```bash
git clone https://github.com/ENAA-School-Student/clinic-patient-management-api
```

## 7.3 Ouvrir le dossier

```bash
cd clinic-patient-management-api
```

## 7.4 Installer les dépendances

```bash
./mvnw clean install
```

## 7.5 Variables d'environnement

Créer un fichier `.env` ou configurer `application.properties` avec :

```env
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=
JWT_SECRET=
PORT=
```

## 7.6 Lancer le projet

```bash
./mvnw spring-boot:run
```

## 7.7 Ouvrir le projet

Après le lancement, l'API est disponible à l'adresse :

```
http://localhost:8080
```

La documentation Swagger est disponible à :

```
http://localhost:8080/swagger-ui.html
```

### Point de vigilance

- Tester toutes les commandes avant la démonstration
- Vérifier les chemins et ports utilisés
- Ne jamais publier : mots de passe, clés API, tokens, identifiants réels

---

# 8. Captures d'écran

## Capture 1 — Ajout d'un patient

![Ajout d'un patient](chemin-vers-image.png)

Cette capture montre le processus d'ajout d'un nouveau patient dans le système via l'endpoint dédié.

## Capture 2 — Création d'un dossier médical

![Création d'un dossier médical](chemin-vers-image.png)

Cette capture montre la création d'un dossier médical associé à un patient existant.

## Capture 3 — Diagramme de cas d'utilisation

![Diagramme de cas d'utilisation](chemin-vers-image.png)

Cette capture présente le diagramme de cas d'utilisation (Use Case) du système HealthCare, illustrant les interactions entre les utilisateurs et l'application.

## Capture 4 — Diagramme de classes

![Diagramme de classes](chemin-vers-image.png)

Cette capture présente le diagramme de classes du projet, montrant les entités principales (Patient, Médecin, Rendez-vous, Dossier médical) et leurs relations.

---

# 9. Contribution personnelle

Ma contribution principale a porté sur **_______________________________________________**.

J'ai également travaillé sur **_______________________________________________**.

J'ai été responsable de **_______________________________________________**.

*(Exemple : J'ai développé les endpoints liés aux patients et aux dossiers médicaux, conçu le modèle de données, et implémenté la couche de sécurité JWT incluant le filtre personnalisé et le service d'authentification.)*

---

# 10. Difficultés rencontrées

## Difficulté 1

**Problème rencontré :** _______________________________________________

**Recherches / tests effectués :** _______________________________________________

**Solution apportée :** _______________________________________________

**Ce que j'ai appris :** _______________________________________________

## Difficulté 2

**Problème rencontré :** _______________________________________________

**Recherches / tests effectués :** _______________________________________________

**Solution apportée :** _______________________________________________

**Ce que j'ai appris :** _______________________________________________

---

# 11. Améliorations possibles

Dans une prochaine version, il serait possible de :

- ajouter des tests automatisés (unitaires et d'intégration) ;
- mettre en place une gestion des rôles plus fine (médecin / patient / administrateur) ;
- ajouter un système de notifications pour les rendez-vous ;
- déployer l'application sur un environnement cloud avec Docker.

Ces améliorations permettraient de renforcer la fiabilité, la sécurité et l'accessibilité du système pour un usage en conditions réelles.

---

# ✅ Checklist finale

## Présentation
- [x] Le nom du projet est clair
- [x] Le projet est présenté en 3 à 5 lignes
- [x] Le public cible est identifié
- [x] Le besoin est expliqué
- [x] L'objectif est précisé

## Fonctionnalités
- [x] 3 à 6 fonctionnalités
- [x] Chaque fonctionnalité commence par un verbe
- [x] Elles correspondent à des actions réelles

## Technologies
- [x] Les technologies sont indiquées
- [x] Leur rôle est expliqué

## Installation
- [x] Les prérequis sont présents
- [x] Le dépôt est correct
- [ ] Les commandes ont été testées
- [x] L'adresse locale est indiquée
- [x] Aucune donnée sensible n'est publiée

## Captures
- [x] Quatre captures incluses
- [x] Chaque capture possède un titre
- [ ] Vérifier que les chemins d'image fonctionnent

## Contribution
- [ ] À compléter avec la contribution réelle

## Difficultés
- [ ] À compléter avec les difficultés rencontrées

## Améliorations
- [x] 4 améliorations proposées, réalistes

<img width="1443" height="963" alt="AjouterPatient" src="https://github.com/user-attachments/assets/121365b3-cc6a-4810-a113-8c04b6183cbc" />
<img width="1491" height="963" alt="creerUnDossier" src="https://github.com/user-attachments/assets/1baec891-1d4b-4e8a-95e7-108bd4661702" />
<img width="1407" height="1143" alt="SequenceDiagramAnnuler" src="https://github.com/user-attachments/assets/aebbff43-f8f1-4d31-8834-b344b14829a3" />
<img width="1720" height="2199" alt="UseCaseDiagramHealthCare" src="https://github.com/user-attachments/assets/bbe81336-8b3a-4344-b353-01850cc90c27" />
<img width="1371" height="843" alt="HealthCareClassdiagram" src="https://github.com/user-attachments/assets/b57445d3-f22d-4809-85ac-51ed8ab146bb" />

