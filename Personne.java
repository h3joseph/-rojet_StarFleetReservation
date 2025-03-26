import java.io.Serializable;

public abstract class Personne implements Serializable {
    private String nom;
    private String prenom;
    private String id;

    public Personne(String nom, String prenom, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getId() { return id; }
    public abstract String getDescription(); // Méthode abstraite pour les sous-classes
}
