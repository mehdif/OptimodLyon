package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import utils.XMLReader;

public class XMLReaderTest {
	
	@Test
	/**
	 * Test la méthode validerXML avec 2 fichiers XML conformes
	 * @throws SAXException
	 * @throws IOException
	 */
	public void testValiderXMLFichiersConformes() throws SAXException, IOException{	
		assertTrue(XMLReader.validerXML("xmlPourTests/plan20x20_wellFormed.xml", "xsd/reseau.xsd"));
		assertTrue(XMLReader.validerXML("xmlPourTests/livraison20x20-2_wellFormed.xml", "xsd/livraison.xsd"));
	}
	
	@Test
	/**
	 * Test la méthode validerXML avec 2 fichiers XML non conformes
	 * @throws SAXException
	 * @throws IOException
	 */
	public void testValiderXMLFichiersNonConformes() throws SAXException, IOException{	
		assertFalse(XMLReader.validerXML("xmlPourTests/plan20x20_notWellFormed.xml", "xsd/reseau.xsd"));
		assertFalse(XMLReader.validerXML("xmlPourTests/livraison20x20-2_notWellFormed.xml", "xsd/livraison.xsd"));
	}

}
