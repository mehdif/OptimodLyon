package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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
    public boolean supprimerDemandeLivraison(DemandeLivraison demandeLivraison) {
        return listeDemandeLivraison.remove(demandeLivraison);
    }

    /**
     * @param adresse 
     * @return
     */
    public DemandeLivraison creerDemandeLivraison(Integer adresse) {
        // TODO implement here
        return null;
    }
    
    /*
    public void getDemandesLivraisonChronologique() {
     
List<DemandeLivraison>  listeOrdonnee = new ArrayList<DemandeLivraison>();
		
		this.listeDemandeLivraison.sort(, new Comparator<DemandeLivraison>() {
			@Override
			public int compare(Fruit fruite1, Fruit fruite2) {	

				return fruite1.fruitName.compareTo(fruite2.fruitName);
			}

			@Override
			public int compare(DemandeLivraison demande1, DemandeLivraison demande2) {
				// TODO Auto-generated method stub
				return demande1.compareTo(demande2);
			}
		});
	}
    //*/
}