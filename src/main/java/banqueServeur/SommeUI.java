package banqueServeur;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SommeUI implements PropertyChangeListener {

	BanqueGUI myGui;
	Banque laBanque;

	public SommeUI(Banque b, BanqueGUI bg) {
		this.laBanque = b;
		this.myGui = bg;
		b.getPropertyChangeSupport().addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		myGui.getTexteSommeBanque().setText(String.valueOf(laBanque.getLeCompte().getSomme()));
		System.out.println("Somme mise a jour : " + laBanque.getLeCompte().getSomme());
		// myGui.getTexteSommeDemandee().setText(arg.toString());
	}
}
