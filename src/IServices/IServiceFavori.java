/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Favori;
import java.util.List;

/**
 *
 * @author Khairi
 */
public interface IServiceFavori {
    
    boolean AjouterFavori(Favori favori);

    boolean SupprimerFavori(int id);

    List<Favori> AfficherFavoris();
    List<Favori> AfficherFavoris(int idUser);
}
