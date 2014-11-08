package vue;

import java.awt.BorderLayout;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Hexanome 4301
 */
public class VueReseau implements VueDessinable {

    /**
     * 
     */
    public VueReseau(JFrame frame) {
    	initialiser(frame);
    }

    /**
     * 
     */
    public List <VuePoint>  vuesPoint;

    /**
     * 
     */
    public List <VueTroncon>  vuesTroncons;

    /**
     * 
     */
    public VueTournee vueTournee;




    /**
     * @return
     */
    public Boolean dessiner() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean initialiser(JFrame frame) {
		JPanel vueReseau = new JPanel();
		frame.getContentPane().add(vueReseau, BorderLayout.CENTER);
        return null;
    }

}