
package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * @author Hexanome 4301
 */
public class VueTroncon implements VueDessinable {

    public VuePoint origine;
    public VuePoint destination;
	public static final float EPAISSEUR_TRAIT_TRONCON_RESEAU = 2.0f;
	public static final float EPAISSEUR_TRAIT_TRONCON_TOURNEE = 3.0f;
	private Color couleur;
	
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
    public VueTroncon(int x1,int y1,int adresse1,int x2, int y2, int adresse2){
    	origine = new VuePoint(x1,y1,adresse1);
    	destination = new VuePoint(x2,y2,adresse2);
    	couleur = Color.BLACK;
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
	public void dessiner(Graphics g) {
		// TODO on ne peux pas caster un double en int directement. 
		Graphics2D g2d = (Graphics2D) g;
		int x1 = (this.origine.getX());
		int y1 = (this.origine.getY());
		int x2 = (this.destination.getX());
		int y2 = (this.destination.getY());
		g2d.setColor(this.couleur);
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT_TRONCON_RESEAU));
		g2d.drawLine(x1, y1, x2, y2);
		
	}
	
    /**
     * Dessine pour un troncon une ligne decalee en cas de superposition
     * 
     * @param g
     * @param decalage
     * @return bool, égal à true si la ligne s'est correctement dessiné, false sinon
     */
	public void dessiner(Graphics g, int decalage) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int x1 = (this.origine.getX());
		int y1 = (this.origine.getY());
		int x2 = (this.destination.getX());
		int y2 = (this.destination.getY());
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT_TRONCON_TOURNEE));
		g.drawLine(x1 + decalage, y1 + decalage, x2 + decalage, y2 + decalage);
		
	}
	
    /**
     * Change la couleur de la vue troncon
     */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	

}
