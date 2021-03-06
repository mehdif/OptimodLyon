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
    protected final double RAYON = 10;
    protected final double OFFSET = RAYON/2;
    private Color couleur;
    //private Color couleurClique = Color.YELLOW;
    private Color couleurNonClique;
    
    
    protected boolean clique = false;
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
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public void setCouleur(Color uneCouleur){
		this.couleur = uneCouleur;
	}
	
	public Color getCouleurNonClique(){
		return this.couleurNonClique;
	}
	
	public void setCouleurNonClique(Color uneCouleur){
		this.couleurNonClique = uneCouleur;
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
		else this.setCouleur(this.couleurNonClique);
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
        return null;
    }
}
