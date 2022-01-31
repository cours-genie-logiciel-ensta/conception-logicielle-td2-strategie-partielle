package banqueServeur;

/**
 * Contient la méthode main()
 * 
 */
public class MainServeur {

	/**
	 * Méthode principale : lance le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Banque maBanque = new Banque(10000, new DemandeDepotSimple());
		/* BanqueGUI remplace MontantBanqueGUI des TPs precedents */
		BanqueGUI banqueGUI = new BanqueGUI(maBanque);
		banqueGUI.initGui();
		maBanque.ouvrirBanque();

	}
}
