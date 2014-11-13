package controleur;

/**
 * @author Hexanome 4301
 */
public interface Commande {
    public boolean execute();
    public boolean unexecute();
}