/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Khairi
 */
public class Favori {
    
    private int id;
    private int idUser;
    private int idProduit;

    public Favori() {
    }

    public Favori(int id, int idUser, int idProduit) {
        this.id = id;
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public Favori(int idUser, int idProduit) {
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    
    
}
