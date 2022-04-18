/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author HP USER
 */
public class Agence {
 private int id,numTel ;
private String nom,adresse;
    public Agence() {
    }

    public Agence(int numTel, String nom, String adresse) {
        this.numTel = numTel;
        this.nom = nom;
        this.adresse = adresse;
    }


    public Agence(int id, int numTel, String nom, String adresse) {
        this.id = id;
        this.numTel = numTel;
        this.nom = nom;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
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

 @Override
public String toString() {
	return "Agence [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", numTel=" + numTel+ "]";
}

  
}


