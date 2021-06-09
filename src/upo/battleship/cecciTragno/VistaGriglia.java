package upo.battleship.cecciTragno;

public interface VistaGriglia {
	
	/**
	 * Colora la cella di giallo, perchè al suo interno c'è una nave
	 *@param x la coordinata x
	 * @param y la coordinata y
	 */
	abstract void colpisciCellaPiena(int x, int y);
	
	/**
	 * Colora la cella di blu, perchè al suo interno non c'è una nave
	 * @param x la coordinata x
	 * @param y la coordinata y
	 */
	abstract void colpisciCellaVuota(int x, int y);
	
	/**
	 * Colora la cella di rosso, perchè al suo interno c'è una nave e la nave è stata affondata
	 * @param x la coordinata x
	 * @param y la coordinata y
	 */
	abstract void colpisciNaveColpita(int x, int y);
	
	/**
	 * Colora il campo quando finisce la partita
	 */
	abstract void finePartita();
	
	/**
	 * Resetta la vista della griglia quando si inizia una nuova partita
	 */
	abstract void reset();
}
