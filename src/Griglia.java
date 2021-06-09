package upo.battleship.cecciTragno;


import java.util.ArrayList;



/**
 * Griglia contenente 10x10 oggetti {@link Cella} e 3 oggetti {@link Nave}.
 * 
 */

public class Griglia{

	/**
	 * La dimensione della Griglia
	 */
	private static final int DIM_GRIGLIA = 11;
	//private Cella cella;
	private Cella [][] celle;
	private ArrayList<Cella> celleLibere;
	private ArrayList<Nave> navi_posizionate;
	private ArrayList<Nave> navi_colpite;
	private ArrayList<Nave> navi_affondate;
	private boolean verticale;
	
	/**
	 * Crea una Griglia con 10x10 oggetti {@link Cella} e 2 oggetti {@link Nave} da posizionare.
	 * 
	 */
	public Griglia () {
		celle = new Cella[DIM_GRIGLIA][DIM_GRIGLIA];
		celleLibere = new ArrayList<Cella>();
		
		for(int i = 0; i < DIM_GRIGLIA; i++) {
			for(int j = 0; j < DIM_GRIGLIA; j++ ) {
				celle[i][j] = new Cella(i, j);
				if(i != 0 && j != 0)
					celleLibere.add(celle[i][j]);
			}
		}
		
		navi_posizionate = new ArrayList<>();
		navi_colpite = new ArrayList<>();
		navi_affondate = new ArrayList<>();
	}
	
	
	/**
	 * Posiziona una nave nella griglia.
	 * @param nave la nave da posizionare
	 * @param x la coordinata x
	 * @param y la coordinata y
	 * @return boolean
	 * <pre>TRUE se il posizionamento è andato a buon fine, FALSE altrimenti</pre>
	 */
	public boolean posizionaNave(Nave nave, int x, int y) {
		
		int lunghezza = nave.getLunghezza();
		
		if(!verificaCoordinate(x, y)) {
			return false;
		}
		
		if(!getVerticale()) {
			if(x + lunghezza > DIM_GRIGLIA) {
				return false;
			}
			for(int i = 0; i < lunghezza; i++) {
				if(!celleLibere.contains(celle[x+i][y]) || !verificaNaveVicina(x+i, y, i)) {
					return false;
				}
			}
			for(int i = 0; i < lunghezza; i++) {
				celle[x+i][y].assegnaNave(nave);
				celleLibere.remove(celle[x+i][y]);
				
			}
		}
		else {
			if(y + lunghezza > DIM_GRIGLIA) {
				return false;
			}
			for(int i = 0; i < lunghezza; i++) {
				if(!celleLibere.contains(celle[x][y+i]) || !verificaNaveVicina(x, y+i, i)) {
					return false;
				}
			}
			for(int i = 0; i < lunghezza; i++) {
					celle[x][y+i].assegnaNave(nave);
					celleLibere.remove(celle[x][y+i]);
			}
		}
		navi_posizionate.add(nave);
		return true;
	}
	
	/**
	 * Colpisci una cella.
	 * @param x-la coordinata x
	 * @param y-la coordinata y
	 * @return 
	 * <pre>-1 errore 
	 * 0 acqua 
	 * 1 colpita 
	 * 2 affondata 
	 * 3 fine partita</pre>
	 */
	public int colpisci (int x, int y) {
		
		int ret = 0;
		int stato_cella = celle[x][y].colpisci();
		
		if(stato_cella < 0)
			return -1;
		else if (stato_cella == 1) {
			if(!(navi_colpite.contains(celle[x][y].getNave()))) {
				navi_posizionate.remove(celle[x][y].getNave());
				navi_colpite.add(celle[x][y].getNave());
			}
			ret = 1;
		}
		else if(stato_cella == 2) {
			if(!(navi_affondate.contains(celle[x][y].getNave()))) {
				navi_colpite.remove(celle[x][y].getNave());
				navi_affondate.add(celle[x][y].getNave());
			}
			ret = 2;
		}
		if(navi_posizionate.isEmpty() && navi_colpite.isEmpty())
			ret = 3;
		
		return ret;
	}

