package upo.battleship.cecciTragno;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * Controlla il flusso di dati/eventi della griglia del {@link Giocatore}
 *
 */
public class ControllerGrigliaGiocatore {
	
	VistaGrigliaGiocatore vistaPlayer;
	BattleshipModel model;
	//BattleshipController battleshipController;
	int i = 0;
	
	public ControllerGrigliaGiocatore(VistaGrigliaGiocatore vista, BattleshipModel model/*, BattleshipController battleshipController*/) {
		this.model = model;
		this.vistaPlayer = vista;
		//this.battleshipController = battleshipController;
		
	}
	
	public MouseListener assegnaGestoreCelle(int x, int y) {
		MouseListener gestoreCelle;
		gestoreCelle = new MouseListener() {
			
			@SuppressWarnings("unused")
			private PopUp popUp;

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(model.getFase() == 1) {
					
					ArrayList<Nave> navi =  model.getGiocatore().getNaviDaPosizionare();
					
					Nave nave = navi.get(i);
					
					try {
						
						if(!model.getGiocatore().getNaviDaPosizionare().isEmpty()) {
									
									boolean valuta = model.getGiocatore().posizionaNave(nave, x, y);
									
									if(valuta == true) {
										vistaPlayer.posizionaNave(nave, y, x);
									}//fine if
									
									else if(valuta == false) {
										popUp = new PopUp("POSIZIONE NAVE NON VALIDA, RITENTA");
										
									}//fine else
							
						}//fine if
					}catch (Exception exc) {
						System.out.println("ERRORE POSIZIONAMENTO NAVE " + exc.getMessage());
					}
					finally {
						if(model.getGiocatore().getNaviDaPosizionare().isEmpty()) {
							popUp = new PopUp("NAVI POSIZIONATE, INIZIO BATTAGLIA");
							model.faseBattaglia();
						}
					}
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
