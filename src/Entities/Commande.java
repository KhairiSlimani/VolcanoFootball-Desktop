package Entities;

import java.sql.Date;

/**
 *
 * @author Khairi
 */
public class Commande {

    private int id;
    private int user;
    private int produit;
    private int quantite;
    private String adresse;
    private Date date;

    public Commande() {
    }

    public Commande(int id, int user, int produit, int quantite, String adresse) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.adresse = adresse;
    }

    public Commande(int user, int produit, int quantite, String adresse) {
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user=" + user + ", produit=" + produit + ", quantite=" + quantite + ", adresse=" + adresse + ", date=" + date + '}';
    }




}
