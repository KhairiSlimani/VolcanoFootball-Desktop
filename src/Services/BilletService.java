package Services;


import Entities.Billet;
import IServices.IServiceBillet;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BilletService implements IServiceBillet {
    Connection con;
    Statement stm;

    public BilletService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public void ajouterBillet(Billet b) throws SQLException  {
        try {

            String req = "INSERT INTO `billetm` (`type`, `prix`) VALUES (?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, b.getType());
            pstm.setString(2, b.getPrix());

            pstm.executeUpdate();
            System.out.println("Ajout effectué");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }


    @Override
    public void modifierBillet( Billet b) throws SQLException {
        String update = "UPDATE `billetm` SET "
                +"`type`=?,`prix`=?"
                + "WHERE id = '" +b.getId()+ "'";
        try {
            PreparedStatement pstm = con.prepareStatement(update);
            pstm.setString(1, b.getType());
            pstm.setString(2, b.getPrix());



            pstm.executeUpdate();
            System.out.println("billet modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }



    public boolean supprimerB(int id) {
        try {
            String req = " DELETE FROM `billetm` WHERE id = " + id + "";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.executeUpdate();
            System.out.println("La supression du billet est effectuée");
            return true;
        } catch (SQLException ex) {
            System.out.println("Echec de supression");
        }
        return false;
    }
    public List<Billet> afficherB() throws SQLException {
        String req = "Select * from `billetm`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Billet> billets = new ArrayList<Billet>();
        while(rst.next()){

            Billet b = new Billet(rst.getInt("id"), rst.getString("type"), rst.getString("prix")  );
            billets.add(b);


        }
        return billets;

    }



}
