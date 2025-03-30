import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            SystemeReservation systeme = new SystemeReservation();

            // Configuration initiale
            Vaisseau vogueMerry = new Vaisseau("001", "Vogue Merry", 3);
            Vaisseau oroJackson = new Vaisseau("002", "Oro Jackson", 20);
            systeme.ajouterVaisseau(vogueMerry);
            systeme.ajouterVaisseau(oroJackson);

            Officier luffy = new Officier("Monkey D.", "Luffy", "C001", "Capitaine", "Roi des Pirates");
            Officier goldRoger = new Officier("Gol D.", "Roger", "C002", "Capitaine", "Roi des Pirates Légendaire");
            systeme.ajouterPersonne(luffy);
            systeme.ajouterPersonne(goldRoger);

            while (true) {
                System.out.println("\n=== Système de Réservation Spatiale ===");
                System.out.println("1. Créer une nouvelle mission");
                System.out.println("2. Réserver une mission existante");
                System.out.println("3. Quitter le programme");
                System.out.print("Votre choix : ");
                String choix = scanner.nextLine();

                if (choix.equals("1")) {
                    // Création d'une nouvelle mission
                    System.out.print("Entrez le nom de la mission : ");
                    String nomMission = scanner.nextLine();
                    
                    System.out.println("Choisissez un vaisseau :");
                    System.out.println("1. Vogue Merry (capacité : 3)");
                    System.out.println("2. Oro Jackson (capacité : 20)");
                    System.out.print("Votre choix : ");
                    Vaisseau vaisseauChoisi = scanner.nextLine().equals("1") ? vogueMerry : oroJackson;

                    System.out.println("Choisissez un capitaine :");
                    System.out.println("1. Monkey D. Luffy");
                    System.out.println("2. Gol D. Roger");
                    System.out.print("Votre choix : ");
                    Officier capitaineChoisi = scanner.nextLine().equals("1") ? luffy : goldRoger;

                    System.out.print("Date de début (format AAAA-MM-JJ) : ");
                    Date dateDebut = Date.valueOf(scanner.nextLine());
                    System.out.print("Date de fin (format AAAA-MM-JJ) : ");
                    Date dateFin = Date.valueOf(scanner.nextLine());
                    System.out.print("Destination de la mission : ");
                    String destination = scanner.nextLine();

                    String missionId = "M" + (systeme.getMissionsDisponibles().size() + 1);
                    Mission nouvelleMission = new Mission(missionId, dateDebut, dateFin, nomMission, destination, vaisseauChoisi);
                    systeme.creerMission(nouvelleMission);
                    systeme.reserverMission("R" + missionId, capitaineChoisi, nouvelleMission);
                    
                    systeme.sauvegarderDonnees("missions_disponibles.dat");
                    System.out.println("Mission créée et enregistrée avec succès !");

                } else if (choix.equals("2")) {
                    // Réservation d'une mission
                    List<Mission> missions = systeme.getMissionsDisponibles();
                    if (missions.isEmpty()) {
                        System.out.println("Aucune mission disponible pour le moment.");
                        continue;
                    }

                    System.out.println("Missions disponibles :");
                    for (int i = 0; i < missions.size(); i++) {
                        Mission m = missions.get(i);
                        System.out.printf("%d. %s - Vaisseau : %s vers %s (du %s au %s)\n",
                            (i + 1), m.getVaisseau().getNomDuVaisseau(),
                            m.getDestination(), m.getDateDebut(), m.getDateFin());
                    }

                    System.out.print("Numéro de la mission à réserver : ");
                    int missionChoix = Integer.parseInt(scanner.nextLine()) - 1;
                    
                    if (missionChoix >= 0 && missionChoix < missions.size()) {
                        System.out.print("Entrez votre nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez votre prénom : ");
                        String prenom = scanner.nextLine();
                        
                        Passager passager = new Passager(nom, prenom, 
                            "P" + (systeme.getMissionsDisponibles().size() + 1),
                            "Inconnu", "Passager");
                        systeme.ajouterPersonne(passager);
                        
                        String reservationId = "R" + (missions.size() + 1);
                        systeme.reserverMission(reservationId, passager, missions.get(missionChoix));
                        systeme.sauvegarderDonnees("reservations.dat");
                        System.out.println("Réservation enregistrée avec succès !");
                    } else {
                        System.out.println("Numéro de mission invalide.");
                    }

                } else if (choix.equals("3")) {
                    System.out.println("Merci d'avoir utilisé le système. Au revoir !");
                    break;
                } else {
                    System.out.println("Choix invalide. Veuillez sélectionner 1, 2 ou 3.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}