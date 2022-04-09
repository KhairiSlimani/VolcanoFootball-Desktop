package Pidev;

import Entities.Match;
import Services.MatchService;
import Entities.Billet;
import Services.BilletService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pidev {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Match m1 =new Match("assemm", "fh", "wwq", "sss", "2022-04-06", "aja");
        Billet b1 =new Billet(2,"vip", "2500");
        MatchService ps = new MatchService();
        BilletService b = new BilletService();

        try {

            //ps.ajouterMatch(m1);
            //b.ajouterBillet(b1);
            //ps.modifierMatch(m1);
           // b.modifierBillet(b1);
            //ps.ajouter1();
            //ps.supprimerMatch(10);
            //ps.supprimerM(10);
            b.supprimerB(2);
            System.out.println(b.afficherB().toString());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }



    }




