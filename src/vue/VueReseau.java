package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Application;
import modele.Point;
import modele.Troncon;

/**
 * VueReseau hérite de la classe JPanel et de ses méthodes de dessin. La classe
 * est constituée d'une liste de vuesPoints et une liste de vuesTroncons.
 * Chacune des vues de ces liste sont appelées à se dessiner sur le JPanel grâce
 * à la méthode dessiner implémentée depuis l'interface VueDessinable
 * 
 * @author Hexanome 4301
 */
public class VueReseau extends JPanel implements VueDessinable {

	private static final long serialVersionUID = 1L;

	private ArrayList<VuePoint> vuesPoints = new ArrayList<VuePoint>();
	private ArrayList<VueTroncon> vuesTroncons = new ArrayList<VueTroncon>();;
	private VueTournee vueTournee;
	private VuePoint vuePointClique;
	private Application application;

	/****************************************************
	 ****************** Constructeurs ********************
	 ****************************************************/

	/**
	 * Constructeur par defaut
	 */
	public VueReseau(final Application application) {
		this.application = application;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (vuePointClique != null) {
					VuePoint vTemp = vuePointClique;
					vTemp.setCouleur(Color.BLACK);
					vTemp.setClique(false);
					vuesPoints.remove(vuePointClique);
					vuesPoints.add(vTemp);
					repaint();
				}
				for (VuePoint v : vuesPoints) {
					if (null != v.getShape() && null != me.getPoint()) {
						if (v.getShape().contains(me.getPoint())) {// check if
																	// mouse is
																	// clicked
																	// within
																	// shape
							// we can either just print out the object class
							// name
							vuePointClique = v;
							v.onClique();
							application.vueFenetre.adressePointClique();
							repaint();
						}
					}
				}
				

			}

			public void mouseEntered(MouseEvent me) {
				for (VuePoint v : vuesPoints) {
					if (null != v.getShape() && null != me.getPoint()) {
						if (v.getShape().contains(me.getPoint())) {
							setCursor(new java.awt.Cursor(
									java.awt.Cursor.HAND_CURSOR));
						}
					}
				}
			}
		});
	}

	/**
	 * Dessine tous les points du réseau
	 */

	public void dessinerPoints(Graphics g) {

		for (int i = 0; i < vuesPoints.size(); i++) {
			vuesPoints.get(i).dessiner(g);
		}
	}

	/**
	 * Dessine tous les troncons du réseau
	 */
	public void dessinerTroncons(Graphics g) {

		for (int j = 0; j < vuesTroncons.size(); j++) {
			vuesTroncons.get(j).dessiner(g);
		}
	}

	public void dessinerTournee(Graphics g) {

		for (VueItineraire uneVueItineraire : this.vueTournee.vuesItineraire) {
			uneVueItineraire.dessiner(g);
		}
	}

	/**
	 * Constructeur de la vueReseau à partir des paramètres précédement chargés
	 * dans le modèle
	 * 
	 * @param troncons
	 * @param points
	 */
	public void chargerVueReseau(List<Troncon> troncons,
			Map<Integer, Point> points) {

		this.vuesPoints.clear();
		this.vuesTroncons.clear();
		// Remplissage de la liste vuesPoints

		Set<Integer> listKeys = points.keySet(); // Obtenir la liste des cles
		Iterator<Integer> it = listKeys.iterator();

		// Parcourir les cles et afficher les entrees de chaque cle;
		while (it.hasNext()) {
			Object key = it.next();
			Point p = points.get(key);

			VuePoint vuePoint = new VuePoint(p.getLongitude(), p.getLatitude(),
					p.getAdresse());
			if (vuePoint != null) {
				this.vuesPoints.add(vuePoint);
			}
		}

		// Remplissage de la liste vueTroncons
		for (int i = 0; i < troncons.size(); i++) {
			Troncon t = troncons.get(i);
			Point origine = new Point(t.getOrigine().getLongitude(), t
					.getOrigine().getLatitude(), t.getOrigine().getAdresse());
			Point destination = new Point(t.getDestination().getLongitude(), t
					.getDestination().getLatitude(), t.getDestination()
					.getAdresse());

			VueTroncon vueTroncon = new VueTroncon(origine.getLongitude(),
					origine.getLatitude(), origine.getAdresse(),
					destination.getLongitude(), destination.getLatitude(),
					destination.getAdresse());
			if (vueTroncon != null) {
				vuesTroncons.add(vueTroncon);
			}
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

	public VuePoint getVuePointClique() {
		return vuePointClique;
	}

	/**
	 * Méthode d'initialisation. Insère le JPanel: vueReseau au centre du cadre
	 * frame passé en paramètre
	 * 
	 * @param frame
	 * 
	 * @return bool , égal à true si l'initialisation s'est correctement
	 *         effectué, false sinon
	 */
	public Boolean initialiser(JFrame frame) {
		boolean bool = true;
		frame.getContentPane().add(this, BorderLayout.CENTER);
		return bool;
	}

	/**
	 * Parcours la liste vuesPoints pour que chaque points se dessinent, ensuite
	 * parcours la liste vuesTroncons pour que chaque troncons se dessinent et
	 * finalement demande a la vueTournee de se dessiner si une tournee a deja
	 * ete cree
	 * 
	 * @param g
	 * @return bool true si tout les �l�ments ont pu se dessiner
	 */
	@Override
	public void dessiner(Graphics g) {
		this.dessinerTroncons(g);
		this.dessinerPoints(g);
		if (this.vueTournee != null)
			this.dessinerTournee(g);
	}

	// public List<VuePoint> trouverVue(java.awt.Point p) {
	// List<VuePoint> l = new ArrayList<VuePoint>();
	// for (Iterator<VuePoint> iter = vuesPoints.iterator(); iter.hasNext();) {
	// VuePoint element = (VuePoint) iter.next();
	// if(this.contains(p)){
	// l.add(element);
	// }
	// }
	// return l;
	// }

	@Override
	public void paintComponent(Graphics g) {
		this.dessiner(g);
	}

	public void setVueTournee(VueTournee vueTournee) {
		this.vueTournee = vueTournee;
	}

}
