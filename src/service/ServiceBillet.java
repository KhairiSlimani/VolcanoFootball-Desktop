/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entities.Billet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.Maconnexion;

/**
 *
 * @author DeLL
 */
public class ServiceBillet {
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceBillet instance;
    
    
    
    public static ServiceBillet getInstance(){
        if(instance==null)
            instance=new ServiceBillet();
        return instance;
    }
    public void add(Billet c) throws SQLException{
              try {

           
            String req = "INSERT INTO `billetm` (`type`, `prix`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, c.getType());
            pstm.setString(2, c.getPrix());

            pstm.executeUpdate();
            System.out.println("Ajout effectué");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        
       /* String req = "INSERT INTO `match` (nom_match,nom_arbitre,stade,tour,date,image) VALUES ('"
                    + c.getNom_match()+"', '" 
                    + c.getNom_arbitre()+"', '" 
                    + c.getStade()+"', '" 
                    + c.getTour()+"', '" 
                    + c.getDate()+"', '" 
                    + c.getImage()+"', '" ;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);*/
    }
     public ObservableList<Billet> getAll() throws SQLException {
     ObservableList<Billet> result =  FXCollections.observableArrayList();
     String req = "SELECT * FROM `billetm`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Billet C = new Billet();

         C.setId(rst.getInt("id"));
         C.setType(rst.getString("type"));
         C.setPrix(rst.getString("prix"));
         
         
         result.add(C);
     }

     return result;
    } 
    public void updateBillet(Billet C) throws SQLException{
        
        String req = "UPDATE `billetm` SET type = ?, prix = ? WHERE id= ?";
     
        PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, C.getType());
            pst.setString(2, C.getPrix());

            pst.setInt(3, C.getId());
            pst.executeUpdate();
    }
    
    
   public void delete(String id) throws SQLException{
        String req= "DELETE FROM `billetm` WHERE  id= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, id);
         pst.executeUpdate();
        
    }



     
    
}
