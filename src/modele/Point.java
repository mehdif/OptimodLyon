package modele;

/**
 * @author Hexanome 4301
 */
public class Point {

	private Integer longitude;
	private Integer latitude;
	private Integer adresse;
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
<<<<<<< HEAD
	
=======

>>>>>>> branch 'master' of https://github.com/mehdif/OptimodLyon.git
	public DemandeLivraison getUneDemande() {
		return uneDemande;
	}

	public void setDemandeLivraison(DemandeLivraison uneDemande) {
		this.uneDemande = uneDemande;
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
