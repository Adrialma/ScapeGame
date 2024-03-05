***Projet Adrialma ScapeGame***

**Description**

Ce projet implémente un jeu d'évasion en ligne où les joueurs résolvent des énigmes pour progresser. Le backend est construit en Java, utilisant des Servlets pour gérer la logique côté serveur et communiquer avec une base de données pour stocker les informations des utilisateurs, des parties jouées et des énigmes.

**Structure des Packages**

**com.adrialma.controller**

Ce package contient les servlets qui gèrent la logique de contrôle du jeu.

**Enigme.java** : Gère les requêtes pour jouer une énigme, incluant la vérification des réponses et la progression dans le jeu.

**GameOver.java** : Traite la fin du jeu, nettoie la session et redirige vers la page de fin.

**HomePage.java** : Affiche la page d'accueil et initialise une nouvelle partie ou reprend une existante.

**Login.java** : Gère l'authentification des utilisateurs.

**LogOut.java** : Permet aux utilisateurs de se déconnecter et nettoie la session.

**OutOfService.java** : Affiche une page d'erreur lorsque le service n'est pas disponible, par exemple, si la base de données n'est pas accessible.

**Register.java** : Gère l'enregistrement de nouveaux utilisateurs.

**com.adrialma.dao**

Ce package inclut les classes pour interagir avec la base de données.

**Crudable.java** : Interface définissant les opérations CRUD génériques.

**DaoBd.java** : Gère la connexion à la base de données.

**GameDAO.java** : Opérations spécifiques à la gestion des parties.

**PuzzleDAO.java** : Opérations spécifiques à la gestion des énigmes.

**UserDAO.java** : Opérations spécifiques à la gestion des utilisateurs.

**com.adrialma.form**

Ce package contient des classes pour la validation des formulaires.

**LoginForm.java** : Valide les données de connexion.

**RegisterForm.java** : Valide les données d'enregistrement des utilisateurs.

**com.adrialma.model**

Ce package définit les modèles de données du jeu.

**Game.java** : Représente une partie, incluant les énigmes et le score.

**Puzzle.java** : Représente une énigme du jeu.

**User.java** : Représente un utilisateur du système.

**com.adrialma.service**

Ce package fournit des services auxiliaires.

**VerificationService.java** : Offre des services comme la vérification de la connexion à la base de données.

**WEB-INF**

Contient les fichiers JSP et la configuration web du projet.

**HomePage.jsp** : Page d'accueil du jeu avec sélection du niveau.

**Login.jsp** : Page de connexion.

**OutOfService.jsp** : Page affichée quand le service est indisponible.

**Register.jsp** : Page d'enregistrement des nouveaux utilisateurs.

**RegisterOK.jsp** : Page de confirmation d'enregistrement réussi.
