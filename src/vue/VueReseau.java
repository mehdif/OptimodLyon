package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
@SuppressWarnings("serial")
public class VueReseau extends JPanel implements VueDessinable {
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
						if (v.getShape().contains(me.getPoint())) {
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
	
	
    /****************************************************
	 *************** Methodes de classes ****************
	 ****************************************************/

	/**
	 * Permet de dessiner les points
	 * @param g 
	 */
	public void dessinerPoints(Graphics g) {
		for (int i = 0; i < vuesPoints.size(); i++) {
			vuesPoints.get(i).dessiner(g);
		}
	}

	/**
	 * Permet de dessiner les troncons
	 * @param g
	 */
	public void dessinerTroncons(Graphics g) {
		for (int j = 0; j < vuesTroncons.size(); j++) {
			vuesTroncons.get(j).dessiner(g);
		}
	}

	/**
	 * Permet de dessiner la tournee une fois calculee
	 * @param g
	 */
	public void dessinerTournee(Graphics g) {
		for (VueItineraire uneVueItineraire : this.vueTournee.vuesItineraire) {
			uneVueItineraire.dessiner(g);
		}
	}

	/**
	 * Permet de charger la vue reseau
	 * @param troncons
	 * @param points
	 */
	


	/**
	 * Permet d'initialiser les elements contenus dans la vue reseau
	 * @param frame : la fenetre de l'application
	 */
	public void initialiser(JFrame frame) {
		frame.getContentPane().add(this, BorderLayout.CENTER);
	}

	/**
	 * Parcours la liste vuesPoints pour que chaque points se dessinent, ensuite
	 * parcours la liste vuesTroncons pour que chaque troncons se dessinent et
	 * finalement demande a la vueTournee de se dessiner si une tournee a deja
	 * ete cree
	 * 
	 * @param g
	 */
	@Override
	public void dessiner(Graphics g) {
		if (this.vueTournee != null) {
			this.dessinerTroncons(g);
			this.dessinerTournee(g);
			this.vueTournee = null;
		} else {
			this.dessinerTroncons(g);
		}
		this.dessinerPoints(g);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.dessiner(g);
	}

	public void setVueTournee(VueTournee vueTournee) {
		this.vueTournee = vueTournee;
	}

}
