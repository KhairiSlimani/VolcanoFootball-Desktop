/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ASUS
 */
public class Voyage {
    private int id, prix;
    private String nom, destination, description,image;
    

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
     public String getDescription() {
        return description;
    }
      public void setDescription(String description) {
        this.description = description;
    }
        public int getPrix() {
        return prix;
    }
      public void setPrix(int prix) {
        this.prix = prix;
    }
           public String getImage() {
        return image;
    }
      public void setImage(String image) {
        this.image = image;
    }
      
    public Voyage() {
    }

    public Voyage(int id, String nom, String destination, String description, int prix, String image) {
        this.id = id;
        this.nom = nom;
        this.destination = destination;
        this.description = description;
        this.prix = prix;
        this.image=image;
    }
        public Voyage(String nom, String destination, String description, int prix, String image) {
       
        this.nom = nom;
        this.destination = destination;
        this.description = description;
        this.prix = prix;
        this.image=image;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", nom=" + nom + ", destination=" + destination +  ", description=" + description + ", prix=" + prix +'}';
    }
    
    
}
