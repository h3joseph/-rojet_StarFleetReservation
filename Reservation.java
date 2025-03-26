import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private String idReservation;
    private Personne passager;
    private Mission mission;
    private Date dateReservation;
    private boolean confirmee;

    public Reservation(String idReservation, Personne passager, Mission mission) {
        this.idReservation = idReservation;
        this.passager = passager;
        this.mission = mission;
        this.dateReservation = new Date();
        this.confirmee = false;

        if (!mission.ajouterReservation(this)) {
            throw new IllegalStateException("Il n'y a plus de place pour ce départ");
        }
    }

    public void confirmer() {
        this.confirmee = true;
    }

    public void annuler() {
        this.confirmee = false;
        mission.getReservations().remove(this); 
    }

    public String getIdReservation() { return idReservation; }
    public Personne getPassager() { return passager; }
    public Mission getMission() { return mission; }
    public Date getDateReservation() { return dateReservation; }
    public boolean isConfirmee() { return confirmee; }
}


    

