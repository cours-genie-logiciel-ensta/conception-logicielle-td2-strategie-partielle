package banqueServeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Une interface graphique qui affiche la banque, le type de retrait, de dépôt,
 * l'état du compte, la dernière opération et la somme en banque
 *
 */
@SuppressWarnings("serial")
public class BanqueGUI extends javax.swing.JFrame {

	StrategieUI strategieUI;
	SommeUI sommeUI;

	private JTextField texteSommeBanque;
	private JTextField texteSommeDemandee;
	private JTextField texteOperationCourante;

	public BanqueGUI(Banque b) {
		super();
		strategieUI = new StrategieUI(b, this);
		sommeUI = new SommeUI(b, this);
	}

	public void initGui() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setLocation(25, 50);
			this.setVisible(true);
			{
				JPanel jPanel1 = new JPanel();
				GridLayout jPanel1Layout = new GridLayout(6, 2);
				jPanel1Layout.setHgap(20);
				jPanel1Layout.setVgap(5);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(262, 266));

				JLabel labelBanque = new JLabel("La Banque");
				jPanel1.add(labelBanque);
				labelBanque.setForeground(Color.YELLOW);
				labelBanque.setBackground(Color.BLUE);
				labelBanque.setOpaque(true);

				JLabel labelAgence = new JLabel(" Etat du compte");
				jPanel1.add(labelAgence);
				labelAgence.setForeground(Color.YELLOW);
				labelAgence.setBackground(Color.BLUE);
				labelAgence.setOpaque(true);

				JLabel labelRetrait = new JLabel("Type de Retrait");
				jPanel1.add(labelRetrait);
				labelRetrait.setBackground(new java.awt.Color(128, 255, 128));
				labelRetrait.setOpaque(true);

				JLabel labelOperationCourante = new JLabel("Derniere operation");
				jPanel1.add(labelOperationCourante);
				labelOperationCourante.setPreferredSize(new java.awt.Dimension(180, 40));
				labelOperationCourante.setOpaque(true);

				JComboBox<String> comboStrategieRetrait = new JComboBox<String>(
						new String[] { StrategieUI.retraitSimple, StrategieUI.retraitPlafond });
				jPanel1.add(comboStrategieRetrait);
				comboStrategieRetrait.setSelectedIndex(0);
				comboStrategieRetrait.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						String typeRetrait = (String) ((JComboBox) evt.getSource()).getSelectedItem();
						strategieUI.setStrategieRetrait(typeRetrait);
					}
				});

				texteOperationCourante = new JTextField("Aucune");
				jPanel1.add(texteOperationCourante);
				texteOperationCourante.setEditable(false);
				texteOperationCourante.setBackground(Color.WHITE);

				JLabel labelDepot = new JLabel("Type de Depot");
				jPanel1.add(labelDepot);
				labelDepot.setBackground(new java.awt.Color(128, 255, 128));
				labelDepot.setOpaque(true);

				JLabel sommeBanque = new JLabel("Somme en banque");
				jPanel1.add(sommeBanque);
				sommeBanque.setOpaque(true);

				JComboBox<String> comboStrategieDepot = new JComboBox<String>(
						new String[] { StrategieUI.depotSimple, StrategieUI.depotTemporise });
				jPanel1.add(comboStrategieDepot);
				strategieUI.setStrategieDepot(StrategieUI.depotSimple);
				comboStrategieDepot.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						String typeDepot = (String) ((JComboBox) evt.getSource()).getSelectedItem();
						strategieUI.setStrategieDepot(typeDepot);
					}
				});

				texteSommeBanque = new JTextField();
				texteSommeBanque.setEditable(false);
				texteSommeBanque.setBackground(Color.WHITE);
				jPanel1.add(texteSommeBanque);

				jPanel1.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoBank.jpg"))));

				JButton boutonQuitter = new JButton("Quitter");
				jPanel1.add(boutonQuitter);
				boutonQuitter.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						strategieUI.quitter();
					}
				});
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * --------------------------------------------------------------- Accessors for
	 * fields that update
	 * ---------------------------------------------------------------
	 */

	public JTextField getOperationCourante() {
		return texteOperationCourante;
	}

	public JTextField getTexteSommeBanque() {
		return texteSommeBanque;
	}

	public JTextField getTexteSommeDemandee() {
		return texteSommeDemandee;
	}
}