	/**
	 * Valuta tutte le celle intorno alla cella selezionata e ripete per ogni cella
	 * @param x la coordinata x
	 * @param y la coordinata y
	 * @param prua la prua della nave (la parte della nave che si posiziona quando si clicca) se == 0 sto considerando la prua
	 * @return boolean TRUE se la verifica va a buon fine, FALSE altrimenti
	 */
	public boolean verificaNaveVicina(int x, int y, int prua) {
		
		if(x == 10 && y == 10 && prua == 0) {
			return false;
		}
		
		int i = x;
		int j = y;
		
		Cella cella = getCella(i, j);
		
		if(!getVerticale()) {
			
			if(j == 10) { // celle con y = 10;
				if(prua == 0) { //prua
					Cella cella1 = getCella(i-1, j);
					Cella cella2 = getCella(i-1, j-1);
					Cella cella3 = getCella(i, j-1);
					Cella cella4 = getCella(i+1, j-1);
					Cella cella5 = getCella(i+1, j);
					
					if(verificaCoordinate(i, j) && cella.getLibera() &&
					   cella1.getLibera() &&
					   cella2.getLibera() &&
					   cella3.getLibera() &&
					   cella4.getLibera() &&
					   cella5.getLibera()) {
						return true;
					}
				}
				else{
					if(i == 10) {
						Cella cella2 = getCella(i-1, j-1);
						Cella cella3 = getCella(i, j-1);
						if(cella.getLibera() && cella2.getLibera() &&
								cella3.getLibera()) {
								return true;
						}
					}
					else {
						Cella cella2 = getCella(i-1, j-1);
						Cella cella3 = getCella(i, j-1);
						Cella cella4 = getCella(i+1, j-1);
						Cella cella5 = getCella(i+1, j);	
						if(verificaCoordinate(i, j) && cella.getLibera() &&
							cella2.getLibera() &&
							cella3.getLibera() &&
							cella4.getLibera() &&
							cella5.getLibera()) {
							return true;
						}
					}
				}
			}
			else {
				if(prua == 0) { //prua
					Cella cella1 = getCella(i-1, j);
					Cella cella2 = getCella(i-1, j-1);
					Cella cella3 = getCella(i, j-1);
					Cella cella4 = getCella(i+1, j-1);
					Cella cella5 = getCella(i+1, j);
					Cella cella6 = getCella(i+1, j+1);
					Cella cella7 = getCella(i, j+1);
					Cella cella8 = getCella(i-1, j+1);
					
					if(verificaCoordinate(i, j) && cella.getLibera() &&
					   cella1.getLibera() &&
					   cella2.getLibera() &&
					   cella3.getLibera() &&
					   cella4.getLibera() &&
					   cella5.getLibera() &&
					   cella6.getLibera() &&
					   cella7.getLibera() &&
					   cella8.getLibera()) {
						return true;
					}
				}
				else{
					if(i == 10) {
						Cella cella2 = getCella(i-1, j-1);
						Cella cella3 = getCella(i, j-1);
						Cella cella7 = getCella(i, j+1);
						Cella cella8 = getCella(i-1, j+1);
						if(cella.getLibera() && cella2.getLibera() &&
								cella3.getLibera() &&
								cella7.getLibera() &&
								cella8.getLibera()) {
								return true;
						}
					}
					else {
						Cella cella2 = getCella(i-1, j-1);
						Cella cella3 = getCella(i, j-1);
						Cella cella4 = getCella(i+1, j-1);
						Cella cella5 = getCella(i+1, j);
						Cella cella6 = getCella(i+1, j+1);
						Cella cella7 = getCella(i, j+1);
						Cella cella8 = getCella(i-1, j+1);
						
						if(verificaCoordinate(i, j) && cella.getLibera() &&
						   cella2.getLibera() &&
						   cella3.getLibera() &&
						   cella4.getLibera() &&
						   cella5.getLibera() &&
						   cella6.getLibera() &&
						   cella7.getLibera() &&
						   cella8.getLibera()) {
							return true;
						}
					}
				}
			}
		}
		else {
			if(i == 10) { // celle con x = 10;
				if(prua == 0) { //prua
					Cella cella1 = getCella(i-1, j);
					Cella cella2 = getCella(i-1, j-1);
					Cella cella3 = getCella(i, j-1);
					Cella cella7 = getCella(i, j+1);
					Cella cella8 = getCella(i-1, j+1);
					
					if(verificaCoordinate(i, j) && cella.getLibera() &&
					   cella1.getLibera() &&
					   cella2.getLibera() &&
					   cella3.getLibera() &&
					   cella7.getLibera() &&
					   cella8.getLibera()) {
						return true;
					}
				}
				else{ 
					if(j == 10) {
						Cella cella1 = getCella(i-1, j);
						Cella cella2 = getCella(i-1, j-1);
						if(cella.getLibera() && cella1.getLibera() &&
								cella2.getLibera()) {
								return true;
						}
					}
					else {
						Cella cella1 = getCella(i-1, j);
						Cella cella2 = getCella(i-1, j-1);
						Cella cella7 = getCella(i, j+1);
						Cella cella8 = getCella(i-1, j+1);
						
						if(verificaCoordinate(i, j) && cella.getLibera() &&
						   cella1.getLibera() && 
						   cella2.getLibera() && 
						   cella7.getLibera() &&
						   cella8.getLibera()) {
							return true;
						}
					}
				}
			}
			else {
				if(prua == 0) { //prua
					Cella cella1 = getCella(i-1, j);
					Cella cella2 = getCella(i-1, j-1);
					Cella cella3 = getCella(i, j-1);
					Cella cella4 = getCella(i+1, j-1);
					Cella cella5 = getCella(i+1, j);
					Cella cella6 = getCella(i+1, j+1);
					Cella cella7 = getCella(i, j+1);
					Cella cella8 = getCella(i-1, j+1);
					
					if(verificaCoordinate(i, j) && cella.getLibera() &&
					   cella1.getLibera() &&
					   cella2.getLibera() &&
					   cella3.getLibera() &&
					   cella4.getLibera() &&
					   cella5.getLibera() &&
					   cella6.getLibera() &&
					   cella7.getLibera() &&
					   cella8.getLibera()) {
						return true;
					}
				}
				else{
					if(j == 10) {
						Cella cella1 = getCella(i-1, j);
						Cella cella2 = getCella(i-1, j-1);
						Cella cella4 = getCella(i+1, j-1);
						Cella cella5 = getCella(i+1, j);
						if(cella.getLibera() && cella1.getLibera() &&
								cella2.getLibera() &&
								cella4.getLibera() &&
								cella5.getLibera()) {
								return true;
						}
					}
					else {
						Cella cella1 = getCella(i-1, j);
						Cella cella2 = getCella(i-1, j-1);
						Cella cella4 = getCella(i+1, j-1);
						Cella cella5 = getCella(i+1, j);
						Cella cella6 = getCella(i+1, j+1);
						Cella cella7 = getCella(i, j+1);
						Cella cella8 = getCella(i-1, j+1);
						
						if(verificaCoordinate(i, j) && cella.getLibera() &&
						   cella1.getLibera() &&
						   cella2.getLibera() && 
						   cella4.getLibera() &&
						   cella5.getLibera() &&
						   cella6.getLibera() && //i+2 > 2 &&
						   cella7.getLibera() &&
						   cella8.getLibera()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica che la nave venga posizionata all'interno della griglia, cioè che non esca dai bordi
	 * @param x la coordinata x
	 * @param y la coordinata y
	 * @return TRUE se la verifica va a buon fine, FALSE altrimenti
	 */
	public boolean verificaCoordinate(int x, int y) {
		
		return(x > 0 && x < DIM_GRIGLIA && y > 0 && y < DIM_GRIGLIA);
	}
	
	public Cella getCella(int x, int y) {
		return celle[x][y];
	}
	
	/**
	 * 
	 * @return navi non ancora colpita
	 */
	public ArrayList<Nave> getNavi() {
		return navi_posizionate;
	}
	
	/**
	 * 
	 * @return navi colpita
	 */
	public ArrayList<Nave> getNaviColpite() {
		return navi_colpite;
	}
	
	/**
	 * 
	 * @return navi affondate
	 */
	
	public ArrayList<Nave> getNaviAffondate() {
		return navi_affondate;
	}


	public void setVerticale(boolean orientamento) {
		this.verticale = orientamento;
		
	}
	
	public boolean getVerticale() {
		return verticale;
	}
	
}
