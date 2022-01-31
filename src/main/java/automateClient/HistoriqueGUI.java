package automateClient;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 * HistoriqueGUI est l'interface d'affichage d'historique 
 * (comme ClientGUI pour les traitements de sommes)
 */
public class HistoriqueGUI extends javax.swing.JFrame {

	private JTextArea texteHistorique;

	private Historique historique;

	public HistoriqueGUI( Historique h ) {
		super("Historique des operations");
		historique = h;
	}

	public JTextArea getTexteHistorique() {
		return texteHistorique;
	}
	
	public void initGUI() {
		this.setLocation(25,550);
		this.setVisible(true);
		try {

			BorderLayout thisLayout = new BorderLayout(5,5);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				JButton boutonHisto = new JButton("Demande Historique");
				getContentPane().add(boutonHisto, BorderLayout.PAGE_START);
				boutonHisto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						getTexteHistorique().setText(historique.demandeHistorique());
					}
				});
			}
			{
				JScrollPane jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(173, 100));
				{
					texteHistorique = new JTextArea();
					jScrollPane1.setViewportView(texteHistorique);
					texteHistorique.setText("Liste des operations vide");
					texteHistorique.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				}
			}
			{
				JButton boutonQuitter = new JButton("Quitter");
				getContentPane().add(boutonQuitter, BorderLayout.PAGE_END);
				boutonQuitter.setPreferredSize(new java.awt.Dimension(93, 30));
				boutonQuitter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						historique.quitAction();
					}
				});
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
