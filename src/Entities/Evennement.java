/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author HP USER
 */
public class Evennement {
    int id;
    String nom,stade;
    Date date;

    public Evennement() {
    }

    
    
    public Evennement(int id, String nom, String stade, Date date) {
        this.id = id;
        this.nom = nom;
        this.stade = stade;
        this.date = date;
    }

    public Evennement(String nom, String stade, Date date) {
        this.nom = nom;
        this.stade = stade;
        this.date = date;
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

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Evennement{" + "id=" + id + ", nom=" + nom + ", stade=" + stade + ", date=" + date + '}';
    }
    
}
