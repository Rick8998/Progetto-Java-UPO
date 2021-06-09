package upo.battleship.cecciTragno;

//import java.util.ArrayList;

/**
 * Nave utilizzata nel gioco
 *
 */

public class Nave {
	
	private int lunghezza;
	private String nome;
	private boolean colpita;
	private boolean affondata;
	private boolean verticale;
	private int vita;
	private int lunghezzaDaPosizionare;
	
	public Nave(int lunghezza, String nome) {
		this.colpita = false;
		this.affondata = false;
		this.verticale = false;
		this.lunghezza = lunghezza;
		this.vita = lunghezza;
		this.nome = nome;
		this.lunghezzaDaPosizionare= lunghezza;
	}
	
	/**
	 * Assegna lo stato ad una nave (colpita, affondata)
	 * @return int
	 * <pre>Stato della nave</pre>
	 */
	public int getStato() {
		int stato = -1;
		if(colpita && !affondata) {
			stato = 1;
		}
		else if(affondata) {
			stato = 2;
		}
		return stato;
	}
	/**
	 * Ritorna la lunghezza della nave
	 * @return int
	 * <pre>La lunghezza della nave</pre>
	 */
	public int getLunghezza() {
		return lunghezza;
	}

	/**
	 * 
	 * @return boolean
	 * <pre>TRUE se la nave è verticale, FALSE altrimenti</pre>
	 */
	public boolean getVerticale() {
		return verticale;
	}

	public void setVerticale(boolean orientamento) {
		this.verticale = orientamento;
	}

	
	/**
	 * Diminuisce di 1 la vita della nave
	 */ 
	public void colpisci() {
		vita--;
		colpita = true;
		if(vita == 0) {
			affondata = true;
		}
		else 
			affondata = false;
	}

	/**
	 * Ritorna il nome della nave
	 * @return String
	 * <pre>Il nome della nave</pre>
	 */
	public String getNome() {
		return nome;
	}

	public int getLunghezzaDaPosizionare() {
		return lunghezzaDaPosizionare;
	}

	public void setLunghezzaDaPosizionare(int lunghezzaDaPosizionare) {
		this.lunghezzaDaPosizionare = lunghezzaDaPosizionare;
	}
	
}
