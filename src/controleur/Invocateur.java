package controleur;

import java.util.*;

/**
 * @author Hexanome 4301
 */
public class Invocateur {

	private List<Commande> commandes = new LinkedList<Commande>();
	// Indice correspondant Ã  la commande en cours dans la liste commandes
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
	 *************** MÃ©thodes de classes ****************
	 ****************************************************/
	
	/**
	 * Permet d'executer la commande "ajouter une demande de livraison"
	 * @param commande : la commande Ã  exÃ©cuter
	 * 
	 * @author Sonia
	 */
	protected void ajouterDemandeLivraison(Commande commande) {
		commande.execute();
		ajouterCommande(commande);
		commandeEnCours++;
	}
	
	/**
	 * Permet d'annuler la derniÃ¨re action effectuÃ©e
	 * @return true si l'annulation de la derniÃ¨re action est possible, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean undo() {
		// Si l'indice commandeEnCours est Ã©gal Ã  zero, on a aucune action Ã  annuler
		if(commandeEnCours == 0){
			System.out.println("Annulation de la dernière opération : échec");
			return false;
		}
		Commande derniereCommande = commandes.get(commandeEnCours-1);
		derniereCommande.unexecute();
		commandeEnCours--;
		System.out.println("Annulation de la dernière opération : succès");
		return true;
	}
	
	/**
	 * Permet de refaire la derniÃ¨re action annulÃ©e
	 * @return true si on a pu refaire la derniÃ¨re action annulÃ©e, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean redo() {
		// Si l'indice commandeEnCours correspond Ã  la tÃªte de la liste commandes, on a plus d'action Ã  refaire
		if(commandeEnCours == commandes.size()){
			System.out.println("Rétablissement de la dernière opération annulée : échec");
			return false;
		}
		Commande derniereCommandeAnnule = commandes.get(commandeEnCours);
		derniereCommandeAnnule.execute();
		commandeEnCours++;
		System.out.println("Rétablissement de la dernière opération annulée : succès");
		return true;
	}

	/**
	 * Permet d'ajouter la commande Ã  notre liste de commande
	 * @param commande : la commande Ã  ajouter la liste commandes
	 * 
	 * @author Sonia
	 */
	private void ajouterCommande(Commande commande) {
		commandes.add(commande);
	}

}