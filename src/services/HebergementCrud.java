/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Agence;
import entities.Hebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.MyConnection;

/**
 *
 * @author HP USER
 */
public class HebergementCrud {
     Connection con;
  public HebergementCrud() {
        con = MyConnection.getInstance().getCnx();
    }
    

   public void ajouterHebergement()throws SQLException {
        String insert = "INSERT INTO Hebergement (nomH,type)"+" VALUES ('travel','Hotel')";
               try{

         Statement st = con.createStatement();
         st.executeUpdate(insert);
System.out.println("Hebergement ajouté avec succés");
  }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
}
   public void ajouterHebergement2(Hebergement h)throws SQLException  {
              try{

     String insert2 = "INSERT INTO Hebergement (nomH,type,adresse,agence_id,photo_h)"+" VALUES (?,?,?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, h.getNomH());
        prst.setString(2, h.getType());
        prst.setString(3, h.getAdresse());

        prst.setInt(4, h.getAgence_id());
        prst.setString(5, h.getPhoto_h());


        prst.executeUpdate();
        System.out.println("Hebergement créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
}

   public List<Hebergement> afficherHebergement() throws SQLException {
      List<Hebergement>myList=  new ArrayList<>();;
        try {

         String select="SELECT * FROM `hebergement`";
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(select);

          while(rs.next()){
                    {
                Hebergement h = new Hebergement();
                h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));

                myList.add(h);
                }
              System.out.println("affichage succées");
            }
          } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return myList;

}

   public void supprimerHebergement(int id) throws SQLException {
 String delete= "DELETE FROM hebergement where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression hebergement avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
   public void modifierHebergement( Hebergement h)throws SQLException {
  String update = "UPDATE `hebergement` SET "
                +"`nomH`=?,`type`=?"
                + "WHERE id = '" +h.getId()+ "'";
        try {
    PreparedStatement prst = con.prepareStatement(update);
             prst.setString(1, h.getNomH());
             prst.setString(2, h.getType());
         

            prst.executeUpdate();
            System.out.println("hebergement modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
 public Hebergement afficherHebergementByAgence(int agence) {
                Hebergement h = new Hebergement();

        try {

            String requete =  "SELECT * FROM agence a, Hebergement h where h.agence_id=a.id AND h.id=" + agence;
           System.out.println(agence);
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                   h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));
                return h;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }   
 
}
