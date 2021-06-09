package upo.battleship.cecciTragno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GrigliaTest {
	static Griglia griglia;
	static Cella [][] celle;
	static Nave nave;
	static Nave nave2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		celle = new Cella[10][10];
		griglia = new Griglia();
		nave = new Nave(3, "naveDaTre");
		nave2 = new Nave(2, "naveDaDue");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++ ) {
				celle[i][j] = new Cella(i, j);
			}
		}
	}

	@Test
	void testPosizionaNave() {
		griglia.setVerticale(false);
		assertTrue(griglia.posizionaNave(nave, 4, 4));
		assertFalse(griglia.posizionaNave(nave, 4, 4));//riposiziono la nave nella stessa posizione
		assertFalse(griglia.posizionaNave(nave, 5, 4));//posiziono una nave in una cella adiacente ad un'altra nave (x)
		assertFalse(griglia.posizionaNave(nave, 4, 5));//posiziono una nave in una cella adiacente ad un'altra nave (y)
	}
	
	@Test
	void testColpisci() {
		griglia.posizionaNave(nave, 6, 6);
		assertEquals(0, griglia.colpisci(3, 6));//cella vuota
		assertEquals(-1, griglia.colpisci(3, 6));//cella vuota + già cercata
		assertEquals(1, griglia.colpisci(6, 6));//cella piena
		assertEquals(-1, griglia.colpisci(6, 6));//cella piena + già cercata
		assertEquals(1, griglia.colpisci(7, 6));//cella piena
		assertEquals(2, griglia.colpisci(8, 6));//cella affondata
		
	}
	
	@Test
	void testVerificaNaveVicina() {
		assertTrue(griglia.verificaNaveVicina(2, 2, 0));
		assertTrue(griglia.verificaNaveVicina(2, 3, 1));
		griglia.posizionaNave(nave2, 2, 2);
		assertFalse(griglia.verificaNaveVicina(3, 2, 0));
		assertFalse(griglia.verificaNaveVicina(2, 3, 1));
	}
	
	@Test
	void testVerificaCoordinate() {
		assertTrue(griglia.verificaCoordinate(2, 2));
		assertFalse(griglia.verificaCoordinate(0, 1));
	}

}
