package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import utils.Properties;
import utils.XMLReader;

public class XMLReaderTest {
	
	@Test
	/**
	 * Test la méthode validerXML avec 2 fichiers XML conformes
	 * @throws SAXException
	 * @throws IOException
	 * 
	 * @author Sonia
	 */
	public void testValiderXMLFichiersConformes() throws SAXException, IOException{	
		assertTrue(XMLReader.validerXML(Properties.CHEMIN_XML_TEST_RESEAU_OK, Properties.CHEMIN_XSD_RESEAU));
		assertTrue(XMLReader.validerXML(Properties.CHEMIN_XML_TEST_DEMANDES_OK, Properties.CHEMIN_XSD_TOURNEE));
	}
	
	@Test
	/**
	 * Test la méthode validerXML avec 2 fichiers XML non conformes
	 * @throws SAXException
	 * @throws IOException
	 * 
	 * @author Sonia
	 */
	public void testValiderXMLFichiersNonConformes() throws SAXException, IOException{	
		assertFalse(XMLReader.validerXML(Properties.CHEMIN_XML_TEST_RESEAU_MALFORME, Properties.CHEMIN_XSD_RESEAU));
		assertFalse(XMLReader.validerXML(Properties.CHEMIN_XML_TEST_DEMANDES_MALFORME, Properties.CHEMIN_XSD_TOURNEE));
	}

}
