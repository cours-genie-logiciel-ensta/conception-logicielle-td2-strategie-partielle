package banqueServeur;

/**
 * Une API simple, qui représente la stratégie (générique) pour un dépôt
 *
 */
public interface IDemandeDepot {

	public int demandeDepot(int unDepot, IBanque b);
}