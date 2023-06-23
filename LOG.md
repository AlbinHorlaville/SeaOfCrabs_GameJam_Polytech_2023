# 07 Juin

## Répartition des tâches:
- Albin et Romain: Réalisation du diagramme d'UI
- Axel et Brice: Réalisation du diagramme de classe
- Emin: Réalisation du diagramme de séquence Client-Serveur
- Rémi: Amélioration de la génération procédurale
- Alexandre: Mise en place du Git
- Albin et Emin: Introduction automates et prototypes
<br>
<br>


### Albin et Romain :
- Diagramme d'UI: Terminé


### Romain :
- Ecriture du Proposal.md: Terminé

### Axel et Brice :
- Diagramme de classe: Terminé
- Questions: Sous-classes player_terre/mer ?

### Emin :
- Diagramme de séquence: Terminé
- Prise en main des automates


### Rémi :
- Amélioration de la génération procédurale.

### Alexandre :
- Initialisation du git: Terminé

### Albin et Emin :
- Réalisation prototype automate: Terminé
<br>
<br>

## Questions du jour :
<br>
<br>

# 08 Juin

## Répartition des tâches:
- Groupe: Redéfinition du jeu
- Albin et Emin: Travail de bonne prise en main des automates
- Alexandre, Axel et Romain : Conception/Structuration du modèle MVC
- Rémi: Prototype forme cylindrique de la map, Prototype projectile 2d isométrique
- Brice: Apprentissage 2d isométrique
<br>
<br>


### Groupe :
- Passage à 2 joueurs : voir Proposal.md : Terminé

### Albin et Emin :
- Compréhension des automates: AST, méthodes et classes

### Alexandre, Axel et Romain :
- Structuration du modèle MVC Terminé (voir diagramme)

### Rémi :
- Prototype projectile 2d isométrique : Terminé
- Prototype forme cylindrique de la map : Détection des bords mais mauvais passage de l'autre extrémité

### Brice :
- Compréhension mathématique de la 2d isométrique: Matrice de passage

### Albin :
- Sprites pirates, tentacule, crablair,palmier, nuage, coffre, épee, boulet de canon: 
<br>
<br>

## Questions du jour : 
<br>
<br>

# 09 Juin: Début du réel développement

## Répartition des tâches:

- Albin et Alexandre: Toolkit UI/Vue du menu
- Axel et Emin: Travail Automates et Visitor
- Brice et Romain: Base du modèle du jeu
- Rémi: Implémentation de la génération procédurale
<br>
<br>


### Albin et Alexandre :
- Toolkit UI/Vue du menu : Checker, Bouton, Scrollbar

### Axel et Emin :
- Compréhension du parcours du visitor dans l'AST et création du diagramme de classes pour les automates

### Brice et Romain :
- Base du modèle du jeu: Création des classes Entities, MoveableEntities StillEntities et des sous-classes.

### Rémi :
- Implémentation de la génération procédurale: Terminé

### Albin :
- Sprites pirates, tentacule, crablair,palmier, nuage, coffre, épee, boulet de canon: Terminé
<br>
<br>


## Questions du jour :
<br>
<br>

# 12 Juin

## Répartition des tâches:

- Albin et Alexandre: Toolkit UI/Vue du menu
- Albin: Animation du pirate
- Axel et Emin: Travail Automates et Visitor
- Brice et Romain: Base du modèle du jeu et 2eme version du Proposal.md
- Rémi: Génération procédurale des vagues et lissage de celles-ci
<br>
<br>


### Albin et Alexandre :
- Toolkit UI/Vue du menu : 

### Albin :
- Animation Pirate: de dos, de face: Terminé
  
### Axel et Emin :
- Afficher l'automate créé à partir du parser

### Brice et Romain :
- Base du modèle du jeu: Finalisation des déclarations des sous-classes de Entities.
-  2ème version du Proposal.md: Terminé



### Rémi :
- Génération procédurale des vagues et lissage de celles-ci: Terminé
<br>
<br>


## Questions du jour :
- Gestion des objets non utilisés: méthode valid? 
- Faut-il gérer une liste de toutes les entités du jeu
<br>
<br>


