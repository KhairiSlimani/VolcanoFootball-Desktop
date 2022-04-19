package IServices;

import Entities.Joueur;

import java.util.List;

public interface IServiceJoueur {
    boolean ajouterJoueur(Joueur joueur);

    boolean modifierJoueur(Joueur joueur);

    boolean supprimerJoueur(int id);

    List<Joueur> afficherJoueurs();
}
