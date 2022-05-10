package Services;



import Entities.stade;

import IServices.IServicestade;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;


/**
 *
 * @author jasser
 */
public class StadeService implements IServicestade {

    Connection con;
    Statement stm;

    public StadeService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean AjouterStade(stade s) {
        try {
            String req = "INSERT INTO `stade` (`nom`, `adresse`, `dateouverture`, `capacite`) VALUES (?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, s.getNom());
            pstm.setString(2, s.getAdresse());
            pstm.setDate(3, (Date) s.getDateouverture());
            pstm.setFloat(4, s.getCapacite());
            
            pstm.executeUpdate();

            System.out.println("Stade Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du stade");
            return false;
        }

    }
    
    @Override
    public boolean ModifierStade(stade s) {
        try {
            String req = "UPDATE stade SET  nom=? , adresse=? , dateouverture=?  , capacite=?  WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, s.getNom());
            pstm.setString(2, s.getAdresse());
            pstm.setDate(3, (Date) s.getDateouverture());   
            pstm.setFloat(4, s.getCapacite());
            pstm.setInt(5, s.getId());
            pstm.executeUpdate();
            System.out.println("Stade Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du stade");
            return false;
        }

    }
    @Override
    public boolean SupprimerStade(int id) {
        try {
            String req = "DELETE FROM `stade` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("Stade Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression");
        }
        return false;

    }
    @Override
    public List<stade> AfficherStade() {
        List<stade> stades = new ArrayList();
        try {
            String req = "SELECT * FROM stade";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                stade stade = new stade();
                stade.setId(rs.getInt("id"));
                stade.setNom(rs.getString("nom"));
                stade.setAdresse(rs.getString("adresse"));
                stade.setDateouverture(rs.getDate("dateouverture"));
                
                stade.setCapacite((int) rs.getFloat("capacite"));
               

                stades.add(stade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des produits");
        }
        return stades;

   }
    @Override
    public stade RecupererStade(int id) {
        
        stade stade = new stade();
        try {
            String req = "SELECT * FROM stade WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                stade.setId(rs.getInt("id"));
                stade.setNom(rs.getString("nom"));
                stade.setAdresse(rs.getString("adresse"));
                stade.setDateouverture(rs.getDate("dateouverture"));
                stade.setCapacite(rs.getFloat("capacite"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return stade;
    }
    public stade RecupererStade(String nom) {
        
        stade stade = new stade();
        try {
            String req = "SELECT * FROM stade WHERE nom=\""+nom+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                stade.setId(rs.getInt("id"));
                stade.setNom(rs.getString("nom"));
                stade.setAdresse(rs.getString("adresse"));
                stade.setDateouverture(rs.getDate("dateouverture"));
                
                stade.setCapacite((int) rs.getFloat("capacite"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return stade;

    }
     public List<stade> recherche(String searched) {

        List<stade> liste = new ArrayList<>();
        try {

            String req = "select * from stade WHERE nom LIKE '%" + searched + "%' OR adresse LIKE '%" + searched + "%' ;";
            PreparedStatement pst = con.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            stade h = new stade();
            while (rs.next()) {

             h.setId(rs.getInt("id"));
                h.setNom(rs.getString("nom"));
                h.setAdresse(rs.getString("adresse"));
                h.setDateouverture(rs.getDate("dateouverture"));
                
                h.setCapacite((int) rs.getFloat("capacite"));
                
                liste.add(h);
            }
            return liste;
        } catch (SQLException ex) {
            System.out.println("errreur" + ex.getMessage());
        }
        return null;

    }

   
    


  
   
    


}
