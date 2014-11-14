package utils;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import modele.Point;
import modele.Troncon;

public class GraphLivraisons implements Graph {
    
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost;
    private ArrayList<ArrayList<Integer>> succ;



    /**
     * Constructeur
     * @param points : liste de tous les points du reseau
     * @param troncons : liste des troncons qui relient les points du reseau
     */
    public GraphLivraisons(int n, int min, int max) {

        minArcCost = min;
        maxArcCost = max;
        nbVertices = n;
        cost = new int[nbVertices][nbVertices];
        succ = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < nbVertices; i++){
        	succ.add(new ArrayList<Integer>());
        }

        
        
    }

	public void setMaxArcCost(int maxArcCost) {
		this.maxArcCost = maxArcCost;
	}

	public void setMinArcCost(int minArcCost) {
		this.minArcCost = minArcCost;
	}
    
    public void setCost(int i, int j, int poids){
    	cost[i][j] = poids;
    }
    
    
    public ArrayList<ArrayList<Integer>> getSucc(){
		return succ;
}
    
    public void setSucc(int index, int succesor){
    		succ.get(index).add(succesor);
    }
    
    
    
    public int getCost(int i, int j){
    	return cost[i][j];
    }
    

	@Override
	public int getMaxArcCost() {
		return maxArcCost;
	}


	@Override
	public int getMinArcCost() {
		return minArcCost;
	}


	@Override
	public int getNbVertices() {
		return nbVertices;
	}


	@Override
	public int[][] getCost() {
		return cost;
	}


	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		
			if ((i<0) || (i>=nbVertices)){
				throw new ArrayIndexOutOfBoundsException();
			}
			int[] tab = new int[succ.get(i).size()];
			for(int j=0;j<tab.length;j++){
				tab[j] = succ.get(i).get(j);
			}
			return tab;
	}


	@Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void displaySuccesors() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void displayCosts() {
		// TODO Auto-generated method stub
		
	}

}