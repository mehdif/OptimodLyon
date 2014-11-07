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
    }

    /**
     * 
     */
    public Receveur receveur;

    /**
     * 
     */
    public Tournee tournee;

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
    	// Code permettant de charger le réseau
    	Reseau reseau = new Reseau() ;
    	reseau.chargerReseauXML();
    }

}