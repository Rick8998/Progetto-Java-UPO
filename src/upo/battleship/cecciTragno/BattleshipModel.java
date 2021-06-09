package upo.battleship.cecciTragno;
/**
 * Model del pattern MVC
 *
 */
public class BattleshipModel{
	
	private final static int FASE_POSIZIONAMENTO_NAVI = 1;
	private final static int FASE_DI_BATTAGLIA = 2;
	private final static int FINE_PARTITA= 3;
	private final static int TURNO_GIOCATORE = 0;
	private int faseProgramma;
	private int turno;
	private Griglia grigliaGiocatore, grigliaComputer;
	private Giocatore giocatore;
	private Computer computer;
	private boolean orientamento;
	
	
	public BattleshipModel() {
		//super();
		faseProgramma = FASE_POSIZIONAMENTO_NAVI;
		turno = TURNO_GIOCATORE;
		grigliaComputer = new Griglia();
		grigliaGiocatore = new Griglia();
		giocatore = new Giocatore(grigliaGiocatore, grigliaComputer);
		computer = new Computer(grigliaGiocatore, grigliaComputer);
	}
	
	public int getTurno() {
		return this.turno;
	}
	
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	/**
	 * Setta la fase di gioco alla fase di posizionamento delle navi
	 */
	public void fasePosizionamento() {
		setFase(FASE_POSIZIONAMENTO_NAVI);
		
	}
	/**
	 * Setta la fase di gioco a fine partita
	 */
	public void fineBattaglia() {
		setFase(FINE_PARTITA);
	}
	
	/**
	 * Setta la fase di gioco alla fase di battaglia
	 */
	public void faseBattaglia() {
		setFase(FASE_DI_BATTAGLIA);
	}
	
	public void setFase(int fase) {
		this.faseProgramma = fase;
	}
	
	public int getFase() {
		return faseProgramma;
	}
	
	/**
	 * Resetta le {@link Griglia}, i giocatori ({@link Computer} e {@link Giocatore}) e le fasi di gioco per quando si vuole iniziare una nuova partita
	 */
	public void reset() {
		faseProgramma = FASE_POSIZIONAMENTO_NAVI;
		turno = TURNO_GIOCATORE;
		grigliaComputer = new Griglia();
		grigliaGiocatore = new Griglia();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				
				grigliaComputer.getCella(i, j).setLibera(true);
				grigliaGiocatore.getCella(i, j).setLibera(true);
			}
		}
		giocatore = new Giocatore(grigliaGiocatore, grigliaComputer);
		computer = new Computer(grigliaGiocatore, grigliaComputer);
	}
	
	public Griglia getGrigliaComputer() {
		return this.grigliaComputer;
		
	}

	public Griglia getGrigliaPlayer() {
		
		return this.grigliaGiocatore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void setVerticale(boolean orientamento) {
		this.orientamento = orientamento;
		setVerticaleGriglia();
	}
	
	public boolean getVerticale() {
		return this.orientamento;
	}
	
	public void setVerticaleGriglia() {
		boolean orientamento = getVerticale();
		grigliaGiocatore.setVerticale(orientamento);
	}

}
