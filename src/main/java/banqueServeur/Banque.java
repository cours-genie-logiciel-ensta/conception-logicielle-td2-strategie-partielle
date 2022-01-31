package banqueServeur;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Représente une banque gérant un compte bancaire particulier
 *
 */
public class Banque implements IBanque {

	private int port = 6666;
	private int hport = 7777;
	/** Les serveurs pour la mise en oeuvre de connexion a distance */
	public ArrayList<ServeurTCP> serveurs = new ArrayList<ServeurTCP>();
	/** Le compte en Banque */
	private CompteBancaire leCompte;

	/** Le type d'operation : depot ou retrait */
	private String typeOperation;
	private IDemandeDepot strategydepot;

	private ArrayList<String> historiqueOperations;

	/**
	 * Support du mécanisme Observable/Observer
	 */
	private final PropertyChangeSupport pcSupport;

	public Banque(int uneSomme) {

		pcSupport = new PropertyChangeSupport(this);

		leCompte = new CompteBancaire(uneSomme);
		typeOperation = "Aucune operation";
		historiqueOperations = new ArrayList<String>();
	}

	public Banque(int uneSomme, IDemandeDepot stratD) {
		this(uneSomme);
		strategydepot = stratD;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return pcSupport;
	}

	public void ouvrirBanque() {
		serveurs.add(new ServeurTCP(this, port));
		serveurs.add(new ServeurTCP(this, hport));
		for (ServeurTCP s : serveurs) {
			s.start();
		}
	}

	@Override
	public String toString() {
		return "La Banque possede un compte avec la somme de " + getLeCompte().getSomme();
	}

	@Override
	public synchronized int demandeRetrait(int unRetrait) {
		return 0;
	}
	@Override
	public synchronized int demandeDepot(int unDepot) {
		return strategydepot.demandeDepot(unDepot, this);
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	@Override
	public CompteBancaire getLeCompte() {
		return leCompte;
	}

	@Override
	public void setTypeOperation(String s) {
		typeOperation = s;
	}

	@Override
	public void faireOperation(String string) {

		String dernierTypeOperation = typeOperation;
		int valeurInitiale = leCompte.getSomme();

		this.historiqueOperations.add(string);
		this.setTypeOperation(string);

		pcSupport.firePropertyChange("somme", valeurInitiale, leCompte.getSomme());
		pcSupport.firePropertyChange("typeOperation", dernierTypeOperation, typeOperation);

	}

	public void setStrategyretrait(String typeRetrait) {
		//.... mehode vide actuellement
	}

	public void setStrategydepot(String typeDepot) {
		if (typeDepot.equals(StrategieUI.depotSimple))
			strategydepot = new DemandeDepotSimple();
		else if (typeDepot.equals(StrategieUI.depotTemporise))
			strategydepot = new DemandeDepotTemporise();
	}

	@Override
	public ArrayList<String> getHistoriqueOperations() {
		return historiqueOperations;
	}

}
