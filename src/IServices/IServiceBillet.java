package IServices;

import Entities.Billet;

import java.sql.SQLException;
import java.util.List;

public interface IServiceBillet {
    void ajouterBillet(Billet b)throws SQLException;
    void modifierBillet(Billet b)throws SQLException;
    boolean supprimerB(int id);



    List<Billet> afficherB() throws SQLException;
}
