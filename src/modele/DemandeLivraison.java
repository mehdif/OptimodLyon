package modele;


/**
 * @author Hexanome 4301
 */
public class DemandeLivraison {

	private Point pointDeLivraison;
    private Client client;
    private PlageHoraire plageHoraire;
    private Boolean confirmee;
    private Integer id;
    
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
}