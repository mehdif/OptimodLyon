package modele;

/**
 * @author Hexanome 4301
 */
public class Point {

	private Integer longitude;
	private Integer latitude;
	private Integer adresse;

	private Integer ordreLivraison;
	private DemandeLivraison uneDemande;

	// public Reseau reseau;

	/**
	 * Constructeur par défaut
	 */
	public Point() {

	}

	public Point(Integer adresse) {
		this.adresse = adresse;
	}

	/**
	 * Constructeur à 3 paramètres
	 * 
	 * @param longitude
	 * @param latitude
	 * @param adresse
	 */
	public Point(Integer longitude, Integer latitude, Integer adresse) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.adresse = adresse;
		this.uneDemande = null;
		this.ordreLivraison = null;
	}

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/

	public Integer getLongitude() {
		return longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public Integer getAdresse() {
		return adresse;
	}

	
	public Integer getOrdreLivraison() {
		return ordreLivraison;
	}

	public DemandeLivraison getUneDemande() {
		return uneDemande;
	}

	public void setDemandeLivraison(DemandeLivraison uneDemande) {
		this.uneDemande = uneDemande;
	}

	
	public void setOrdreLivraison(int id)
	{
		this.ordreLivraison = id;
	}

	/**
	 * Test l'existence d'une demande de livraison pour un point
	 */
	public Boolean possedeUneDemande() {

		if (this.uneDemande != null)
			return true;
		else
			return false;
	}
}
