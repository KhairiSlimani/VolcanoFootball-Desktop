
package IServices;

import Entities.stade;
import java.util.List;
import Services.StadeService;

/**
 *
 * @author jasser
 */
public interface IServicestade {
    boolean AjouterStade(stade stade);

    /**
     *
     * @param stade
     * @return
     */
    boolean ModifierStade(stade stade);

    boolean SupprimerStade(int id);

    List<stade> AfficherStade();

    stade RecupererStade(int id);

    /**
     *
     * @param nom
     * @return
     */
    stade RecupererStade(String nom);
    
}
