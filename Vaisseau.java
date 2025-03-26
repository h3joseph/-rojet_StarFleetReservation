import java.util.ArrayList;
import java.util.List;

public class Vaisseau {
    private String nomDuVaisseau;
    private String immatriculation;
    private int capaciteMax;
    private List<Mission> missions;

    public Vaisseau(String immatriculation, String nomDuVaisseau, int capaciteMax) {
        this.immatriculation = immatriculation;
        this.nomDuVaisseau = nomDuVaisseau;
        this.capaciteMax = capaciteMax;
        this.missions = new ArrayList<>();
    }
    public String getNomDuVaisseau() { return nomDuVaisseau; }
    public int getCapaciteMax() { return capaciteMax; }

    public void ajouterUneMission(Mission mission) {
        missions.add(mission);
    }

    
}