package IServices;


import Entities.kiosque;

import java.util.List;

public interface IServiceKiosque {

    boolean AjouterKiosque(kiosque kiosque);

    boolean ModifierKiosque(kiosque kiosque);

    boolean SupprimerKiosque(int id);

    List<kiosque> AfficherKiosque();
    List<kiosque> AfficherKiosque(int idUser);
    kiosque RecupererKiosque(int id);
}
