/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Match;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Packard bell
 */
public class ServiceMatch {
    Connection cnx= MyDB.getInstance().getCon();
    private static ServiceMatch instance;
    
    
    
    public static ServiceMatch getInstance(){
        if(instance==null)
            instance=new ServiceMatch();
        return instance;
    }
    public void add(Match c) throws SQLException{
              try {

            String req = "INSERT INTO `match` (`nom_match`, `nom_arbitre`, `stade`,`tour`, `date`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, c.getNom_match());
            pstm.setString(2, c.getNom_arbitre());
            pstm.setString(3, c.getStade());
            pstm.setString(4, c.getTour());
            pstm.setString(5, c.getDate());
           
            pstm.executeUpdate();
            System.out.println("Ajout effectué");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        
    }
     public ObservableList<Match> getAll() throws SQLException {
     ObservableList<Match> result =  FXCollections.observableArrayList();
     String req = "SELECT * FROM `match`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Match C = new Match();

         C.setId(rst.getInt("id"));
         C.setNom_match(rst.getString("nom_match"));
         C.setNom_arbitre(rst.getString("nom_arbitre"));
         C.setStade(rst.getString("stade"));
         C.setTour(rst.getString("tour"));
         C.setDate(rst.getString("date"));
        
         
         result.add(C);
     }

     return result;
    } 
    public void updateMatch(Match C) throws SQLException{
        
        String req = "UPDATE `match` SET nom_match = ?, nom_arbitre = ?, stade = ?, tour = ?, date = ? WHERE id= ?";
        PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, C.getNom_match());
            pst.setString(2, C.getNom_arbitre());
            pst.setString(3, C.getStade());
            pst.setString(4, C.getTour());
            pst.setString(5, C.getDate());
            
            pst.setInt(6, C.getId());
            pst.executeUpdate();
    }
    
    
   public void delete(int id) throws SQLException{
        String req= "DELETE FROM `match` WHERE  id= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setInt(1, id);
         pst.executeUpdate();
        
    }



     
     
}
