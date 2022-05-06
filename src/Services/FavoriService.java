/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Favori;
import Entities.Produit;
import IServices.IServiceFavori;
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

/**
 *
 * @author Khairi
 */
public class FavoriService implements IServiceFavori {

    Connection con;
    Statement stm;

    public FavoriService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean AjouterFavori(Favori f) {
        
        try {
            String req = "INSERT INTO `favori` (`id`, `user_id`, `produit_id`) VALUES (?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setInt(1, f.getId());
            pstm.setInt(2, f.getIdUser());
            pstm.setInt(3, f.getIdProduit());
            pstm.executeUpdate();

            System.out.println("Favori Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du favori");
            return false;
        }

    }

    @Override
    public boolean SupprimerFavori(int id) {
        try {
            String req = "DELETE FROM `favori` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("Favori Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression");
        }
        return false;
    }

    @Override
    public List<Favori> AfficherFavoris() {
        
        List<Favori> favoris = new ArrayList();
        try {
            String req = "SELECT * FROM favori";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Favori f = new Favori();
                f.setId(rs.getInt("id"));
                f.setIdUser(rs.getInt("user_id"));
                f.setIdProduit(rs.getInt("produit_id"));

                favoris.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des favoris");
        }
        return favoris;

    }

    @Override
    public List<Favori> AfficherFavoris(int idUser) {
        
        List<Favori> favoris = new ArrayList();
        try {
            String req = "SELECT * FROM favori WHERE user_id = " + idUser + "";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Favori f = new Favori();
                f.setId(rs.getInt("id"));
                f.setIdUser(rs.getInt("user_id"));
                f.setIdProduit(rs.getInt("produit_id"));

                favoris.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des favoris");
        }
        return favoris;

    }
    
}
