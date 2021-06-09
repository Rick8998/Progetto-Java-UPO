package upo.battleship.cecciTragno;

import java.util.ArrayList;



/**
 * Giocatore umano
 *
 */
public class Giocatore {
	
	protected Griglia grigliaGiocatore;
	public Griglia grigliaComputer;
	private ArrayList<Nave> naviDaPosizionare;
	private ArrayList<Nave> naviPosizionate;
	private int[] lunghezze = {5, 3, 2};
	private String[] nomi = {"incrociatore", "classeLambda" , "cacciaTie"};
	private boolean giocoPronto;
	public boolean fineTurno, finePartita;
	
	

	public Giocatore(Griglia grigliaGiocatore, Griglia grigliaComputer) {
		this.grigliaGiocatore = grigliaGiocatore;
		this.grigliaComputer = grigliaComputer;
		
		naviDaPosizionare = new ArrayList<Nave>();
		naviPosizionate = new ArrayList<Nave>();
		Nave nave;
		for(int i = 0; i < 3; i++) {
			nave = new Nave(lunghezze[i], nomi[i]);
			naviDaPosizionare.add(nave);
		}
		
		fineTurno = false;
		finePartita = false;
		giocoPronto = false;
	}
	
	
	/**
	 * Permette al {@link Giocatore} di posizionare una {@link Nave}, dati in input la nave da 
	 * posizionare e la cella dove posizionare la sua prua
	 * @param nave la nave da posizionare
	 * @param x la coordinata x
	 * @param y la coordinata y
	 * @return boolean
	 * 		TRUE se la nave è stata posizionata, FALSE altrimenti
	 */
	public boolean posizionaNave(Nave nave, int x, int y) {
		boolean ret = false;
		if(naviDaPosizionare.contains(nave)) {
			nave.setVerticale(grigliaGiocatore.getVerticale());
			ret = grigliaGiocatore.posizionaNave(nave, x, y);
			if (ret == true) {
				naviDaPosizionare.remove(nave);
				naviPosizionate.add(nave);
			}
			if(naviDaPosizionare.isEmpty()) {
				giocoPronto = true;
			}
		}
		return ret;
				
	}
	
	/**
	 * Il {@link Giocatore} colpisce una cella nel campo dell'IA
	 * @param x la coordinata x
	 * @param y la coordinata y
	 * @return int
	 * 	<pre>-1 errore
	 *  0 vuoto
	 *  1 colpito
	 *  2 affondato
	 *  3 fine_partita</pre> 
	 */
	public int colpisci(int x, int y) {
		return grigliaComputer.colpisci(x, y);
	}

	public Griglia getCampoGiocatore() {
		return grigliaGiocatore;
	}

	public Griglia getCampoIA() {
		return grigliaComputer;
	}

	public ArrayList<Nave> getNaviDaPosizionare() {
		return naviDaPosizionare;
	}

	public ArrayList<Nave> getNavi() {
		return naviPosizionate;
	}
	
	public String[] getNomeNavi() {
		return nomi;
	}

	public boolean isGiocoPronto() {
		return giocoPronto;
	}
	
}
