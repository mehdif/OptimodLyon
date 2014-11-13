package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import modele.DemandeLivraison;
import modele.PlageHoraire;
import modele.Reseau;
import modele.Tournee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Properties;

public class TourneeTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * setUpStreams et cleanUpStreams permettent de récupérer la sortie standard
	 * avant les tests et la réinitialiser apres les tests
	 */
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	/**
	 * Test avec un fichier bien formé et sans anomalie, vérification de l'objet
	 * tournee crée
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeSansAnomalie() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);

		assertTrue(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_SIMPLIFIE_OK));
		List<PlageHoraire> listePlages = tournee.getPlagesHoraires();
		List<DemandeLivraison> demandesLivraison = null;

		// Est-ce que le nombre de plages horaires est le bon ?
		assertEquals(listePlages.size(), 2);

		// Est-ce que les caractéristiques des plages horaires sont les bonnes ?

		// Est-ce que le nombre de demandes de livraison pour la 1ere plage est
		// le bon ?
		demandesLivraison = listePlages.get(0).getDemandeLivraison();
		assertEquals(demandesLivraison.size(), 2);

		// Est-ce que les caractéristiques des demandes de livraison pour la
		// 1ere plage sont les bonnes ?
		assertEquals(demandesLivraison.get(0).getId(), (Integer) 1);
		assertEquals(demandesLivraison.get(0).getClient().getId(),
				(Integer) 532);
		assertEquals(demandesLivraison.get(0).getPointDeLivraison()
				.getAdresse(), (Integer) 1);
		assertEquals(demandesLivraison.get(1).getId(), (Integer) 2);
		assertEquals(demandesLivraison.get(1).getClient().getId(),
				(Integer) 328);
		assertEquals(demandesLivraison.get(1).getPointDeLivraison()
				.getAdresse(), (Integer) 2);

		// Est-ce que le nombre de demandes de livraison pour la 2eme plage est
		// le bon ?
		demandesLivraison = listePlages.get(1).getDemandeLivraison();
		assertEquals(demandesLivraison.size(), 1);

		// Est-ce que les caractéristiques des demandes de livraison pour la
		// 2eme plage sont les bonnes ?
		assertEquals(demandesLivraison.get(0).getId(), (Integer) 3);
		assertEquals(demandesLivraison.get(0).getClient().getId(),
				(Integer) 321);
		assertEquals(demandesLivraison.get(0).getPointDeLivraison()
				.getAdresse(), (Integer) 2);
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : une
	 * des livraisons se fait à un point inconnu du réseau
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie1() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_POINT_INCONNU));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_TOURNEE_POINT_INCONNU + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante :
	 * l'entrepot ne fait pas référence à un point connu du réseau
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie2() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_ENTREPOT_INCONNU));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_TOURNEE_ENTREPOT_INCONNU + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : une
	 * heure d'une plage horaire est aberrante (123:78:69 par ex)
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie3() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_HEURE_ABERRANTE));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_FORMAT_PLAGE + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : 2
	 * plages se superposent
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie4() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_SUPERPOSITION_PLAGE_1));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_SUPERPOSITION_PLAGE + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : 2
	 * plages se superposent
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie5() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_SUPERPOSITION_PLAGE_2));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_SUPERPOSITION_PLAGE + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante :
	 * l'heure de début et de fin d'une plage sont identiques
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie6() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_PLAGE_VIDE));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_VIDE_PLAGE + "\r\n");
	}
	
	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante :
	 * l'heure de début et de fin d'une plage sont échangées
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie7() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_PLAGE_FIN_DEBUT));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.ERREUR_FIN_DEBUT_PLAGE + "\r\n");
	}

	/**
	 * Test avec un fichier de demandes de livraison mal forme
	 * 
	 * @author Sonia
	 */
	@Test
	public void testChargerDonneesDemandeXML_MalForme() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML(Properties.CHEMIN_XML_TEST_DEMANDES_MALFORME));
		assertEquals(outContent.toString(), Properties.CHARGEMENT_RESEAU_OK
				+ "\r\n" + Properties.XML_NON_VALIDE + "\r\n");
	}

}
