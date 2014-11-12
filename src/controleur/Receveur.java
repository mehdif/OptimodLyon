package controleur;

import java.util.List;

import modele.DemandeLivraison;
import modele.PlageHoraire;
import modele.Tournee;

/**
 * @author Hexanome 4301
 */
public class Receveur {
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

	/**
     * Constructeur par defaut
     */
	public Receveur() {
	}
	
	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/
	
	/**
	 * Action effectuée lors de l'appel de la commande ajouterDemandeLivraison
	 * @param tournee : la tournee dans laquelle on veut ajouter la demande de livraison
	 * @param demandeLivraison : la demande de livraison que l'on veut ajouter
	 * @return : true si l'ajout s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean ajouterDemandeLivraison(Tournee tournee, DemandeLivraison demandeLivraison) {
		// TODO : transférer ce code vers ajouterDemandeLivraison
		PlageHoraire plage = demandeLivraison.getPlageHoraire();
		List<PlageHoraire> listePlagesHorairesTournee = tournee.getPlagesHoraires();
		for (int i = 0; i < listePlagesHorairesTournee.size(); i++) {
			if (plage.equals(listePlagesHorairesTournee.get(i))) {
				listePlagesHorairesTournee.get(i).ajouterDemandeLivraison(
						demandeLivraison);
				return true;
			}
		}
		return false;
	}

	/**
	 * Action effectuée lors de l'appel de la commande supprimerDemandeLivraison
	 * @param tournee : la tournee dans laquelle on veut ajouter la demande de livraison
	 * @param demandeLivraison : la demande de livraison que l'on veut ajouter
	 * @return : true si la suppression s'est bien passée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean supprimerDemandeLivraison(Tournee tournee,
			DemandeLivraison demandeLivraison) {
		// TODO : transférer ce code vers supprimerDemandeLivraison
		PlageHoraire plage = demandeLivraison.getPlageHoraire();
		List<PlageHoraire> listePlagesHorairesTournee = tournee.getPlagesHoraires();
		for (int i = 0; i < listePlagesHorairesTournee.size(); i++) {
			if (plage.equals(listePlagesHorairesTournee.get(i))) {
				listePlagesHorairesTournee.get(i).supprimerDemandeLivraison(demandeLivraison);
				return true;
			}
		}
		return false;
	}

}