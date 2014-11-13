package modele;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.TourneeException;
import utils.XMLReader;

/**
 * @author Hexanome 4301
 */
public class Tournee {
	public SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private List<Itineraire> itineraires;
	private List<PlageHoraire> plagesHoraires = new ArrayList<PlageHoraire>();
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
		// TODO implement here
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

	/**
	 * Returns the number of combinations of k elements within n
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
	 * @author Sonia
	 * @param nomFichier
	 *            : nom du fichier XML que l'on veut charger. Si null, le
	 *            fichier est choisi via l'explorateur de fichier
	 * @return true si le chargement s'est passé correctement, false sinon
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
					"xsd/livraison.xsd")) {
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
						.equals("PlagesHoraires")) {
					NodeList listeElementsOrdre2 = listeElementsOrdre1.item(i)
							.getChildNodes();
					for (int j = 0; j < listeElementsOrdre2.getLength(); j++) {
						if (listeElementsOrdre2.item(j).getNodeName()
								.equals("Plage")) {

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
										.equals("Livraison")) {

									// Récupération des attributs de
									// DemandeLivraison
									DemandeLivraison demandeLivraison = chargerDemandeLivraison(
											listeElementsOrdre4.item(k), plage);
									if (null == demandeLivraison) {
										// TODO ajouter le test associé
										throw new TourneeException("Erreur");
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
						.equals("Entrepot")) {
					listeAttributs = listeElementsOrdre1.item(i)
							.getAttributes();
					Integer adresseEntrepot = Integer.parseInt(listeAttributs
							.getNamedItem("adresse").getNodeValue());
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
				System.out.println("Chargement des livraisons réussi");
				return true;
			} else {
				throw new TourneeException("Erreur : l'entrepôt décrit dans le document ne correspond pas à un des points du réseau, abandon du chargement des livraisons");
			}
		} catch (SAXException e) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel a construteur.parse(xml)");
			return false;
		} catch (IOException e) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel a construteur.parse(xml)");
			return false;
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out
					.println("lors de l'appel a fabrique.newDocumentBuilder();");
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
	 */
	private PlageHoraire chargerPlage(Node element,
			List<PlageHoraire> listePlages) {
		try {
			// Récupération des attributs de PlageHoraire
			NamedNodeMap listeAttributs = element.getAttributes();
			String debut = listeAttributs.getNamedItem("heureDebut")
					.getNodeValue();
			String fin = listeAttributs.getNamedItem("heureFin").getNodeValue();

			// Verification du format des heures
			String[] debutSplit = debut.split(":");
			String[] finSplit = fin.split(":");
			if (debutSplit.length != 3 || finSplit.length != 3) {
				System.out
						.println("Erreur : le format (HH:mm:ss) d'heure d'une ou plusieurs plages n'est pas respecté, abandon du chargement des livraisons");
				return null;
			}
			if (Integer.parseInt(debutSplit[0]) > 23
					|| Integer.parseInt(finSplit[0]) > 23) {
				System.out
						.println("Erreur : le format (HH:mm:ss) d'heure d'une ou plusieurs plages n'est pas respecté, abandon du chargement des livraisons");
				return null;
			}
			if (Integer.parseInt(debutSplit[1]) > 59
					|| Integer.parseInt(finSplit[1]) > 59
					|| Integer.parseInt(debutSplit[2]) > 59
					|| Integer.parseInt(finSplit[2]) > 59) {
				System.out
						.println("Erreur : le format (HH:mm:ss) d'heure d'une ou plusieurs plages n'est pas respecté, abandon du chargement des livraisons");
				return null;
			}

			Calendar calDebut = Calendar.getInstance();
			Calendar calFin = Calendar.getInstance();
			calDebut.setTime(dateFormat.parse(debut));
			calFin.setTime(dateFormat.parse(fin));

			boolean superpositionPlage = false;
			for (int i = 0; i < listePlages.size(); i++) {
				Calendar debutTest = listePlages.get(i).getDebut();
				Calendar finTest = listePlages.get(i).getFin();
				// Si le debut d'une plage que l'on a déjà inséré est comprise
				// dans la plage que l'on veut inserer
				if (debutTest.getTime().after(calDebut.getTime())
						&& debutTest.getTime().before(calFin.getTime())) {
					superpositionPlage = true;
					break;
				}
				// Si la fin d'une plage que l'on a déjà inséré est comprise
				// dans la plage que l'on veut inserer
				else if (finTest.getTime().after(calDebut.getTime())
						&& finTest.getTime().before(calFin.getTime())) {
					superpositionPlage = true;
					break;
				}
				// Si le debut de la plage que l'on veut inserer est comprise
				// dans une plage que l'on a déjà inséré
				else if (calDebut.getTime().after(debutTest.getTime())
						&& calDebut.getTime().before(finTest.getTime())) {
					superpositionPlage = true;
					break;
				}
				// Si la fin de la plage que l'on veut inserer est comprise dans
				// une plage que l'on a déjà inséré
				else if (calFin.getTime().after(debutTest.getTime())
						&& calFin.getTime().before(finTest.getTime())) {
					superpositionPlage = true;
					break;
				}
			}
			if (superpositionPlage) {
				System.out
						.println("Erreur : Une ou plusieurs plages horaires se superposent, abandon du chargement des livraisons");
				return null;
			}
			return new PlageHoraire(calDebut, calFin);
		} catch (ParseException e) {
			System.out.println("Erreur lors du parsing des dates");
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
	 */
	private DemandeLivraison chargerDemandeLivraison(Node element,
			PlageHoraire plage) throws TourneeException {
		NamedNodeMap listeAttributs = element.getAttributes();
		Integer adresse = Integer.parseInt(listeAttributs.getNamedItem(
				"adresse").getNodeValue());
		Integer idClient = Integer.parseInt(listeAttributs.getNamedItem(
				"client").getNodeValue());
		Integer id = Integer.parseInt(listeAttributs.getNamedItem("id")
				.getNodeValue());
		Client client = new Client(idClient);
		// Vérification si l'adresse récupéré correspond à l'adresse d'un point
		// du réseau
		Point pointDeLivraison = reseau.getPoints().get(adresse);
		if (null == pointDeLivraison) {
			
			throw new TourneeException("Erreur : le document renseigné possède un ou plusieurs points de livraison inconnus, abandon du chargement des livraisons");
			//System.out.println("Erreur : le document renseigné possède un ou plusieurs points de livraison inconnus, abandon du chargement des livraisons");
			//return null;
		}
		return new DemandeLivraison(pointDeLivraison, client, plage, false, id);
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
