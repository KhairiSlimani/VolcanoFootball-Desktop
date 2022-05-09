/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Evennement;
import java.util.List;

/**
 *
 * @author HP USER
 */
public interface IServiceEvent {
    boolean ajouter(Evennement evennement);

    boolean modifier(Evennement evennement);

    boolean Supprimer(int id);

    List<Evennement> Afficher();
      Evennement RecupererHebergement(int id);
}
