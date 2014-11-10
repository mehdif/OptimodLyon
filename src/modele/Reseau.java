package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.XMLReader;

/**
 * @author Hexanome 4301
 */
public class Reseau {

	private List<Troncon> troncons;
	/**
	 * Integer : Adresse (afin de pouvoir accéder au point via son adresse)
	 */
	private Map<Integer, Point> points;

	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

	/**
	 * Constructeur par défaut
	 */
	public Reseau() {
	}

	/**
	 * Constructeur avec tout les paramètres
	 * 
	 * @param troncons
	 * @param points
	 */
	public Reseau(List<Troncon> troncons, Map<Integer, Point> points) {
		this.troncons = troncons;
		this.points = points;
	}

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
	public boolean chargerReseauXML() {
		try {
			Map<Integer, Point> points = new HashMap<Integer, Point>();
			List<Troncon> troncons = new ArrayList<Troncon>();

			File xml = XMLReader.ouvrirFichier();
			if (!XMLReader.validerXML(xml.getAbsolutePath(), "xsd/reseau.xsd")) {
				return false;
			}

			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			// lecture du contenu d'un fichier XML avec DOM
			Document documentXML = constructeur.parse(xml);
			Element racine = documentXML.getDocumentElement();
			NodeList listeElements = racine.getChildNodes();
			
			// Remplissage de la map avec tous les noeuds
			for (int i = 0; i < listeElements.getLength(); i++) {
				NamedNodeMap listeAttributs = listeElements.item(i)
						.getAttributes();
				if (listeElements.item(i).getNodeName().equals("Noeud")) {
					Point p = new Point(Integer.parseInt(listeAttributs.item(1)
							.getNodeValue()), Integer.parseInt(listeAttributs
							.item(2).getNodeValue()),
							Integer.parseInt(listeAttributs.item(0)
									.getNodeValue()));
					points.put(Integer.parseInt(listeAttributs.item(0)
							.getNodeValue()), p);
				}
			}
			
			// On reparcours pour pouvoir récupérer le point d'origine
			for (int i = 0; i < listeElements.getLength(); i++) {
				NamedNodeMap listeAttributs = listeElements.item(i)
						.getAttributes();
				Point pointOrigine = null;
				Point pointDestination = null;
				if (listeElements.item(i).getNodeName().equals("Noeud")) {
					Integer idPoint = Integer.parseInt(listeAttributs.item(1)
							.getNodeValue());
					pointOrigine = (Point) points.get(idPoint);
				}
				// On récupére les noeuds fils
				NodeList listeSousElements = listeElements.item(i)
						.getChildNodes();
				// Remplissage de la liste de tronçons 
				for (int j = 0; j < listeSousElements.getLength(); j++) {
					if (listeSousElements.item(j).getNodeName()
							.equals("LeTronconSortant")) {
						listeAttributs = listeSousElements.item(j)
								.getAttributes();
						Integer idPointDestination = Integer
								.parseInt(listeAttributs.item(0).getNodeValue());
						Double distance = Double.parseDouble(listeAttributs
								.item(1).getNodeValue().replace(",", "."));
						String nomRue = listeAttributs.item(2).getNodeValue();
						Double vitesse = Double.parseDouble(listeAttributs
								.item(3).getNodeValue().replace(",", "."));
						pointDestination = (Point) points
								.get(idPointDestination);
						Troncon t = new Troncon(nomRue, vitesse, distance,
								pointOrigine, pointDestination);
						troncons.add(t);
					}
				}
			}
			this.points = points;
			this.troncons = troncons;
			return true;
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out
					.println("lors de l'appel a fabrique.newDocumentBuilder();");
			return false;
		} catch (SAXException e) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel a construteur.parse(xml)");
			return false;
		} catch (IOException e) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel a construteur.parse(xml)");
			return false;
		}

	}

	/**
     * 
     */
	public Point getPointViaAdresse() {
		return null;
		// TODO implement here
	}

}