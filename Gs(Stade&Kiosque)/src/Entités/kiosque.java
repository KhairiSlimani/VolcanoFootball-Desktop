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
public class kiosque {
    private int id,stade_id ;
    private String nom,type;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   public int getStade_id() {
        return stade_id;
    }

    public void setStade_id(int stade_id) {
        this.stade_id = stade_id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public kiosque() {
    }

    public kiosque(int id, String nom, String type, int stade_id) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.stade_id=stade_id;
    }
  public kiosque(String nom, String type, int stade_id) {
       
        this.nom = nom;
        this.type = type;
        this.stade_id=stade_id;
    }

    @Override
    public String toString() {
        return "ReservationV{" + "id=" + id + ", nom=" + nom + ", type=" + type +", stade_id=" + stade_id +'}';
    }
    
    
}
