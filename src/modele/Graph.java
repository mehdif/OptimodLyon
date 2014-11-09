package modele;


import java.util.List;

public class Graph {
    private final List<Point> points;
    private final List<Troncon> troncons;

    /**
     * Constructeur
     * @param points : liste de tous les points du reseau
     * @param troncons : liste des troncons qui relient les points du reseau
     */
    public Graph(List<Point> points, List<Troncon> troncons) {
        this.points = points;
        this.troncons = troncons;
    }

    
    public List<Point> getPoints() {
        return points;
    }

    public List<Troncon> getTroncons() {
        return troncons;
    }

}