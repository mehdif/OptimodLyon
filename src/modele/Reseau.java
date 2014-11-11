package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * Permet de charger le réseau depuis un fichier XML
	 * 
	 * @param nomFichier
	 *            : nom du fichier XML que l'on veut charger. Si null, le fichier
	 *            est choisi via l'explorateur de fichier
	 * @return true si le chargement s'est passé correctement, false sinon
	 */
	public boolean chargerReseauXML(String nomFichier) {
		try {
			File xml;
			if (null == nomFichier) {
				xml = XMLReader.ouvrirFichier();
			} else {
				xml = new File(nomFichier);
			}
			if(null == xml){
				return false;
			}
			if (!XMLReader.validerXML(xml.getAbsolutePath(), "xsd/reseau.xsd")) {
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
			// instancié Reseau afin qu'on puisse vérifier d'abord qu'il n'y ai
			// pas d'incohérence entre les noeuds et les noeuds mentionnés dans
			// les tronçons
			Map<Integer, Point> pointsBuffer = new HashMap<Integer, Point>();
			List<Troncon> tronconsBuffer = new ArrayList<Troncon>();

			// Premier parcour du xml pour remplir la map avec tous les noeuds
			for (int i = 0; i < listeElementsOrdre1.getLength(); i++) {
				listeAttributs = listeElementsOrdre1.item(i).getAttributes();
				if (listeElementsOrdre1.item(i).getNodeName().equals("Noeud")) {
					Point p = new Point(Integer.parseInt(listeAttributs
							.getNamedItem("x").getNodeValue()),
							Integer.parseInt(listeAttributs.getNamedItem("y")
									.getNodeValue()),
							Integer.parseInt(listeAttributs.getNamedItem("id")
									.getNodeValue()));
					pointsBuffer.put(Integer.parseInt(listeAttributs
							.getNamedItem("id").getNodeValue()), p);
				}
			}

			// On reparcours pour pouvoir récupérer le point d'origine
			for (int i = 0; i < listeElementsOrdre1.getLength(); i++) {
				listeAttributs = listeElementsOrdre1.item(i).getAttributes();
				Point pointOrigine = null;
				Point pointDestination = null;
				if (listeElementsOrdre1.item(i).getNodeName().equals("Noeud")) {
					Integer idPoint = Integer.parseInt(listeAttributs
							.getNamedItem("id").getNodeValue());
					pointOrigine = (Point) pointsBuffer.get(idPoint);

					// On récupére les noeuds fils
					NodeList listeElementsOrdre2 = listeElementsOrdre1.item(i)
							.getChildNodes();
					// Remplissage de la liste de tronçons
					for (int j = 0; j < listeElementsOrdre2.getLength(); j++) {
						if (listeElementsOrdre2.item(j).getNodeName()
								.equals("LeTronconSortant")) {
							listeAttributs = listeElementsOrdre2.item(j)
									.getAttributes();
							Integer idPointDestination = Integer
									.parseInt(listeAttributs.getNamedItem(
											"idNoeudDestination")
											.getNodeValue());
							if (idPointDestination.equals(pointOrigine
									.getAdresse())) {
								System.out
										.println("Erreur : l'adresse du point de destination mentionné dans un ou plusieurs tronçons est égale à l'adresse du point de d'origine, abandon du chargement du plan");
								return false;
							}
							Double distance = Double.parseDouble(listeAttributs
									.getNamedItem("longueur").getNodeValue()
									.replace(",", "."));
							String nomRue = listeAttributs.getNamedItem(
									"nomRue").getNodeValue();
							Double vitesse = Double.parseDouble(listeAttributs
									.getNamedItem("vitesse").getNodeValue()
									.replace(",", "."));
							pointDestination = (Point) pointsBuffer
									.get(idPointDestination);
							if (null == pointDestination) {
								System.out
										.println("Erreur : le point de destination mentionné dans un ou plusieurs tronçons n'est pas connu, abandon du chargement du plan");
								return false;
							}
							Troncon t = new Troncon(nomRue, vitesse, distance,
									pointOrigine, pointDestination);
							tronconsBuffer.add(t);
						}
					}
				}
			}
			this.points = pointsBuffer;
			this.troncons = tronconsBuffer;
			return true;
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out
					.println("lors de l'appel a fabrique.newDocumentBuilder();");
			return false;
		} catch (SAXException e) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel a constructeur.parse(xml)");
			return false;
		} catch (IOException e) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel a constructeur.parse(xml)");
			return false;
		}

	}

	/**
     * 
     */
	public Point getPointViaAdresse(Integer adresse) {
		return (Point) points.get(adresse);
	}

	// TODO : méthode à effacer avant le rendu, sert juste à tester
	public void afficherReseau() {
		Set<Integer> cles = points.keySet();
		Iterator<Integer> it = cles.iterator();
		System.out.println("Liste des points du réseau : ");
		while (it.hasNext()) {
			Integer cle = (Integer) it.next();
			Point point = (Point) points.get(cle);
			System.out.println(">> Adresse : " + point.getAdresse() + ", ("
					+ point.getLongitude() + ", " + point.getLatitude() + ")");
		}
		System.out.println();
		System.out.println("Liste des tronçons : ");
		for (int i = 0; i < troncons.size(); i++) {
			System.out.println(">> Nom rue : " + troncons.get(i).getNomRue()
					+ " vitesse : " + troncons.get(i).getVitesse()
					+ " distance : " + troncons.get(i).getDistance());
			System.out.println(" 		>> Origine : "
					+ troncons.get(i).getOrigine().getAdresse());
			System.out.println(" 		>> Destination : "
					+ troncons.get(i).getDestination().getAdresse());

		}
	}

}