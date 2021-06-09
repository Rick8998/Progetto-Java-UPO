package upo.battleship.cecciTragno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	static Giocatore giocatore;
	static Griglia posizionaNaveGriglia;
	static Griglia colpisciGriglia;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		posizionaNaveGriglia = new Griglia();
		colpisciGriglia = new Griglia();
		giocatore = new Giocatore(posizionaNaveGriglia, colpisciGriglia);
	}

	@Test
	void testPosizionaNave() {
		posizionaNaveGriglia.setVerticale(false);
		assertTrue(giocatore.posizionaNave(giocatore.getNaviDaPosizionare().get(0), 4, 4));
		assertFalse(giocatore.posizionaNave(giocatore.getNaviDaPosizionare().get(0), 4, 4)); //posiziono una nave nella stessa cella di quella di prima
		assertFalse(giocatore.posizionaNave(giocatore.getNaviDaPosizionare().get(0), 10, 10)); //posizione illegale (dimensioni griglia sforate)
	}

	@Test
	void testColpisci() {
		colpisciGriglia.posizionaNave(giocatore.getNaviDaPosizionare().get(0), 4, 4); //posiziono solo una nave
		assertEquals(1, giocatore.colpisci(4, 4));
		assertEquals(-1, giocatore.colpisci(4, 4));
		assertEquals(1, giocatore.colpisci(5, 4));
		assertEquals(1, giocatore.colpisci(6, 4));
		assertEquals(1, giocatore.colpisci(7, 4));
		assertEquals(3, giocatore.colpisci(8, 4)); //fine partita
	}
}
