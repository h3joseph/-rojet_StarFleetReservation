import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemeReservation implements Serializable, InterfaceClient {
    private List<Vaisseau> vaisseaux = new ArrayList<>();
    private List<Personne> personnes = new ArrayList<>();
    private List<Mission> missions = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void ajouterVaisseau(Vaisseau vaisseau) {
        vaisseaux.add(vaisseau);
    }

    @Override
    public void ajouterPersonne(Personne personne) {
        personnes.add(personne);
    }

    public void creerMission(Mission mission) {
        if (!vaisseaux.contains(mission.getVaisseau())) {
            throw new IllegalArgumentException("Vaisseau non reconnu");
        } else {
            missions.add(mission);
        }
    }

    @Override
    public List<Mission> getMissionsDisponibles() {
        List<Mission> disponibles = new ArrayList<>();
        for (Mission mission : missions) {
            if (mission.getReservations().size() < mission.getVaisseau().getCapaciteMax()) {
                disponibles.add(mission);
            }
        }
        return disponibles;
    }

    @Override
    public void reserverMission(String idReservation, Personne passager, Mission mission) {
        if (!missions.contains(mission)) {
            throw new IllegalArgumentException("Mission non reconnue");
        }
        Reservation reservation = new Reservation(idReservation, passager, mission);
        reservations.add(reservation);
    }

    @Override
    public void confirmerReservation(String idReservation) {
        for (Reservation res : reservations) {
            if (res.getIdReservation().equals(idReservation)) {
                res.confirmer();
                return;
            }
        }
        throw new IllegalArgumentException("Réservation non trouvée : " + idReservation);
    }

    @Override
    public void annulerReservation(String idReservation) {
        for (Reservation res : reservations) {
            if (res.getIdReservation().equals(idReservation)) {
                res.annuler();
                reservations.remove(res);
                return;
            }
        }
        throw new IllegalArgumentException("Réservation non trouvée : " + idReservation);
    }

    @Override
    public void proposerReservations(Scanner scanner, Personne client) {
        List<Mission> disponibles = getMissionsDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("Pas de missions disponibles.");
            return;
        }
        System.out.println("Missions disponibles :");
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println((i + 1) + ". " + disponibles.get(i).getVaisseau().getNomDuVaisseau() + " vers " + disponibles.get(i).getDestination());
        }
        System.out.println("Voulez-vous réserver ? (oui/non)");
        if (scanner.nextLine().equalsIgnoreCase("oui")) {
            System.out.println("Numéro de la mission :");
            int choix = Integer.parseInt(scanner.nextLine()) - 1;
            if (choix >= 0 && choix < disponibles.size()) {
                reserverMission("R" + (reservations.size() + 1), client, disponibles.get(choix));
                System.out.println("Réservation faite !");
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }

    @Override
    public void listerCapitaines() {
        System.out.println("Capitaines :");
        for (Vaisseau v : vaisseaux) {
            System.out.println("- " + v.getNomDuVaisseau() + " :");
            boolean trouvé = false;
            for (Reservation r : reservations) {
                if (r.getMission().getVaisseau().equals(v) && r.getPassager() instanceof Officier) {
                    Officier o = (Officier) r.getPassager();
                    if (o.getRang().equalsIgnoreCase("Capitaine")) {
                        System.out.println("  " + o.getNom() + " " + o.getPrenom());
                        trouvé = true;
                    }
                }
            }
            if (!trouvé) System.out.println("  Aucun capitaine.");
        }
    }

    @Override
    public void sauvegarderDonnees(String fichier) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(this);
        }
    }

    public static SystemeReservation chargerDonnees(String fichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (SystemeReservation) ois.readObject();
        }
    }
}