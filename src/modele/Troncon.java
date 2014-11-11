package modele;

/**
 * @author Hexanome 4301
 */
public class Troncon {
	
	private String nomRue;
    private Double vitesse;
    private Double distance;
    private Point origine;
    private Point destination;

	/**
	 * Constructeur avec tous les parametres
	 * @param nomRue
	 * @param vitesse
	 * @param distance
	 * @param origine
	 * @param destination
	 */
    public Troncon(String nomRue, Double vitesse, Double distance, Point origine, Point destination) {
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
	
    public Double getWeight() {
        return (distance*vitesse);
    }
}