package controleur;

import modele.DemandeLivraison;
import modele.Tournee;

/**
 * @author Hexanome 4301
 */
public class CommandeAjouterDemandeLivraison implements Commande {
	
	private Receveur receveur;
	private Tournee tournee;
	private DemandeLivraison demandeLivraison;

	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/
	
	/**
	 * Constructeur avec tout les paramètres
	 * @param receveur
	 * @param tournee : la tournee dans laquelle on veut ajouter la demande de livraison
	 * @param demandeLivraison : la demande de livraison que l'on veut ajouter
	 * 
	 * @author Sonia
	 */
	public CommandeAjouterDemandeLivraison(Receveur receveur, Tournee tournee,
			DemandeLivraison demandeLivraison) {
		this.tournee = tournee;
		this.demandeLivraison = demandeLivraison;
		this.receveur = receveur;
	}
	
	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/

	@Override
	/**
	 * Action à effectuer lorsqu'on veut exécuter la commande "ajouterDemandeLivraison"
	 * @return : true si l'ajout s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean execute() {
		return receveur.ajouterDemandeLivraison(tournee, demandeLivraison);
	}
	
	@Override
	/**
	 * Action à effectuer lorsqu'on veut annuler la commande "ajouterDemandeLivraison"
	 * @return : true si l'annulation l'ajout s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean unexecute() {
		return receveur.supprimerDemandeLivraison(tournee, demandeLivraison);
	}
}