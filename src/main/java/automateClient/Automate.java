package automateClient;

/**
 * Représente un automate lié à un compte client, permettant de se connecter à
 * une banque, et de pouvoir effectuer un retrait
 * 
 * Cette classe représente également la somme en poche de l'utilisateur de cet
 * automate
 * 
 * @author sylvainguerin
 *
 */

public class Automate implements IAutomate {

	private int port = 6666;
	private Portefeuille portefeuille;
	private ClientTCP monClientTCP;

	public Automate(int sommeenpoche) {
		monClientTCP = new ClientTCP("localhost", port);
		portefeuille = new Portefeuille(sommeenpoche);
	}

	public void setArgentEnPoche(int uneSomme) {
		portefeuille.setArgentPoche(uneSomme);
	}

	public Portefeuille getPortefeuille() {
		return portefeuille;
	}

	public int getArgentEnPoche() {
		return portefeuille.getSomme();
	}

	@Override
	public void depot(int unDepot) {
		System.out.println("Dépôt de " + unDepot);
		portefeuille.retirerSomme(unDepot);
		System.out.println("Somme en poche finale " + portefeuille.getSomme());
	}

	@Override
	public void retrait(int unRetrait) {
		System.out.println("Retrait de " + unRetrait);
		portefeuille.ajouterSomme(unRetrait);
		System.out.println("Somme en poche finale " + portefeuille.getSomme());
	}

	public int demandeRetrait(int laSomme) {
		int valeurRetrait = 0;
		String valeurTransmise = monClientTCP.transmettreChaine("retrait " + laSomme);
		valeurRetrait = Integer.parseInt(valeurTransmise);
		retrait(valeurRetrait);
		return valeurRetrait;
	}

	public int demandeDepot(int laSomme) {
		int valeurDepot = 0;
		String valeurTransmise = monClientTCP.transmettreChaine("depot " + laSomme);
		valeurDepot = Integer.parseInt(valeurTransmise);
		depot(valeurDepot);
		return valeurDepot;
	}

	@Override
	public boolean connexionBanque() {
		return monClientTCP.connecterAuServeur();
	}

	@Override
	public void deconnexionBanque() {
		monClientTCP.deconnecterDuServeur();
	}

	@Override
	public String toString() {
		return "Somme en poche : " + portefeuille;
	}

}
