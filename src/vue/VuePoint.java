package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * @author Hexanome 4301
 */
public class VuePoint implements VueCliquable, VueDessinable {

    private int x;
    private int y;
    private double rayon;
    private final double OFFSET = 5;
    
	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/
	
	/**
	 * Constructeur par défaut de VuePoint
	 */
	public VuePoint(){
		
	}
	
    /**
     * Constructeur de VuePoint à partir de coordonnées x et y 
     * 
     * @param x
     * @param y
     */
    public VuePoint(int x, int y) {
    	this.x = x;
    	this.y = y;
    	this.rayon = 10;
    }


	/****************************************************
	 **************** Getter & Setter *******************
	 ****************************************************/

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Dessine un rond pour une vuePoint a partir des coordonnees x et y en attribut de
	 * note: le rayon est un rayon defini par defaut
	 * 
	 * @param g
	 * @return bool, égal à true si le rond s'est correctement dessiné, false sinon
	 */
	@Override
    public void dessiner(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int x = (this.x);
		int y = (this.y);
		Ellipse2D rond = new Ellipse2D.Double( (double) x - OFFSET , (double) y - OFFSET, this.rayon, this.rayon);
		//g.fillOval(x - offset , y - offset, 10, 10);
		g2d.fill(rond);
    }
	
	/**
     * @return
     */
    public Boolean onClicPoint() {
        // TODO implement here
        return null;
    }



	public Boolean estClique() {
		// TODO Auto-generated method stub
		return null;
	}

}