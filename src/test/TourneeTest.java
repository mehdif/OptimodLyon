package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import modele.DemandeLivraison;
import modele.PlageHoraire;
import modele.Reseau;
import modele.Tournee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeSansAnomalie() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML("xmlPourTests/planSimplifie_BienForme.xml");
		Tournee tournee = new Tournee(reseau);

		assertTrue(tournee
				.chargerDonneesDemandeXML("xmlPourTests/livraisonSimplifie_BienForme.xml"));
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
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie1() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML("xmlPourTests/planSimplifie_BienForme.xml");
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML("xmlPourTests/livraisonSimplifie_BienFormeAvecAnomalie1.xml"));
		assertEquals(
				outContent.toString(),
				"Erreur : le document renseigné possède un ou plusieurs points de livraison inconnus, abandon du chargement des livraisons\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante :
	 * l'entrepot ne fait pas référence à un point connu du réseau
	 */
	@Test
	public void testChargerDonneesDemandeXML_BienFormeAvecAnomalie2() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML("xmlPourTests/planSimplifie_BienForme.xml");
		Tournee tournee = new Tournee(reseau);
		assertFalse(tournee
				.chargerDonneesDemandeXML("xmlPourTests/livraisonSimplifie_BienFormeAvecAnomalie2.xml"));
		assertEquals(
				outContent.toString(),
				"Erreur : l'entrepôt décrit dans le document ne correspond pas à un des points du réseau, abandon du chargement des livraisons\r\n");
	}

	@Test
	public void testChargerDonneesDemandeXML_MalForme() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML("xmlPourTests/plan20x20_wellFormed.xml");
		Tournee tournee = new Tournee(reseau);
		tournee.chargerDonneesDemandeXML("xmlPourTests/livraison20x20-2_notWellFormed.xml");
		File xml = new File("xmlPourTests/livraison20x20-2_notWellFormed.xml");
		assertEquals(outContent.toString(), xml.getAbsolutePath()
				+ " n'est pas valide\r\n");
	}

}
