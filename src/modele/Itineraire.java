package modele;

import java.awt.Color;
import java.util.*;

/**
 * @author Hexanome 4301
 */
public class Itineraire {

    /**
     * 
     */
    public Itineraire() {
    	troncons = new ArrayList<Troncon>();
    }

    /**
     * 
     */
    private List <Troncon> troncons;
    private Color couleur;

    /**
     * @return
     */
    private Boolean ajouterTroncon() {
        // TODO implement here
        return null;
    }


	public void ajouterTroncon(Troncon unTroncon) {
		// TODO Auto-generated method stub
		troncons.add(unTroncon);
	}


	public List<Troncon> getTroncons() {
		return troncons;
	}
	

}