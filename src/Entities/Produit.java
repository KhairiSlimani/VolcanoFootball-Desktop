package Entities;

/**
 *
 * @author Khairi
 */
public class Produit {

    private int id;
    private String nom;
    private String type;
    private String taille;
    private String couleur;
    private int nbrEtoiles;
    private String description;
    private float prix;
    private String photo;

    public Produit() {
    }

    public Produit(int id, String nom, String type, String taille, String couleur, int nbrEtoiles, String description, float prix, String photo) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.taille = taille;
        this.couleur = couleur;
        this.nbrEtoiles = nbrEtoiles;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public Produit(String nom, String type, String taille, String couleur, int nbrEtoiles, String description, float prix, String photo) {
        this.nom = nom;
        this.type = type;
        this.taille = taille;
        this.couleur = couleur;
        this.nbrEtoiles = nbrEtoiles;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbrEtoiles() {
        return nbrEtoiles;
    }

    public void setNbrEtoiles(int nbrEtoiles) {
        this.nbrEtoiles = nbrEtoiles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", taille=" + taille + ", couleur=" + couleur + ", nbrEtoiles=" + nbrEtoiles + ", description=" + description + ", prix=" + prix + ", photo=" + photo + '}';
    }


}
