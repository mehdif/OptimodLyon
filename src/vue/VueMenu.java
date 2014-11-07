package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		btnCalculerTournee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
        return null;
    }

    /**
     * @return
     */
    public Boolean onClicChargerReseau() {
		btnChargerReseau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
        return null;
    }

    /**
     * @return
     */
    public Boolean onClicUndo() {
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
        return null;
    }

    /**
     * @return
     */
    public Boolean onClicRedo() {
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
        return null;
    }

    /**
     * 
     */
    public void onClicChargerDemandeLivraison() {
		btnChargerDemandeLivraison.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
    }

    /**
     * 
     */
    public void onClicGenererFeuilleDeRoute() {
		btnGenererFeuilleDeRoute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		btnGenererFeuilleDeRoute = new JButton("Générer feuille de route");
		vueMenuGauche.add(btnGenererFeuilleDeRoute);
		//Fin vue menu de gauche
        return creation;
    }

}