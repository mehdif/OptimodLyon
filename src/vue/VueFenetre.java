package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Application;

/**
 * @author Hexanome 4301
 */
public class VueFenetre extends JFrame {

	/**
	 * 
	 */
	//public JFrame frame ;
	
	/**
	 * 
	 */
	private Application application;
	
	/**
     * 
     */
    public VueDetail vueDetail = new VueDetail();
    
    /**
     * 
     */
	public VueListe vueListe = new VueListe();
	
	/**
	 * 
	 */
	public VueReseau vueReseau;
	
	/**
	 * 
	 */
	public VueMenu vueMenu;
	
    /**
     * 
     */
    public VueFenetre(Application application) {
    	this.application = application;
    	vueMenu = new VueMenu(application);
		//frame = new JFrame();
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu.initialiser(this);
		vueDetail.initialiser(this); 
		vueListe.initialiser(this);
		vueReseau.initialiser(this);
		this.setVisible(true);
    }
}
