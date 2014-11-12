package controleur;

import modele.Client;
import modele.DemandeLivraison;
import modele.PlageHoraire;
import modele.Point;
import modele.Reseau;
import modele.Tournee;
import vue.VueFenetre;

/**
 * @author Hexanome 4301
 */
public class Application {

	public Receveur receveur;
	public static Tournee tournee;
	public VueFenetre vueFenetre;

	/**
     * 
     */
	public Application() {
		vueFenetre = new VueFenetre(this);
	}

	/**
     * 
     */
	public void calculerTournee() {
		// TODO implement here
		tournee.calculerTournee();
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
	public void affichageInfos(Client client, Integer adresse,
			PlageHoraire plageHoraire) {
		// TODO implement here
	}

	public boolean chargerReseauXML() {
		Reseau reseau = new Reseau();
		boolean chargementOK = reseau.chargerReseauXML(null);
		if (chargementOK) {
			tournee = new Tournee(reseau);
		}
		return chargementOK;
	}

	public boolean chargerDemandeLivraisonXML() {
		boolean chargementOK = tournee.chargerDonneesDemandeXML(null);
		return chargementOK;
	}

	public static void main(String[] args) {
		new Application();
	}
}
