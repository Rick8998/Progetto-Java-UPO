package upo.battleship.cecciTragno;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.Observable;
//import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * Vista per gestire la griglia del {@link Giocatore}
 *
*/
@SuppressWarnings("serial")
public class VistaGrigliaGiocatore extends JPanel implements VistaGriglia{

	JLabel[][] cellePlayer;
	//private BattleshipController controllore;
	private BattleshipModel modello;
	ControllerGrigliaGiocatore controllerGiocatore;
	
	public VistaGrigliaGiocatore(BattleshipModel modello/*, BattleshipController controllore*/) {
		
		//this.controllore = controllore;
		this.modello = modello;
		
		controllerGiocatore = new ControllerGrigliaGiocatore(this, this.modello/*, this.controllore*/);
		
		this.setSize(100, 100);
		this.setLayout(new GridLayout(11,11));
		cellePlayer = new JLabel[11][11];
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				
				cellePlayer[i][j] = new JLabel();                                     // i j invertite
				cellePlayer[i][j].addMouseListener(this.controllerGiocatore.assegnaGestoreCelle(j, i));
				cellePlayer[i][j].setPreferredSize(new Dimension(10, 10));
				cellePlayer[i][j].setOpaque(true);
				cellePlayer[i][j].setHorizontalAlignment(JLabel.CENTER);
				cellePlayer[i][j].setVerticalAlignment(JLabel.CENTER);
				//Setto il colore del label a bianco
				cellePlayer[i][j].setBackground(Color.CYAN);
				//Aggiungo un bordo (creato prima del ciclo)
				cellePlayer[i][j].setBorder(border);
				
				//Condizione per saltare la prima cella e identificare le altre
				if(i == 0 && j > 0 ) { 
					cellePlayer[i][j] = new JLabel("" + j);
					cellePlayer[i][j].setPreferredSize(new Dimension(10, 10));
					cellePlayer[i][j].setOpaque(true);
					cellePlayer[i][j].setHorizontalAlignment(JLabel.CENTER);
					cellePlayer[i][j].setVerticalAlignment(JLabel.CENTER);
					//Setto il colore del label a bianco
					cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					cellePlayer[i][j].setBorder(border);
				}
				//Condizione per saltare la prima cella e identificare le altre
				if(j == 0 && i > 0 ) {
					cellePlayer[i][j] = new JLabel("" + i);
					cellePlayer[i][j].setPreferredSize(new Dimension(10, 10));
					cellePlayer[i][j].setOpaque(true);
					cellePlayer[i][j].setHorizontalAlignment(JLabel.CENTER);
					cellePlayer[i][j].setVerticalAlignment(JLabel.CENTER);
					//Setto il colore del label a bianco
					cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					cellePlayer[i][j].setBorder(border);
				}
				
				if(j == 0 && i == 0 ) {
					cellePlayer[i][j] = new JLabel();
					cellePlayer[i][j].setPreferredSize(new Dimension(10, 10));
					cellePlayer[i][j].setOpaque(true);
					//Setto il colore del label a bianco
					cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					//Aggiungo un bordo (creato prima del ciclo)
					cellePlayer[i][j].setBorder(border);
				}
				this.add(cellePlayer[i][j]);	
			}
		}
		this.setVisible(true);
	}

	public JLabel getCella(int x, int y) {
		return this.cellePlayer[x][y];
	}
	
	public void setCella(int x, int y) {
		String colpito = "X";
		this.cellePlayer[x][y].setText(colpito);
	}
	
	@Override
	public void colpisciCellaPiena(int x, int y) {
		String colpito = "X";
		
		this.cellePlayer[y][x].setText(colpito);
		this.cellePlayer[y][x].setBackground(Color.YELLOW);
		
	}
	
	@Override
	public void colpisciCellaVuota(int x, int y) {
		String colpito = "O";
		
		this.cellePlayer[y][x].setText(colpito);
		this.cellePlayer[y][x].setBackground(Color.BLUE);
		
	}
	
	@Override
	public void colpisciNaveColpita(int x, int y) {
		String colpito = "X";
		
		this.cellePlayer[y][x].setText(colpito);
		this.cellePlayer[y][x].setBackground(Color.RED);
		
	}

	/**
	 * Colora le celle dove è contenuta la nave di grigio
	 * @param nave la nave da posizionare
	 * @param x la coordinata x
	 * @param y la coordinata y
	 */
	public void posizionaNave(Nave nave, int x, int y) {
		
		int lunghezza = nave.getLunghezza();
		boolean verticale = nave.getVerticale();
		
		if(verticale) {
			for( int j = 0; j < lunghezza; j++) {
				cellePlayer[x+j][y].setBackground(Color.GRAY);
			}
		}
		else if(!verticale) {
			for(int i = 0; i < lunghezza; i++) {
				cellePlayer[x][y+i].setBackground(Color.GRAY);
			}
		}
	}
	
	@Override
	public void finePartita() {
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if((i != 0 && j != 0) && (i != 0 && j > 0) && (j != 0 && i > 0) ) {
					this.cellePlayer[i][j].setBackground(Color.RED);
					this.cellePlayer[i][j].setText("");
				}
			}
		}
	}

	@Override
	public void reset() {
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if((i != 0 && j != 0) && (i != 0 && j > 0) && (j != 0 && i > 0) ) {
					this.cellePlayer[i][j].setBackground(Color.CYAN);
					this.cellePlayer[i][j].setText("");
				}
				else if(i == 0 && j > 0 ){
					this.cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					this.cellePlayer[i][j].setText("" + j);
				}
				else if(j == 0 && i > 0 ){
					this.cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					this.cellePlayer[i][j].setText("" + i);
				}
				else {
					this.cellePlayer[i][j].setBackground(Color.LIGHT_GRAY);
					this.cellePlayer[i][j].setText("");
				}
			}
		}
	}
}
