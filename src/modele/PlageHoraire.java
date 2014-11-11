package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Hexanome 4301
 */
public class PlageHoraire {

	private Calendar debut;
    private Calendar fin;
    private List <DemandeLivraison> listeDemandeLivraison = new ArrayList<DemandeLivraison>();
    private Boolean dispo;
    
    /****************************************************
	 ********************* Getter **********************
	 ****************************************************/
	
    public Calendar getDebut() {
		return debut;
	}

	public Calendar getFin() {
		return fin;
	}

	public List<DemandeLivraison> getDemandeLivraison() {
		return listeDemandeLivraison;
	}

	public Boolean getDispo() {
		return dispo;
	}
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

	/**
     * Constructeur par défaut
     */
    public PlageHoraire() {
    }
    
    /**
     * Constructeur avec 2 paramètres
     */
    public PlageHoraire(Calendar debut, Calendar fin){
    	this.debut = debut;
    	this.fin = fin;
    }

    /****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/

    
    // TODO : Peut on la passer à public afin de de pouvoir ajouter des demandes depuis tournee
//    /**
//     * 
//     * @return
//     */
//    private Boolean ajouterDemandeLivraison() {
//        return null;
//    }
    
    
    /**
     * 
     * @param demandeLivraison
     * @return
     */
    public boolean ajouterDemandeLivraison(DemandeLivraison demandeLivraison) {
        return listeDemandeLivraison.add(demandeLivraison);
    }

    /**
     * @return
     */
    private Boolean supprimerDemandeLivraison() {
        // TODO implement here
        return null;
    }

    /**
     * @param adresse 
     * @return
     */
    public DemandeLivraison creerDemandeLivraison(Integer adresse) {
        // TODO implement here
        return null;
    }

}