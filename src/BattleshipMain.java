package upo.battleship.cecciTragno;

/**
 * Main del programma
 */
public class BattleshipMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		BattleshipModel modello = new BattleshipModel();
		BattleshipView vista = new BattleshipView("Battleship Cecci-Tragno", modello);	
	}
	


}
