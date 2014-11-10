package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import modele.Reseau;

/**
 * @author Hexanome 4301
 */
public class VueFenetre {

    /**
     * 
     */
    public VueFenetre() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu.initialiser(frame);
		vueDetail.initialiser(frame);
		vueListe.initialiser(frame);
		vueReseau.initialiser(frame);
		frame.setVisible(true);
    }

    /**
     * 
     */
    public VueMenu vueMenu = new VueMenu();

    /**
     * 
     */
    public VueDetail vueDetail = new VueDetail();

    /**
     * 
     */
    public JFrame frame ;

    /**
     * 
     */
    public VueListe vueListe = new VueListe();

    /**
     * 
     */
    public VueReseau vueReseau = new VueReseau();

}