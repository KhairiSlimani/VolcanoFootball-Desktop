package IServices;

import Entities.Commande;

import java.util.List;

public interface IServiceCommande {

    boolean AjouterCommande(Commande commande);

    boolean ModifierCommande(Commande commande);

    boolean SupprimerCommande(int id);

    List<Commande> AfficherCommandes();
    List<Commande> AfficherCommandes(int idUser);
    Commande RecupererCommande(int id);
}
