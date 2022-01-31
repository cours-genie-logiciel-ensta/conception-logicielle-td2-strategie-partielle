package automateClient;

/**
 * Historique encapsule le ClientTCP dédié aux demandes d'affichage
 * d'historique, au meme titre que Automate pour le ClientTCP dédié 
 * aux opérations de traitement
 */
public class Historique {

	public static final String requeteHisto = "afficherHistorique";
	
	private int port = 7777;

	private ClientTCP monClient;
	
	public Historique(){
		monClient = new ClientTCP("localhost", port);	
	}
	
	public String demandeHistorique() {
		return monClient.transmettreChaineConnexionPonctuelle(requeteHisto);
	}

	public void quitAction() {
		System.exit(0);		
	}
}
