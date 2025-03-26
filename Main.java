import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            InterfaceClient systeme = new SystemeReservation();

           
            Vaisseau vogueMerry = new Vaisseau("001", "Vogue Merry", 3);
            Vaisseau oroJackson = new Vaisseau("002", "Oro Jackson", 20);
            ((SystemeReservation) systeme).ajouterVaisseau(vogueMerry);
            ((SystemeReservation) systeme).ajouterVaisseau(oroJackson);

            
            Officier luffy = new Officier("Monkey D.", "Luffy", "C001", "Capitaine", "Roi des Pirates");
            Officier goldRoger = new Officier("Gol D.", "Roger", "C002", "Capitaine", "Roi des Pirates Légendaire");
            Passager chopper = new Passager("Tony Tony", "Chopper", "P001", "Île de Drum", "Médecin de bord");
            systeme.ajouterPersonne(luffy);
            systeme.ajouterPersonne(goldRoger);
            systeme.ajouterPersonne(chopper);

           
            Mission missionVogueMerry = new Mission("M001", Date.valueOf("2025-04-01"), Date.valueOf("2025-04-10"),
                    "Recherche du One Piece", "Grand Line", vogueMerry);
            Mission missionOroJackson = new Mission("M002", Date.valueOf("1980-01-01"), Date.valueOf("1980-01-15"),
                    "Découverte de Laugh Tale", "Laugh Tale", oroJackson);
            ((SystemeReservation) systeme).creerMission(missionVogueMerry);
            ((SystemeReservation) systeme).creerMission(missionOroJackson);

            systeme.reserverMission("R001", luffy, missionVogueMerry);
            systeme.reserverMission("R003", goldRoger, missionOroJackson);

            
            while (true) {
                System.out.println("\n1. Réserver une mission");
                System.out.println("2. Voir les capitaines");
                System.out.println("3. Quitter");
                System.out.print("Choix : ");
                String choix = scanner.nextLine();

                if (choix.equals("1")) {
                    systeme.proposerReservations(scanner, chopper);
                } else if (choix.equals("2")) {
                    systeme.listerCapitaines();
                } else if (choix.equals("3")) {
                    break;
                } else {
                    System.out.println("Choix invalide.");
                }
            }

            // Sauvegarde des données
            systeme.sauvegarderDonnees("onepiece_reservations.dat");
            System.out.println("Données sauvegardées !");

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}