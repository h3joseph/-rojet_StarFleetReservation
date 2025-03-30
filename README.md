projet java initialisation d un systeme de réservation de navire extraterrestre

Dans ce projet on a 2 types de personne les Officiers (ici j ai mis que des capitaine de navire ) et des passagers les deux classe sont des filles de la classe personne
Apres ca on a la classe vaisseau qui est liee avec un attribut de la classe Mission qui est ajouterUneMission() l attribut sert a attribuer un Vaisseau a une mission donnée.
La classe mission initialise les mission est liee liée a la classe resrvation on a un attribut ajouter reservation avec  une condition qui a pour but que le nombre de reservation ne sois pas superieur a la capacite maximale e la classe Vaisseau
la classe reservation qui nous permettra de reserver des mission  avec confirmation et annulationn de la dites reservation

pour administrer toute les class  j ai fais un systeme de reservation qui se base presqu entirement sur une cass que j ai creé qui s appelle interface client qui prend tout mes attribut "de liaison " des attributs qui vont permettre aux clients
de faire des actions sur l interface

au niveau de la classe Systemereservation j ai mis en place une logique qui gere toute les interaction avec des liste qui contiennent les vaisseaux les personne les mission et les reservation. Dans cette classe on peu ajouter des vaisseau avec la methode ajouterVaisseau() et des personne avec ajouterPersonne() qui est une methode qui viens de l interface client. Pour les mission on a creerMission() qui verifie si le vaisseau existe avant de l ajouter sinon ca renvoie une erreur.

Avec les ajout que j ai fais on a maintenant une gestion plus poussee au niveau de la creation des mission. L utilisateur peu maintenant creer lui meme sa mission en rentrant un nom un vaisseau parmis ceux disponible (Vogue Merry ou Oro Jackson) et un capitaine (Luffy ou Gold Roger). Pour rendre ca plus robuste j ai ajouter une gestion des date avec une methode saisirDate() qui oblige l utilisateur a rentrer un format AAAA-MM-JJ et qui verifie que la date est valide. Si le format est pas bon ou si la date existe pas (genre 2025-13-01) ca demande de reessayer jusqu a avoir un truc correct. En plus j ai mis une verif pour que la date de fin sois toujours apres la date de debut sinon ca bloque et ca renvoie au menu.

Pour la reservation l utilisateur peu voir toute les mission dispo avec leur detail (nom vaisseau destination et date) et choisir une mission par un numero. Apres il rentre son nom et prenom pour se faire enregistrer comme passager et la reservation se fait avec un ID unique. Toute les mission cree sont sauver dans un fichier "missions_disponibles.dat" et les reservation dans "reservations.dat" pour garder une trace.

Tout ca reste gerer par l interface client qui fais le lien avec SystemeReservation et qui permet de proposer les action a l utilisateur (creer mission reserver mission quitter) dans une boucle avec un menu clair en francais. Le systeme est plus solide maintenant avec la gestion des erreur sur les date et une interface qui guide mieux l utilisateur.
