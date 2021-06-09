package upo.battleship.cecciTragno;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.ArrayList;

import javax.swing.JButton;
/**
 * Controller del pattern MVC
 */
public class BattleshipController{
	private BattleshipView vista;
	private BattleshipModel modello;
	private boolean orientamento; //true = verticale, false = orizzontale
	VistaGrigliaComputer vistaComputer;
	@SuppressWarnings("unused")
	private PopUp popUp;

	public BattleshipController(BattleshipModel m, BattleshipView v) {
		this.modello = m;
		this.vista = v;
		//this.vistaComputer = vistaComputer;
		modello.fasePosizionamento();
		posizionaNaviCPU();
		
		
	}
	
	/**
	 * Permette il posizionamento, randomico, delle navi della CPU
	 */
	public void posizionaNaviCPU() {
		
		if(modello.getFase() == 1) {
			//ArrayList<Nave> navi =  modello.getComputer().getNaviDaPosizionare();
			//Nave nave = navi.get(0);
			
			try {
				
				modello.getComputer().posizionaRandom();
				/*if(valuta == false) {
					//navi.add(nave);
				}*/
		
			}catch (Exception exc) {
				System.out.println("ERRORE POSIZIONAMENTO NAVE");
			}
			finally {
				popUp = new PopUp("LA FLOTTA IMPERIALE SI E' DISPOSTA");
			}
		}
	}

	public ActionListener assegnaGestoreNewGame() {
		ActionListener gestoreNewGame;
		
		gestoreNewGame = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modello.reset();
				vista.reset();
				posizionaNaviCPU();
			}
			
		};
		
		return gestoreNewGame;
	}
	
	public MouseListener assegnaGestoreVerticale() {
		MouseListener gestoreVerticale;
		
		gestoreVerticale = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "VERTICALE") {
					orientamento = true;
					modello.setVerticale(orientamento);
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return gestoreVerticale;
	}
	
	
	public MouseListener assegnaGestoreOrizzontale() {
		MouseListener gestoreOrizzontale;
		
		gestoreOrizzontale = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton bottone = new JButton();
				bottone = (JButton) e.getSource();
				if(bottone.getText() == "ORIZZONTALE") {
					orientamento = false;
					modello.setVerticale(orientamento);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return gestoreOrizzontale;
	}
	
	public boolean getOrientamento() {
		return orientamento;
	}	
}
