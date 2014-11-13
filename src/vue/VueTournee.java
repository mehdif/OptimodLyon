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
	public void dessiner(Graphics g) {

		// TODO

		for (VueItineraire uneVueItineraire : vuesItineraire) {
			uneVueItineraire.dessiner(g);
		};
	}
	
    /**
     * Methode ajoutant une vue itineraire a la vue tournee
     */
	public void ajouterVueItineraire(VueItineraire uneVueItineraire){
		
		this.vuesItineraire.add(uneVueItineraire);
		
	}

}