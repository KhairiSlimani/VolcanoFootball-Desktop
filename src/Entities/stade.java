/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.JFXDatePicker;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class stade {
    private int id;
    private String nom, adresse;
    private Date dateouverture;
    private float capacite;

    public stade(int id, String nom, String adresse, float capacite) {
        this.id=id;
        this.nom=nom;
        this.capacite=capacite;
        this.adresse=adresse;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
     public Date getDateouverture() {
        return dateouverture;
    }
      public void setDateouverture(Date dateouverture) {
        this.dateouverture = dateouverture;
    }
        public float getCapacite() {
        return capacite;
    }
      public void setCapacite(float capacite) {
        this.capacite = capacite;
    }
      
    public stade() {
    }

    public stade(int id, String nom, String adresse, Date dateouverture, float capacite) {
        this.id = id;
        this.nom = nom; 
        this.adresse = adresse;
        this.dateouverture = dateouverture;
        this.capacite = capacite;
    }
        public stade(String nom, String adresse, Date dateouverture, float capacite) {
       
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
