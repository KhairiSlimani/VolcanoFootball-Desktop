package Services;

import Entities.Produit;
import IServices.IServiceProduit;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Khairi
 */
public class ProduitService implements IServiceProduit {

    Connection con;
    Statement stm;

    public ProduitService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean AjouterProduit(Produit p) {
        try {
            String req = "INSERT INTO `produit` (`nom`, `type`, `taille`, `couleur`, `nbr_etoiles`, `description`, `prix`, `photo`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, p.getNom());
            pstm.setString(2, p.getType());
            pstm.setString(3, p.getTaille());
            pstm.setString(4, p.getCouleur());
            pstm.setInt(5, p.getNbrEtoiles());
            pstm.setString(6, p.getDescription());
            pstm.setFloat(7, p.getPrix());
            pstm.setString(8, p.getPhoto());
            pstm.executeUpdate();

            System.out.println("Produit Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du produit");
            return false;
        }

    }

    @Override
    public boolean ModifierProduit(Produit p) {
        try {
            String req = "UPDATE produit SET  nom=? , taille=? , couleur=? , nbr_etoiles=? , description=? , prix=? , photo=?   WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, p.getNom());
            pstm.setString(2, p.getType());
            pstm.setString(3, p.getTaille());
            pstm.setString(4, p.getCouleur());
            pstm.setInt(5, p.getNbrEtoiles());
            pstm.setString(6, p.getDescription());
            pstm.setFloat(7, p.getPrix());
            pstm.setString(8, p.getPhoto());
            pstm.executeUpdate();
            System.out.println("Produit Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du produit");
            return false;
        }

    }

    @Override
    public boolean SupprimerProduit(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("Produit Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression");
        }
        return false;

    }

    @Override
    public List<Produit> AfficherProduits() {
        List<Produit> produits = new ArrayList();
        try {
            String req = "SELECT * FROM produit";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setNom(rs.getString("nom"));
                produit.setType(rs.getString("type"));
                produit.setTaille(rs.getString("taille"));
                produit.setCouleur(rs.getString("couleur"));
                produit.setNbrEtoiles(rs.getInt("nbr_etoiles"));
                produit.setDescription(rs.getString("description"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setPhoto(rs.getString("photo"));

                produits.add(produit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des produits");
        }
        return produits;

    }


}
