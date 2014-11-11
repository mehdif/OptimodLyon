package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Before;

import controleur.Application;

/**
 * @author Hexanome 4301
 */
public class VueMenu {

	private JButton btnChargerReseau;
	private JButton btnChargerDemandeLivraison;
	private JButton btnCalculerTournee;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnGenererFeuilleDeRoute;
	private Application application;
	// Redirection de la sortie standard
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/

	public ByteArrayOutputStream getOutContent() {
		return outContent;
	}

	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/

	/**
	 * setUpStreams permettent de récupérer la sortie standard
	 */
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	/**
     * 
     */
	public VueMenu(Application application) {
		this.application = application;
	}

	/**
	 * @return
	 */
	public Boolean onClicCalculerTournee() {
		btnCalculerTournee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		return null;
	}

	/**
	 * @return
	 */
	public Boolean onClicChargerReseau() {
		btnChargerReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setUpStreams();
				application.chargerReseauXML();
				application.vueFenetre.refresh();
			}
		});
		return null;
	}

	/**
	 * @return
	 */
	public Boolean onClicUndo() {
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		return null;
	}

	/**
	 * @return
	 */
	public Boolean onClicRedo() {
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		return null;
	}

	/**
     * 
     */
	public void onClicChargerDemandeLivraison() {
		btnChargerDemandeLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				application.chargerDemandeLivraisonXML();
				application.vueFenetre.refresh();
			}
		});
	}

	/**
     * 
     */
	public void onClicGenererFeuilleDeRoute() {
		btnGenererFeuilleDeRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

	/**
	 * @return
	 */
	public Boolean initialiser(JFrame frame) {
		Boolean creation = true;
		// Vue menu du haut pour les boutons de chargement
		JPanel vueMenuHaut = new JPanel(new GridLayout(1, 2));
		frame.getContentPane().add(vueMenuHaut, BorderLayout.NORTH);

		btnChargerReseau = new JButton("Charger le plan");
		vueMenuHaut.add(btnChargerReseau);
		onClicChargerReseau();

		btnChargerDemandeLivraison = new JButton(
				"Charger les demandes de livraison");
		vueMenuHaut.add(btnChargerDemandeLivraison);
		onClicChargerDemandeLivraison();
		// Fin vue menu du haut

		// Vue menu de gauche pour les bouttons du menu
		JPanel vueMenuGauche = new JPanel(new GridLayout(4, 1));
		frame.getContentPane().add(vueMenuGauche, BorderLayout.WEST);

		btnCalculerTournee = new JButton("Calculer");
		vueMenuGauche.add(btnCalculerTournee);
		onClicCalculerTournee();

		btnUndo = new JButton("Undo");
		vueMenuGauche.add(btnUndo);
		onClicUndo();

		btnRedo = new JButton("Redo");
		vueMenuGauche.add(btnRedo);
		onClicRedo();

		btnGenererFeuilleDeRoute = new JButton("Generer feuille de route");
		vueMenuGauche.add(btnGenererFeuilleDeRoute);
		onClicGenererFeuilleDeRoute();
		// Fin vue menu de gauche
		return creation;
	}

}