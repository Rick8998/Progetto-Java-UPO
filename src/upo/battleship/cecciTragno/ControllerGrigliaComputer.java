package upo.battleship.cecciTragno;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Controlla il flusso di dati/eventi della griglia del {@link Computer}
 *
 */
public class ControllerGrigliaComputer{
	
	VistaGrigliaComputer vistaComputer;
	VistaGrigliaGiocatore vistaPlayer;
	BattleshipModel model;
	BattleshipController controllore;
	PopUp popUp;
	
	public ControllerGrigliaComputer(VistaGrigliaComputer vista, BattleshipModel model, BattleshipController controllore, VistaGrigliaGiocatore vista2) {
		this.model = model;
		this.vistaComputer = vista;
		this.controllore = controllore;
		this.vistaPlayer = vista2;
	}
	
	public MouseListener assegnaGestoreCelle(int x, int y) {
		MouseListener gestoreCelle;
		
		gestoreCelle = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(model.getFase() == 2 && model.getTurno() == 0) {
					//l'utente colpisce
					int valuta = model.getGrigliaComputer().colpisci(x, y);
					if(valuta == 0) {
						vistaComputer.colpisciCellaVuota(x, y);
						model.setTurno(1);
					}
					else if(valuta == -1) {
						popUp = new PopUp("CELLA COLPITA NON VALIDA, RITENTA");
						valuta = model.getGrigliaComputer().colpisci(x, y);
					}
					else if(valuta == 1) {
						vistaComputer.colpisciCellaPiena(x, y);
						model.setTurno(1);
					}
					else if(valuta == 2) {
						vistaComputer.colpisciNaveColpita(x, y);
						model.setTurno(1);
					}
					else if(valuta == 3) {
						vistaComputer.finePartita();
						popUp = new PopUp("LA RIBELLIONE HA VINTO!!!");
						model.setFase(3);
						model.setTurno(1);
					}
				}
				
				if(model.getFase() == 2 && model.getTurno() == 1) {
					//Il Computer colpisce
					int stato = model.getComputer().colpisci();
					if(stato == 0) {
						vistaPlayer.colpisciCellaVuota(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
					}
					else if(stato == 1) {
						vistaPlayer.colpisciCellaPiena(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
					}
					else if(stato == 2) {
						vistaPlayer.colpisciNaveColpita(model.getComputer().getColpo().getX(), model.getComputer().getColpo().getY());
					}
					else if(stato == 3) {
						vistaPlayer.finePartita();
						popUp = new PopUp("L'IMPERO HA VINTO, STAY DETERMINATED");
						model.setFase(3);
					}
					model.setTurno(0);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		return gestoreCelle;
	}
	
	
}
