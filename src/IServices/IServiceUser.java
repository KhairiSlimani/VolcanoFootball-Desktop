package IServices;

import Entities.User;

import java.util.List;

public interface IServiceUser {

    boolean AjouterUser(User user);

    boolean ModifierUser(User user);

    boolean SupprimerUser(int id);

    List<User> AfficherUsers();

}
