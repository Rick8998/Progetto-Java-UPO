package upo.battleship.cecciTragno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * Bottone di uscita dai {@link PopUp}
 */
@SuppressWarnings("serial")
public class ExitButton extends JButton implements ActionListener {

	public ExitButton() {
		super("OK");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
