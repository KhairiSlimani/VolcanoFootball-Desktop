/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Agence;

/**
 *
 * @author HP USER
 */
public interface IServiceAgence {
        boolean ajouterAgence2(Agence agence);
              boolean supprimerAgence(int id);
      boolean modifierAgence(Agence agence);
}
