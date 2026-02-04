# CampusMaster Backend — Documentation API (FR)

## Démarrage rapide

1. **Base de données MySQL**
   - Créez la base `campusmaster`.
   - Vérifiez `src/main/resources/application.properties` (URL, user, password).
   - Optionnel : exécuter `campusmaster.sql` si vous voulez des tables/données.

2. **Lancer l’application**
   - Java 17 recommandé
   - `mvn spring-boot:run`
   - API : `http://localhost:8080`

3. **Swagger / OpenAPI**
   - UI Swagger : `http://localhost:8080/swagger-ui.html`
   - JSON OpenAPI : `http://localhost:8080/api-docs`

## Authentification (JWT)

### 1) Inscription (publique)
`POST /api/auth/inscription`

Exemple:
```json
{
  "email": "etudiant1@campus.com",
  "motDePasse": "Pass1234!",
  "nomComplet": "Etudiant Test",
  "role": "ETUDIANT"
}
```

> Note: `dateInscription` est maintenant automatiquement renseignée côté backend.

### 2) Connexion (publique)
`POST /api/auth/connexion`

Exemple:
```json
{
  "email": "etudiant1@campus.com",
  "motDePasse": "Pass1234!"
}
```

Réponse:
```json
{
  "token": "JWT_TOKEN",
  "role": "ROLE_ETUDIANT"
}
```

### 3) Appels authentifiés
Ajoutez l’en-tête HTTP:
`Authorization: Bearer <JWT_TOKEN>`

## CORS / Erreur 403 sur le navigateur

Le backend inclut maintenant une configuration CORS ouverte (dev) + autorise les requêtes **OPTIONS**.
Cela corrige généralement les 403 liés au *preflight* lors des appels depuis un front React/Angular/Vue.

En production : remplacez `*` par la liste des domaines autorisés dans `CorsConfig`.

---

## CRUD (endpoints principaux)

Base path: `/api/v1`

### Semestres
- `GET /api/v1/semestres`
- `GET /api/v1/semestres/{id}`
- `POST /api/v1/semestres`
- `PUT /api/v1/semestres/{id}`
- `DELETE /api/v1/semestres/{id}`

### Modules
- `GET /api/v1/modules`
- `GET /api/v1/modules/{id}`
- `POST /api/v1/modules`
- `PUT /api/v1/modules/{id}`
- `DELETE /api/v1/modules/{id}`

### Matières
- `GET /api/v1/matieres`
- `GET /api/v1/matieres/{id}`
- `POST /api/v1/matieres`  (body: `MatiereRequest`)
- `PUT /api/v1/matieres/{id}` (body: `MatiereRequest`)
- `DELETE /api/v1/matieres/{id}`

Body `MatiereRequest`:
```json
{ "libelle": "Maths", "moduleId": 1 }
```

### Cours
- `GET /api/v1/cours`
- `GET /api/v1/cours/{id}`
- `POST /api/v1/cours` (body: `CoursRequest`)
- `PUT /api/v1/cours/{id}` (body: `CoursRequest`)
- `DELETE /api/v1/cours/{id}`

Body `CoursRequest`:
```json
{
  "titre": "Réseaux",
  "description": "Intro TCP/IP",
  "semestreId": 1,
  "enseignantId": 2
}
```

### Devoirs
- `GET /api/v1/devoirs`
- `GET /api/v1/devoirs/{id}`
- `POST /api/v1/devoirs` (body: `DevoirRequest`)
- `PUT /api/v1/devoirs/{id}` (body: `DevoirRequest`)
- `DELETE /api/v1/devoirs/{id}`

### Supports de cours
- `GET /api/v1/supports-cours`
- `GET /api/v1/supports-cours/{id}`
- `POST /api/v1/supports-cours` (body: `SupportCoursRequest`)
- `PUT /api/v1/supports-cours/{id}` (body: `SupportCoursRequest`)
- `DELETE /api/v1/supports-cours/{id}`

### Discussions & Messages
- Discussions:
  - `GET /api/v1/discussions`
  - `GET /api/v1/discussions/{id}`
  - `POST /api/v1/discussions` (body: `DiscussionRequest`)
  - `PUT /api/v1/discussions/{id}` (body: `DiscussionRequest`)
  - `DELETE /api/v1/discussions/{id}`