# 13 Juin
- Albin et Alexandre: Toolkit UI/Vue du menu : Finalisation écran de démarrage et implémentation tableau des scores
- Axel et Emin: Faire marcher le cowBoy en utilisant un automate (.gal) + Merger les automates
- Brice : Sprite Loader
- Romain: Deplacement du joueur dans le modéle (Début de liaison à l'automate) + SoundManager
- Rémi: Fixer bug vagues
<br>
<br>

# 14 Juin

- Albin: Ajout de l'interface de commandes pour J1 et J2, Création de la - - barre de points de vie, Ajout de Sprites
- Romain: Multi Buffering de touche (On peut appuyer sur plusieurs touche à la fois) + Déplacement du bateau
- Rémi: Otimisation de la vue (affichage uniquement des tiles dans le viewport) et ajout getter pour tiles sous une certaine position
- Alexandre: création de la vue d'avant jeu (selection des armes, selection de la graine...) + création des composants nécessaires (input, box)
- Axel : Correction des fonctions pour le parser d'automate + Implémenations des classes des entités faisant partie du décors
- Emin : Début d'implémentation des fonctions du modéle pour intéractions automates - entités (test avec joueur sur terre)


# 15 Juin

- Albin: Conception d'un modèle de collision entre une arme et une entité (hit box en cône), Implémentation CrabLair (Avatar et génération des entités), Ajout de Sprites
- Romain: Transition de Bateau à Joueur, application du déplacement 2 joueurs, activation de la minimap
- Brice: Equation de déplacement à deux joueurs 
- Rémi: Amélioration de la génération des troncons (ajout de tuile de transition entre la terre et le sable) et ajout de getter pratique pour des informations sur le modèle de la map
- Alexandre : création de composant pour la vue de jeu, barre de vie et timer
- Axel : Liaison des classes qui permettent l'interaction des joueurs avec le décors au niveau du modèle + Ajout des fonctions non implementées dans l'automate (Closest, GotStuff, GotPower, Cell ...)
- Emin : Fin d'implémentation de l'automate joueur + début conception et implémentation de l'automate bateau

# 16 Juin

- Albin: Implémentation Tree, avatar, génération des entités dans GameModele, Conception et implémentation Cloud, Fix d'affichage comme alignement des sprites sur les cases de la map, des animations des personnages, ..., Ajout de Sprites
- Romain:  Générateur D'automate pour chaque entité (Permet de changer les automates de nos entités facilement via ConfigFile) + Génération des entités sur la carte (SpawnerCrab, Crab) + Début de leurs avatars.
- Rémi: Fix "gros bug" concernant la positions sur la map (bug découvert la veille). 
- Alexandre: création de la vue de config des automates
- Axel : Début du travail sur les crabes et les cavernes à crabes. Rendre le Crab lair's fonctionnel avant de gérer les crabes.
- Emin : Fin d'implémentation du bateau avec automate + debug de colision pirate - environnement pour l'empêcher de marcher sur l'eau ou sur le décors

# 17 Juin (Samedi)

# 18 Juin (Dimanche)
- Rémi: La map est maintenant cylindrique et implémentation de la génération des troncons du crab King et du kraken
- Alexandre: avancement de la vue de config des automates
- Emin : Fix colision pirate - environnement grâce au fix de positions sur la map de Rémi

# 19 Juin

- Albin: Tentative d'implémenter un GIF sur le menu, non fructueuse, les joueurs passent désormais derrière les palmiers si ils sont censés le faire (et autres entités), implémentation Avatar trésor de la mer, bateau ennemi Ajout de Sprites
- Romain: Liaison de l'interface graphique de changement d'avatar avec le fichier de config de chargement des automates. Ajout Avatar: Cloud, Ship, SeaTreasure
- Rémi: Ajout de la minimap. Ajout de nouvelle tiles pour faire spawner les entitées. Ajout d'un titre lorsque l'on change de section
- Alexandre: création de la vue d'entrée sur le jeu. Le joueur doit entrer son nom d'utilisateur + début création de la base de données pour enregistrer les utilisateurs et leur score
- Axel : Fonction de déplacement vers le joueur pour les ennemis crabes et de débloquages lorsqyue le crabe est bloqué. + Essai de faire bouger les crabes vers le joueur sans qu'il se regroupe (C'est une échec).
- Emin : Implémentation de combat terrestre + tirer des boulets de canon avec le bateau (sans dégats)

# 20 Juin

- Alexandre: développement d'une classe DAO (singleton) pour la base de données
- Rémi: Fonction move des bateaux ennemie et amélioration de la génération des troncons (ajout des tiles de spawn des ennemie)
- Romain: Création du Rhum + Réinitialisation de la partie + Mise à jour de la Base de données + 
- Albin: Fix du problème de perspective entre les joueurs et les palmiers, implémentation des Bonus, Modèle du nuage, Vue de l'épée et animation de celle-ci dans les 4 directions
- Axel : Implèmentation d'une version stable des crabes et merge de celle-ci + Fix de la barre de vie + Début du travail sur les trésors
- Emin: Fix bug CurrentModificationException + Création du premier boss CrabKing

# 21 Juin
- Axel: Ajout des croix rouges fonctionnels, des trésors fonctionnels (sur terre et en mer), des Bonus qui peuvent maintenant être utilisé en jeu par le joueur.
- Rémi: Réalisation de l'attaque utilisé par les bateau ennemies ainsi que le kraken. Et réalisation des fonctions des automates de ship et krakenTentacle. 
- Emin: Fin d'implémentation du CrabKing + fix bugs crabs et CrabKing
- Albin: Jonction Vue modèle de l'épée pour 1 joueur, corrections hitbox. Debug Nuages.


 # 22 Juin
 - Rémi: Réalisation de la fonction die du kraken (amenant sur la vue de victoire). Résolution de multiple léger problème (Titre de section mal affiché lorsque l'ont revenait en arrière, l'offset des vagues est peut maintenant être propre au différente section, la génération de l'ile du crab King est plus propre)
 - Albin: Inventaire des boulets de canon, fix de l'épée dans le mode 2 joueurs, équilibrage hit-box épée et palmier, Sprites
