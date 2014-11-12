package controleur;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;

import modele.Client;
import modele.PlageHoraire;
import modele.Point;
import modele.Reseau;
import modele.Tournee;
import vue.VueEntrepot;
import vue.VueFenetre;
import vue.VuePoint;

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
     * @param unReseau
     * Dessine le reseau dans la fenetre principale.
     */
    public void dessinerReseau(Reseau unReseau) {

		this.vueFenetre.vueReseau.chargerVueReseau(unReseau.getTroncons(), unReseau.getPoints());
		this.vueFenetre.vueReseau.repaint();
    }
    /**
     * Affiche les demandes de livraison sur le plan suivant le code couleur
     */
    public void afficherDemandesLivraison(){

		this.vueFenetre.vueReseau.getVuesPoints().clear();
		
		VueEntrepot vueEntrepot = new VueEntrepot(this.tournee.getEntrepot().getLongitude(), this.tournee.getEntrepot().getLatitude());
		vueEntrepot.setCouleur(Color.RED);
		this.vueFenetre.vueReseau.getVuesPoints().add(vueEntrepot);
		
		Map<Integer, Point> points = this.tournee.getReseau().getPoints();
		Iterator iterator = points.entrySet().iterator();
		while (iterator.hasNext()) {
			
			Map.Entry unPoint = (Map.Entry) iterator.next();
			Point pointCourant = (Point) (unPoint.getValue());
			VuePoint vuePointCourant = new VuePoint(
					pointCourant.getLongitude(), pointCourant.getLatitude());
			
			if (pointCourant.possedeUneDemande()) {
				
				vuePointCourant.setCouleur(Color.GREEN);
				this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
			} else
				this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
		}
		
		
		this.vueFenetre.vueReseau.repaint();
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
    
    /**
     * Charge et dessine le plan du reseau.
     */
    public boolean chargerReseauXML(){
    	Reseau reseau = new Reseau();
    	boolean chargementOK = reseau.chargerReseauXML(null);
    	if(chargementOK){
    		tournee = new Tournee(reseau);
    		this.dessinerReseau(reseau);

    	}
    	
    	return chargementOK;
    }
    
    public boolean chargerDemandeLivraisonXML(){
    	boolean chargementOK = tournee.chargerDonneesDemandeXML(null);
    	

    	this.vueFenetre.vueReseau.repaint();
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

