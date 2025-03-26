import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface InterfaceClient {
    void ajouterPersonne(Personne personne);
    List<Mission> getMissionsDisponibles();
    void reserverMission(String idReservation, Personne passager, Mission mission);
    void confirmerReservation(String idReservation);
    void annulerReservation(String idReservation);
    void proposerReservations(Scanner scanner, Personne client); 
    void listerCapitaines(); 
    void sauvegarderDonnees(String fichier) throws IOException;
}
