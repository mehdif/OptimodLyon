package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Reseau;

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

    /**
     * 
     */
    public VueMenu(JFrame frame) {
    	initialiser(frame);
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
    public Boolean onClicChargerReseau(final Reseau reseau) {
		btnChargerReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reseau.chargerReseauXML();
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
    	//Vue menu du haut pour les boutons de chargement
		JPanel vueMenuHaut = new JPanel(new GridLayout(1, 2));
		frame.getContentPane().add(vueMenuHaut, BorderLayout.NORTH);
		
		btnChargerReseau = new JButton("Charger le plan");
		vueMenuHaut.add(btnChargerReseau);

		btnChargerDemandeLivraison = new JButton("Charger les demandes de livraison");
		vueMenuHaut.add(btnChargerDemandeLivraison);
		//Fin vue menu du haut
		
		//Vue menu de gauche pour les bouttons du menu
		JPanel vueMenuGauche = new JPanel(new GridLayout(4, 1));
		frame.getContentPane().add(vueMenuGauche, BorderLayout.WEST);

		btnCalculerTournee = new JButton("Calculer");
		vueMenuGauche.add(btnCalculerTournee);

		btnUndo = new JButton("Undo");
		vueMenuGauche.add(btnUndo);

		btnRedo = new JButton("Redo");
		vueMenuGauche.add(btnRedo);
		
		btnGenererFeuilleDeRoute = new JButton("Generer feuille de route");
		vueMenuGauche.add(btnGenererFeuilleDeRoute);
		//Fin vue menu de gauche
        return creation;
    }

}