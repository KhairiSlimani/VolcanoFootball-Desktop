/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Packard bell
 */
public class Conge {
    
    //les attributs du classe produit
    private int IdConge;
    private int IdEmploye;
    
    private String DebutConge;
    private String FinConge;
    
    private String TypeConge;
   
    
   
  
    //constructeur paramétré
    public Conge(int IdEmploye, String DebutConge, String FinConge, String TypeConge) {
        
        
        this.IdEmploye = IdEmploye;
        this.DebutConge = DebutConge;
        this.FinConge=FinConge;
        this.TypeConge=TypeConge;
        
    }
     //constructeur par default
    public Conge(){
        
    }

    public int getIdConge() {
        return IdConge;
    }

    public int getIdEmploye() {
        return IdEmploye;
    }

    public String getDebutConge() {
        return DebutConge;
    }

    public String getFinConge() {
        return FinConge;
    }

    public String getTypeConge() {
        return TypeConge;
    }

    public void setIdConge(int IdConge) {
        this.IdConge = IdConge;
    }

    public void setIdEmploye(int IdEmploye) {
        this.IdEmploye = IdEmploye;
    }

    public void setDebutConge(String DebutConge) {
        this.DebutConge = DebutConge;
    }

    public void setFinConge(String FinConge) {
        this.FinConge = FinConge;
    }

    public void setTypeConge(String TypeConge) {
        this.TypeConge = TypeConge;
    }

    @Override
    public String toString() {
        return "Produit{" + "IdConge=" + IdConge + ", IdEmploye=" + IdEmploye + ", DebutConge=" + DebutConge + ", FinConge=" + FinConge + ", TypeConge=" + TypeConge + '}';
    }
    

    
}
