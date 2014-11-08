package vue;

import java.awt.BorderLayout;
import java.util.*;

import javax.swing.JFrame;

import controleur.Application;

/**
 * @author Hexanome 4301
 */
public class VueFenetre {

    /**
     * 
     */
    public VueFenetre() {
		frame = new JFrame("OptimodLyon");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu = new VueMenu(frame);
		vueDetail = new VueDetail(frame);
		vueListe = new VueListe(frame);
		vueReseau = new VueReseau(frame);
    }

    /**
     * 
     */
    public VueMenu vueMenu;

    /**
     * 
     */
    public VueDetail vueDetail;

    /**
     * 
     */
    public JFrame frame ;

    /**
     * 
     */
    public VueListe vueListe;

    /**
     * 
     */
    public VueReseau vueReseau;

}