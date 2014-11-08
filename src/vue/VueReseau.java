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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    public VueReseau(JFrame frame) {
    	initialiser(frame);
    }
    
    public VueReseau(List<Troncon> troncons,Map<Integer, Point> points) {
   		
    	//Remplissage de la liste vuesPoints
    		
		Set<Integer> listKeys=points.keySet();  // Obtenir la liste des cles
		Iterator<Integer> it=listKeys.iterator();
		
		// Parcourir les cles et afficher les entrees de chaque cle;
		while(it.hasNext())
		{
			Object key= it.next();
			Point p = points.get(key);
			//TODO pb de cast int to double
			//VuePoint vuePoint = new VuePoint((p.getLongitude(),p.getLatitude());
			//vuesPoints.add(vuePoint);
		}
		
		//Remplissage de la liste vueTroncons
		//TODO parcours de la liste de troncons
    }
    /**
     * 
     */
    public List <VuePoint>  vuesPoints;

    /**
     * 
     */
    public List <VueTroncon>  vuesTroncons;

    /**
     * 
     */
    public VueTournee vueTournee;


    /**
     * @return
     */
    public Boolean initialiser(JFrame frame) {
		JPanel vueReseau = new JPanel();
		frame.getContentPane().add(vueReseau, BorderLayout.CENTER);
        return null;
    }

    /**
     * Parcours la liste vuesPoints pour que chaque points se dessinent,
     * ensuite parcours la liste vuesTroncons pour que chaque troncons se dessinent
     * et finalement demande a la vueTournee de se dessiner si une tournee a deja ete cree
     * 
     * @param g
     * @return true si tout les éléments ont pu se dessiner
     */
	@Override
	public Boolean dessiner(Graphics g) {
		for(int i=0; i<vuesPoints.size(); i++){
            vuesPoints.get(i).dessiner(g);
		}
		for(int j=0; j<vuesTroncons.size();j++){
			vuesTroncons.get(j).dessiner(g);
		}
		vueTournee.dessiner(g);
		
		return true;
	}

    @Override
	public void paintComponent(Graphics g) {
    	this.dessiner(g);
	}
}