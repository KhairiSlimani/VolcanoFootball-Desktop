/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Agence;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IServices.IServiceAgence;
import IServices.IServiceProduit;

/**
 *
 * @author HP USER
 */
public class AgenceCrud implements IServiceAgence {
  Connection con;
  public AgenceCrud() {
        con = MyDB.getInstance().getCon();
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
   
 
    @Override
  public boolean ajouterAgence2(Agence a)  {
              try{

     String insert2 = "INSERT INTO agence (nom,numTel,adresse)"+" VALUES (?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, a.getNom());
        prst.setInt(2, a.getNumTel());
        prst.setString(3, a.getAdresse());
        prst.executeUpdate();
      
            System.out.println("Agence Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AgenceCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du Agence");
            return false;
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
    @Override
   public boolean supprimerAgence(int id)  {
 String delete= "DELETE FROM agence where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression agence avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      return false;
}
    @Override
   public boolean modifierAgence( Agence a) {
  String update = "UPDATE `agence` SET "
                +"`nom`=?,`numTel`=?,`adresse`=?"
                + " WHERE id=?";
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
      return false;
}

    public Agence RecupererAgence(int id) {
        
        Agence produit = new Agence();
        try {
            String req = "SELECT * FROM agence WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.setAdresse(rs.getString("adresse"));
                produit.setNumTel(rs.getInt("numTel"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return produit;

    }

}
