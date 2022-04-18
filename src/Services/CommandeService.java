package Services;

import Entities.Commande;
import Entities.Produit;
import IServices.IServiceCommande;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandeService implements IServiceCommande {

    Connection con;
    Statement stm;

    public CommandeService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean AjouterCommande(Commande c) {
        try {
            String req = "INSERT INTO `commande` (`user_id`, `produit_id`, `quantite`, `date_ajout`, `adresse`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setInt(1, c.getUser());
            pstm.setInt(2, c.getProduit());
            pstm.setInt(3, c.getQuantite());
            pstm.setDate(4, c.getDate());
            pstm.setString(5, c.getAdresse());
            pstm.executeUpdate();

            System.out.println("Commande Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du commande");
            return false;
        }

    }

    @Override
    public boolean ModifierCommande(Commande c) {

        try {
            String req = "UPDATE commande SET  user_id=? , produit_id=? , quantite=? , date_ajout=? , adresse=?  WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setInt(1, c.getUser());
            pstm.setInt(2, c.getProduit());
            pstm.setInt(3, c.getQuantite());
            pstm.setDate(4, c.getDate());
            pstm.setString(5, c.getAdresse());
            pstm.executeUpdate();

            System.out.println("Commande Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du commande");
            return false;
        }


    }

    @Override
    public boolean SupprimerCommande(int id) {
        try {
            String req = "DELETE FROM `commande` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("Commande Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression du commande");
        }
        return false;

    }

    @Override
    public List<Commande> AfficherCommandes() {
        List<Commande> commandes = new ArrayList();
        try {
            String req = "SELECT * FROM commande";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Commande c = new Commande();
                c.setUser(rs.getInt("user_id"));
                c.setProduit(rs.getInt("produit_id"));
                c.setQuantite(rs.getInt("quantite"));
                c.setDate(rs.getDate("date_ajout"));
                c.setAdresse(rs.getString("adresse"));

                commandes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des commandes");
        }
        return commandes;

    }
}
