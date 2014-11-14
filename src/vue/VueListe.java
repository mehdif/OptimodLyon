package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	JTextPane retourConsole;
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

    /**
     * Construteur par defaut
     */
    public VueListe() {
    	
    }
    
    /****************************************************
	 *************** Methodes de classes ****************
	 ****************************************************/

   /**
    * Permet d'initialiser la vue du bas afin d'afficher les retours de la console
    * @param frame : la fenetre
    * @param outContent : la sortie console
    */
    public void initialiser(JFrame frame, ByteArrayOutputStream outContent) {		
		//Vue du bas pour l'historique des actions
		vueHistorique = new JPanel(new GridLayout(1, 1));
		vueHistorique.setSize(100, 500);
		vueHistorique.setPreferredSize(new Dimension(100, 100));
		frame.getContentPane().add(vueHistorique, BorderLayout.SOUTH);
		
		retourConsole = new JTextPane();
		retourConsole.setText(outContent.toString());
		vueHistorique.add(retourConsole);

		//Fin vue du bas
    }

    
    /**
     * Permet de rafraichir la vue du bas
     * @param outContent : la sortie console
     */
    public void refresh(ByteArrayOutputStream outContent) {		
		retourConsole.setText(outContent.toString());
		retourConsole.repaint();
		outContent.reset();
    }
}