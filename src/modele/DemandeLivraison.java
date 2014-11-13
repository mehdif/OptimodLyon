package modele;

import java.util.Calendar;


/**
 * @author Hexanome 4301
 */
public class DemandeLivraison implements Comparable<DemandeLivraison> {

	private Point pointDeLivraison;
    private Client client;
    private PlageHoraire plageHoraire;
    private Boolean confirmee;
    private Integer id;
    private Calendar horaireDePassage;
    
    /****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/
    
    /**
     * Constructeur par défaut
     */
    public DemandeLivraison() {
    }
    
    /**
     * Constructeur avec tout les paramètres
     * @param pointDeLivraison
     * @param client
     * @param plageHoraire
     * @param confirmee
     * @param id
     */
    public DemandeLivraison(Point pointDeLivraison, Client client,
			PlageHoraire unePlageHoraire, Boolean confirmee, Integer id) {
		this.pointDeLivraison = pointDeLivraison;
		this.client = client;
		this.plageHoraire = unePlageHoraire;
		this.confirmee = confirmee;
		this.id = id;
		this.plageHoraire = unePlageHoraire;
	}
    
    /****************************************************
   	 ********************* Getter **********************
   	 ****************************************************/

	public Point getPointDeLivraison() {
		return pointDeLivraison;
	}

	public Client getClient() {
		return client;
	}

	public PlageHoraire getPlageHoraire() {
		return plageHoraire;
	}

	public Boolean getConfirmee() {
		return confirmee;
	}

	public Integer getId() {
		return id;
	}

	public Calendar getHoraireDePassage() {
		return horaireDePassage;
	}

	public void setHoraireDePassage(Calendar horaireDePassage) {
		this.horaireDePassage = horaireDePassage;
	}

	@Override
	public int compareTo(DemandeLivraison uneDemande) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		Integer result = null;
		if (this.horaireDePassage.compareTo(uneDemande.horaireDePassage) == EQUAL)
			result = EQUAL;
		if(this.horaireDePassage.compareTo(uneDemande.horaireDePassage) == BEFORE)
			result = BEFORE;
		if(this.horaireDePassage.compareTo(uneDemande.horaireDePassage) == AFTER)
			result = AFTER;
		return result;
	}
	
	
}