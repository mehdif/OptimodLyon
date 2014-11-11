package vue;

import java.awt.Color;
import java.awt.Graphics;


/**
 * @author Hexanome 4301
 */
public class VueTroncon implements VueDessinable {

    public VuePoint origine;
    public VuePoint destination;
	public Color couleur;
	
	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/
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
    	origine = new VuePoint(x1,y1);
    	destination = new VuePoint(x2,y2);
    }

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/
    
	public VuePoint getOrigine() {
		return origine;
	}

	public VuePoint getDestination() {
		return destination;
	}
    
    
    /**
     * Dessine pour un troncon une ligne reliant la vue point origine a la vue point destination
     * 
     * @param g
     * @return bool, égal à true si la ligne s'est correctement dessiné, false sinon
     */
	@Override
	public Boolean dessiner(Graphics g) {
		boolean bool = true;
		// TODO on ne peux pas caster un double en int directement. 
		int x1 = (this.origine.getX());
		int y1 = (this.origine.getY());
		int x2 = (this.destination.getX());
		int y2 = (this.destination.getY());
		g.drawLine(x1, y1, x2, y2);
		
		return bool;
	}
	
	public void dessiner(Graphics g, int decalage){
		
		int x1 = (this.origine.getX());
		int y1 = (this.origine.getY());
		int x2 = (this.destination.getX());
		int y2 = (this.destination.getY());
		
		g.drawLine(x1 + decalage, y1 + decalage, x2 + decalage, y2 + decalage);
	}


}