# PROPOSITION DE JEU - Groupe Enseignants

# PacMan

### Phase de jeu classique : `jeu d'action`
- un labyrinthe sur une `planète torique` comme dans le célèrre jeu PacMan
- `Viewport` : tout le labyrinthe à l'écran
- des fantômes, des pacgums 

#### Objectif    
- rammasser toutes les pacgums pour passer à la phase Bonus

#### Jeu
- ne pas se faire toucher par les fantômes
- 3 vies  
- les grosses PacGum rendent les fantômes vulnérables

#### Originalité       
- Le comportement des fantômes est défini par des automates depuis un fichier `.gal` contenant :
  *RedGhot, GreenGhost, PinkGhost, YellowGhost, BlueGhost, PanicGhost*
- <strike>Chaque grosse PacGum</strike> **chaque fantôme dévoré** donne droit à un Bot pour la phase Bonus

#### `TODO` définir les actions Pop et Wizz
##### pour les fantômes 
+ `MP>` Pop : accélération pendant un pas
+ `MP>` Wizz: destruction d'un mur
+ `MP>` Egg: reproduction s'il est dans l'enceinte centrale  

##### pour PacMan
+ `MP>` Pop: parraliser le fantôme de la case adjacente (temps limité)
+ `MP>` Wizz: se cacher dans le sol (temps limité)
  
### Phase bonus : principalement un `jeu de stratégie`     
- `temps limité`
- un monde de `taille illimité`, `généré automatiquement` au fur et à mesure des déplacements 
- des obstacles, **des trous**, des fantômes 
- `Viewport` centré sur PacMan : PacMan reste au centre de l'écran et c'est le décor qui glisse en dessous


#### Objectif 
- Rammasser le plus de pacgums possibles grâce aux bots pour marquer des points
  + `OG>` Est-ce que PacMan ramasse aussi des pacgums ?
  + `MP>` oui, ça me semble bien. 

#### Jeu et Originalité 
- Avant de lancer l'action, le joueur attribue un automate (chargé en début de jeu depuis un fichier) à chaque bot amassés dans la phase 1
<BLOCKQUOTE>
 Il faut prévoir une interface graphique agréable. Par exemple un bandeau en bas de page avec, pour chaque bot amassé, un bouton de sélection de l'automate parmi ceux chargé en mémoire depuis un fichier .gal
</BLOCKQUOTE>

- Ensuite PacMan se déplace pour déposer chaque bot à l'endroit de son choix.
- Chaque Bot démarre aussitôt qu'il est déposé
- Les fantômes n'attaquent ni les bots, ni PacMan, mais mangent des pacgums


#### Idées supplémentaires à l'étude

##### 1. PacMan peut **jeter des sorts** aux fantômes, c'est à dire :
+ leur envoyer un automate emballé dans une pacgum
+ quand le fantôme est touché, son automate est remplacé par celui emballé dans la pacgum

###### QUESTIONS 
- comment choisir les automates de sort ? 
- combien PacMan en a-t'il ?
- est-ce intéressant pour le jeu ? 
  + `MP>` Ça met plus d'action dans la phase Bonus
  + `OG>` Mais PacMan est déjà occupé à bloquer les fantômes

##### 2. Une minimap qui permet de voir tous les bots partis explorers le monde ?

##### 3. La possibilité de prendre temporairement le contrôle d'un Bot en cliquant dessus ?
+ `OG>` dans ce cas que devient Pac Man ? Il reste immobile ?

#### `TODO` définir les actions Pop et Wizz
+ `MP>` Pop  = sortir d'un trou ?
+ `MP>` Wizz = pousser un obstacle pour écraser un fantôme ou boucher un trou ?
   


