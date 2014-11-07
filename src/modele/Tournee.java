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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
		plagesHoraires.add(plage);
		return null;
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
	private void calculerTournee() {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public PlageHoraire getPlageHoraireByHeureDebut() {
		// TODO implement here
		return null;
	}

	/**
     * 
     */
	public void chargerDonneesDemandeXML() {
		try {		
			File xml = XMLReader.ouvrirFichier();
//			StreamSource ssXml =  new StreamSource(xml);
//			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
//			StreamSource xsd = new StreamSource(new File("C:/Users/Sonia/git/OptimodLyon/livraison.xsd"));
//	
//			Schema schema = factory.newSchema(xsd);
//			Validator validator = schema.newValidator();
//			validator.validate(ssXml);
			
			DocumentBuilder constructeur = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();

			// lecture du contenu d'un fichier XML avec DOM
			Document documentXML = constructeur.parse(xml);
			Element racine = documentXML.getDocumentElement();
			NodeList listeElements = racine.getChildNodes();
			
			for (int i = 0; i < listeElements.getLength(); i++) {
				NamedNodeMap listeAttributs = listeElements.item(i)
						.getAttributes();
				if (listeElements.item(i).getNodeName()
						.equals("PlagesHoraires")) {
					NodeList listeSousElements = listeElements.item(i)
							.getChildNodes();
					for (int j = 0; j < listeSousElements.getLength(); j++) {
						
						if (listeSousElements.item(j).getNodeName()
								.equals("Plage")) {
							listeAttributs = listeSousElements.item(j)
									.getAttributes();
							
							String debut = listeAttributs.getNamedItem(
									"heureDebut").getNodeValue();
							String fin = listeAttributs
									.getNamedItem("heureFin").getNodeValue();
							Calendar calDebut = Calendar.getInstance();
							Calendar calFin = Calendar.getInstance();

							calDebut.setTime(dateFormat.parse(debut));
							calFin.setTime(dateFormat.parse(fin));
							PlageHoraire plage = new PlageHoraire(calDebut, calFin);
							
							listeSousElements = listeSousElements.item(j).getChildNodes().item(1).getChildNodes();
							for(int k = 0; k< listeSousElements.getLength() ; k++){
								if (listeSousElements.item(k).getNodeName()
										.equals("Livraison")){
									listeAttributs = listeSousElements.item(k)
											.getAttributes();
									Integer adresse = Integer.parseInt(listeAttributs.getNamedItem("adresse").getNodeValue());
									Integer idClient = Integer.parseInt(listeAttributs.getNamedItem("client").getNodeValue());
									Integer id = Integer.parseInt(listeAttributs.getNamedItem("id").getNodeValue()); 
									Client client = new Client(idClient);
									Point pointDeLivraison = reseau.getPoints().get(adresse);
									DemandeLivraison uneDemande = new DemandeLivraison(pointDeLivraison, client,
											plage, false, id);
									plage.ajouterDemandeLivraison(uneDemande);
									ajouterPlageHoraire(plage);
								}
							}
						}
					}
				} else if (listeElements.item(i).getNodeName()
						.equals("Entrepot")){
					listeAttributs = listeElements.item(i)
							.getAttributes();
					Integer adresseEntrepot = Integer.parseInt(listeAttributs.getNamedItem("adresse").getNodeValue());
					Point pointEntrepot = reseau.getPoints().get(adresseEntrepot);
					Entrepot entrepot = new Entrepot(pointEntrepot.getLongitude(), pointEntrepot.getLatitude(), pointEntrepot.getAdresse());
					this.entrepot = entrepot ;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel a construteur.parse(xml)");
		} catch (IOException e) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel a construteur.parse(xml)");
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out
					.println("lors de l'appel a fabrique.newDocumentBuilder();");
		}
	}

}