- Messages de discussion:
  - `GET /api/v1/messages-discussion`
  - `GET /api/v1/messages-discussion/{id}`
  - `POST /api/v1/messages-discussion` (body: `MessageDiscussionRequest`)
  - `PUT /api/v1/messages-discussion/{id}` (body: `MessageDiscussionRequest`)
  - `DELETE /api/v1/messages-discussion/{id}`

### Messagerie privée
- `GET /api/v1/messages-prives`
- `GET /api/v1/messages-prives/{id}`
- `POST /api/v1/messages-prives` (body: `MessagePriveRequest`)
- `PUT /api/v1/messages-prives/{id}` (body: `MessagePriveRequest`)
- `DELETE /api/v1/messages-prives/{id}`

### Notifications
- `GET /api/v1/notifications`
- `GET /api/v1/notifications/{id}`
- `POST /api/v1/notifications` (body: `NotificationRequest`)
- `PUT /api/v1/notifications/{id}` (body: `NotificationRequest`)
- `DELETE /api/v1/notifications/{id}`

### Dépôts & Notes de devoir
- Dépôts:
  - `GET /api/v1/depots-devoir`
  - `GET /api/v1/depots-devoir/{id}`
  - `POST /api/v1/depots-devoir` (body: `DepotDevoirRequest`)
  - `PUT /api/v1/depots-devoir/{id}` (body: `DepotDevoirRequest`)
  - `DELETE /api/v1/depots-devoir/{id}`
- Notes:
  - `GET /api/v1/notes-devoir`
  - `GET /api/v1/notes-devoir/{id}`
  - `POST /api/v1/notes-devoir` (body: `NoteDevoirRequest`)
  - `PUT /api/v1/notes-devoir/{id}` (body: `NoteDevoirRequest`)
  - `DELETE /api/v1/notes-devoir/{id}`

### Utilisateurs (admin / gestion)
- `GET /api/v1/utilisateurs`
- `GET /api/v1/utilisateurs/{id}`
- `PUT /api/v1/utilisateurs/{id}/activer`
- `PUT /api/v1/utilisateurs/{id}/desactiver`
- `DELETE /api/v1/utilisateurs/{id}`

---

## Codes d’erreur

Le backend renvoie maintenant des erreurs JSON standardisées:
```json
{
  "timestamp": "...",
  "status": 400,
  "error": "Bad Request",
  "message": "..."
}
```

- **400**: données invalides / paramètres manquants
- **401/403**: non authentifié / interdit
- **404**: ressource introuvable
- **409**: conflit (contrainte BD)
- **500**: erreur serveur inattendue


## Sécurité (RBAC)

Rôles disponibles : `ADMIN`, `ENSEIGNANT`, `ETUDIANT`.

### Règles d'accès
- Public : `/api/auth/**`, Swagger (`/swagger-ui/**`, `/v3/api-docs/**`)
- ADMIN : `/api/admin/**` et `/api/v1/**` (CRUD brut)
- ENSEIGNANT : `/api/enseignants/**`
- ETUDIANT : `/api/etudiants/**`
- Messagerie : `/api/messagerie/**` (authentifié)

### Endpoints recommandés pour le frontend (basés sur l'utilisateur connecté)
- Étudiant :
  - `GET /api/etudiants/me/cours`
  - `POST /api/etudiants/me/devoirs/{devoirId}/depots?urlFichier=...`
  - `GET /api/etudiants/me/notes`
- Enseignant :
  - `GET /api/enseignants/me/cours`
  - `POST /api/enseignants/me/cours`
  - `POST /api/enseignants/me/cours/{coursId}/devoirs`
  - `POST /api/enseignants/me/depots/{depotId}/noter?note=...&commentaire=...`
- Messagerie :
  - `POST /api/messagerie/me/prive/envoyer?destinataireId=...&contenu=...`
  - `GET /api/messagerie/me/prive/historique?avecUtilisateurId=...`

**Header requis pour les appels protégés**
`Authorization: Bearer <JWT_TOKEN>`
