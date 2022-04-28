/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Agence;
import Entities.Hebergement;
import IServices.IServiceHebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Utils.MyDB;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP USER
 */
public class HebergementCrud implements IServiceHebergement {
     Connection con;
  public HebergementCrud() {
        con = MyDB.getInstance().getCon();
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
     @Override
   public boolean ajouterHebergement2(Hebergement h)  {
              try{

     String insert2 = "INSERT INTO Hebergement (nomH,type,adresse,agence_id,photo_h)"+" VALUES (?,?,?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, h.getNomH());
        prst.setString(2, h.getType());
        prst.setString(3, h.getAdresse());

        prst.setInt(4, h.getAgence_id());
        prst.setString(5, h.getPhoto_h());


        prst.executeUpdate();
         System.out.println("Hebergement Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AgenceCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du Hebergement");
            return false;
        }

}
    @Override
   public List<Hebergement> afficherHebergement()  {
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
    @Override
   public boolean supprimerHebergement(int id) {
 String delete= "DELETE FROM hebergement where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression hebergement avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return false;
}
       @Override
   public boolean modifierHebergement( Hebergement h) {
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
         return false;
}
 public Hebergement afficherHebergementByAgence(int agence) {
                Hebergement h = new Hebergement();

        try {

            String requete =  "SELECT * FROM agence a, Hebergement h where h.agence_id=a.id AND h.id=" + agence;
           System.out.println(agence);
            Statement st = MyDB.getInstance().getCon().createStatement();
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
  @Override
    public Hebergement RecupererHebergement(int id) {
        
        Hebergement hebergement = new Hebergement();
        try {
            String req = "SELECT * FROM hebergement WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                hebergement.setId(rs.getInt("id"));
                hebergement.setNomH(rs.getString("nomh"));
                hebergement.setType(rs.getString("type"));
                hebergement.setAgence_id(rs.getInt("agence_id"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return hebergement;

        
    }
    
    public List<Hebergement> recherche(String searched) {

        List<Hebergement> lista = new ArrayList<>();
        try {

            String req = "select * from hebergement WHERE nomH LIKE '%" + searched + "%' OR adresse LIKE '%" + searched + "%' OR type LIKE '%" + searched + "%' ;";
            PreparedStatement pst = con.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Hebergement h = new Hebergement();
            while (rs.next()) {

              h.setId(rs.getInt(1));
                h.setNomH(rs.getString("nomH"));
                h.setType(rs.getString("type"));
                h.setAdresse(rs.getString("adresse"));
                h.setAgence_id(rs.getInt("agence_id"));

                lista.add(h);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("errreur" + ex.getMessage());
        }
        return null;

    }

}
