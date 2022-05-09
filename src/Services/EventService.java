/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Agence;
import Entities.Evennement;
import Entities.Reservation;
import IServices.IServiceEvent;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP USER
 */
public class EventService implements IServiceEvent {
    Connection con;
  public EventService() {
        con = MyDB.getInstance().getCon();
    }
  


    @Override
    public boolean ajouter(Evennement e)  {
              try{

     String insert2 = "INSERT INTO eventd (nom,stade,date)"+" VALUES (?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setString(1, e.getNom());
        prst.setString(2, e.getStade());
        prst.setDate(3, (Date) e.getDate());

    


        prst.executeUpdate();
        System.out.println("Evennement Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du Evennement");
            return false;
        }
}

    @Override
    public boolean modifier(Evennement h) {
        try{
 String req = "UPDATE eventd SET  nom=? , stade=? , date=?  WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, h.getNom());
            pstm.setString(2, h.getStade());
        pstm.setDate(3, (Date) h.getDate());
            pstm.setInt(4, h.getId());
            pstm.executeUpdate();
            System.out.println("Evennement Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du Evennement");
            return false;
        }    }

    @Override
    public boolean Supprimer(int id) {
 String delete= "DELETE FROM eventd where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression event avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return false;    }

    @Override
    public List<Evennement> Afficher() {
     List<Evennement>myList=  new ArrayList<>();;
        try {

         String select="SELECT * FROM `eventd`";
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(select);
          while(rs.next()){
                Evennement a = new Evennement();
                a.setId(rs.getInt(1));
                a.setNom(rs.getString("nom"));
                a.setStade(rs.getString("stade"));
                a.setDate(rs.getDate("date"));
            
                myList.add(a);
              System.out.println("affichage succées");
            }
          } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return myList;
    }

   @Override
    public Evennement RecupererHebergement(int id) {
        
        Evennement hebergement = new Evennement();
        try {
            String req = "SELECT * FROM eventd WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                hebergement.setId(rs.getInt("id"));
                hebergement.setNom(rs.getString("nom"));
                hebergement.setStade(rs.getString("stade"));
                hebergement.setDate((Date)rs.getDate("date"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return hebergement;

        
    } 
}
