package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * @author Hexanome 4301
 */
public class VueListe {

	
	JPanel vueHistorique;
	JTextPane txtpnHistorique;
	JTextPane historique1;

    /**
     * 
     */
    public VueListe() {
    	
    }

    /**
     * @return
     */	
    public Boolean initialiser(JFrame frame, ByteArrayOutputStream outContent) {		
		//Vue du bas pour l'historique des actions
		JPanel vueHistorique = new JPanel(new GridLayout(3, 1));
		vueHistorique = new JPanel(new GridLayout(3, 1));
		frame.getContentPane().add(vueHistorique, BorderLayout.SOUTH);
		
		txtpnHistorique = new JTextPane();
		txtpnHistorique.setText("HISTORIQUE");
		vueHistorique.add(txtpnHistorique);
		
		historique1 = new JTextPane();
		historique1.setText(outContent.toString());
		vueHistorique.add(historique1);

		//Fin vue du bas
        return null;
    }

    
    /**
     * @return
     */
    public void refresh(ByteArrayOutputStream outContent) {		
		historique1.setText(outContent.toString());
		historique1.repaint();
    }
}