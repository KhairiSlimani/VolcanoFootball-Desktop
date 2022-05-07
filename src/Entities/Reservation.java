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
    private int user_id;
    private int hebergement;

private Date dateDebut, dateFin;

    public Reservation() {
    }

    public Reservation(int id, int user_id, int hebergement, Date dateDebut, Date dateFin) {
        this.id = id;
        this.hebergement = hebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
                this.user_id = user_id;

    }

    public Reservation(int user_id, int hebergement, Date dateDebut, Date dateFin) {
        this.hebergement = hebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
         this.user_id = user_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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