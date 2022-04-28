/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author ASUS
 */
public class stade {
    private int id,capacite;
    private String nom, adresse,dateouverture;
    

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
     public String getDateouverture() {
        return dateouverture;
    }
      public void setDateouverture(String dateouverture) {
        this.dateouverture = dateouverture;
    }
        public int getCapacite() {
        return capacite;
    }
      public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
      
    public stade() {
    }

    public stade(int id, String nom, String adresse, String dateouverture, int capacite) {
        this.id = id;
        this.nom = nom; 
        this.adresse = adresse;
        this.dateouverture = dateouverture;
        this.capacite = capacite;
    }
        public stade(String nom, String adresse, String dateouverture, int capacite) {
       
        this.nom = nom;
        this.adresse = adresse;
        this.dateouverture = dateouverture;
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "stade{" + "id=" + id + ", nom=" + nom + ", dateouverture=" + dateouverture +  ", dateouverture=" + dateouverture + ", capacite=" + capacite +'}';
    }
    
    
}
