package upo.battleship.cecciTragno;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Finestra che esce quando avvengono determinati eventie che serve per informare l'utente
 */
@SuppressWarnings("serial")
public class PopUp extends JFrame{
	
	private JLabel messaggio;

	public PopUp(String eccezione){
		super.setLayout(new BorderLayout());
		
		setTitle("Comunicazione di servizio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);
		setBackground(Color.LIGHT_GRAY);
		messaggio = new JLabel(eccezione);
		messaggio.setHorizontalAlignment(JLabel.CENTER);
		messaggio.setVerticalAlignment(JLabel.CENTER);
		add(messaggio, BorderLayout.CENTER);
		setVisible(true);
		setAlwaysOnTop(true);
	}
}
