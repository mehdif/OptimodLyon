package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * @author Hexanome 4301
 */
public class VueEntrepot extends VuePoint {

	private Rectangle2D shape;
	
	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/
	/**
	 * Constructeur par défaut
	 */
	public VueEntrepot() {
		super();
	}

	/**
	 * Constructeur à trois paramètre
	 * @param x : longitude de l'entrepot
	 * @param y : latitude de l'entrepot
	 * @param adresse : adresse de l'entrepot
	 */
	public VueEntrepot(int x, int y, int adresse) {
		super(x, y, adresse);
	}
	
	/****************************************************
	 *************** Methodes de classes ****************
	 ****************************************************/

	/**
	 * Permet de dessine l'entrepot
	 */
	@Override
	public void dessiner(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int x = (this.getX());
		int y = (this.getY());
		this.shape = new Rectangle2D.Double( (double) x - OFFSET , (double) y - OFFSET, RAYON, RAYON);
		if(clique){
			this.setCouleur(Color.YELLOW);
		}
		else this.setCouleur(this.getCouleurNonClique());
		g2d.setColor(this.getCouleur());
		g2d.fill(this.shape);
	}
}