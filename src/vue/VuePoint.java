package vue;

import java.awt.Graphics;

/**
 * @author Hexanome 4301
 */
public class VuePoint implements VueCliquable, VueDessinable {

	/**
	 * Constructeur par defaut de VuePoint
	 */
	public VuePoint(){
		
	}
	
    /**
     * Constructeur de VuePoint a partir de coordonnees x et y 
     * 
     * @param x
     * @param y
     */
    public VuePoint(Double x, Double y) {
    	this.x = x;
    	this.y = y;
    }

    /**
     * 
     */
    private Double x;

    /**
     * 
     */
    private Double y;


    public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
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

	/**
	 * Dessine un rond pour une vuePoint a partir des coordonnees x et y en attribut de
	 * note: le rayon est un rayon defini par defaut
	 * 
	 * @param g
	 * @return true si le rond s'est correctement dessine
	 */
	@Override
    public Boolean dessiner(Graphics g) {
		
		// TODO on ne peux pas caster un double en int directement.
		int x = (int) Math.round(this.x);
		int y = (int) Math.round(this.y);
		
		g.fillOval(x, y, 10, 10);
		
        return true;
    }
}