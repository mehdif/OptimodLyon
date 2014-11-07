package modele;

/**
 * @author Hexanome 4301
 */
public class Troncon {

	/**
	 * Constructeur avec tous les paramètres
	 * @param nomRue
	 * @param vitesse
	 * @param distance
	 * @param origine
	 * @param destination
	 */
    public Troncon(String nomRue, Double vitesse, Double distance,
			Point origine, Point destination) {
		this.vitesse = vitesse;
		this.distance = distance;
		this.nomRue = nomRue;
		this.origine = origine;
		this.destination = destination;
	}

	/**
     * Constructeur par défaut
     */
    public Troncon() {
    }

    /**
     * 
     */
    private Double vitesse;

    /**
     * 
     */
    private Double distance;

    /**
     * 
     */
    private String nomRue;

    /**
     * 
     */
    private Point origine;

    /**
     * 
     */
    private Point destination;
    
    /****************************************************
     ********************* Getter  **********************
     ****************************************************/

	public Double getVitesse() {
		return vitesse;
	}

	public Double getDistance() {
		return distance;
	}

	public String getNomRue() {
		return nomRue;
	}

	public Point getOrigine() {
		return origine;
	}

	public Point getDestination() {
		return destination;
	}
}