## Extension expérimentale : un jeu reconfigurable

   MP propose un menu de configuration
   - il permet d'attribuer un automate, une animation, des caractéristiques à chaque entité.
   - on sauve la configuration en sérialisant les données (= transformation de graphes d'objets en un codage binaire qu'on peut sauver dans un fichier et relire pour rétablir le graphe d'objects en mémoire)
   - on peut ainsi recharger la configuration

#### Voici à quoi pourrait ressembler le menu
```
                                                ------------ Pickable 
                                               |   ------- Storable
				               |  |  ----- Throwable
				               |  |  |  -- Jumpable
				               |  |  |  |  - pUshable
	                                       P  S  T  J  U  --- sWappable  
 1 PacMan   [> Player.gal ] [> PacMan.ani   ] [ ][ ][ ][ ][Y][ ]  ...
 6 RedGhost [> GhostR.gal ] [> RedGhost.ani ] [ ][ ][ ][Y][Y][ ]  ...
40 PacGum   [> Idle.gal   ] [> PacGum.ani   ] [Y][Y][Y][ ][ ][ ]  ...
60 Wall     [> Idle.gal   ] [> Wall.ani     ] [ ][ ][ ][ ][ ][ ]  ...
```

L'idée c'est qu'on peut changer totalement le jeu depuis ce menu ; imaginez ce que donnerait le jeu configuré ainsi :
```
				               P  S  T  J  U  W
 1 PacMan   [> Runner.gal ] [> PacMan.ani   ] [Y][Y][Y][ ][ ][ ]  ...
 6 RedGhost [> Player.gal ] [> RedGhost.ani ] [Y][ ][Y][ ][ ][Y]  ...
40 PacGum   [> Ghost.gal  ] [> PacGum.ani   ] [ ][ ][ ][ ][ ][ ]  ...
60 Wall     [> Wave.gal   ] [> Wall.ani     ] [ ][ ][ ][ ][Y][ ]  ...
```

- on jouerait avec 6 fantômes, on pourrait passer de l'un à l'autre (swap)
- on pourrait capturer PacMan et le jeter
- on pourrait pousser les murs
- les pacgums se déplaceraient
- les mures bougeraient par vague



## Extension expérimentale : MineCraft intégral

   MP propose que tous les éléments du jeu soit Pickable, Storable.
   - avec l'action Pick, on peut prendre un mur, prendre un fantôme et les stocker pour les déposer ailleurs (action Throw).
   - Throw = un lancer si l'entité est Throwable, c'est un dépôt sinon.
   - Lorsqu'un bot Pick quelque chose cela tombe dans le sac du joueur.
   
#### interface graphique
    - Un bandeau sur la droite indique combien d'éléments de chaque type (mur, fantôme,...) on a dans son sac.
    - le joueur sélectionne (en couleur 1) la prochaine entité à déposer. Par défaut c'est la dernière entité ramassé.
    - le joueur sélectionne (en couleur 2) ce que les bots déposent



## Extension: Création d'un monde pour tester ses automates

Création d'un monde vide dans lequel on peut placer via la souris des entités.

- Par défaut pas d'automate 
- Touche A + clic droit souris = un entité de catégorie adversaire
- Touche @ + clic droit souris = un entité de la catégorie joueur
- ...

- Touche ... + clic gauche souris = ouverture d'un fenetre pour sélectionner un fichier gal
- Barre espace : start/pause de la simulation



## Extension: les états = les hummeurs des bots

On peut fixer des noms d'états  Angry, Happy, Peaceful, Hunting, ...
Et installer une correspondance entre sprites et états de l'automate.
Dans les états "Hunting" _1 et _2 on affiche un sprite de chasseur à l'affut
et dans les états "Happy" _1 et _2 on affiche un sprite de chasseur satisfait.

```haskell
Automate(Hunting_1){
 * (Hunting_1) ...
 * (HUnting_2) ...
 * (Happy_1) ...
 * (Happy_2) ...
}

```


## Extension: le joueur peut choisir son rôle

À tout moment, par un clic souris sur une entité, le joueur peut jouer avec cette entité.
- On mémorise l'automate courant de l'entité
- On lui affecte l'automate du player

**Que devient l'entité précédente du player ?**
- elle reprend son ancien automate
- si c'était le player, il disparaît du plateau, le joueur pourra le régénérer par un clic sur une position libre du plateau


## Extension: le sac est un monde à part

Quand le joueur contrôle une entité qui est Pickable et qu'il se fait Picked, il se retrouve dans un monde (le sac) où tous les objets stockés prennent vie
"à la toy story". C'est un jeu caché dans le jeu.

Que faut-il y faire ? trouver la sortie du sac et s'entraider pour réussir à atteindre la sortie (~Lemmings ?).
Le jeu extérieur continue et de nouveaux objects picked apparaissent dans le sac.


---
    AUTHOR: Olivier Grüber, Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 



