package controleur;

import modele.DemandeLivraison;
import modele.Tournee;

/**
 * @author Hexanome 4301
 */
public class CommandeSupprimerDemandeLivraison implements Commande {
	
	private Receveur receveur;
	private Tournee tournee;
	private DemandeLivraison demandeLivraison;

	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/
	
	/**
	 * Constructeur avec tout les paramètres
	 * @param receveur
	 * @param tournee : la tournee dans laquelle on veut supprimer la demande de livraison
	 * @param demandeLivraison : la demande de livraison que l'on veut supprimer
	 * 
	 * @author Sonia
	 */
	public CommandeSupprimerDemandeLivraison(Receveur receveur, Tournee tournee,
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
	 * Action à effectuer lorsqu'on veut exécuter la commande "supprimerDemandeLivraison"
	 * @return : true si la suppression s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean execute() {
		return receveur.supprimerDemandeLivraison(tournee, demandeLivraison);
	}
	
	@Override
	/**
	 * Action à effectuer lorsqu'on veut annuler la commande "supprimerDemandeLivraison"
	 * @return : true si l'annulation de la suppression s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean unexecute() {
		return receveur.ajouterDemandeLivraison(tournee, demandeLivraison);
	}
}