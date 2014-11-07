package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import modele.Client;
import modele.PlageHoraire;

/**
 * @author Hexanome 4301
 */
public class VueDetail {	
	
	private JButton btnSupprimerAjouter;
	private JTextPane ChampClient;
	private JTextPane ChampAdresse;
	private JTextPane champPlageHoraire;
		


    /**
     * 
     */
    public VueDetail(JFrame frame) {
    	initialiser(frame);
    }

    /**
     * 
     */
    public void onClicAjouterDemande() {
		btnSupprimerAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
    }

    /**
     * @param client 
     * @param adresse 
     * @param plageHoraire
     */
    public void affichageInfos(Client client, Integer adresse, PlageHoraire plageHoraire) {
    	ChampClient.setText(client.toString());
		ChampAdresse.setText(Integer.toString(adresse));
		champPlageHoraire.setText(plageHoraire.toString());
    }

    /**
     * 
     */
    public void onClicSupprimerDemande() {
		btnSupprimerAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
    }

    /**
     * @return
     */
    public Boolean initialiser(JFrame frame) {		
		//Vue de droite pour les détails du point de livraison sélectionné
		JPanel vueDetail = new JPanel(new GridLayout(8, 1));
		frame.getContentPane().add(vueDetail, BorderLayout.EAST);
		
		JTextPane txtpnDtail = new JTextPane();
		txtpnDtail.setText("D\u00E9tail de la livraison");
		vueDetail.add(txtpnDtail);
		
		JTextPane txtpnClient = new JTextPane();
		txtpnClient.setText("Client :");
		vueDetail.add(txtpnClient);
		
		ChampClient = new JTextPane();
		vueDetail.add(ChampClient);
		
		JTextPane txtpnAdresse = new JTextPane();
		txtpnAdresse.setText("Adresse :");
		vueDetail.add(txtpnAdresse);
		
		ChampAdresse = new JTextPane();
		vueDetail.add(ChampAdresse);
		
		JTextPane txtpnPlageHoraire = new JTextPane();
		txtpnPlageHoraire.setText("Plage horaire de livraison :");
		vueDetail.add(txtpnPlageHoraire);
		
		champPlageHoraire = new JTextPane();
		vueDetail.add(champPlageHoraire);
		
		btnSupprimerAjouter = new JButton("Supprimer / Ajouter");
		vueDetail.add(btnSupprimerAjouter);
		//Fin vue de droite
        return null;
    }

}