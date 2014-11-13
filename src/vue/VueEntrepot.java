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

    /**
     * 
     */
private Rectangle2D shape;

	public VueEntrepot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VueEntrepot(int x, int y, int adresse) {
		super(x, y, adresse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dessiner(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int x = (this.getX());
		int y = (this.getY());
	
		this.shape = new Rectangle2D.Double( (double) x - OFFSET , (double) y - OFFSET, RAYON, RAYON);
	
		if(clique){
			this.setCouleur(Color.YELLOW);
		}
		g2d.setColor(this.couleur);
		g2d.fill(this.shape);
	}


}