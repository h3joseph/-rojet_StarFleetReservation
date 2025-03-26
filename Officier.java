public class Officier extends Personne {
    private String rang;
    private String specialite;

    public Officier(String nom, String prenom, String id, String rang, String specialite) {
        super(nom, prenom, id);
        this.rang = rang;
        this.specialite = specialite;
    }

    @Override
    public String getDescription() {
        return getNom() + " " + getPrenom() + "\n"
                + "Rang : " + rang + ", spécialisé en : " + specialite;
    }

    public String getRang() {return rang;}
    public String getSpecialite(){return specialite;}
}
