/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hebergement;
import Entities.Reservation;
import IServices.IServiceHebergement;
import IServices.IServiceReservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP USER
 */
public class ReservationCrud implements IServiceReservation {
       Connection con;
  public ReservationCrud() {
        con = MyDB.getInstance().getCon();
    }
    
    
    
    public List<Reservation> afficher() throws SQLException {
         List<Reservation>myList=  new ArrayList<>();;
        try {

         String select="SELECT * FROM `reservation`";
         Statement st =con.createStatement();
         ResultSet rs =    st.executeQuery(select);

          while(rs.next()){
                    {
                Reservation h = new Reservation();
                h.setId(rs.getInt(1));
                h.setDateDebut(rs.getDate("dateDebut"));
                h.setDateFin(rs.getDate("dateFin"));
                h.setHebergement(rs.getInt("hebergement"));

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
    public boolean ajouterReservation(Reservation r)  {
              try{

     String insert2 = "INSERT INTO Reservation (dateDebut,dateFin,hebergement,user_id)"+" VALUES (?,?,?,?)";
    PreparedStatement prst = con.prepareStatement(insert2);
        
        prst.setDate(1, (Date) r.getDateDebut());
        prst.setDate(2, (Date) r.getDateFin());
        prst.setInt(3, r.getHebergement());
            prst.setInt(4, r.getUser_id());

    


        prst.executeUpdate();
        System.out.println("Hebergement Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(AgenceCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du Hebergement");
            return false;
        }
}
      public void supprimerReservation(int id) {
 String delete= "DELETE FROM reservation where id= ?";
        try {
    PreparedStatement prst = con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression Reservation avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
       @Override
   public boolean modifierReservation( Reservation h) {
   try {
            String req = "UPDATE reservation SET  dateDebut=? , dateFin=? , hebergement=?   WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setDate(1, (Date) h.getDateDebut());
            pstm.setDate(2, (Date) h.getDateFin());
            pstm.setInt(3, h.getHebergement());
            pstm.setInt(4, h.getId());
            pstm.executeUpdate();
            System.out.println("reservation Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HebergementCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du reservation");
            return false;
        }

}
}