package automateClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

//----------------------------------------------------------------------------
// BanqueObservateur/ClientGUI.java                                                                  
//----------------------------------------------------------------------------
/**
 * * This code was edited or generated using CloudGarden's Jigloo * SWT/Swing
 * GUI Builder, which is free for non-commercial * use. If Jigloo is being used
 * commercially (ie, by a corporation, * company or business for any purpose
 * whatever) then you * should purchase a license for each developer using
 * Jigloo. * Please visit www.cloudgarden.com for details. * Use of Jigloo
 * implies acceptance of these licensing terms. * A COMMERCIAL LICENSE HAS NOT
 * BEEN PURCHASED FOR * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED *
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ClientGUI extends JFrame implements PropertyChangeListener {

	private int sommenpoche;
	private int sommetraitee;
	private String typeoperation;
	private Automate automate;

	private JTextField jTextFieldSommenpoche;
	private JTextField jTextFieldSommeatraiter;

	// Constructors
	public ClientGUI(Automate unAutomate) {
		super("Automate Bancaire");
		automate = unAutomate;

		// On vient ensuite "écouter" le portefeuille (c'est la classe ClientGUI qui va
		// recevoir les notifications)
		automate.getPortefeuille().getPropertyChangeSupport().addPropertyChangeListener(this);

		sommenpoche = 0;
		sommetraitee = 0;
		typeoperation = "retrait";
	}

	private JLabel createJLabelSommenpoche() {
		JLabel jLabelSommenpoche = new JLabel("Somme en poche");
		jLabelSommenpoche.setPreferredSize(new java.awt.Dimension(150, 40));
		jLabelSommenpoche.setHorizontalTextPosition(SwingConstants.CENTER);
		return jLabelSommenpoche;
	}

	private JLabel createJLabelSommetraitee() {
		JLabel jLabelSommetraitee = new JLabel("Somme a traiter");
		jLabelSommetraitee.setPreferredSize(new java.awt.Dimension(150, 40));
		jLabelSommetraitee.setHorizontalTextPosition(SwingConstants.CENTER);
		return jLabelSommetraitee;
	}

	public JTextField createJTextFieldSommenpoche() {
		if (jTextFieldSommenpoche == null) {
			jTextFieldSommenpoche = new JTextField();
			jTextFieldSommenpoche.setText(Integer.toString(this.getSommenpoche()));
			jTextFieldSommenpoche.setSize(new java.awt.Dimension(120, 37));
			jTextFieldSommenpoche.setHorizontalAlignment(SwingConstants.LEFT);
			jTextFieldSommenpoche.setEditable(false);
		}
		return jTextFieldSommenpoche;
	}

	private JTextField createJTextFieldSommeatraiter() {
		if (jTextFieldSommeatraiter == null) {
			jTextFieldSommeatraiter = new JTextField();
			jTextFieldSommeatraiter.setSize(new java.awt.Dimension(140, 37));
			jTextFieldSommeatraiter.setBackground(new java.awt.Color(181, 217, 38));
			jTextFieldSommeatraiter.setForeground(new java.awt.Color(0, 0, 128));
			jTextFieldSommeatraiter.setFont(new java.awt.Font("Courier New", 1, 18));
			jTextFieldSommeatraiter.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
		}
		return jTextFieldSommeatraiter;
	}

	public int getSommenpoche() {
		return sommenpoche;
	}

	public int getSommetraitee() {
		return sommetraitee;
	}

	public String getTypeoperation() {
		return typeoperation;
	}

	/** Initialisation de la GUI pour l'interface client */
	public void initGUI() {
		System.out.println(automate);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			getContentPane().setForeground(new java.awt.Color(255, 0, 128));
			this.setLocation(25, 350);
			this.setVisible(true);
			this.setFont(new java.awt.Font("Antique Olive", 0, 10));
			getContentPane().setBackground(new java.awt.Color(255, 128, 64));
			getContentPane().setLayout(new BorderLayout(3, 2));

			// Panel pour les boutons
			JPanel jPanelsud = new JPanel();
			getContentPane().add(jPanelsud, BorderLayout.SOUTH);
			jPanelsud.setPreferredSize(new java.awt.Dimension(392, 36));
			jPanelsud.setBackground(new java.awt.Color(255, 128, 128));
			// Valider
			JButton valider = new JButton("Valider");
			jPanelsud.add(valider);
			valider.setSize(190, 30);
			valider.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					boolean connexionOk = automate.connexionBanque();
					if (connexionOk == false) {
						setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(getContentPane(),
								"Pas de serveur ou nombre d'actions maximal atteint!\n Fermez le client.",
								"Erreur transaction", JOptionPane.ERROR_MESSAGE);
					} else {
						System.out.println("la somme a traiter " + jTextFieldSommeatraiter.getText());
						setSommenpoche(Integer.parseInt(jTextFieldSommenpoche.getText()));
						System.out.println("le type d'operation : " + getTypeoperation());
						if (getTypeoperation().equals("retrait")) {
							System.out.println(" somme traitee en retrait " + jTextFieldSommeatraiter.getText());
							automate.demandeRetrait(Integer.parseInt(jTextFieldSommeatraiter.getText()));
						}
						if (getTypeoperation().equals("depot")) {
							System.out.println(" somme traitee en depot " + jTextFieldSommeatraiter.getText());
							automate.demandeDepot(Integer.parseInt(jTextFieldSommeatraiter.getText()));
						}
						automate.deconnexionBanque();
						System.out.println("Deconnection banque. Etat du compte : " + automate);
					}
				}
			});

			JButton quitter = new JButton("Quitter");
			jPanelsud.add(quitter);
			quitter.setSize(190, 30);
			quitter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					System.exit(0);
				}
			});

			// Panel pour Somme en poche (ouest)
			JPanel jPanelouest = new JPanel(new GridLayout(2, 1, 4, 10));
			getContentPane().add(jPanelouest, BorderLayout.WEST);
			jPanelouest.setOpaque(true);
			jPanelouest.setFont(new java.awt.Font("Tahoma", 1, 13));
			jPanelouest.setSize(150, 118);
			jPanelouest.add(createJLabelSommenpoche());
			jPanelouest.add(createJTextFieldSommenpoche());
			jPanelouest.setBorder(new LineBorder(Color.WHITE, 4, false));

			// Panel pour Somme a traiter (centre)
			JPanel jPanelcentre = new JPanel(new GridLayout(2, 1, 4, 10));
			getContentPane().add(jPanelcentre, BorderLayout.CENTER);
			jPanelcentre.setOpaque(true);
			jPanelcentre.setForeground(new java.awt.Color(255, 128, 64));
			jPanelcentre.setBackground(new java.awt.Color(255, 128, 0));
			jPanelcentre.setBorder(new LineBorder(new java.awt.Color(255, 128, 0), 4, false));
			jPanelcentre.setPreferredSize(new java.awt.Dimension(200, 118));

			jPanelcentre.add(createJLabelSommetraitee());
			jPanelcentre.add(createJTextFieldSommeatraiter());

			// Panel pour choix de l'operation (est)
			JPanel jPanelNO = new JPanel();
			jPanelNO.setPreferredSize(new java.awt.Dimension(193, 47));
			jPanelNO.setBackground(new java.awt.Color(255, 128, 128));
			GridLayout jPanelNOLayout = new GridLayout(3, 1);
			jPanelouest.add(jPanelNO);
			jPanelNOLayout.setHgap(5);
			jPanelNOLayout.setVgap(5);
			jPanelNOLayout.setColumns(1);
			jPanelNO.setLayout(jPanelNOLayout);
			getContentPane().add(jPanelNO, BorderLayout.EAST);
			// Choix de l'operation
			JLabel jLabelOperation = new JLabel("Choix de l'operation");
			jPanelNO.add(jLabelOperation);
			jLabelOperation.setForeground(Color.WHITE);
			jLabelOperation.setPreferredSize(new java.awt.Dimension(183, 38));
			jLabelOperation.setBackground(new java.awt.Color(0, 128, 64));
			jLabelOperation.setOpaque(true);
			// Bouton retrait
			JRadioButton jRadioButtonRetrait = new JRadioButton("Retrait");
			jPanelNO.add(jRadioButtonRetrait);
			jRadioButtonRetrait.setBackground(new java.awt.Color(23, 238, 233));
			jRadioButtonRetrait.setFont(new java.awt.Font("Tahoma", 1, 11));
			jRadioButtonRetrait.setSelected(true);
			// Bouton retrait
			JRadioButton jRadioButtonDepot = new JRadioButton("Depot");
			jPanelNO.add(jRadioButtonDepot);
			jRadioButtonDepot.setBackground(new java.awt.Color(128, 255, 0));
			jRadioButtonDepot.setFont(new java.awt.Font("Tahoma", 1, 11));
			jRadioButtonDepot.setSelected(false);
			jPanelNO.add(jRadioButtonDepot);

			ButtonGroup buttonGroup1 = new ButtonGroup();
			buttonGroup1.add(jRadioButtonDepot);

			jRadioButtonDepot.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					System.out.println("jRadioButtonDepot.actionPerformed, event=" + evt);
					setTypeoperation("depot");
				}
			});

			buttonGroup1.add(jRadioButtonRetrait);
			jRadioButtonRetrait.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					System.out.println("jRadioButtonRetrait.actionPerformed, event=" + evt);
					setTypeoperation("retrait");
				}
			});

			pack();
			this.setAlwaysOnTop(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param sommenpoche
	 */
	public void setSommenpoche(int sommenpoche) {
		this.sommenpoche = sommenpoche;
	}

	/**
	 * @param sommetraitee
	 */
	public void setSommetraitee(int sommetraitee) {
		this.sommetraitee = sommetraitee;
	}

	/**
	 * @param typeoperation
	 */
	public void setTypeoperation(String typeoperation) {
		this.typeoperation = typeoperation;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		jTextFieldSommenpoche.setText(Integer.toString(automate.getArgentEnPoche()));
	}

}
