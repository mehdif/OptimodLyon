package controleur;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;

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
         * Constructeur par d�faut
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
         *************** Méthodes de classes ****************
         ****************************************************/

    public void calculerTournee() {
        // TODO implement here
    }

    /**
     * @param unReseau
     * Dessine le reseau dans la fenetre principale.
     */
    public void dessinerReseau(Reseau unReseau) {

                this.vueFenetre.vueReseau.chargerVueReseau(unReseau.getTroncons(), unReseau.getPoints());
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
                
                while (iterator.hasNext()) {
                        
                        Map.Entry unPoint = (Map.Entry) iterator.next();
                        Point pointCourant = (Point) (unPoint.getValue());
                        VuePoint vuePointCourant = new VuePoint(pointCourant.getLongitude(), pointCourant.getLatitude(), pointCourant.getAdresse());
                        
                        //Test de l'existence d'une demande de livraison associee au point courant
                        if (pointCourant.possedeUneDemande()) {
                        
                                vuePointCourant.setCouleur(Color.RED);
                                this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
                        
                        } else
                                this.vueFenetre.vueReseau.getVuesPoints().add(vuePointCourant);
                }
                
                //Affichage de l'entrepot
                
                VueEntrepot vueEntrepot = new VueEntrepot(this.tournee.getEntrepot().getLongitude(), this.tournee.getEntrepot().getLatitude(), this.tournee.getEntrepot().getAdresse());
                vueEntrepot.setCouleur(Color.YELLOW);
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
    public void affichageInfos(Client client, Integer adresse, PlageHoraire plageHoraire) {
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
    
    public void pointClique (){
    	vueFenetre.vueReseau.getVuePointClique().isClique();
    }
    
    public void afficherItineraire(){
            
            System.out.println("clic afficherItineraire");
            Map<Integer, Point> points = this.tournee.getReseau().getPoints();
            
            //* Generation d'une tournee de test
            
            Point point0 = points.get(0);
            Point point1 = points.get(1);
            Point point2 = points.get(2);
            Point point3 = points.get(3);
            Point point4 = points.get(4);
            Point point5 = points.get(5);
            Point point6 = points.get(6);
            
            Troncon troncon1 = new Troncon("a", 1.0, 1.0, point0, point1);
            Troncon troncon2 = new Troncon("b", 1.0, 1.0, point1, point2);
            Troncon troncon3 = new Troncon("c", 1.0, 1.0, point2, point3);
            Troncon troncon4 = new Troncon("d", 1.0, 1.0, point3, point4);
            Troncon troncon5 = new Troncon("e", 1.0, 1.0, point4, point5);
            Troncon troncon6 = new Troncon("f", 1.0, 1.0, point5, point6);
            
            Itineraire unItineraire = new Itineraire();
            unItineraire.ajouterTroncon(troncon1);
            unItineraire.ajouterTroncon(troncon2);
            unItineraire.ajouterTroncon(troncon3);
            unItineraire.ajouterTroncon(troncon4);
            unItineraire.ajouterTroncon(troncon5);
            unItineraire.ajouterTroncon(troncon6);
            
            VueItineraire uneVueItineraire = new VueItineraire(unItineraire.getTroncons(), Color.RED);
            VueTournee uneVueTournee = new VueTournee();
            uneVueTournee.ajouterVueItineraire(uneVueItineraire);
            //*/
            this.vueFenetre.vueReseau.setVueTournee(uneVueTournee);
            this.vueFenetre.vueReseau.repaint();
    }
            
    public static void main(String []args){
            new Application();
    }

}
