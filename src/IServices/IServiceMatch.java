package IServices;


import Entities.Match;

import java.sql.SQLException;
import java.util.List;

public interface IServiceMatch {
    void ajouterMatch(Match t)throws SQLException;
    void ajouter1() throws SQLException;
   void modifierMatch(Match t)throws SQLException;
   boolean supprimerM(int id);

    void supprimerMatch(int id) throws SQLException;

    List<Match> afficher() throws SQLException;
}
