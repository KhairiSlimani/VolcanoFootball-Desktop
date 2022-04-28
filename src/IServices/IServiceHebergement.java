/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Hebergement;
import java.util.List;

/**
 *
 * @author HP USER
 */
public interface IServiceHebergement {
          boolean ajouterHebergement2(Hebergement hebergement);
              boolean supprimerHebergement(int id);
    List<Hebergement> afficherHebergement();

            boolean modifierHebergement(Hebergement hebergement);

            Hebergement RecupererHebergement(int id);

}
