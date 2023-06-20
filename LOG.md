# 07 Juin

## Répartition des tâches:
- Albin et Romain: Réalisation du diagramme d'UI
- Axel et Brice: Réalisation du diagramme de classe
- Emin: Réalisation du diagramme de séquence Client-Serveur
- Rémi: Amélioration de la génération procédurale
- Alexandre: Mise en place du Git
- Albin et Emin: Introuction automates et prototypes
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
- 

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
- Axel et Emin: Premier tests automates
- Brice : Sprite Loader
- Romain: Deplacement du joueur dans le modéle (Début de liaison à l'automate) + SoundManager
- Rémi: Fixer bug vagues
<br>
<br>

# 14 Juin

- Albin: Ajout de l'interface de commandes pour J1 et J2, Création de la - - barre de points de vie, Ajout de Sprites
- Romain: Multi Buffering de touche (On peut appuyer sur plusieurs touche à la fois) + Déplacement du bateau
- Rémi: Otimisation de la vue (affichage uniquement des tiles dans le viewport) et ajout getter pour tiles sous une certaine position
# 15 Juin

- Albin: Conception d'un modèle de collision entre une arme et une entité (hit box en cône), Implémentation CrabLair (Avatar et génération des entités), Ajout de Sprites
- Romain: Transition de Bateau à Joueur, application du déplacement 2 joueurs, activation de la minimap
- Brice: Equation de déplacement à deux joueurs 
- Rémi: Amélioration de la génération des troncons (ajout de tuile de transition entre la terre et le sable) et ajout de getter pratique pour des informations sur le modèle de la map
# 16 Juin

- Albin: Implémentation Tree, avatar, génération des entités dans GameModele, Conception et implémentation Cloud, Fix d'affichage comme alignement des sprites sur les cases de la map, des animations des personnages, ..., Ajout de Sprites
- Romain:  Générateur D'automate pour chaque entité (Permet de changer les automates de nos entités facilement via ConfigFile) + Génération des entités sur la carte (SpawnerCrab, Crab) + Début de leurs avatars.
- Rémi: Fix "gros bug" concernant la positions sur la map (bug découvert la veille). 


# 17 Juin (Samedi)

# 18 Juin (Dimanche)
- Rémi: La map est maintenant cylindrique et implémentation de la génération des troncons du crab King et du kraken

# 19 Juin

- Albin: Tentative d'implémenter un GIF sur le menu, non fructueuse, les joueurs passent désormais derrière les palmiers si ils sont censés le faire (et autres entités), implémentation Avatar trésor de la mer, bateau ennemi Ajout de Sprites
- Romain: Liaison de l'interface graphique de changement d'avatar avec le fichier de config de chargement des automates. Ajout Avatar: Cloud, Ship, SeaTreasure
- Rémi: Ajout de la minimap. Ajout de nouvelle tiles pour faire spawner les entitées. Ajout d'un titre lorsque l'on change de section


# 20 Juin

