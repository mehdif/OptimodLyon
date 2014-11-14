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
import modele.Point;

/**
 * @author Hexanome 4301
 */
public class VueDetail {	
	
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JTextPane champClient;
	private JTextPane champAdresse;
	private JTextPane champPlageHoraire;
	private JTextPane champDemande;
	private Application application;
	
	
	/****************************************************
	 ****************** Constructeur ********************
	 ****************************************************/

    /**
     * Constructeur par defaut
     */
    public VueDetail() {
    }

    /****************************************************
	 *************** Methodes de classes ****************
	 ****************************************************/

    public void onClicAjouterDemande() {
    	btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
    }

    /**
     * Permet d'afficher les informations du point selectionne
     * @param client : String contenant l'ID du client
     * @param adresse : String contenant l'adresse de la demande de livraison
     * @param heureDebut : String contenant l'heure de d�but de la plage horaire associee a la demande de livraison
     * @param heureFin : String contenant l'heure de fin de la plage horaire associee a la demande de livraison
     * @param demande : booleen qui indique si il y a une demande de livraison sur ce point
     */
    public void affichageInfos(String client, String adresse, String heureDebut, String heureFin, boolean demande) {
	    champClient.setText(client);
		champAdresse.setText(adresse);
		champPlageHoraire.setText(heureDebut + "  -  " + heureFin);
		champDemande.setText(demande == true ? "Oui" : "Non");
		champClient.repaint();
		champAdresse.repaint();
		champPlageHoraire.repaint();
		champDemande.repaint();
    }

    public void onClicSupprimerDemande() {
    	btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
    }

    /**
     * Permet d'initialiser les elements de la vue detail
     * @param frame : fentre principale de notre application
     */
    public void initialiser(JFrame frame) {		
		//Vue de droite pour les details du point de livraison selectionne
		JPanel vueDetail = new JPanel(new GridLayout(11, 1));
		frame.getContentPane().add(vueDetail, BorderLayout.EAST);
		
		JTextPane txtpnDtail = new JTextPane();
		txtpnDtail.setText("D\u00E9tail Point");
		vueDetail.add(txtpnDtail);
		
		JTextPane txtpnAdresse = new JTextPane();
		txtpnAdresse.setText("Adresse :");
		vueDetail.add(txtpnAdresse);
		
		champAdresse = new JTextPane();
		vueDetail.add(champAdresse);
		
		JTextPane txtpnDemande = new JTextPane();
		txtpnDemande.setText("Demande :");
		vueDetail.add(txtpnDemande);
		
		champDemande = new JTextPane();
		vueDetail.add(champDemande);
		
		JTextPane txtpnClient = new JTextPane();
		txtpnClient.setText("Client :");
		vueDetail.add(txtpnClient);
		
		champClient = new JTextPane();
		vueDetail.add(champClient);
		
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