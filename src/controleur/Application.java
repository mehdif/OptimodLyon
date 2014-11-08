package controleur;

import java.awt.EventQueue;

import solver.Solver;
import modele.Client;
import modele.PlageHoraire;
import modele.Reseau;
import modele.Tournee;
import vue.VueFenetre;
import vue.VueFenetre;

/**
 * @author Hexanome 4301
 */
public class Application {

    /**
     * 
     */
    public Application() {
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
    
    public static void main(String []args){
    	// Code permettant de charger le r��seau puis les livraisons
    	Reseau reseau = new Reseau() ;
    	reseau.chargerReseauXML();
    	tournee = new Tournee(reseau);
    	tournee.chargerDonneesDemandeXML();
    	
    	//Main pour l'interface graphique
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//			    	VueFenetre window = new VueFenetre();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
    	//Main pour l'interface graphique
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//			    	VueFenetre window = new VueFenetre();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
    }

}