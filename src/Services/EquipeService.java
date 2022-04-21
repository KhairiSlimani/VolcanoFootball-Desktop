package Services;

import Entities.Equipe;
import IServices.IServiceEquipe;
import Utils.MyDB;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EquipeService implements IServiceEquipe {
    Connection con;
    Statement stm;

    public EquipeService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public boolean ajouterEquipe(Equipe equipe) {
        try {

            String req = "INSERT INTO `equipe`( `nom_equipe`, `date_creation`, `drapeau_equipe`, `nom_entreneur`,`email`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, equipe.getNom_equipe());
            pstm.setDate(2,  equipe.getDate_creation());
            pstm.setString(3, equipe.getDrapeau_equipe());
            pstm.setString(4, equipe.getNom_entreneur());
            pstm.setString(5, equipe.getEmail());
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
    public boolean modifierEquipe(Equipe equipe) {
        try {
            String req = "UPDATE equipe SET  nom_equipe=? , date_creation=? , drapeau_equipe=? , nom_entreneur=? , email=?   WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, equipe.getNom_equipe());
            pstm.setDate(2, equipe.getDate_creation());
            pstm.setString(3, equipe.getDrapeau_equipe());
            pstm.setString(4, equipe.getNom_entreneur());
            pstm.setString(5, equipe.getEmail());
            pstm.setInt(6,equipe.getId());
            pstm.executeUpdate();
            System.out.println("Modification effectuée");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Echec de modification");
            return false;
        }
    }
    @Override
    public boolean supprimerEquipe(int id) {
        try {
            String req = " DELETE FROM `equipe` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("La supression du equipe est effectuée");
            return true;
        } catch (SQLException ex) {
            System.out.println("Echec de supression");
        }
        return false;
    }
    @Override
    public ObservableList<Equipe> afficherEquipe() {
        ObservableList<Equipe> equipes = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM equipe";
            PreparedStatement pstm = con.prepareStatement(req);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNom_equipe(rs.getString("nom_equipe"));
                equipe.setDate_creation(rs.getDate("date_creation"));
                equipe.setNom_entreneur(rs.getString("nom_entreneur"));
                equipe.setDrapeau_equipe(rs.getString("drapeau_equipe"));
                equipe.setEmail(rs.getString("email"));

                equipes.add(equipe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec");
        }
        return equipes;
    }

}
