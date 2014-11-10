package modele;


/**
 * @author Hexanome 4301
 */
public class DemandeLivraison {

	private Point pointDeLivraison;
    private Client client;
    private PlageHoraire unePlageHoraire;
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
     * @param unePlageHoraire
     * @param confirmee
     * @param id
     */
    public DemandeLivraison(Point pointDeLivraison, Client client,
			PlageHoraire unePlageHoraire, Boolean confirmee, Integer id) {
		this.pointDeLivraison = pointDeLivraison;
		this.client = client;
		this.unePlageHoraire = unePlageHoraire;
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

	public PlageHoraire getUnePlageHoraire() {
		return unePlageHoraire;
	}

	public Boolean getConfirmee() {
		return confirmee;
	}

	public Integer getId() {
		return id;
	}
}