package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import controleur.Application;
import modele.Client;
import modele.PlageHoraire;

/**
 * @author Hexanome 4301
 */
public class VueDetail {	
	
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JTextPane champClient;
	private JTextPane champAdresse;
	private JTextPane champPlageHoraire;


    /**
     * 
     */
    public VueDetail() {
    }

    /**
     * 
     */
    public void onClicAjouterDemande() {
    	btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
    }

    /**
     * @param client 
     * @param adresse 
     * @param plageHoraire
     */
    public void affichageInfos(String client, String adresse, String heureDebut, String heureFin) {
    	champClient.setText(client);
		champAdresse.setText(adresse);
		champPlageHoraire.setText(heureDebut + "\n" + heureFin);
		champClient.repaint();
		champAdresse.repaint();
		champPlageHoraire.repaint();
		
    }

    /**
     * 
     */
    public void onClicSupprimerDemande() {
    	btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
    }

    /**
     * @return
     */
    public void initialiser(JFrame frame) {		
		//Vue de droite pour les d�tails du point de livraison s�lectionn�
		JPanel vueDetail = new JPanel(new GridLayout(9, 1));
		frame.getContentPane().add(vueDetail, BorderLayout.EAST);
		
		JTextPane txtpnDtail = new JTextPane();
		txtpnDtail.setText("D\u00E9tail de la livraison");
		vueDetail.add(txtpnDtail);
		
		JTextPane txtpnClient = new JTextPane();
		txtpnClient.setText("Client :");
		vueDetail.add(txtpnClient);
		
		champClient = new JTextPane();
		vueDetail.add(champClient);
		
		JTextPane txtpnAdresse = new JTextPane();
		txtpnAdresse.setText("Adresse :");
		vueDetail.add(txtpnAdresse);
		
		champAdresse = new JTextPane();
		vueDetail.add(champAdresse);
		
		JTextPane txtpnPlageHoraire = new JTextPane();
		txtpnPlageHoraire.setText("Plage horaire de livraison :");
		vueDetail.add(txtpnPlageHoraire);
		
		champPlageHoraire = new JTextPane();
		vueDetail.add(champPlageHoraire);
		
		btnAjouter = new JButton("Ajouter");
		vueDetail.add(btnAjouter);
		onClicAjouterDemande();
		
		btnSupprimer = new JButton("Supprimer");
		vueDetail.add(btnSupprimer);
		onClicSupprimerDemande();
		//Fin vue de droite
    }

}