package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * @author Hexanome 4301
 */
public class VueListe {
	
	private JTextPane historique1;
	private JTextPane historique2;

    /**
     * 
     */
    public VueListe() {
    }

    /**
     * @return
     */
    public void initialiser(JFrame frame) {		
		//Vue du bas pour l'historique des actions
		JPanel vueHistorique = new JPanel(new GridLayout(3, 1));
		frame.getContentPane().add(vueHistorique, BorderLayout.SOUTH);
		
		JTextPane txtpnHistorique = new JTextPane();
		txtpnHistorique.setText("HISTORIQUE");
		vueHistorique.add(txtpnHistorique);
		
		historique1 = new JTextPane();
		historique1.setText("blablabla");
		vueHistorique.add(historique1);
		
		historique2 = new JTextPane();
		historique2.setText("blablabla");
		vueHistorique.add(historique2);
		//Fin vue du bas
    }

}