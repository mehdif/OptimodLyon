package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import modele.Itineraire;

/**
 * @author Hexanome 4301
 */
public class VueTournee implements VueDessinable {

	/**
     * 
     */
    public List <VueItineraire> vuesItineraire =new ArrayList<VueItineraire>();
    /**
     * 
     */
    public VueTournee() {
    	 
    }
    
    public VueTournee(List<Itineraire> list) {
    	for(Itineraire itineraireCourant : list) {
    		VueItineraire uneVueItineraire = new VueItineraire(
    				itineraireCourant.getTroncons(), Color.RED);
    		vuesItineraire.add(uneVueItineraire);
    	}    	
    }

    

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