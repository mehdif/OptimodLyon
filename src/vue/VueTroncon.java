package vue;

import java.awt.Graphics;


/**
 * @author Hexanome 4301
 */
public class VueTroncon implements VueDessinable {

    /**
     * Constructeur par defaut de VueTroncon
     */
    public VueTroncon() {
    }
    
    /**
     * 
     * Constructeur de VueTroncon à partir des coordonnees brut d'un point d'origine et d'un point de destination
     * 
     * @param x1 coordonnee x de la VuePoint origine
     * @param y1 coordonnee y de la VuePoint origine
     * @param x2 coordonnee x de la VuePoint destination
     * @param y2 coordonnee y de la VuePoint destination
     */
    public VueTroncon(int x1,int y1,int x2, int y2){
    	//origine = new VuePoint(x1,y1);
    	//destination = new VuePoint(x2,y2);
    }
    /**
     * 
     */
    public VuePoint origine;

    /**
     * 
     */
    public VuePoint destination;

    /**
     * Dessine pour un troncon une ligne reliant la vue point origine a la vue point destination
     * 
     * @param g
     * @return true si la ligne s'est correctement dessine
     */
	@Override
	public Boolean dessiner(Graphics g) {
		
		// TODO on ne peux pas caster un double en int directement. 
		int x1 = (int) Math.round(this.origine.getX());
		int y1 = (int) Math.round(this.origine.getY());
		int x2 = (int) Math.round(this.destination.getX());
		int y2 = (int) Math.round(this.destination.getY());
		
		g.drawLine(x1, y1, x2, y2);
		
		return true;
	}

}