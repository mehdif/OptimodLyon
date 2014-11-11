package vue;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.*;

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

public static boolean zoneEstVide(VueTroncon unTroncon) {
	
	int [] pointsX = {
			unTroncon.getOrigine().getX(),
			unTroncon.getDestination().getX(),
			};
	
	int []pointsY = {
			unTroncon.getOrigine().getY(),
			unTroncon.getDestination().getY(),
			};
	
	Polygon TronconTest = new Polygon(pointsX, pointsY, 4);
	
   Area zoneTest = new Area(TronconTest);
   return zoneTest.isEmpty();
}

}