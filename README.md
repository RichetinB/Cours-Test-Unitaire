# Book Management API

Projet Spring Boot - TP Tests unitaires / intégration / composant

---

## 🚀 Stack

- Java 21
- Kotlin
- Spring Boot 3.1.3
- PostgreSQL (Testcontainers en test)
- Liquibase
- Kotest
- MockK
- Cucumber
- RestAssured

---

## ▶️ Lancer le projet

### 1. Prérequis

- Java 21
- Docker installé (pour les tests BDD / integration DB)

---

### 🐳 Mise en place docker 

Il faut exécuter le lancement du docker-compose de l'application avec la commande 

```
docker compose up -d
```

### 2. Lancer l’application

```bash
./gradlew bootRun
```

ou en lançant le run de BookManagementApplication

### L’API sera disponible sur : 
```
http://localhost:8080/books
```

### 🧪 Lancer les tests
```
./gradlew test
```

ou en lançant tous les test depuis le dossier

### 📌 Fonctionnalités
- Ajouter un livre
- Lister les livres
- Réserver un livre
- Empêcher double réservation

### 🗄️ Base de données
- PostgreSQL
- Gestion via Liquibase
- Tests avec Testcontainers

### ⚠️ Java version

Projet configuré pour :

```
Java 21
```

## 📌 API Endpoints

### GET /books
Retourne tous les livres

### POST /books
Ajoute un livre

### POST /books/{id}/reserve
Réserve un livre

### Documentation Swagger disponible :

```
http://localhost:8080/swagger-ui/index.html
```

Les tests d’intégration utilisent Testcontainers (PostgreSQL via Docker).
