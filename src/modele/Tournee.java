package modele;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.DijkstraAlgorithm;
import utils.GraphLivraisons;
//github.com/mehdif/OptimodLyon.git
import utils.Properties;
import utils.TSP;
import utils.TourneeException;
import utils.XMLReader;

/**
 * @author Hexanome 4301
 */
public class Tournee {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static int ordreLivraison = 1;

	private List<Itineraire> itineraires;
	private List<PlageHoraire> plagesHoraires = new ArrayList<PlageHoraire>();
	private Map<String, LinkedList<Point>> paths = new HashMap<String, LinkedList<Point>>();
	private Entrepot entrepot;
	private Reseau reseau;

	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

	/**
	 * Constructeur par défaut
	 */
	public Tournee() {
	}

	/**
	 * Constructeur à un paramètre
	 * 
	 * @param reseau
	 */
	public Tournee(Reseau reseau) {
		this.reseau = reseau;
	}

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/

	public List<Itineraire> getItineraires() {
		return itineraires;
	}

	public List<PlageHoraire> getPlagesHoraires() {
		return plagesHoraires;
	}

	public Entrepot getEntrepot() {
		return entrepot;
	}

	public Reseau getReseau() {
		return reseau;
	}

	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/

	/**
	 * @return
	 */
	private Boolean ajouterItineraire() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	private Boolean supprimerItineraire() {
		// TODO implement here
		return null;
	}

	/**
	 * 
	 * @param plage
	 * @return
	 */
	private Boolean ajouterPlageHoraire(PlageHoraire plage) {
		return plagesHoraires.add(plage);
	}

	/**
	 * @return
	 */
	private Boolean supprimerPlageHoraire() {
		// TODO implement here
		return null;
	}

	/**
	 * 
	 */
	public void calculerTournee() {


		//Recuperer toutes les demandes de livraison et creer le graphe
		List<Point> listePoints = new ArrayList<Point>(reseau.getPoints().values());
		GraphLivraisons graph = creerGraphPointLivraison(listePoints);
		
		//Appel a CHOCO
		TSP tsp = new TSP(graph);
		tsp.solve(200000,graph.getNbVertices()*graph.getMaxArcCost()+1);

		ArrayList<Point> globalPath = creerItineraire(tsp);
		/*for(Point p : itineraire){
			System.out.print(" " + p.getAdresse());
		}*/
		
		Itineraire itineraire = new Itineraire();
		//remplirItineraire(globalPath);
		
		System.out.println("DONE");
		
	}

