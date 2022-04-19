package entities;

import java.sql.Date;

/**
 *
 * @author jrady
 */

public class Match {

    private int id;
    private String nom_match;
    private String nom_arbitre;
    private String stade;
    private String tour;
  
    private String date;

    
    public Match(int id, String nom_match, String nom_arbitre, String tour, String stade, String date) {
        this.id = id;
        this.nom_match = nom_match;
        this.nom_arbitre = nom_arbitre;
        this.stade = stade;
        this.tour = tour;
        this.date = date;
      
    }
    public Match(){
    }
    
@Override
    public String toString() {
        return "Match{" + "id=" + id + ", nom_match=" + nom_match + ", nom_arbitre=" + nom_arbitre + ", stade=" + stade + ",tour=" + tour + ",date=" + date + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_match() {
        return nom_match;
    }

    public void setNom_match(String nom_match) {
        this.nom_match = nom_match;
    }

    public String getNom_arbitre() {
        return nom_arbitre;
    }

    public void setNom_arbitre(String nom_arbitre) {
        this.nom_arbitre = nom_arbitre;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

}
