package Services;

import Entities.Joueur;
import IServices.IServiceJoueur;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoueurService implements IServiceJoueur {
    Connection con;
    Statement stm;

    public JoueurService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public boolean ajouterJoueur(Joueur joueur) {
        try {

            String req = "INSERT INTO `joueur`( `nom_joueur`, `prenom_joueur`, `age`,  `position`, `photo`, `description`,`equipe_id`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, joueur.getNom_joueur());
            pstm.setString(2, joueur.getPrenom_joueur());
            pstm.setInt(3, joueur.getAge());
            pstm.setString(4, joueur.getPosition());
            pstm.setString(5,joueur.getPhoto());
            pstm.setString(6, joueur.getDescription());
            pstm.setInt(7, joueur.getEquipe());
            pstm.executeUpdate();
            System.out.println("Ajout effectué");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Echec d'ajout");
            return false;
        }
    }
    @Override
    public boolean modifierJoueur(Joueur joueur) {
        try {
            String req = "UPDATE joueur SET  nom_joueur=? , prenom_joueur=? , age=? , position=? , photo=? , description=?, equipe_id=? WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, joueur.getNom_joueur());
            pstm.setString(2, joueur.getPrenom_joueur());
            pstm.setInt(3, joueur.getAge());
            pstm.setString(4, joueur.getPosition());
            pstm.setString(5,joueur.getPhoto());
            pstm.setString(6, joueur.getDescription());
            pstm.setInt(7,joueur.getEquipe());
            pstm.setInt(8,joueur.getId());
            pstm.executeUpdate();
            System.out.println("Modification effectuée");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec de modification");
            return false;
        }
    }
    @Override
    public boolean supprimerJoueur(int id) {
        try {
            String req = " DELETE FROM `joueur` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("La supression du joueur est effectuée");
            return true;
        } catch (SQLException ex) {
            System.out.println("Echec de supression");
        }
        return false;
    }
    @Override
    public List<Joueur> afficherJoueurs() {
        List<Joueur> joueur = new ArrayList();
        try {
            String req = "SELECT * FROM joueur";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Joueur j = new Joueur();
                j.setNom_joueur(rs.getString("nom_joueur"));
                j.setPrenom_joueur(rs.getString("prenom_joueur"));
                j.setAge(rs.getInt("age"));
                j.setDescription(rs.getString("description"));
                j.setPosition(rs.getString("position"));
                j.setPhoto(rs.getString("photo"));
                j.setEquipe(rs.getInt("equipe_id"));

                joueur.add(j);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec");
        }
        return joueur;
    }

}