	/*public ArrayList<Troncon> remplirItineraire(ArrayList<Point> globalPath){
		
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();
		for(int i = 0; i < globalPath.size() - 1; i++){
			Point p1 = globalPath.get(i);
			Point p2 = globalPath.get(i+1);
			
			if(p2.get() == 0){
				for(Troncon t : reseau.getTroncons()){
					if(p1.equals(t.getOrigine()) && p2.equals(t.getDestination())){
						troncons.add(t);
					}
				}
			}
		}
		
		
	}*/
	
	
	public void afficherCost(int[][] cost, int size){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(cost[i][j] != 0)
					System.out.print(cost[i][j] + " ");
			}
			System.out.println("\n");
		}
	}
	
	
	public GraphLivraisons creerGraphPointLivraison(List<Point> points){
		

		//Creation du graphe
		GraphLivraisons graph = new GraphLivraisons(getNombreLivraisons() + 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
		

		for(int i = 0; i < plagesHoraires.size() - 1; i++){
		
			//Premiere plage horaire
			if(i == 0){
				creerPremiereFenetre(entrepot, plagesHoraires.get(i), plagesHoraires.get(i+1), graph);
			}
			else if(i == ( plagesHoraires.size() - 1 )){
				creerDerniereFenetre(entrepot, plagesHoraires.get(i), graph);
			}
			else{
				creerFenetreLivraison(plagesHoraires.get(i), plagesHoraires.get(i+1), graph);

			}
		}
		
		creerDerniereFenetre(entrepot, plagesHoraires.get(2), graph);
		
        for(int i = 0; i < graph.getNbVertices(); i++) {
            for (int j = 0; j < graph.getNbVertices(); j++) {
                System.out.print(graph.getCost(i, j)+ "  ");
            }
            System.out.println("\n");
        }
		
		//displaySuccesors(graph);
        return graph;
		
	}
	

	public ArrayList<Point> creerItineraire(TSP tsp){
		int[] next = tsp.getNext();
		
		ArrayList<Point> itineraire = new ArrayList<Point>();
		
		for(int i = 0; i < next.length; i++){
			
			Point source;
			Point destination;
			
			if(i == 0){
				source = reseau.getPoints().get(entrepot.getAdresse());
			}
			else{
				source = findPointFromid(i);
			}
			
			if(next[i] == 0){
				destination = reseau.getPoints().get(entrepot.getAdresse());
			}
			else{
				destination = findPointFromid(next[i]);
			}
			
			System.out.println("Point = " + source.getAdresse()+" "+destination.getAdresse());
			LinkedList<Point> path = getPathBetweenNodes(source, destination);
			System.out.println("path = " + path.size());
			itineraire.addAll(path);
		}
		
		return itineraire;
	}

	public void creerPremiereFenetre(Entrepot entrepot, PlageHoraire plageHoraireCourante, PlageHoraire plageHoraireSuivante, GraphLivraisons graph){
		
		//Points et troncons du plan du reseau
		List<Point> listePointsReseau = new ArrayList<Point>(reseau.getPoints().values());
		List<Troncon> listeTronconsReseau = new ArrayList<Troncon>(reseau.getTroncons());

		//Creer Dijkstra avec les points et les troncons du reseau
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(listePointsReseau, listeTronconsReseau);

		//Relier l'entrepot a la premiere plage horaire
		List<DemandeLivraison> dem = plageHoraireCourante.getDemandeLivraison();
		for(int i = 0; i < dem.size(); i++){
			
			Point p = dem.get(i).getPointDeLivraison();
			//System.out.println(entrepot.getAdresse() + " " + entrepot.getLongitude() + " " + entrepot.getLatitude());
			//Point e = listePointsReseau.get(350);
			//System.out.println(p1.getAdresse() + " " + p1.getLongitude() + " " + p1.getLatitude());
	        dijkstra.execute(reseau.getPoints().get(entrepot.getAdresse()));
	        LinkedList<Point> path = dijkstra.getPath(p);
	        //paths.put(entrepot.getAdresse()+""+p.getAdresse(), new LinkedList<Point>(path));
	        
	        //Mettre a jour la matrice des couts
	        graph.setCost(0, p.getOrdreLivraison(), (int) getPathCost(path, reseau.getTroncons()));

	        //Mettre a jour les successeurs
	    	graph.setSucc(0, p.getOrdreLivraison());
	    	
			System.out.println("Entrepot ; " + p.getOrdreLivraison() + " Poids = " + graph.getCost(0, p.getOrdreLivraison()));				
		}
		
		//Relier les points d'une plage horaire entre eux	
		ArrayList<Point[]> combinaisonsP = getCombinaisons(plageHoraireCourante.getDemandeLivraison());
		
		//ArrayList<Point> p = plageHoraireCourante.getDemandeLivraison().ge;
		for(Point[] pts : combinaisonsP){
			
	        dijkstra.execute(pts[0]);
	        LinkedList<Point> path = dijkstra.getPath(pts[1]);
	        paths.put(pts[0].getAdresse()+""+ pts[1].getAdresse(), new LinkedList<Point>(path));
	        
	        
	        //Mettre a jour la matrice des couts
	        int value = (int) getPathCost(path, reseau.getTroncons());
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        updateMinMaxCost(graph, value);
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        graph.setCost(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison(), value);

	        //Mettre a jour les successeurs
	    	graph.setSucc(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison());
	    	graph.setSucc(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison());
	        
			System.out.println("Point " + pts[0].getOrdreLivraison() + " ; " + pts[1].getOrdreLivraison() + " Poids = " + graph.getCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison()));		
		}
		

		//Reliser les points avec la plage horaire suivante
		for(DemandeLivraison ds : plageHoraireSuivante.getDemandeLivraison()){
			
			for(DemandeLivraison dc : plageHoraireCourante.getDemandeLivraison()){
				
				Point pSource = dc.getPointDeLivraison();
				Point pDestination = ds.getPointDeLivraison();
				
		        dijkstra.execute(pSource);
		        LinkedList<Point> path = dijkstra.getPath(pDestination);
		        paths.put(pSource.getAdresse()+""+pDestination.getAdresse(), new LinkedList<Point>(path));
		        
		        //Mettre a jour la matrice des couts et les valeurs min et max
		        int value = (int) getPathCost(path, reseau.getTroncons());
		        graph.setCost(pSource.getOrdreLivraison(), pDestination.getOrdreLivraison(), value);
		        updateMinMaxCost(graph, value);
		        //Mettre a jour les successeurs
		    	graph.setSucc(pSource.getOrdreLivraison(), pDestination.getOrdreLivraison());		        
			}
		}
	}
	
	
	public void creerFenetreLivraison(PlageHoraire plageHoraireCourante, PlageHoraire plageHoraireSuivante, GraphLivraisons graph){
		

		//Points et troncons du plan du reseau
		List<Point> listePointsReseau = new ArrayList<Point>(reseau.getPoints().values());
		List<Troncon> listeTronconsReseau = new ArrayList<Troncon>(reseau.getTroncons());

		//Creer Dijkstra avec les points et les troncons du reseau
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(listePointsReseau, listeTronconsReseau);

		
		//Relier les points d'une plage horaire entre eux	
		ArrayList<Point[]> combinaisonsP = getCombinaisons(plageHoraireCourante.getDemandeLivraison());
		
		for(Point[] pts : combinaisonsP){
			
	        dijkstra.execute(pts[0]);
	        LinkedList<Point> path = dijkstra.getPath(pts[1]);
	        paths.put(pts[0].getAdresse()+""+ pts[1].getAdresse(),new LinkedList<Point>(path));
	        
	        //Mettre a jour la matrice des couts
	        int value = (int) getPathCost(path, reseau.getTroncons());
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        updateMinMaxCost(graph, value);
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        graph.setCost(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison(), value);

	        //Mettre a jour les successeurs
	    	graph.setSucc(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison());
	    	graph.setSucc(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison());
	        
			System.out.println("Point " + pts[0].getOrdreLivraison() + " ; " + pts[1].getOrdreLivraison() + " Poids = " + graph.getCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison()));		
		}
		
		//Reliser les points avec la plage horaire suivante
		for(DemandeLivraison ds : plageHoraireSuivante.getDemandeLivraison()){
			
			for(DemandeLivraison dc : plageHoraireCourante.getDemandeLivraison()){
				
				Point pSource = dc.getPointDeLivraison();
				Point pDestination = ds.getPointDeLivraison();
				
		        dijkstra.execute(pSource);
		        LinkedList<Point> path = dijkstra.getPath(pDestination);
		        paths.put(pSource.getAdresse()+""+ pDestination.getAdresse(), new LinkedList<Point>(path));
		        
		        //Mettre a jour la matrice des couts et les valeurs min et max
		        int value = (int) getPathCost(path, reseau.getTroncons());
		        graph.setCost(pSource.getOrdreLivraison(), pDestination.getOrdreLivraison(), value);
		        updateMinMaxCost(graph, value);
		        //Mettre a jour les successeurs
		    	graph.setSucc(pSource.getOrdreLivraison(), pDestination.getOrdreLivraison());		        
			}
		}
		
		
	}

	
	public void creerDerniereFenetre(Entrepot entrepot, PlageHoraire plageHoraire, GraphLivraisons graph){
		

		//Points et troncons du plan du reseau
		List<Point> listePointsReseau = new ArrayList<Point>(reseau.getPoints().values());
		List<Troncon> listeTronconsReseau = new ArrayList<Troncon>(reseau.getTroncons());

		//Creer Dijkstra avec les points et les troncons du reseau
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(listePointsReseau, listeTronconsReseau);
		
		//Relier les points d'une plage horaire entre eux	
		ArrayList<Point[]> combinaisonsP = getCombinaisons(plageHoraire.getDemandeLivraison());
		
		for(Point[] pts : combinaisonsP){
			
	        dijkstra.execute(pts[0]);
	        LinkedList<Point> path = dijkstra.getPath(pts[1]);
	        paths.put(pts[0].getAdresse() +""+ pts[1].getAdresse() ,new LinkedList<Point>(path));
	        
	        //Mettre a jour la matrice des couts
	        int value = (int) getPathCost(path, reseau.getTroncons());
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        updateMinMaxCost(graph, value);
	        graph.setCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison(), value);
	        graph.setCost(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison(), value);

	        //Mettre a jour les successeurs
	    	graph.setSucc(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison());
	    	graph.setSucc(pts[1].getOrdreLivraison(), pts[0].getOrdreLivraison());
	        
			System.out.println("Point " + pts[0].getOrdreLivraison() + " ; " + pts[1].getOrdreLivraison() + " Poids = " + graph.getCost(pts[0].getOrdreLivraison(), pts[1].getOrdreLivraison()));		
		}
		
		//Relier les points de la dernière plage horaire avec l'entrepot
		for(DemandeLivraison d : plageHoraire.getDemandeLivraison()){
			
			Point pSource = d.getPointDeLivraison();
			Point entrp = listePointsReseau.get(14);
	        dijkstra.execute(pSource);
	        LinkedList<Point> path = dijkstra.getPath(entrp);
	        paths.put(pSource.getAdresse() + "" + entrp.getAdresse(),new LinkedList<Point>(path));
	        
	        //Mettre a jour la matrice des couts
	        graph.setCost(pSource.getOrdreLivraison(), 0, (int) getPathCost(path, reseau.getTroncons()));

	        //Mettre a jour les successeurs
	    	graph.setSucc(pSource.getOrdreLivraison(), 0);
	    	
		}

	}


	public void updateMinMaxCost(GraphLivraisons g, int value){
		if(value > g.getMaxArcCost()){
			g.setMaxArcCost(value);
		}
		
		if(value < g.getMinArcCost()){
			g.setMinArcCost(value);
		}
	}

  
	public LinkedList<Point> getPathBetweenNodes(Point source, Point destination){
		
		//Points et troncons du plan du reseau
		List<Point> listePointsReseau = new ArrayList<Point>(reseau.getPoints().values());
		List<Troncon> listeTronconsReseau = new ArrayList<Troncon>(reseau.getTroncons());

		//Creer Dijkstra avec les points et les troncons du reseau
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(listePointsReseau, listeTronconsReseau);
		
        dijkstra.execute(source);
        LinkedList<Point> path = dijkstra.getPath(destination);
        
        return path;
		
	}
	
	public Point findPointFromid(int id){
		Map<Integer, Point> points = reseau.getPoints();
		
		for (Map.Entry<Integer, Point> entry : points.entrySet())
		{
		    if((entry.getValue().getOrdreLivraison() != null) && (id == entry.getValue().getOrdreLivraison())){
		    	return entry.getValue();
		    }
		}
		return null;
	}
    public void displaySuccesors(GraphLivraisons g) {
        for(int i = 0; i < g.getSucc().size(); i++) {
            ArrayList<Integer> succesors = g.getSucc().get(i);

            System.out.println("\nLes successeurs de " + i + " sont : ");
            for (int j = 0; j < succesors.size(); j++) {
                System.out.print(succesors.get(j) + " ");
            }
        }
        System.out.println("\n");
    }
    
	public double getPathCost(LinkedList<Point> path, List<Troncon> troncons){
		
		double cost = 0;
		for (int i = 0; i < path.size() - 1; i++) {
            for (Troncon troncon : troncons) {
                if (troncon.getDestination().equals(path.get(i + 1))) {
                    cost += troncon.getWeight();
                    break;
                }
            }
        }
        return cost;
	}
	
	public int getNombreLivraisons(){
		int sum = 0;
		for(int i = 0; i < plagesHoraires.size(); i++){
			sum += plagesHoraires.get(i).getDemandeLivraison().size();
		}
		return sum;
	}

	private void chocoImplementation() {
		// TODO Auto-generated method stub

	}

	private ArrayList<ArrayList<Integer>> prepareChoco() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Computes the number of Combinations
	 * 
	 * @param demandeLivraison
	 *            : list of demandeLivraison
	 * @return a list of points array : for each array there's origin and
	 *         destination
	 */
	private ArrayList<Point[]> getCombinaisons(
			List<DemandeLivraison> demandeLivraison) {
		int n = demandeLivraison.size();
		// //Point[] tempCouple = null;
		//
		// int numberOfCombinations = numberOfCombinations(2,n);
		// int increment = 0 ;
		// while (increment != numberOfCombinations){
		//
		// for(Point[] couple : tempList){
		//
		// if(!couple.equals(tempCouple)){
		//
		// tempList.add(tempCouple);
		// }
		// }
		//
		// increment++;
		// }
		//
		//
		ArrayList<Point[]> tempList = null;

		for (int i = 0; i < demandeLivraison.size(); i++) {
			for (int j = i + 1; j < demandeLivraison.size(); j++) {
				Point[] tempCouple = {
						demandeLivraison.get(i).getPointDeLivraison(),
						demandeLivraison.get(j).getPointDeLivraison() };
				tempList.add(tempCouple);
			}
		}

		if (tempList.size() != numberOfCombinations(2, n)) {
			System.out.println("Combinaisons manquantes");
		}

		return tempList;
	}


	/** Returns the number of combinations of k elements within n
=======
	/**
	 * Returns the number of combinations of k elements within n
>>>>>>> branch 'master' of https://github.com/mehdif/OptimodLyon.git
	 * 
	 * @param k
	 * @param n
	 * @return
	 */
	private int numberOfCombinations(int k, int n) {
		// TODO Auto-generated method stub
		return factorielle(n) / (factorielle(k) * factorielle(n - k));
	}

	/**
	 * Factorielle
	 * 
	 * @param number
	 * @return
	 */
	private int factorielle(int number) {
		int fact = 1;
		for (int i = number; i >= 1; i--) {
			fact = fact * i;
		}

		return fact;
	}

	/**
	 * @return
	 */
	public PlageHoraire getPlageHoraireByHeureDebut() {
		// TODO implement here
		return null;
	}

	/**
	 * Permet de charger les demandes de livraison depuis un fichier XML
	 * 
	 * @param nomFichier
	 *            : nom du fichier XML que l'on veut charger. Si null, le
	 *            fichier est choisi via l'explorateur de fichier
	 * @return true si le chargement s'est passé correctement, false sinon
	 * 
	 * @author Sonia
	 */
	public boolean chargerDonneesDemandeXML(String nomFichier) {
		try {
			File xml;
			if (null == nomFichier) {
				xml = XMLReader.ouvrirFichier();
			} else {
				xml = new File(nomFichier);
			}
			if (null == xml) {
				return false;
			}
			if (!XMLReader.validerXML(xml.getAbsolutePath(),
					Properties.CHEMIN_XSD_TOURNEE)) {
				return false;
			}

			// Lecture du contenu d'un fichier XML avec DOM
			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document documentXML = constructeur.parse(xml);
			Element racine = documentXML.getDocumentElement();
			NodeList listeElementsOrdre1 = racine.getChildNodes();
			NamedNodeMap listeAttributs = null;

			// Creation d'objets buffers qui permettent de ne pas directement
			// instancié Tournee afin qu'on puisse vérifier d'abord qu'il n'y ai
			// pas d'incohérence entre les livraisons renseignées et le réseau
			// chargé au préalable
			List<PlageHoraire> listePlagesBuffer = new ArrayList<PlageHoraire>();
			Point pointEntrepotBuffer = null;

			for (int i = 0; i < listeElementsOrdre1.getLength(); i++) {
				if (listeElementsOrdre1.item(i).getNodeName()
						.equals(Properties.NODE_PLAGESHORAIRES)) {
					NodeList listeElementsOrdre2 = listeElementsOrdre1.item(i)
							.getChildNodes();
					for (int j = 0; j < listeElementsOrdre2.getLength(); j++) {
						if (listeElementsOrdre2.item(j).getNodeName()
								.equals(Properties.NODE_PLAGE)) {

							// Récupération des attributs de PlageHoraire
							PlageHoraire plage = chargerPlage(
									listeElementsOrdre2.item(j),
									listePlagesBuffer);
							if (null == plage) {
								return false;
							}

							NodeList listeElementsOrdre3 = listeElementsOrdre2
									.item(j).getChildNodes();
							NodeList listeElementsOrdre4 = listeElementsOrdre3
									.item(1).getChildNodes();
							for (int k = 0; k < listeElementsOrdre4.getLength(); k++) {
								if (listeElementsOrdre4.item(k).getNodeName()
										.equals(Properties.NODE_LIVRAISON)) {

									// Récupération des attributs de
									// DemandeLivraison
									DemandeLivraison demandeLivraison = chargerDemandeLivraison(
											listeElementsOrdre4.item(k), plage);
									if (null == demandeLivraison) {
										return false;
									}
									plage.ajouterDemandeLivraison(demandeLivraison);
									reseau.getPointViaAdresse(
											demandeLivraison
													.getPointDeLivraison()
													.getAdresse())
											.setDemandeLivraison(
													demandeLivraison);

								}
							}
							listePlagesBuffer.add(plage);
						}
					}
				} else if (listeElementsOrdre1.item(i).getNodeName()
						.equals(Properties.NODE_ENTREPOT)) {
					listeAttributs = listeElementsOrdre1.item(i)
							.getAttributes();
					Integer adresseEntrepot = Integer.parseInt(listeAttributs
							.getNamedItem(Properties.ATTRIBUTE_ADRESSE)
							.getNodeValue());
					pointEntrepotBuffer = reseau.getPoints().get(
							adresseEntrepot);
				}
			}
			// Si le pointEntrepotBuffer n'est pas null c'est que tous les
			// points renseignés pour les livraisons et l'entrepôt sont bien des
			// points appartenant au réseau
			if (null != pointEntrepotBuffer) {
				this.entrepot = new Entrepot(
						pointEntrepotBuffer.getLongitude(),
						pointEntrepotBuffer.getLatitude(),
						pointEntrepotBuffer.getAdresse());
				for (int i = 0; i < listePlagesBuffer.size(); i++) {
					ajouterPlageHoraire(listePlagesBuffer.get(i));
				}
				System.out.println(Properties.CHARGEMENT_TOURNEE_OK);
				return true;
			} else {
				throw new TourneeException(
						Properties.ERREUR_TOURNEE_ENTREPOT_INCONNU);
			}
		} catch (SAXException e) {
			System.out.println(Properties.SAXEXCEPTION_MESSAGE);
			return false;
		} catch (IOException e) {
			System.out.println(Properties.IOEXCEPTION_MESSAGE);
			return false;
		} catch (ParserConfigurationException e) {
			System.out.println(Properties.PARSERCONFIGURATIONEXCEPTION_MESSAGE);
			return false;
		} catch (TourneeException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Permet de creer une plage horaire à partir d'un noeud DOM
	 * 
	 * @param element
	 * @return PlageHoraire crée
	 * @throws TourneeException
	 * 
	 * @author Sonia
	 */
	private PlageHoraire chargerPlage(Node element,
			List<PlageHoraire> listePlages) {
		try {
			// Récupération des attributs de PlageHoraire
			NamedNodeMap listeAttributs = element.getAttributes();
			String debut = listeAttributs.getNamedItem(
					Properties.ATTRIBUTE_HEUREDEBUT).getNodeValue();
			String fin = listeAttributs.getNamedItem(
					Properties.ATTRIBUTE_HEUREFIN).getNodeValue();

			// Verification du format des heures
			String[] debutSplit = debut.split(":");
			String[] finSplit = fin.split(":");
			if (debutSplit.length != 3 || finSplit.length != 3) {
				throw new TourneeException(Properties.ERREUR_FORMAT_PLAGE);
			}
			if (Integer.parseInt(debutSplit[0]) > 23
					|| Integer.parseInt(finSplit[0]) > 23) {
				throw new TourneeException(Properties.ERREUR_FORMAT_PLAGE);
			}
			if (Integer.parseInt(debutSplit[1]) > 59
					|| Integer.parseInt(finSplit[1]) > 59
					|| Integer.parseInt(debutSplit[2]) > 59
					|| Integer.parseInt(finSplit[2]) > 59) {
				throw new TourneeException(Properties.ERREUR_FORMAT_PLAGE);
			}

			Calendar calDebut = Calendar.getInstance();
			Calendar calFin = Calendar.getInstance();
			calDebut.setTime(dateFormat.parse(debut));
			calFin.setTime(dateFormat.parse(fin));

			if (calDebut.after(calFin)) {
				throw new TourneeException(Properties.ERREUR_FIN_DEBUT_PLAGE);
			} else if (calDebut.equals(calFin)) {
				throw new TourneeException(Properties.ERREUR_VIDE_PLAGE);
			}

			for (int i = 0; i < listePlages.size(); i++) {
				Calendar debutTest = listePlages.get(i).getDebut();
				Calendar finTest = listePlages.get(i).getFin();
				// Si le debut d'une plage que l'on a déjà inséré est comprise
				// dans la plage que l'on veut inserer
				if (debutTest.getTime().after(calDebut.getTime())
						&& debutTest.getTime().before(calFin.getTime())) {
					throw new TourneeException(
							Properties.ERREUR_SUPERPOSITION_PLAGE);
				}
				// Si la fin d'une plage que l'on a déjà inséré est comprise
				// dans la plage que l'on veut inserer
				else if (finTest.getTime().after(calDebut.getTime())
						&& finTest.getTime().before(calFin.getTime())) {
					throw new TourneeException(
							Properties.ERREUR_SUPERPOSITION_PLAGE);
				}
				// Si le debut de la plage que l'on veut inserer est comprise
				// dans une plage que l'on a déjà inséré
				else if (calDebut.getTime().after(debutTest.getTime())
						&& calDebut.getTime().before(finTest.getTime())) {
					throw new TourneeException(
							Properties.ERREUR_SUPERPOSITION_PLAGE);
				}
				// Si la fin de la plage que l'on veut inserer est comprise dans
				// une plage que l'on a déjà inséré
				else if (calFin.getTime().after(debutTest.getTime())
						&& calFin.getTime().before(finTest.getTime())) {
					throw new TourneeException(
							Properties.ERREUR_SUPERPOSITION_PLAGE);
				}
			}
			return new PlageHoraire(calDebut, calFin);
		} catch (ParseException e) {
			System.out.println(Properties.PARSEEXCEPTION_MESSAGE);
			return null;
		} catch (TourneeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Permet de creer une demande de livraison à partir d'un noeud DOM et de la
	 * plage horaire associée
	 * 
	 * @param element
	 * @param plage
	 * @return DemandeLivraison crée
	 * @throws TourneeException
	 * 
	 * @author Sonia
	 */
	private DemandeLivraison chargerDemandeLivraison(Node element,
			PlageHoraire plage) {
		try {
			NamedNodeMap listeAttributs = element.getAttributes();
			Integer adresse = Integer.parseInt(listeAttributs.getNamedItem(
					Properties.ATTRIBUTE_ADRESSE).getNodeValue());
			Integer idClient = Integer.parseInt(listeAttributs.getNamedItem(
					Properties.ATTRIBUTE_CLIENT).getNodeValue());
			Integer id = Integer.parseInt(listeAttributs.getNamedItem(
					Properties.ATTRIBUTE_ID).getNodeValue());
			Client client = new Client(idClient);
			// Vérification si l'adresse récupéré correspond à l'adresse d'un
			// point
			// du réseau
			Point pointDeLivraison = reseau.getPoints().get(adresse);
			if (null == pointDeLivraison) {
				throw new TourneeException(
						Properties.ERREUR_TOURNEE_POINT_INCONNU);
			}
			pointDeLivraison.setOrdreLivraison(ordreLivraison);
			ordreLivraison++;
			return new DemandeLivraison(pointDeLivraison, client, plage, false,
					id);
		} catch (TourneeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	

	// TODO : méthode à effacer avant le rendu, sert juste à tester
	public void afficherTournee() {
		System.out
				.println("Entrepot : " + entrepot.getAdresse() + " " + "("
						+ entrepot.getLongitude() + ", "
						+ entrepot.getLatitude() + ")");
		for (int i = 0; i < getPlagesHoraires().size(); i++) {
			System.out.println("Plage horaire : "
					+ getPlagesHoraires().get(i).getDebut().getTime() + " - "
					+ getPlagesHoraires().get(i).getFin().getTime());
			for (int j = 0; j < getPlagesHoraires().get(i)
					.getDemandeLivraison().size(); j++) {
				System.out.println(" >> Point de livraison : "
						+ getPlagesHoraires().get(i).getDemandeLivraison()
								.get(j).getPointDeLivraison().getAdresse());
			}
		}

	}
}
