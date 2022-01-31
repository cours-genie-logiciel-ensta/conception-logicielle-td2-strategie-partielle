package banqueServeur;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StrategieUI implements PropertyChangeListener {

	public static final String retraitSimple = "RetraitSimple";
	public static final String retraitPlafond = "RetraitPlafond";
	public static final String depotSimple = "DepotSimple";
	public static final String depotTemporise = "DepotTemporise";

	BanqueGUI myGui;
	Banque laBanque;

	public StrategieUI(Banque b, BanqueGUI bg) {
		myGui = bg;
		laBanque = b;
		b.getPropertyChangeSupport().addPropertyChangeListener(this);
	}

	public void setStrategieRetrait(String s) {
		laBanque.setStrategyretrait(s);
	}

	public void quitter() {
		System.exit(0);
	}

	public void setStrategieDepot(String s) {
		laBanque.setStrategydepot(s);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		myGui.getOperationCourante().setText(String.valueOf(laBanque.getTypeOperation()));

	}
}
