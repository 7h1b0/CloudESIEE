# CloudESIEE

### Consignes
Projet de l'unité SI-4201C à l'ESIEE Paris.
Cadre du projet :
* Sujet libre
* Utiliser les notions vues en cours

# One Twenty
### Description 
One twenty est une application web sous forme de jeu de hasard. Chaque utilisateur propose
un nombre entre 1 et 20. Pour gagner l'utilisateur doit évaluer la moyenne des propositions
faites avant lui.

![Screenshot](https://raw.github.com/7h1b0/CloudESIEE/master/accueil.png "Index")

### Fonctionnement
L'utilisateur arrive sur la page d'accueil contenant un formulaire. Il entre son pseudo et accède au jeu.
Il entre alors des nombres entre 1 et 20. Si sa proposition est égal à la moyenne des propositions faites avant lui, il gagne et son pseudo est ajouté à la liste des gagnants.

![Screenshot](https://raw.github.com/7h1b0/CloudESIEE/master/game.png "Game")

### Caractéristiques
* 3 Bases de données object ( Proposition, Gagnant et Moyenne )
* 4 Files de taches ( Add, Restore, Calcul et Init ) 
* Mémoire cache ( Contenant le score du joueur )
* Multi-tenancy
* Servlet - JSP
