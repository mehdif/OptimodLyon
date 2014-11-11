package controleur;

import modele.Client;
import modele.PlageHoraire;
import modele.Reseau;
import modele.Tournee;
import vue.VueFenetre;

/**
 * @author Hexanome 4301
 */
public class Application {

    /**
     * 
     */
    public Application() {
    	vueFenetre = new VueFenetre(this);
    }

    /**
     * 
     */
    public Receveur receveur;

    /**
     * 
     */
    public static Tournee tournee;

    /**
     * 
     */
    public VueFenetre vueFenetre ;

    /**
     * 
     */
    public void calculerTournee() {
        // TODO implement here
    }

    /**
     * 
     */
    public void dessinerReseau() {
        // TODO implement here
    }

    /**
     * 
     */
    public void genererFeuilleDeRoute() {
        // TODO implement here
    }

    /**
     * @param client 
     * @param adresse 
     * @param plageHoraire
     */
    public void affichageInfos(Client client, Integer adresse, PlageHoraire plageHoraire) {
        // TODO implement here
    }
    
    public boolean chargerReseauXML(){
    	Reseau reseau = new Reseau();
    	boolean chargementOK = reseau.chargerReseauXML(null);
    	if(chargementOK){
    		tournee = new Tournee(reseau);
    		this.vueFenetre.vueReseau.chargerVueReseau(reseau.getTroncons(), reseau.getPoints());
    		this.vueFenetre.vueReseau.repaint();
    	}
    	
    	return chargementOK;
    }
    
    public boolean chargerDemandeLivraisonXML(){
    	boolean chargementOK = tournee.chargerDonneesDemandeXML(null);
    	return chargementOK;
    }
    
    public static void main(String []args){
    	// Code permettant de charger le réseau puis les livraisons
		// Reseau reseau = new Reseau() ;
		// reseau.chargerReseauXML(null);
		// //reseau.afficherReseau();
		// tournee = new Tournee(reseau);
		// tournee.chargerDonneesDemandeXML(null);
		 //tournee.afficherTournee();
    	
    	//Main pour l'interface graphique
    	//new VueFenetre();
    	new Application();
    }

}
