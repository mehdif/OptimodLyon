package modele;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import utils.XMLReader;

/**
 * @author Hexanome 4301
 */
public class Reseau {

	/**
	 * Constructeur par défaut
	 */
	public Reseau() {
	}

	/**
	 * Constructeur avec tout les paramètres
	 * @param troncons
	 * @param points
	 */
	public Reseau(List<Troncon> troncons, Map<Integer, Point> points) {
		this.troncons = troncons;
		this.points = points;
	}

	/**
     * 
     */
	private List<Troncon> troncons;

	/**
     * 
     */
	private Map<Integer, Point> points;

	/****************************************************
	 ********************* Getter **********************
	 ****************************************************/

	public List<Troncon> getTroncons() {
		return troncons;
	}

	public Map<Integer, Point> getPoints() {
		return points;
	}

	/****************************************************
	 *************** Méthodes de classes ****************
	 ****************************************************/

	/**
     * Méthodes qui permet de charger le réseau depuis un fichier XML
     */
	public void chargerReseauXML() {
		Map<Integer, Point> points = new HashMap<Integer, Point>();
		List<Troncon> troncons = new ArrayList<Troncon>();
		Document documentXML = XMLReader.lireDepuisFichierXML();
		Element racine = documentXML.getDocumentElement();
		NodeList listeElements = racine.getChildNodes();
		for (int i = 0; i < listeElements.getLength(); i++) {
			NamedNodeMap listeAttributs = listeElements.item(i).getAttributes();
			if (listeElements.item(i).getNodeName().equals("Noeud")) {
				Point p = new Point(Integer.parseInt(listeAttributs.item(1)
						.getNodeValue()), Integer.parseInt(listeAttributs.item(
						2).getNodeValue()), Integer.parseInt(listeAttributs
						.item(0).getNodeValue()));
				points.put(
						Integer.parseInt(listeAttributs.item(0).getNodeValue()),
						p);
			}
		}
		for (int i = 0; i < listeElements.getLength(); i++) {
			NamedNodeMap listeAttributs = listeElements.item(i).getAttributes();
			Point pointOrigine = null;
			Point pointDestination = null;
			if (listeElements.item(i).getNodeName().equals("Noeud")) {
				Integer idPoint = Integer.parseInt(listeAttributs.item(1)
						.getNodeValue());
				pointOrigine = (Point) points.get(idPoint);
			}
			NodeList listeSousElements = listeElements.item(i).getChildNodes();
			for (int j = 0; j < listeSousElements.getLength(); j++) {
				if (listeSousElements.item(j).getNodeName()
						.equals("LeTronconSortant")) {
					listeAttributs = listeSousElements.item(j).getAttributes();
					Integer idPointDestination = Integer
							.parseInt(listeAttributs.item(0).getNodeValue());
					Double distance = Double.parseDouble(listeAttributs.item(1)
							.getNodeValue().replace(",", "."));
					String nomRue = listeAttributs.item(2).getNodeValue();
					Double vitesse = Double.parseDouble(listeAttributs.item(3)
							.getNodeValue().replace(",", "."));
					pointDestination = (Point) points.get(idPointDestination);
					Troncon t = new Troncon(nomRue, vitesse, distance,
							pointOrigine, pointDestination);
					troncons.add(t);
				}
			}
		}
		this.points = points;
		this.troncons = troncons;
	}

	/**
     * 
     */
	public Point getPointViaAdresse() {
		return null;
		// TODO implement here
	}

}