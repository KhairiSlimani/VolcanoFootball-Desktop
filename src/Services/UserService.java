package Services;

import Entities.User;
import Gui.AlertsController;
import Gui.SessionManager;
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
            String req = "INSERT INTO `user` (`role`, `password`, `nom`, `prenom`, `email`, `token`, `activated`, `username`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, u.getRole());
            pstm.setString(2, u.getPassword());
            pstm.setString(3, u.getNom());
            pstm.setString(4, u.getPrenom());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getToken());
            pstm.setInt(7, u.getActivated());
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
            String req = "UPDATE user SET  role=? , password=? , nom=? , prenom=? , email=? , token=? , activated=?, username=? WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, u.getRole());
            pstm.setString(2, u.getPassword());
            pstm.setString(3, u.getNom());
            pstm.setString(4, u.getPrenom());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getToken());
            pstm.setInt(7, u.getActivated());
            pstm.setString(8, u.getUsername());
            pstm.setInt(9, u.getId());
            pstm.executeUpdate();

            
            System.out.println("User Modifié "+u.getId());
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
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setActivated(rs.getInt("activated"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'affichage des users");
        }
        return users;
    }

    @Override
    public boolean Login(String username, String password) {
        
        try {
            String req = "SELECT * FROM user WHERE username=\""+username+"\";";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) 
            {
                if(rs.getString("password").equalsIgnoreCase(password)){
                    if(rs.getInt("activated") == 0){
                        AlertsController.get().Alert(".","Erreur","Ce compte n'est pas encore activé!");
                        return false;
                    }
                    else
                    {
                        SessionManager.get().StartSession(rs.getInt("id"),rs.getString("username"), rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("role"));
                        return true;
                    }
                }
                else
                {
                    AlertsController.get().Alert(".","Erreur","Mauvais mot de passe!");
                    return false;
                }
            }
            
            AlertsController.get().Alert(".","Erreur","User n'existe pas!");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur Login");
        }

        return false;
    }

    @Override
    public boolean Register(User u) {
        
        try {
            String req = "SELECT * FROM user WHERE username=\""+u.getUsername()+"\";";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) 
            {
                AlertsController.get().Alert(".","Erreur","Username existe déja!");
                return false;

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur Register");
        }
        
        try {
            String req = "INSERT INTO `user` (`role`, `password`, `nom`, `prenom`, `email`, `token`, `activated`, `username`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, u.getRole());
            pstm.setString(2, u.getPassword());
            pstm.setString(3, u.getNom());
            pstm.setString(4, u.getPrenom());
            pstm.setString(5, u.getEmail());
            pstm.setString(6, u.getToken());
            pstm.setInt(7, u.getActivated());
            pstm.setString(8, u.getUsername());
            pstm.executeUpdate();

            AlertsController.get().Alert("information","Succès","Compte Créer!");
            System.out.println("User Ajouté");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur d'ajout du user");
            return false;
        }

    }

    @Override
    public User RecupererUser(String username) {
        User user = new User();
        try {
            String req = "SELECT * FROM user WHERE username=\""+username+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setActivated(rs.getInt("activated"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return user;

    }

    @Override
    public User RecupererUser(int id) {
        
        User user = new User();
        try {
            String req = "SELECT * FROM user WHERE id=\""+id+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setActivated(rs.getInt("activated"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return user;

    }

    @Override
    public User RecupererUserEmail(String email) {
        
                User user = new User();
        try {
            String req = "SELECT * FROM user WHERE email=\""+email+"\";";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setActivated(rs.getInt("activated"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de récuperation");
        }
        return user;

    }

    
    
}
