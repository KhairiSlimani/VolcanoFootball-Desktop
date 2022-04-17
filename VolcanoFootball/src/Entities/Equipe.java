package Entities;

import java.util.Date;

/**
 *
 * @author jrady
 */
public class Equipe {
    private int id;
    private String nom_equipe;
    private Date date_creation;
    private String drapeau_equipe;
    private String nom_entreneur;
    private int joueur;

    public Equipe() {
    }

    public Equipe(int id, int joueur, String nom_equipe, Date date_creation,String nom_entreneur) {
        this.id = id;
        this.joueur = joueur;
        this.nom_equipe = nom_equipe;
        this.date_creation= date_creation;
        this.nom_entreneur = nom_entreneur;

    }
    public Equipe( int joueur, String nom_equipe, Date date_creation,String nom_entreneur) {
        this.joueur = joueur;
        this.nom_equipe = nom_equipe;
        this.date_creation = date_creation;
        this.nom_entreneur = nom_entreneur;

    }

    public void setId(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getDrapeau_equipe() {
        return drapeau_equipe;
    }

    public void setDrapeau_equipe(String drapeau_equipe) {
        this.drapeau_equipe = drapeau_equipe;
    }

    public String getNom_entreneur() {
        return nom_entreneur;
    }

    public void setNom_entreneur(String nom_entreneur) {
        this.nom_entreneur = nom_entreneur;
    }

    public int getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id=" + id + ", nom_equipe=" + nom_equipe + ", date_creation=" + date_creation + ",drapeau_equipe="+ drapeau_equipe + ",nom_entreneur" +nom_entreneur + '}';
    }
}
