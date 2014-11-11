package vue;

import java.awt.Graphics;
import java.util.*;

/**
 * @author Hexanome 4301
 */
public class VueTournee implements VueDessinable {

    /**
     * 
     */
    public VueTournee() {
    }

    /**
     * 
     */
    public List <VueItineraire> vuesItineraire;

	@Override
	public Boolean dessiner(Graphics g) {

		// TODO

		for (VueItineraire uneVueItineraire : vuesItineraire) {
			uneVueItineraire.dessiner(g);
		};
		return null;
	}

}