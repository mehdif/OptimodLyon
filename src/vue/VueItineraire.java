package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.util.*;

import modele.Troncon;

/**
 * @author Hexanome 4301
 */
public class VueItineraire implements VueDessinable {

    /**
     * 
     */
    public VueItineraire() {
    }

    /**
     * Constructeur de VueItineraire à partir de troncons
     */
    public VueItineraire( List<Troncon> desTroncons, Color uneCouleur ) {
    	
    	this.vuesTroncon = new ArrayList<VueTroncon>();
    	
    	for(Troncon unTroncon : desTroncons) {
    		
    		VueTroncon uneVueTroncon = new VueTroncon(
    				unTroncon.getOrigine().getLongitude(), 
    				unTroncon.getOrigine().getLatitude(),
    				unTroncon.getOrigine().getAdresse(),
    				unTroncon.getDestination().getLongitude(),
    				unTroncon.getDestination().getLatitude(),
    				unTroncon.getDestination().getAdresse());
    		uneVueTroncon.setCouleur(uneCouleur);
    		this.vuesTroncon.add(uneVueTroncon);
    	}
    }

    /**
     * 
     */
    public List <VueTroncon> vuesTroncon;

    public static final int DECALAGE = 1;
    
	@Override
	public void dessiner(Graphics g) {
		// TODO Auto-generated method stub
		for (VueTroncon uneVueTroncon : vuesTroncon) {
			if (zoneEstVide(uneVueTroncon)) {

				uneVueTroncon.dessiner(g);
			} else {

				uneVueTroncon.dessiner(g, DECALAGE);
			}
		};
	}

    /**
     * @author Vincent
     * @param unTroncon
     * test de l'existence d'une ligne pour eviter la superposition
     */
public static boolean zoneEstVide(VueTroncon unTroncon) {
	
	Line2D TronconTest = new Line2D.Double((double) unTroncon.getOrigine().getX(), (double) unTroncon.getOrigine().getY(), (double) unTroncon.getDestination().getX(),(double) unTroncon.getDestination().getY());
   Area zoneTest = new Area(TronconTest);
   return zoneTest.isEmpty();
}

}