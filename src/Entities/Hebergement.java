/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author HP USER
 */
public class Hebergement {
     private int id;
    private String type;
    private String nomH;
    private String adresse;
    private int agence_id;


    public Hebergement() {
    }

    public Hebergement(int id, String type, String nomH, String adresse, int agence_id) {
        this.id = id;
        this.type = type;
        this.nomH = nomH;
        this.adresse = adresse;
        this.agence_id = agence_id;

    }

    public Hebergement(String  type, String nomH,String adresse) {
        this.type = type;
        this.nomH = nomH;
        this.adresse = adresse;


    }

    public Hebergement(  int id, String type, String nomH,String adresse) {
        this.id = id;
        this.type = type;
        this.nomH = nomH;
        this.adresse = adresse;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomH() {
        return nomH;
    }

    public void setNomH(String nomH) {
        this.nomH = nomH;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAgence_id() {
        return agence_id;
    }

    public void setAgence_id(int agence_id) {
        this.agence_id = agence_id;
    }



    @Override
    public String toString() {
        return "Hebergement{" + "id=" + id + ", type=" + type + ", nomH=" + nomH + ", adresse=" + adresse + ", agence_id=" + agence_id + '}';
    }

   

   

 
  

}

