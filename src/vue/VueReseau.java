package vue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Point;
import modele.Troncon;

/**
 * @author Hexanome 4301
 */
public class VueReseau  extends JPanel implements VueDessinable {

	private static final long serialVersionUID = 1L;
    
	private ArrayList <VuePoint>  vuesPoints = new ArrayList<VuePoint>();
    private ArrayList <VueTroncon>  vuesTroncons = new ArrayList<VueTroncon>();;
    private VueTournee vueTournee;
    
	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/
	
	/**
	 * Constructeur par defaut
	 */
	public VueReseau(){
		
	}
	/**
	 * Constructeur appelant la méthode d'initialisation qui insère la vueReseau
	 * dans une JFrame
	 * 
	 * @param frame , le cadre JFrame contenant la vueReseau
	 */
    public VueReseau(JFrame frame) {
    	initialiser(frame);
    }
    
    /**
     * Constructeur de la vueReseau à partir des paramètres précédement 
     * chargés dans le modèle
     * 
     * @param troncons
     * @param points
     */
    public VueReseau(List<Troncon> troncons,Map<Integer, Point> points) {
   		
    	//Remplissage de la liste vuesPoints
    		
		Set<Integer> listKeys=points.keySet();  // Obtenir la liste des cles
		Iterator<Integer> it=listKeys.iterator();
		
		// Parcourir les cles et afficher les entrees de chaque cle;
		while(it.hasNext())
		{
			Object key= it.next();
			Point p = points.get(key);
			
			VuePoint vuePoint = new VuePoint(p.getLongitude(),p.getLatitude());
			if(vuePoint != null){
				this.vuesPoints.add(vuePoint);
			}
		}
		
		//Remplissage de la liste vueTroncons
		for(int i=0;i<troncons.size();i++){
			Troncon t = troncons.get(i);
			Point origine = t.getOrigine();
			Point destination = t.getDestination();
			
//			VueTroncon vueTroncon = new VueTroncon(origine.getLongitude(),origine.getLatitude(),destination.getLongitude(),destination.getLatitude());
//			if(vueTroncon != null){
//				vuesTroncons.add(vueTroncon);
//			}
		}
    }

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/
    public List<VuePoint> getVuesPoints() {
		return vuesPoints;
	}
	public List<VueTroncon> getVuesTroncons() {
		return vuesTroncons;
	}
	public VueTournee getVueTournee() {
		return vueTournee;
	}

    /**
     * Méthode d'initialisation. Insère le JPanel: vueReseau au centre du cadre frame
     * passé en paramètre
     * 
     * @param frame
     * 
     * @return bool , égal à true si l'initialisation s'est correctement effectué, false sinon
     */
    public Boolean initialiser(JFrame frame) {
    	boolean bool = true;
		frame.getContentPane().add(this, BorderLayout.CENTER);
        return bool;
    }

    
	/**
     * Parcours la liste vuesPoints pour que chaque points se dessinent,
     * ensuite parcours la liste vuesTroncons pour que chaque troncons se dessinent
     * et finalement demande a la vueTournee de se dessiner si une tournee a deja ete cree
     * 
     * @param g
     * @return bool true si tout les �l�ments ont pu se dessiner
     */
	@Override
	public void dessiner(Graphics g) {
		for(int i=0; i<vuesPoints.size(); i++){
            vuesPoints.get(i).dessiner(g);
		}
//		for(int j=0; j<vuesTroncons.size();j++){
//			vuesTroncons.get(j).dessiner(g);
//		}
//		vueTournee.dessiner(g);
		
	}

    @Override
	public void paintComponent(Graphics g) {
    	this.dessiner(g);
	}
}
