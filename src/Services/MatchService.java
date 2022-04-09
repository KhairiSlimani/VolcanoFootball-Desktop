package Services;


import Entities.Match;
import IServices.IServiceMatch;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchService implements IServiceMatch {
    Connection con;
    Statement stm;

    public MatchService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public void ajouterMatch(Match t) throws SQLException  {
        try {

            String req = "INSERT INTO `match` (`nom_match`, `nom_arbitre`, `stade`,`tour`, `date` ,`image`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, t.getNom_match());
            pstm.setString(2, t.getNom_arbitre());
            pstm.setString(3, t.getStade());
            pstm.setString(4, t.getTour());
            pstm.setString(5, t.getDate());
            pstm.setString(6, t.getImage());
            pstm.executeUpdate();
            System.out.println("Ajout effectué");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    public void ajouter1()throws SQLException {
        String insert = "INSERT INTO `match` (`id`, `nom_match`, `nom_arbitre`, `stade`, `tour`, `date`, `image`) VALUES (10, 'bibibn', 'fh', 'wwq', 'sss', '2022-04-06', 'aa')";
        try{

            Statement st = con.createStatement();
            st.executeUpdate(insert);
            System.out.println("Agence ajouté avec succés");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierMatch( Match t) throws SQLException {
        String update = "UPDATE `match` SET "
                +"`nom_match`=?,`nom_arbitre`=?,`stade`=?,`tour`=?,`date`=?,`image`=?"
                + "WHERE id = '" +t.getId()+ "'";
        try {
            PreparedStatement pstm = con.prepareStatement(update);
            pstm.setString(1, t.getNom_match());
            pstm.setString(2, t.getNom_arbitre());
            pstm.setString(3, t.getStade());
            pstm.setString(4, t.getTour());
            pstm.setString(5, t.getDate());
            pstm.setString(6, t.getImage());


            pstm.executeUpdate();
            System.out.println("match modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }



    public void supprimerMatch(int id) throws SQLException {
        String delete= "DELETE FROM match WHERE id =?";
        try {
            PreparedStatement pstm = con.prepareStatement(delete);
            pstm.executeUpdate();
            System.out.println("La supression du joueur est effectuée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public boolean supprimerM(int id) {
        try {
            String req = " DELETE FROM `match` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("La supression du joueur est effectuée");
            return true;
        } catch (SQLException ex) {
            System.out.println("Echec de supression");
        }
        return false;
    }
    public List<Match> afficher() throws SQLException {
        String req = "Select * from `match`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Match> matchs = new ArrayList<Match>();
        while(rst.next()){

            Match m = new Match(rst.getInt("id"), rst.getString("nom_match"), rst.getString("nom_arbitre"), rst.getString("stade"),rst.getString("tour") ,rst.getString("date"), rst.getString("image") );
            matchs.add(m);


        }
        return matchs;

    }



}
