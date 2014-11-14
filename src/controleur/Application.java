package controleur;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modele.Client;
import modele.DemandeLivraison;
import modele.Itineraire;
import modele.PlageHoraire;
import modele.Point;
import modele.Reseau;
import modele.Tournee;
import modele.Troncon;
import vue.VueEntrepot;
import vue.VueFenetre;
import vue.VueItineraire;
import vue.VuePoint;
import vue.VueTournee;
import vue.VueTroncon;
/**
 * @author Hexanome 4301
 */
public class Application {
        public Receveur receveur;
        public static Tournee tournee;
        public VueFenetre vueFenetre;
        private Invocateur invocateur;
        /****************************************************
         ****************** Constructeur ********************
         ****************************************************/
        

    /**
         * Constructeur par dï¿½faut
     */
    public Application() {
                invocateur = new Invocateur();
                receveur = new Receveur();
        vueFenetre = new VueFenetre(this);
    }


        /****************************************************
         ********************* Getter **********************
         ****************************************************/
        
        public Receveur getReceveur() {
                return receveur;
        }
        public static Tournee getTournee() {
                return tournee;
        }
        public VueFenetre getVueFenetre() {
                return vueFenetre;
        }
        public Invocateur getInvocateur() {
                return invocateur;
        }
        
        /****************************************************
         *************** MÃ©thodes de classes ****************
         ****************************************************/
    public void calculerTournee() {
        tournee.calculerTournee();
    	
    }
    /**
     * @param unReseau
     * Dessine le reseau dans la fenetre principale.
     */
    public void dessinerReseau(Reseau unReseau) {
		this.chargerVueReseau(unReseau.getTroncons(),
				unReseau.getPoints());
        this.vueFenetre.vueReseau.repaint();

    }
    /**
     * Affiche les demandes de livraison sur le plan suivant le code couleur
     */
    public void afficherDemandesLivraison(){
            //Effacement des elements de la collection de vuePoint
                this.vueFenetre.vueReseau.getVuesPoints().clear();
                
                Map<Integer, Point> points = this.tournee.getReseau().getPoints();
                Iterator iterator = points.entrySet().iterator();
                
                Map<PlageHoraire, Color> mapCouleur = new HashMap<PlageHoraire, Color>();
                mapCouleur.clear();
                while (iterator.hasNext()) {
                        
                Map.Entry unPoint = (Map.Entry) iterator.next();
                Point pointCourant = (Point) (unPoint.getValue());
			VuePoint vuePointCourant = new VuePoint(
					pointCourant.getLongitude(), pointCourant.getLatitude(),
					pointCourant.getAdresse());

			// Test de l'existence d'une demande de livraison associee au point
			// courant
                        if (pointCourant.possedeUneDemande()) {
                        	
                        	//Test de l'existence d'une couleur associee a une plage horaire
                            if( ( mapCouleur.get( pointCourant.getUneDemande().getPlageHoraire() ) ) == null )
                            {
                            	Color randomCouleur = new Color( alea(255), alea(255), alea(255) );
                            	mapCouleur.put(pointCourant.getUneDemande().getPlageHoraire() , randomCouleur);
                            }
                            
				vuePointCourant.setCouleurNonClique(mapCouleur.get( pointCourant.getUneDemande().getPlageHoraire() ));
                                this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
                        
			} else {
				vuePointCourant.setCouleurNonClique(Color.BLACK);
                                this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
                }
		}
                
                //Affichage de l'entrepot
                
		VueEntrepot vueEntrepot = new VueEntrepot(this.tournee.getEntrepot()
				.getLongitude(), this.tournee.getEntrepot().getLatitude(),
				this.tournee.getEntrepot().getAdresse());
		vueEntrepot.setCouleurNonClique(Color.GREEN);
                this.vueFenetre.vueReseau.getVuesPoints().add(vueEntrepot);
                
                //Rafraichissement de l'affichage
                
                this.vueFenetre.vueReseau.repaint();
    }

    
    /**
     * Methode generant la feuille de route dans un fichier texte
     */
    public void genererFeuilleDeRoute() {
    }
    /**
     * @param client 
     * @param adresse 
     * @param plageHoraire
     */
	public void affichageInfos(Client client, Integer adresse,
			PlageHoraire plageHoraire) {
        // TODO implement here
    }
    
