package Pidev;
import Entities.Joueur;
import Services.JoueurService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Pidev {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// String nom_joueur,String prenom_joueur,int age, String position,String photo,int equipe, String Description

        Joueur p2 = new Joueur(6,"Foulen","BEN FOULEN",33,"aaa","aaaaa",1,"aaaaaaaaaa");
        Joueur p1 = new Joueur("Foulen","BEN FOULEN",33,"aaa","aaaaa",1,"aaaaaaaaaa");
        JoueurService ps = new JoueurService();

        //ps.ajouterJoueur(p2);
        //System.out.println("personne ajoutee");
        ps.modifierJoueur(p2);
        //ps.supprimerJoueur(5);
        System.out.println(ps.afficherJoueurs().toString());



    }


}

