package modele;

/**
 * @author Hexanome 4301
 */
public class Point {
	
	//private Integer longitude;
	//private Integer latitude;
	private int adresse;
	//private DemandeLivraison uneDemande;
	//public Reseau reseau;

	/**
	 * Constructeur par défaut
	 */
	public Point() {
		
	}
	
	public Point(int adresse) {
		this.adresse = adresse;
	}

	/**
	 * Constructeur à 3 paramètres
	 * 
	 * @param longitude
	 * @param latitude
	 * @param adresse
	 */
	public Point(Integer longitude, Integer latitude, int adresse) {
		//this.longitude = longitude;
		//this.latitude = latitude;
		this.adresse = adresse;
	}
	
	 /****************************************************
     ********************* Getter  **********************
     ****************************************************/

	/*public Integer getLongitude() {
		return longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}
	


	public DemandeLivraison getUneDemande() {
		return uneDemande;
	}

	public Reseau getReseau() {
		return reseau;
	}*/
	
	public int getAdresse() {
		return adresse;
	}
}