package IServices;

import Entities.Produit;
import Entities.User;

import java.util.List;

public interface IServiceProduit {

    boolean AjouterProduit(Produit produit);

    boolean ModifierProduit(Produit produit);

    boolean SupprimerProduit(int id);

    List<Produit> AfficherProduits();

    Produit RecupererProduit(int id);
    Produit RecupererProduit(String nom);
}
