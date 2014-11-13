package modele;


/**
 * @author Hexanome 4301
 */
public class Client {
	
	private Integer id;

	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/
	
    /**
     * Constructeur par défaut
     */
    public Client() {
    }

    /**
     * Constructeur avec tous les paramètres
     * @param id
     */
	public Client(Integer id) {
		this.id = id;
	}
    
	/****************************************************
   	 ********************* Getter **********************
   	 ****************************************************/
	
	public Integer getId() {
		return id;
	}
}