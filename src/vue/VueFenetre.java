package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controleur.Application;

/**
 * @author Hexanome 4301
 */
public class VueFenetre {
	/**
	 * 
	 */
	public JFrame frame ;
	
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
    	vueReseau = new VueReseau(application);
    	vueMenu = new VueMenu(application);
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu.initialiser(frame);
		vueDetail.initialiser(frame); 
		vueListe.initialiser(frame, vueMenu.getOutContent());
		vueReseau.initialiser(frame);
		frame.setVisible(true);
    }
    
    public void refresh(){
		vueListe.refresh(vueMenu.getOutContent());
    }
       
}