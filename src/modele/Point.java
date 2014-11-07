package modele;

import java.util.*;

/**
 * @author Hexanome 4301
 */
public class Point {

    /**
     * 
     */
    public Point() {
    }

    /**
     * 
     */
    private Integer longitude;

    /**
     * 
     */
    private Integer latitude;

    /**
     * 
     */
    private Integer adresse;

    /**
     * 
     */
    private DemandeLivraison uneDemande;

    /**
     * 
     */
    public Reseau reseau;


    /**
     * 
     */
    public Reseau points;

    /**
     * 
     */
    public Set<Troncon> origine;

    /**
     * 
     */
    public Set<Troncon> destination;

}