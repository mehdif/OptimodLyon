package controleur;

import java.util.*;

import utils.Properties;

/**
 * @author Hexanome 4301
 */
public class Invocateur {

	private List<Commande> commandes = new LinkedList<Commande>();
	// Indice correspondant à la commande en cours dans la liste commandes
	private int commandeEnCours = 0;
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

	/**
     * Constructeur par defaut
     */
	public Invocateur() {
	}
	
	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/
	
	/**
	 * Permet d'executer la commande "ajouter une demande de livraison"
	 * @param commande : la commande à exécuter
	 * 
	 * @author Sonia
	 */
	protected void ajouterDemandeLivraison(Commande commande) {
		commande.execute();
		ajouterCommande(commande);
		commandeEnCours++;
	}
	
	/**
	 * Permet d'executer la commande "supprimer une demande de livraison"
	 * @param commande : la commande à exécuter
	 * 
	 * @author Sonia
	 */
	protected void supprimerDemandeLivraison(Commande commande) {
		commande.execute();
		ajouterCommande(commande);
		commandeEnCours++;
	}
	
	/**
	 * Permet d'annuler la dernière action effectuée
	 * @return true si l'annulation de la dernière action est possible, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean undo() {
		// Si l'indice commandeEnCours est égal à zero, on a aucune action à annuler
		if(commandeEnCours == 0){
			System.out.println(Properties.UNDO_KO);
			return false;
		}
		Commande derniereCommande = commandes.get(commandeEnCours-1);
		derniereCommande.unexecute();
		commandeEnCours--;
		System.out.println(Properties.UNDO_OK);
		return true;
	}
	
	/**
	 * Permet de refaire la dernière action annulée
	 * @return true si on a pu refaire la dernière action annulée, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean redo() {
		// Si l'indice commandeEnCours correspond à la tête de la liste commandes, on a plus d'action à refaire
		if(commandeEnCours == commandes.size()){
			System.out.println(Properties.REDO_KO);
			return false;
		}
		Commande derniereCommandeAnnule = commandes.get(commandeEnCours);
		derniereCommandeAnnule.execute();
		commandeEnCours++;
		System.out.println(Properties.REDO_OK);
		return true;
	}

	/**
	 * Permet d'ajouter la commande à notre liste de commande
	 * @param commande : la commande à ajouter la liste commandes
	 * 
	 * @author Sonia
	 */
	private void ajouterCommande(Commande commande) {
		commandes.add(commande);
	}

}