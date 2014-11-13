package utils;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 * 
 * @author Sonia
 *
 */
public class XMLReader {
	private static JFileChooser jFileChooserXML;

	public static File ouvrirFichier() {
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
	
	
	public static boolean validerXML(String fichierXML, String fichierXSD) throws SAXException, IOException {
		 
        // 1. Récupère une "factory" pour le XML Schema du W3C
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
         
        // 2. Compile le schéma
        File schemaLocation = new File(fichierXSD);
        Schema schema = factory.newSchema(schemaLocation);
     
        // 3. Récupère un validateur depuis le schéma
        Validator validator = schema.newValidator();
         
        // 4. Parse le document que l'on veut vérifier
        Source source = new StreamSource(fichierXML);
         
        // 5. Vérifie le document
        try {
            validator.validate(source);
            return true;
        }
        catch (SAXException ex) {
             System.out.println(Properties.XML_NON_VALIDE);
            return false;
        } 
	}
}

