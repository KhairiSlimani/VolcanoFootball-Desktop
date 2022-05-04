package IServices;

import Entities.Equipe;

import java.util.List;

public interface IServiceEquipe {
    boolean ajouterEquipe(Equipe equipe);

    boolean modifierEquipe(Equipe equipe);

    boolean supprimerEquipe(int id);

    List<Equipe> afficherEquipe();
    List<Equipe> afficherEquipeOrderBy();
}
