package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;



/**
 * @author Hexanome 4301
 */
public class VuePoint implements VueCliquable, VueDessinable {

	private int x;
    private int y;
    private int adresse;
    private final double RAYON = 10;
    private final double OFFSET = RAYON/2;
    private Color couleur = Color.BLACK;
    
    private boolean clique = false;
    private Ellipse2D shape;
    
	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/
	
	public boolean isClique() {
		return clique;
	}

	public void setClique(boolean clique) {
		this.clique = clique;
	}

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
    public VuePoint(int x, int y, int adresse) {
    	this.x = x;
    	this.y = y;
    	this.adresse = adresse;
    }


	/****************************************************
	 **************** Getter & Setter *******************
	 ****************************************************/

    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getAdresse() {
		return adresse;
	}
	
	public void setCouleur(Color uneCouleur){
		this.couleur = uneCouleur;
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
		this.shape = new Ellipse2D.Double( (double) x - OFFSET , (double) y - OFFSET, RAYON, RAYON);
	
		if(clique){
			this.setCouleur(Color.YELLOW);
		}
		g2d.setColor(this.couleur);
		g2d.fill(this.shape);
    }

	public Ellipse2D getShape() {
		return shape;
	}
	
	public void paintComponent(Graphics g) {

		dessiner(g);
	}

	/**
     * @return
     */
	@Override
    public Boolean onClique() {
        this.clique = true;
        //System.out.println("Point x: " + this.getX() +"\ny : " + this.getY() );
        return null;
    }
}
