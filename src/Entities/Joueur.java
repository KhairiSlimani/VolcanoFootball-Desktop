package Entities;

/**
 *
 * @author jrady
 */

public class Joueur {
    private int id;
    private String nom_joueur;
    private String prenom_joueur;
    private int age;
    private String position;
    private String photo;
    private int equipe;
    private String Description;

    public Joueur(){
    }
    public Joueur(int id, String nom_joueur,String prenom_joueur,int age, String position,String photo,int equipe, String Description){
        this.id=id;
        this.nom_joueur=nom_joueur;
        this.prenom_joueur=prenom_joueur;
        this.age=age;
        this.position=position;
        this.photo=photo;
        this.equipe=equipe;
        this.Description=Description;
    }
    public Joueur( String nom_joueur,String prenom_joueur,int age, String position,String photo,int equipe, String Description){
        this.nom_joueur=nom_joueur;
        this.prenom_joueur=prenom_joueur;
        this.age=age;
        this.position=position;
        this.photo=photo;
        this.equipe=equipe;
        this.Description=Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public String getPrenom_joueur() {
        return prenom_joueur;
    }

    public void setPrenom_joueur(String prenom_joueur) {
        this.prenom_joueur = prenom_joueur;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getEquipe() {
        return equipe;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", nom_joueur=" + nom_joueur + ", prenom_joueur=" + prenom_joueur + ",age="+ age + ",position" +position + ",photo="+photo +",Description="+Description+'}';
    }
}
