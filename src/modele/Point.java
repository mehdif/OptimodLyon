package modele;

/**
 * @author Hexanome 4301
 */
public class Point {
	
	private Integer longitude;
	private Integer latitude;
	private Integer adresse;
	//private DemandeLivraison uneDemande;
	//public Reseau reseau;

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
	}
	
	 /****************************************************
     ********************* Getter  **********************
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
}