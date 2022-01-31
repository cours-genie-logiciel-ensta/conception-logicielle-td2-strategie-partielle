package banqueServeur;

import java.util.ArrayList;

/**
 * API représentant la banque gérant un compte bancaire
 *
 */
public interface IBanque {

	public int demandeRetrait(int unRetrait);

	public int demandeDepot(int unDepot);

	public CompteBancaire getLeCompte();

	public void setTypeOperation(String string);

	public void faireOperation(String string);

	public ArrayList<String> getHistoriqueOperations();

}