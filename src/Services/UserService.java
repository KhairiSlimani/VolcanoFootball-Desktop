package Services;

import Entities.Produit;
import Entities.User;
import IServices.IServiceProduit;
import IServices.IServiceUser;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements IServiceUser {

    Connection con;
    Statement stm;

    public UserService() {
        con = MyDB.getInstance().getCon();
    }


    @Override
    public boolean AjouterUser(User u) {
        try {
            String req = "INSERT INTO `user` (`roles`, `password`, `nom`, `prenom`, `email`, `token`, `enabled`, `username`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, u.getRole());
            pstm.setString(2, u.getPassword());
            pstm.setString(3, u.getNom());
            pstm.setString(4, u.getPrenom());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getToken());
            pstm.setBoolean(7, u.getEnabled());
            pstm.setString(8, u.getUsername());
            pstm.executeUpdate();

            System.out.println("User Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du user");
            return false;
        }

    }

    @Override
    public boolean ModifierUser(User u) {
        try {
            String req = "UPDATE user SET  roles=? , password=? , nom=? , prenom=? , email=? , token=? , enabled=?, username=?   WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, u.getRole());
            pstm.setString(2, u.getPassword());
            pstm.setString(3, u.getNom());
            pstm.setString(4, u.getPrenom());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getToken());
            pstm.setBoolean(7, u.getEnabled());
            pstm.setString(8, u.getUsername());
            pstm.executeUpdate();

            System.out.println("User Modifié");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de modification du user");
            return false;
        }

    }

    @Override
    public boolean SupprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("User Supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de supression du user");
        }
        return false;

    }

    @Override
    public List<User> AfficherUsers() {
        List<User> users = new ArrayList();
        try {
            String req = "SELECT * FROM user";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setRole(rs.getString("roles"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setUsername(rs.getString("username"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des users");
        }
        return users;

    }
}
