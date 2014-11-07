package utils;

import java.io.File;

import javax.swing.JFileChooser;

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
}

