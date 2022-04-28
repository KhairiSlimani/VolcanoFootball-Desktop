/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author HP USER
 */
public class Reservation {
   	private int id;
    private int user;
    private int hebergement;

private Date dateDebut, dateFin;

    public Reservation() {
    }

    public Reservation(int id, int user, int hebergement, Date dateDebut, Date dateFin) {
        this.id = id;
        this.user = user;
        this.hebergement = hebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Reservation(int user, int hebergement, Date dateDebut, Date dateFin) {
        this.user = user;
        this.hebergement = hebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public int getHebergement() {
        return hebergement;
    }

    public void setHebergement(int hebergement) {
        this.hebergement = hebergement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

   
 
}   