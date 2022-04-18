/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Agence;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP USER
 */
public class AgenceCrud  {
  Connection con;
  public AgenceCrud() {
        con = MyConnection.getInstance().getCnx();
    }
    

   public void ajouterAgence()throws SQLException {
        String insert = "INSERT INTO agence (nom,numTel,adresse)"+" VALUES ('travel','12344556','ariana')";
               try{

         Statement st = con.createStatement();
         st.executeUpdate(insert);
System.out.println("Agence ajouté avec succés");
  }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
}
   public void ajouterAgence2(Agence a)throws SQLException  {
              try{

     String insert2 = "INSERT INTO agence (nom,numTel,adresse)"+" VALUES (?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, a.getNom());
        prst.setInt(2, a.getNumTel());
        prst.setString(3, a.getAdresse());
        prst.executeUpdate();
        System.out.println("Agence créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
}

   public List<Agence> afficherAgence() throws SQLException {
      List<Agence>myList=  new ArrayList<>();;
        try {

         String select="SELECT * FROM `agence`";
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(select);
          while(rs.next()){
                Agence a = new Agence();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString("nom"));
                a.setAdresse(rs.getString("adresse"));
                a.setNumTel(rs.getInt("numTel"));
            
                myList.add(a);
              System.out.println("affichage succées");
            }
          } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return myList;

}

   public void supprimerAgence(int id) throws SQLException {
 String delete= "DELETE FROM agence where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression agence avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
   public void modifierAgence(int id, Agence a)throws SQLException {
  String update = "UPDATE `agence` SET "
                +"`nom`=?,`numTel`=?,`adresse`=?"
                + "WHERE id = '" +id+ "'";
        try {
    PreparedStatement prst = con.prepareStatement(update);
             prst.setString(1, a.getNom());
             prst.setInt(2, a.getNumTel());
             prst.setString(3, a.getAdresse());
         

            prst.executeUpdate();
            System.out.println("agence modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

}