    /**
     * Charge et dessine le plan du reseau.
     */
    public boolean chargerReseauXML(){
            Reseau reseau = new Reseau();
            boolean chargementOK = reseau.chargerReseauXML(null);
            if(chargementOK){
                    tournee = new Tournee(reseau);
                    this.dessinerReseau(reseau);
            }
            
            return chargementOK;
    }
    
    public boolean chargerDemandeLivraisonXML(){
            boolean chargementOK = tournee.chargerDonneesDemandeXML(null);
            this.vueFenetre.vueReseau.repaint();
            return chargementOK;
    }
    
    
    public void chargerVueReseau(List<Troncon> troncons,
            Map<Integer, Point> points) {

        this.vueFenetre.vueReseau.getVuesPoints().clear();
        this.vueFenetre.vueReseau.getVuesTroncons().clear();
        // Remplissage de la liste vuesPoints

        Set<Integer> listKeys = points.keySet(); // Obtenir la liste des cles
        Iterator<Integer> it = listKeys.iterator();

        // Parcourir les cles et afficher les entrees de chaque cle;
        while (it.hasNext()) {
            Object key = it.next();
            Point p = points.get(key);

            VuePoint vuePoint = new VuePoint(p.getLongitude(), p.getLatitude(),
                    p.getAdresse());
            if (vuePoint != null) {
                this.vueFenetre.vueReseau.getVuesPoints().add(vuePoint);
            }
        }

        // Remplissage de la liste vueTroncons
        for (int i = 0; i < troncons.size(); i++) {
            Troncon t = troncons.get(i);
            Point origine = new Point(t.getOrigine().getLongitude(), t
                    .getOrigine().getLatitude(), t.getOrigine().getAdresse());

            Point destination = new Point(t.getDestination().getLongitude(), t
                    .getDestination().getLatitude(), t.getDestination()
                    .getAdresse());

            VueTroncon vueTroncon = new VueTroncon(origine.getLongitude(),
                    origine.getLatitude(), origine.getAdresse(),
                    destination.getLongitude(), destination.getLatitude(),
                    destination.getAdresse());
            if (vueTroncon != null) {
                this.vueFenetre.vueReseau.getVuesTroncons().add(vueTroncon);
            }
        }
    }

    
    public void afficherItineraire(){
    	
    	VueTournee uneVue = new VueTournee(tournee.getItineraires());
    		this.vueFenetre.vueReseau.setVueTournee(uneVue);
            this.vueFenetre.vueReseau.repaint();
    }
            
    public void recupererPoint(int adresse){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    	Point point = tournee.getReseau().getPointViaAdresse(adresse);
    	if(null != point.getUneDemande()){
			String idClient = point.getUneDemande().getClient().getId()
					.toString();
    		String adresseString = new Integer(adresse).toString();
			String heureDebut = dateFormat.format(point.getUneDemande().getPlageHoraire()
					.getDebut().getTime()).toString();
			String heureFin = dateFormat.format(point.getUneDemande().getPlageHoraire().getFin()
					.getTime()).toString();
			vueFenetre.affichageInfos(idClient, adresseString, heureDebut,
					heureFin, true);
    	}
    	else{

    		String idClient = "Non d�fini";
    		String adresseString = new Integer(adresse).toString();
    		String heureDebut = "Non d�fini";
			String heureFin = "";
			vueFenetre.affichageInfos(idClient, adresseString, heureDebut,
					heureFin, false);
    		
    	}
		// System.out.println(point.getAdresse()+ " : " + "(" +
		// point.getLongitude() + ", " + point.getLatitude()+ ")");
    	//System.out.println(point.getUneDemande());
    }
    
    private int alea(int max){
		return (int) (Math.random() * max);
	}
           
    public static void main(String []args){

       //new Application();
    	
    	
		Reseau reseau = new Reseau();
		reseau.chargerReseauXML("xmlPourTests/plan10x10.xml");
		Tournee tournee = new Tournee(reseau);
		tournee.chargerDonneesDemandeXML("xmlPourTests/livraison10x10-3.xml");
        tournee.calculerTournee();
      }
}
