package upo.battleship.cecciTragno;

import java.util.ArrayList;

/**
 * Giocatore computer
 *
 */
public class Computer {
	
	protected Griglia grigliaGiocatore;
	public Griglia grigliaComputer;
	
	private ArrayList<Cella> bersaglio; //contiene i bersagli
	private ArrayList<Cella> cercato; //contiene celle già  cercate
	private ArrayList<Cella> posIntorno;
	private Cella[][] cellePossibili;
	private boolean secondoColpo;
	private Cella colpo;
	@SuppressWarnings("unused")
	private Cella primoColpo;
	private Cella posVecchia;
	private Cella newPos;
	@SuppressWarnings("unused")
	private boolean bersaglioUsato;
	private ArrayList<Nave> naviDaPosizionare;
	private ArrayList<Nave> naviPosizionate;
	private int[] lunghezze = {5, 3, 2};
	private String[] nomi = {"incrociatore", "classeLambda" , "cacciaTie"};
	public boolean fineTurno, finePartita;
	int x, y, z;
	
	 
	public Computer(Griglia grigliaGiocatore, Griglia grigliaComputer) {

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
	
		cellePossibili = new Cella[11][11];
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				cellePossibili[i][j] = new Cella(i, j);
			}
		}
		
		bersaglioUsato = false;
		secondoColpo = false;
		bersaglio = new ArrayList<Cella>();
		cercato = new ArrayList<Cella>();
	}
	
	/**
	 * Permette al {@link Computer} di colpire una posizione randomica
	 * @return boolean
	*/ 
	public boolean posizionaRandom() {
		while (!naviDaPosizionare.isEmpty()) {
			// int i = 0;
			Nave nave = naviDaPosizionare.get(0);
			boolean[] vertical = new boolean[2];
			vertical[0] = true;
			vertical[1] = false;
			// giocatore_1.posizionaNave(nave, posizione, verticale);
			x = (int) (Math.random() * 10) % 10;
			y = (int) (Math.random() * 10) % 10;
			z = (int) (Math.random() * 10) % 2;
			boolean orientamento = vertical[z];
			if (posizionaNave(nave, x, y, orientamento)) {
				naviDaPosizionare.remove(nave);
			}
		}
		return true;
	}

	/**
	 * Permette il posizionamento delle navi del {@link Computer}
	 * @param nave la nave da posizionare
	 * @param x la coordinata x 
	 * @param y la coordinata y
	 * @param orientamento l'orientamento della nave
	 * @return boolean
	 */
	public boolean posizionaNave(Nave nave, int x, int y, boolean orientamento) {
		
		grigliaComputer.setVerticale(orientamento);
		if(naviDaPosizionare.contains(nave)) {
			if(grigliaComputer.posizionaNave(nave, x, y)) {
				naviPosizionate.add(nave);
				return true;
			}
		}
		return false;			
	}
	
	/**
	 * Permette all'{@link Computer} di colpire una {@link Cella} casuale
	 * @return int lo stato della cella colpita
	 */
	public int colpisci() {
		if(bersaglio.isEmpty()) {
			bersaglioUsato = false;
			secondoColpo = false;
			colpo = random();
			while(cercato.contains(colpo)) {
				colpo = random();
			}
			primoColpo = colpo;
		} else {
			bersaglioUsato = true;
			while(cercato.contains(colpo) && !bersaglio.isEmpty()) {
				colpo = bersaglio.remove(0);
			}
		}
		
		cercato.add(colpo);
		int ris = grigliaGiocatore.colpisci(colpo.getX(), colpo.getY());
		
		if (ris == -1) {
			if(cercato.contains(colpo)) {
				return -1;
			}
		}
		
		if (ris == 1) {
			
			if (!secondoColpo) {
				secondoColpo = true;
				posVecchia = colpo;
	
				posIntorno = intorno(colpo);
	
				while (!posIntorno.isEmpty()) {
					if(!cercato.contains(posIntorno.get(0)))
						bersaglio.add(posIntorno.remove(0));
					else
						posIntorno.remove(0);
				}
			}
			else {
	
				if (colpo.getX() > posVecchia.getX() && colpo.getX() < 10) {
					newPos = new Cella(colpo.getX() + 1, colpo.getY());
					bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
				}
	
				else if (colpo.getX() < posVecchia.getX() && colpo.getX() > 1) {
					newPos = new Cella(colpo.getX() - 1, colpo.getY());
					bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
				}
				
				else if (colpo.getY() > posVecchia.getY() && colpo.getY() < 10) {
					newPos = new Cella(colpo.getX(), colpo.getY() + 1);
					bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
				}
	
				else if (colpo.getY() < posVecchia.getY() && colpo.getY() > 1) {
					newPos = new Cella(colpo.getX(), colpo.getY() - 1);
					bersaglio.add(cellePossibili[newPos.getX()][newPos.getY()]);
				}
			}
		}

		if (ris == 2) {
			while(!bersaglio.isEmpty()) {
				cercato.add(bersaglio.remove(0));
			}
			//bersaglio.clear();
			secondoColpo = false;
		}
		
		if(ris == 3) {
			finePartita = true;
		}
		
		return ris;
	}

	/**
	 * Sceglie una {@link Cella} casuale da colpire
	 * @return {@link Cella}
	 * <pre>cella papabile per essere colpita, inserendola alla griglia di celle possibili</pre>
	 */
	private Cella random() {
		Cella pos;
		do {
			pos = new Cella(((int) ((Math.random() * 10) % 10) + 1),((int) ((Math.random() * 10) % 10) + 1));
		}
		while (pos.getX() < 1 || pos.getX() > 10 || pos.getY() < 1 || pos.getY() > 10);
		
		return cellePossibili[pos.getX()][pos.getY()];
	}
	
	public Cella getColpo() {
		return colpo;
	}
	
	/**
	 * Una volta colpita una nave, il {@link Computer} colpisce nei dintorni del colpo sparato precedentemente
	 * @param colpo la cella di cui verificare l'intorno
	 * @return ArrayList delle caselle vicine
	 */
	protected ArrayList<Cella> intorno(Cella colpo) {
		ArrayList<Cella> posizioni = new ArrayList<Cella>();
		Cella new_pos = new Cella(0, 0);
		if (colpo.getX() > 1) {
			new_pos = new Cella(colpo.getX() - 1, colpo.getY());
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getX() < 10) {
			new_pos = new Cella(colpo.getX() + 1, colpo.getY());
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getY() < 10) {
			new_pos = new Cella(colpo.getX(), colpo.getY() + 1);
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}

		if (colpo.getY() > 1) {
			new_pos = new Cella(colpo.getX(), colpo.getY() - 1);
			posizioni.add(cellePossibili[new_pos.getX()][new_pos.getY()]);
		}
		
		return posizioni;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public ArrayList<Nave> getNaviDaPosizionare() {
		return naviDaPosizionare;
	}

	public ArrayList<Nave> getNavi() {
		return naviPosizionate;
	}
}
