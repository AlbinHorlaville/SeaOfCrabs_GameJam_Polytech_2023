# game-jam-2023

## Description
This project is a three-week game jam carried out by a team of 7 ~~students~~ **GOATS** in their 3rd year of engineering school at Polytech Grenoble. To change an Automata, go to Settings from the main menu, then click on Automata in the bottom right corner.

## Table of contents (M. Périn)

- [Lancement du projet](LANCEMENT.md)

- [Fonctionnement](FONCTIONNEMENT.md)

- [Planning](PLANNING.md)

- [Description du projet](PROJET.org)

- [Exigences des enseignants](EXIGENCES.org)

- [GAL : Langage de description des automates](gal/)

- [Conseils et F.A.Q](faq/)

- Le code du parser `NE PAS LE MODIFIER` = info3.game.automata/

## Visuals

## Installation

## Usage

## Diagram of branches
```mermaid
%%{init: { 'logLevel': 'debug', 'theme': 'base', 'gitGraph': {'showBranches': true}} }%%
gitGraph
   commit id:"c0"
   commit id:"c1"
   branch dev
   checkout dev
   commit id:"c2"
   branch username.featureName
   commit tag:"Adding a new feature" id:"c3"
   checkout dev
   branch dev_backup
   checkout username.featureName
   commit id:"c4"
   checkout dev
   merge username.featureName tag:"Merge request required"
   checkout main
   merge dev tag:"version 1"
   checkout dev
   commit id:"c5"
   branch username.bugBugName
   commit tag:"Fixing bug" id:"c6"
   commit id:"c7"
   checkout dev
   merge username.bugBugName tag:"Merge request required"
   commit id:"c8"
   checkout dev_backup
   merge dev
   checkout main
   merge dev tag:"version 2"
   commit id:"c9"
   
```
## Support
- Subject : https://docs.google.com/document/d/1w6SYsfEvb1wG3l1srZwv-_pa3St1dWhl15AsuIeGE5Q/edit?usp=sharing
- Programming rules : https://docs.google.com/document/d/1nYG2XREdBI_83_6ZF_3v8mkZfkId4XAePgkKXEKUcUA/edit?usp=sharing

## Contact
- Project manager : romain.miras@etu.univ-grenoble-alpes.fr
- Git manager : alexandre.arle@etu.univ-grenoble-alpes.fr

## Authors
- Romain MIRAS @mirasr (project manager)
- Alexandre ARLE @arlea (git manager)
- Rémi DEL MEDICO @delmedir
- Axel COLE @coleax
- Emin GUNDOGAN @gundogae
- Albin HORLAVILLE @horlavia
- Brice DECURNINGE @decurnib 
