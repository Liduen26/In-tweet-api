# in'tweet

## Technologies
- JavaScript
- Java
- Docker
- Sonar

## Contributeurs
- Raphaël Orieux
- Jean-Baptiste Daulaus
- Amaury Pinheiro

## Fonctionnalités du projet
- Écrire, voir et répondre à des in'tweet
- Modération :
  - Suppression des in'tweet
  - Bannissement des utilisateurs

## Déploiement en production
Le projet est déployé à l'aide de Docker et Kubernetes.  
À compléter : le process KUBE

## Tests effectués
- **Sécurité** :
  - Protection contre l'injection SQL
  - Tests de résistance aux attaques de brute force
- **Fonctionnel** :  
  À compléter
- **Intégration** :  
  - Test d'initialisation : vérifie que le projet se lance correctement en s'assurant que le titre de la page est **"Projet react"**.
  - Test de formulaire de login :
    - Envoie le formulaire de login sans credentials.
    - Vérifie que le front renvoie bien deux erreurs :
      - **"Le username ne peut pas être vide"**
      - **"Le password ne peut pas être vide"**

## Description du pipeline DevOps
Le projet utilise un pipeline CI/CD pour automatiser les phases de build, test et déploiement.  
À compléter : détails du pipeline CI/CD (S/O Raphaël).

## Gestion des tickets
La gestion des tickets a été effectuée à l'aide de Trello, permettant le suivi des tâches et la répartition du travail entre les contributeurs.
