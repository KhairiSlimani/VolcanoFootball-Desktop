package Services;



import Entities.kiosque;
import IServices.IServiceCommande;
import IServices.IServiceKiosque;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KiosqueService implements IServiceKiosque {

    Connection con;
    Statement stm;

    public KiosqueService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean AjouterKiosque(kiosque c) {
        try {
            String req = "INSERT INTO `kiosque` ( `stade_id`, `nom`, `type`) VALUES (?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setInt(1, c.getStade_id());
            pstm.setString(2, c.getNom());
            pstm.setString(3, c.getType());
            pstm.executeUpdate();

            System.out.println("Kiosque Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du kiosque");
            return false;
        }

    }

    @Override
    public boolean ModifierKiosque(kiosque k) {

        try {
            String req = "UPDATE kiosque SET  nom=? , type=? , stade_id=?  WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            
            pstm.setString(1 , k.getNom());
            pstm.setString(2, k.getType());
            pstm.setInt(3, k.getStade_id());
            pstm.setInt(4, k.getId());
            pstm.executeUpdate();

            System.out.println("Kiosque Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du kiosque");
            return false;
        }


    }

    @Override
    public boolean SupprimerKiosque(int id) {
        try {
            String req = "DELETE FROM `kiosque` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("Kiosque Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression du kiosque");
        }
        return false;

    }

    @Override
    public List<kiosque> AfficherKiosque() {
        List<kiosque> kiosque = new ArrayList();
        try {
            String req = "SELECT * FROM kiosque";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                kiosque k= new kiosque();
                k.setId(rs.getInt("id"));
                
                k.setStade_id(rs.getInt("stade_id"));
                k.setNom(rs.getString("nom"));
                
                k.setType(rs.getString("type"));

                kiosque.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des kiosques");
        }
        return kiosque;

    }

    @Override
    public kiosque RecupererKiosque(int id) {
        
        kiosque kiosque = new kiosque();
        try {
            String req = "SELECT * FROM kiosque WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                kiosque.setId(rs.getInt("id"));
                kiosque.setStade_id(rs.getInt("stade_id"));
                
                kiosque.setNom(rs.getString("nom"));
                kiosque.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return kiosque;

    }

    @Override
    public List<kiosque> AfficherKiosque(int id) {
        List<kiosque> kiosque = new ArrayList();
        try {
            String req = "SELECT * FROM kiosque WHERE id = " + id+ "";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                kiosque c = new kiosque();
                c.setId(rs.getInt("id"));
                c.setStade_id(rs.getInt("stade_id"));
                c.setNom(rs.getString("nom"));
                c.setType(rs.getString("type"));

                kiosque.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StadeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des commandes");
        }
        return kiosque;

    }

 
    
}
