package utils;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLReader {
	private static JFileChooser jFileChooserXML;

	private static File ouvrirFichier() {
		jFileChooserXML = new JFileChooser();
		// Note: source for ExampleFileFilter can be found in FileChooserDemo,
		// under the demo/jfc directory in the JDK.
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("xml");
		filter.setDescription("Fichier XML");
		jFileChooserXML.setFileFilter(filter);
		jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (jFileChooserXML.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
		}
		return null;
	}

	public static Document lireDepuisFichierXML() {
		File xml = ouvrirFichier();
		Document document = null;
		if (xml != null) {
			try {
				// creation d'un constructeur de documents a l'aide d'une
				// fabrique
				DocumentBuilder constructeur = DocumentBuilderFactory
						.newInstance().newDocumentBuilder();

				// Validateur DTD
				// DocumentBuilderFactory factory = DocumentBuilderFactory
				// .newInstance();
				// factory.setValidating(true);
				// factory.setNamespaceAware(true);

				// lecture du contenu d'un fichier XML avec DOM
				document = constructeur.parse(xml);
				return document;

				// todo : traiter les erreurs
			} catch (ParserConfigurationException pce) {
				System.out.println("Erreur de configuration du parseur DOM");
				System.out
						.println("lors de l'appel a fabrique.newDocumentBuilder();");
			} catch (SAXException se) {
				System.out.println("Erreur lors du parsing du document");
				System.out.println("lors de l'appel a construteur.parse(xml)");
			} catch (IOException ioe) {
				System.out.println("Erreur d'entree/sortie");
				System.out.println("lors de l'appel a construteur.parse(xml)");
			}
		}
		return document;
	}
}

