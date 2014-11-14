package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modele.Point;
import modele.Reseau;
import modele.Troncon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Properties;

public class ReseauTest {
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
	 * réseau crée
	 */
	@Test
	public void testChargerReseauXML_BienFormeSansAnomalie() {
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK);
		Map<Integer, Point> points = reseau.getPoints();
		List<Troncon> troncons = reseau.getTroncons();

		// Est-ce que le nombre de points est le bon ?
		assertEquals(points.size(), 3);

		Set<Integer> cles = reseau.getPoints().keySet();
		// Est-ce que les clés de la map sont les bonnes ?
		assertTrue(cles.contains(0));
		assertTrue(cles.contains(1));
		assertTrue(cles.contains(2));

		// Est-ce que les caractéristiques des points sont les bonnes ?
		Point point1 = points.get(0);
		assertEquals(point1.getAdresse(), (Integer) 0);
		assertEquals(point1.getLongitude(), (Integer) 51);
		assertEquals(point1.getLatitude(), (Integer) 37);
		Point point2 = points.get(1);
		assertEquals(point2.getAdresse(), (Integer) 1);
		assertEquals(point2.getLongitude(), (Integer) 26);
		assertEquals(point2.getLatitude(), (Integer) 65);
		Point point3 = points.get(2);
		assertEquals(point3.getAdresse(), (Integer) 2);
		assertEquals(point3.getLongitude(), (Integer) 23);
		assertEquals(point3.getLatitude(), (Integer) 116);

		// Est-ce que le nombre de tronçons est le bon ?
		assertEquals(troncons.size(), 6);

		// Est-ce que les caractéristiques des troncons sont les bonnes ?
		Troncon troncon1 = troncons.get(0);
		assertEquals(troncon1.getNomRue(), "v0");
		assertEquals(troncon1.getVitesse(), (Double) 4.4);
		assertEquals(troncon1.getDistance(), (Double) 300.2);
		assertEquals(troncon1.getOrigine().getAdresse(), (Integer) 0);
		assertEquals(troncon1.getDestination().getAdresse(), (Integer) 1);
		Troncon troncon2 = troncons.get(1);
		assertEquals(troncon2.getNomRue(), "h0");
		assertEquals(troncon2.getVitesse(), (Double) 4.3);
		assertEquals(troncon2.getDistance(), (Double) 116.4);
		assertEquals(troncon2.getOrigine().getAdresse(), (Integer) 0);
		assertEquals(troncon2.getDestination().getAdresse(), (Integer) 2);
		Troncon troncon3 = troncons.get(2);
		assertEquals(troncon3.getNomRue(), "v0");
		assertEquals(troncon3.getVitesse(), (Double) 4.5);
		assertEquals(troncon3.getDistance(), (Double) 300.2);
		assertEquals(troncon3.getOrigine().getAdresse(), (Integer) 1);
		assertEquals(troncon3.getDestination().getAdresse(), (Integer) 0);
		Troncon troncon4 = troncons.get(3);
		assertEquals(troncon4.getNomRue(), "v1");
		assertEquals(troncon4.getVitesse(), (Double) 4.2);
		assertEquals(troncon4.getDistance(), (Double) 408.7);
		assertEquals(troncon4.getOrigine().getAdresse(), (Integer) 1);
		assertEquals(troncon4.getDestination().getAdresse(), (Integer) 2);
		Troncon troncon5 = troncons.get(4);
		assertEquals(troncon5.getNomRue(), "v2");
		assertEquals(troncon5.getVitesse(), (Double) 4.7);
		assertEquals(troncon5.getDistance(), (Double) 408.7);
		assertEquals(troncon5.getOrigine().getAdresse(), (Integer) 2);
		assertEquals(troncon5.getDestination().getAdresse(), (Integer) 1);
		Troncon troncon6 = troncons.get(5);
		assertEquals(troncon6.getNomRue(), "v3");
		assertEquals(troncon6.getVitesse(), (Double) 3.8);
		assertEquals(troncon6.getDistance(), (Double) 291.2);
		assertEquals(troncon6.getOrigine().getAdresse(), (Integer) 2);
		assertEquals(troncon6.getDestination().getAdresse(), (Integer) 0);

		// Est-ce que tout s'est bien déroulé ?
		assertTrue(reseau
				.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_SIMPLIFIE_OK));
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : un
	 * des tronçons indique un point de destination inexistant
	 */
	@Test
	public void testChargerReseauXML_BienFormeAvecAnomalie1() {
		Reseau reseau = new Reseau();
		assertFalse(reseau
				.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_DESTINATION_INCONNUE));
		assertEquals(outContent.toString(),
				Properties.ERREUR_RESEAU_DESTINATION_INCONNUE + "\r\n");
	}

	/**
	 * Test avec un fichier bien formé mais présentant l'anomalie suivante : un
	 * des tronçons indique un point de destination égal au point d'origine
	 */
	@Test
	public void testChargerReseauXML_BienFormeAvecAnomalie2() {
		Reseau reseau = new Reseau();
		assertFalse(reseau
				.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_ORIGINE_DEST_IDENTIQUE));
		assertEquals(outContent.toString(),
				Properties.ERREUR_RESEAU_ORIGINE_DESTINATION + "\r\n");
	}

	@Test
	public void testChargerReseauXML_MalForme() {
		Reseau reseau = new Reseau();
		assertFalse(reseau
				.chargerReseauXML(Properties.CHEMIN_XML_TEST_RESEAU_MALFORME));
		assertEquals(outContent.toString(), Properties.XML_NON_VALIDE + "\r\n");
	}

}
