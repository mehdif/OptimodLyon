package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modele.Point;
import modele.Troncon;


public class DijkstraAlgorithm {

    private final List<Point> nodes;
    private final List<Troncon> Troncons;
    private Set<Point> settledNodes;
    private Set<Point> unSettledNodes;
    private Map<Point, Point> predecessors;
    private Map<Point, Double> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Point>(graph.getPoints());
        this.Troncons = new ArrayList<Troncon>(graph.getTroncons());
    }

    public void execute(Point source) {
        settledNodes = new HashSet<Point>();
        unSettledNodes = new HashSet<Point>();
        distance = new HashMap<Point, Double>();
        predecessors = new HashMap<Point, Point>();
        distance.put(source, (double) 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Point node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Point node) {
        List<Point> adjacentNodes = getNeighbors(node);
        for (Point target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private Double getDistance(Point node, Point target) {
        for (Troncon Troncon : Troncons) {
            if (Troncon.getOrigine().equals(node)
                    && Troncon.getDestination().equals(target)) {
                return Troncon.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Point> getNeighbors(Point node) {
        List<Point> neighbors = new ArrayList<Point>();
        for (Troncon Troncon : Troncons) {
            if (Troncon.getOrigine().equals(node)
                    && !isSettled(Troncon.getDestination())) {
                neighbors.add(Troncon.getDestination());
            }
        }
        return neighbors;
    }

    private Point getMinimum(Set<Point> Pointes) {
        Point minimum = null;
        for (Point Point : Pointes) {
            if (minimum == null) {
                minimum = Point;
            } else {
                if (getShortestDistance(Point) < getShortestDistance(minimum)) {
                    minimum = Point;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Point Point) {
        return settledNodes.contains(Point);
    }

    private Double getShortestDistance(Point destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Point> getPath(Point target) {
        LinkedList<Point> path = new LinkedList<Point>();
        Point step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}