package automateClient;

import java.beans.PropertyChangeSupport;

public class Portefeuille {

	int somme = 0;

	/**
	 * Support du mécanisme Observable/Observer
	 */
	private PropertyChangeSupport pcSupport;

	public Portefeuille(int uneSomme) {
		super();
		pcSupport = new PropertyChangeSupport(this);
		this.somme = uneSomme;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return pcSupport;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int uneSomme) {
		this.somme = uneSomme;
	}

	public void setArgentPoche(int uneSomme) {
		System.out.println("Somme deposee dans le porte-feuille " + uneSomme);
		this.setSomme(uneSomme);
	}

	public void retirerSomme(int uneSomme) {
		int derniereSomme = getSomme();
		somme -= uneSomme;
		pcSupport.firePropertyChange("somme", derniereSomme, getSomme());
	}

	public void ajouterSomme(int uneSomme) {
		int derniereSomme = getSomme();
		somme += uneSomme;
		pcSupport.firePropertyChange("somme", derniereSomme, getSomme());
	}

	@Override
	public String toString() {
		return "Somme du portefeuille = " + somme;
	}

}
