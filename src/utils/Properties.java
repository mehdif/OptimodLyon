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
	

}
