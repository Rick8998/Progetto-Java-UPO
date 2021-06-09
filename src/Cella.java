package upo.battleship.cecciTragno;


/**
 * 	
 * Una Cella della {@link Griglia} che può contenere una {@link Nave} 
 * e può essere colpita dal {@link Giocatore} o dal {@link Computer}. * 
 *
 */

public class Cella{
	
	private int x, y;
	private boolean libera;
	private int stato; //-2= cella non cercata,  0 = acqua, 1 = colpito, 2 = affondato, 3 = fine partita
	private Nave nave;
	
	public Cella(int x, int y) {
		this.x = x;
		this.y = y;
		this.libera = true;
		this.nave = null;
		this.stato = -2;
	}
	
	/**
	 * 
	 * @param nave la nave da assegnare
	 * 
	 * @return boolean
	 * <pre>TRUE se la nave è stata assegnata. FALSE altrimenti</pre>
	 */
	
	public boolean assegnaNave(Nave nave) {
		boolean ret = true;
		if(libera == true && nave.getLunghezzaDaPosizionare() != 0) {
			this.nave = nave;
			libera = false;
			ret = true;
			this.nave.setLunghezzaDaPosizionare(this.nave.getLunghezzaDaPosizionare()-1);
			/*int length = this.nave.getLunghezzaDaPosizionare();
			length--;
			this.nave.setLunghezzaDaPosizionare(length);*/
		}
		else
			ret = false;
		
		return ret;
	}
	
	/**
	 * Colpisci la cella, se c'è la nave colpisce anche la nave, se no 
	 * segna la cella come "guardata" 
	 * 
	 * @return int
	 * <pre> -1 errore
	 *  0 acqua
	 *  1 colpita
	 *  2 affondata
	 *  3 fine partita</pre>
	 */
	public int colpisci() {
		int ret = 0;
		if (stato == 1 || stato == 2 || stato == 3 || stato == 0) {
			return -1;
		}
		
		if (!libera) {
			nave.colpisci();
			stato = nave.getStato();
			ret = stato;
		}
		else
			stato = ret;
		return ret;
	}
	
	/**
	 * 
	 * @return int
	 * <pre>Stato della nave contenuta nella cella</pre>
	 */
	
	/*public int getStato() {
		if (nave != null && nave.getStato() == 3)
			stato = nave.getStato();
		
		return stato;
	}*/
	
	/**
	 * 
	 * @return boolean
	 * <pre>TRUE se la cella è libera, FALSE altrimenti</pre>
	 */
	public boolean getLibera() {
		return libera;
	}
	
	/**
	 * 
	 * @return {@link Nave}
	 */
	public Nave getNave() {
		return nave;
	}
	
	/**
	 * 
	 * @return int
	 * <pre>La X della cella</pre>
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return int
	 * <pre>La Y della cella</pre>
	 */
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLibera(boolean libera) {
		this.libera = libera;
	}
	
	
	
}
