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
    public VueFenetre(Reseau r) {
		frame = new JFrame("OptimodLyon");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu = new VueMenu(frame);
		vueDetail = new VueDetail(frame);
		vueListe = new VueListe(frame);
		
		vueReseau = new VueReseau(r.getTroncons(),r.getPoints());
		frame.getContentPane().add(vueReseau, BorderLayout.CENTER);
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