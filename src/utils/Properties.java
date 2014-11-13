package utils;

public final class Properties {

	/**
	 * Message propre à la classe Invocateur
	 */
	public static String UNDO_OK = new String(
			"Annulation de la dernière opération : SUCCES");
	public static String UNDO_KO = new String(
			"Annulation de la dernière opération : ECHEC");

	public static String REDO_OK = new String(
			"Rétablissement de la dernière opération annulée : SUCCES");
	public static String REDO_KO = new String(
			"Rétablissement de la dernière opération annulée : ECHEC");
	
	/**
	 * Message propre à la classe XMLReader
	 */
	public static String XML_NON_VALIDE = new String("Erreur : Le fichier XML est invalide");
	

	/**
	 * Message propre à la classe Reseau
	 */

	public static String CHEMIN_XSD_RESEAU = new String("xsd/reseau.xsd");

	public static String ERREUR_RESEAU_POINT_SURDEFINI = new String(
			"Erreur : un ou plusieurs point est défini plusieurs fois : abandon du chargement du réseau");

	public static String ERREUR_RESEAU_ORIGINE_DESTINATION = new String(
			"Erreur : l'adresse du point de destination mentionné dans un ou plusieurs tronçons est égale à l'adresse du point de d'origine, abandon du chargement du plan");

	public static String ERREUR_RESEAU_DESTINATION_INCONNUE = new String(
			"Erreur : le point de destination mentionné dans un ou plusieurs tronçons n'est pas connu, abandon du chargement du plan");

	public static String CHARGEMENT_RESEAU_OK = new String(
			"Chargement du réseau : SUCCES");

	/**
	 * Message propre à la classe Tournee
	 */

	public static String CHEMIN_XSD_TOURNEE = new String("xsd/livraison.xsd");

	public static String ERREUR_TOURNEE_POINT_INCONNU = new String(
			"Erreur : le document renseigné possède un ou plusieurs points de livraison inconnus, abandon du chargement des livraisons");

	public static String ERREUR_TOURNEE_ENTREPOT_INCONNU = new String(
			"Erreur : l'entrepôt décrit dans le document ne correspond pas à un des points du réseau, abandon du chargement des livraisons");

	public static String ERREUR_FORMAT_PLAGE = new String(
			"Erreur : le format (HH:mm:ss) d'heure d'une ou plusieurs plages n'est pas respecté, abandon du chargement des livraisons");

	public static String ERREUR_SUPERPOSITION_PLAGE = new String(
			"Erreur : Une ou plusieurs plages horaires se superposent, abandon du chargement des livraisons");

	public static String ERREUR_FIN_DEBUT_PLAGE = new String(
			"Erreur : L'heure de début d'une ou plusieurs plages horaires est après son heure de fin, abandon du chargement des livraisons");

	public static String ERREUR_VIDE_PLAGE = new String(
			"Erreur : L'heure de début d'une ou plusieurs plages horaires est égale à son heure de fin, abandon du chargement des livraisons");
	
	public static String CHARGEMENT_TOURNEE_OK = new String(
			"Chargement des demandes de livraisons : SUCCES");
	
	/**
	 * String correspondant aux éléments DOM des demandes de livraisons
	 */
	
	public static String NODE_PLAGESHORAIRES = new String("PlagesHoraires");
	public static String NODE_PLAGE = new String("Plage");
	public static String NODE_LIVRAISON = new String("Livraison");
	public static String NODE_ENTREPOT = new String("Entrepot");
	public static String ATTRIBUTE_ADRESSE = new String("adresse");
	public static String ATTRIBUTE_HEUREDEBUT = new String("heureDebut");
	public static String ATTRIBUTE_HEUREFIN = new String("heureFin");
	public static String ATTRIBUTE_CLIENT = new String("client");
	public static String ATTRIBUTE_ID = new String("id");
	
	/**
	 * Messages exceptions divers
	 */
	
	public static String PARSEEXCEPTION_MESSAGE = new String("Erreur lors du parsing des dates, abandon du chargement des livraisons");
	
	public static String SAXEXCEPTION_MESSAGE = new String("Erreur lors du parsing du document lors de l'appel à constructeur.parse(xml), abandon du chargement des livraisons");
	
	public static String IOEXCEPTION_MESSAGE = new String("Erreur d'entrée/sortie lors du parsing du document lors de l'appel à constructeur.parse(xml), abandon du chargement des livraisons");
	
	public static String PARSERCONFIGURATIONEXCEPTION_MESSAGE = new String("Erreur de configuration du parseur DOM lors de l'appel à fabrique.newDocumentBuilder(), abandon du chargement des livraisons");
	
	/**
	 * Fichiers tests
	 */
	 public static String CHEMIN_XML_TEST_RESEAU_MALFORME = new String("xmlPourTests/Reseau/MalForme/plan20x20_notWellFormed.xml");
	 public static String CHEMIN_XML_TEST_RESEAU_OK = new String("xmlPourTests/Reseau/BienForme_SansAnomalie/plan20x20_wellformed.xml");
	 public static String CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK = new String("xmlPourTests/Reseau/Simplifie_BienForme_SansAnomalie/planSimplifie_BienForme.xml");
	 public static String CHEMIN_XML_TEST_RESEAU_DESTINATION_INCONNUE = new String("xmlPourTests/Reseau/Simplifie_BienForme_AvecAnomalie/DestinationInconnue.xml");
	 public static String CHEMIN_XML_TEST_RESEAU_NOEUD_SURDEFINI = new String("xmlPourTests/Reseau/Simplifie_BienForme_AvecAnomalie/NoeudSurdefini.xml");
	 public static String CHEMIN_XML_TEST_RESEAU_ORIGINE_DEST_IDENTIQUE = new String("xmlPourTests/Reseau/Simplifie_BienForme_AvecAnomalie/OrigineDestinationIdentique.xml");

	 
	 public static String CHEMIN_XML_TEST_DEMANDES_MALFORME = new String("xmlPourTests/DemandesLivraisons/MalForme/livraison20x20-2_notWellFormed.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_OK = new String("xmlPourTests/DemandesLivraisons/BienForme_SansAnomalie/livraison20x20-2_wellformed.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_SIMPLIFIE_OK = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_SansAnomalie/livraisonSimplifie_BienForme.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_ENTREPOT_INCONNU = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/EntrepotInconnu.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_HEURE_ABERRANTE = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/PlageHeureAberrante.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_POINT_INCONNU = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/PointInconnu.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_SUPERPOSITION_PLAGE_1 = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/SuperpositionPlage.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_SUPERPOSITION_PLAGE_2 = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/SuperpositionPlage_2.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_PLAGE_VIDE = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/PlageVide.xml");
	 public static String CHEMIN_XML_TEST_DEMANDES_PLAGE_FIN_DEBUT = new String("xmlPourTests/DemandesLivraisons/Simplifie_BienForme_AvecAnomalie/PlageFinDebut.xml");
	 
}
