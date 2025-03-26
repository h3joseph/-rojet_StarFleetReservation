public class Passager extends Personne {
    private String origine;
    private String motifDeVoyage;

    public Passager(String nom, String prenom, String id, String origine, String motifDeVoyage) {
        super(nom, prenom, id);
        this.origine = origine;
        this.motifDeVoyage = motifDeVoyage;
    }

    @Override
    public String getDescription() {
        return getNom() + " " + getPrenom() + "\n"
                + "d'origine : " + origine + " voyage pour : " + motifDeVoyage;
    }
}