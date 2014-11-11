package vue;

import java.awt.Graphics;

/**
 * @author Hexanome 4301
 */
public class VuePoint implements VueCliquable, VueDessinable {

    private int x;
    private int y;
    
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
		int x = (this.x);
		int y = (this.y);
		
		g.fillOval(x, y, 10, 10);
		
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