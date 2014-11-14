package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import modele.Client;
import modele.PlageHoraire;
import controleur.Application;

/**
 * @author Hexanome 4301
 */
public class VueFenetre {

	public JFrame frame ;
	private Application application;
    public VueDetail vueDetail = new VueDetail();
	public VueListe vueListe = new VueListe();
	public VueReseau vueReseau;
	public VueMenu vueMenu;
	
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/
	
    /**
     * Constructeur a un parametre
     * @param application : controleur de notre application
     */
    public VueFenetre(Application application) {
    	this.application = application;
    	vueReseau = new VueReseau(application);
    	vueMenu = new VueMenu(application);
		frame = new JFrame();
		frame.setSize(700, 700);
		//frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		vueMenu.initialiser(frame);
		vueDetail.initialiser(frame); 
		vueListe.initialiser(frame, vueMenu.getOutContent());
		vueReseau.initialiser(frame);
		frame.setVisible(true);
    }
    
    /****************************************************
	 *************** Methodes de classes ****************
	 ****************************************************/
    
    /**
     * Permet de rafraichir la vue du bas afin d'afficher la sortie console
     */
    public void refresh(){
		vueListe.refresh(vueMenu.getOutContent());
    }
    
    /**
     * Permet de recuperer l'adresse du point clique afin de l'afficher dans la vue detail de droite
     */
    public void adressePointClique(){
    	application.recupererPoint(vueReseau.getVuePointClique().getAdresse());
    }
    
    /**
     * Permet de recuperer les informations du point clique
     * @param client : String contenant l'ID du client
     * @param adresse : String contenant l'adresse de livraison
     * @param heureDebut : String contenant l'heure de debut de la plage horaire de livraison
     * @param heureFin : String contenant l'heure de fin de la plage horaire de livraison
     * @param demande : booleen qui indique si il y a une demande de livraison sur ce point
     */
    public void affichageInfos(String client, String adresse, String heureDebut, String heureFin, boolean demande) {
    	vueDetail.affichageInfos(client, adresse, heureDebut, heureFin, demande);
    }
    
}
