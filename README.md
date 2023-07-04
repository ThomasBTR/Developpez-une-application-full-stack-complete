# MDD - Monde de dev *by Orion*

Version : 1.0.0

## Description
Ce projet est un réseau social dédié aux développeurs. Le but est de pouvoir partager des articles liées à des thématiques
du monde du développement logiciel.


## Fonctionnalités
La version comprend le MVP (Minimum Viable Product) du projet. Il est possible de :
 - créer un compte,
 - se connecter,
 - Accéder aux articles d'un thème donné,
 - Trier les articles :
   - par date de création,
   - par auteur,
   - par thèmes,
 - créer des articles,
 - consulter les articles,
 - commenter des articles.

## Technologies embarquées

### Front
 - Node 16.20.0
 - npm 8.19.4
 - Angular 15.2.1
 - Angular Material
 - Tailwind CSS

### Back
 - Java 11
 - Spring Boot 2.7.9
 - Spring Security
 - Spring Data JPA
 - Spring Web
 - Spring Validation
 - Open API 3.0.3
 - OpenApi Generator 6.6.0
 - MapStruct 1.5.3
 - Lombok
 - Database : MySql 5.7
 - Docker & Docker Compose pour lancer le serveur MySql

## Installation

Pour lancer le projet en local, il faut :
 - Lancer la base de données à partir du docker-compose localiser dans les ressources du projet :
    Depuis la racine du projet, lancer la commande :
```bash
cd resources/docker
```
puis

```bash
docker-compose up -d
```
    
 - lancer ensuite le script `script.sql` dans le dossier `resources/sql` du projet.
 - Ajoutez les variables d'environnements externalisées à vos variables d'environnement (ou dans votre IDE).
 Ces variables sont présentent dans le fichier `mdd-properties-prod` situé dans les ressources du projet.
 - A partir de votre IDE, lancer l'installation du projet ou en invite de commande :
````bash
 mvn clean install
 ````

 - Pour lancer l'installation du front, il vous suffit d'aller dans le répertoire front du projet et de lancer la commande :
```bash
npm install
```

## Lancer le projet


Pour lancer le projet back, il vous suffit de lancer la commande :
````bash
 mvn spring-boot:run
 ````

Pour lancer le projet front, il vous suffit de lancer la commande :
````bash
 npm start
 ````

## Découpage du projet

## Partie Front

Le projet est découpé selon les recommandations d'angular en composants, services, modules et interfaces.

Vous trouverez dans ce projet : 
- un module `app` qui contient les composants et services de l'application.
- un module `guard` qui contient les composants et services de sécurisation et redirection de l'application.
- un module `auth` qui contient les composants et services liés à l'authentification.
- un module `article` qui contient les composants et services liés aux articles.
- un module `user` qui contient les composants et services liés aux utilisateurs.
- un module `theme` qui contient les composants et services liés aux thèmes.
- un module `comment` qui contient les composants et services liés aux commentaires.

## Partie Back

Le projet est découpé selon les recommandations de Spring Boot en couche.
Vous trouverez un découpage en packages, services, repositories et controllers.

## Tests

Le projet étant un MVP, aucun test n'ont été réalisé à date à part des tests bouts en bouts manuels.


## Auteur
ThomasBTR 