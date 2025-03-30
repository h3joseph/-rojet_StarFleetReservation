import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String code;
    private String description;
    private Date dateDepart;
    private Date dateRetour;
    private String destination;
    private List<Reservation> reservations;
    private Vaisseau vaisseau;

    public Mission(String code, Date dateDepart, Date dateRetour, String description,
                   String destination, Vaisseau vaisseau) {
        this.code = code;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.description = description;
        this.destination = destination;
        this.vaisseau = vaisseau;
        this.reservations = new ArrayList<>();
    }

    public Vaisseau getVaisseau() { return vaisseau; }
    public String getDestination() { return destination; }
    public List<Reservation> getReservations() { return reservations; }
    public boolean ajouterReservation(Reservation reservation) {
        if (reservations.size() < vaisseau.getCapaciteMax()) {
            reservations.add(reservation);
            return true;
        }
        return false;
    }
    public Date getDateDebut() { return dateDepart; }
    public Date getDateFin() { return dateRetour; }
}