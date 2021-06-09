package upo.battleship.cecciTragno;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ComputerTest {
	static Computer computer;
	static Griglia posizionaNaveGriglia;
	static Griglia colpisciGriglia;
	static ArrayList<Cella> intorno;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		posizionaNaveGriglia = new Griglia();
		colpisciGriglia = new Griglia();
		computer = new Computer(posizionaNaveGriglia, colpisciGriglia);
		intorno = new ArrayList<Cella>();
	}

	@Test
	void testPosizionaRandom() {
		assertTrue(computer.posizionaRandom());
	}
	
	@Test
	void testPosizionaNave() {
		assertTrue(computer.posizionaNave(computer.getNaviDaPosizionare().get(0), 4, 4, true));
		assertFalse(computer.posizionaNave(computer.getNaviDaPosizionare().get(0), 4, 4, true));
		assertFalse(computer.posizionaNave(computer.getNaviDaPosizionare().get(0), 11, 4, true));
	}
	
	@Test
	void testIntorno() {
		Cella cella = new Cella(5, 5);
		Cella cellaIntorno1 = new Cella(4, 5);
		Cella cellaIntorno2 = new Cella(6, 5);
		Cella cellaIntorno3 = new Cella(5, 6);
		Cella cellaIntorno4 = new Cella(5, 4);
		intorno = computer.intorno(cella);
		ArrayList<Cella> intornoTest = new ArrayList<Cella>();
		intornoTest.add(cellaIntorno1);
		intornoTest.add(cellaIntorno2);
		intornoTest.add(cellaIntorno3);
		intornoTest.add(cellaIntorno4);
		assertEquals(intornoTest.size(), intorno.size());
		for(int i = 0; i < 4; i++) {
			assertEquals(intornoTest.get(i).getX(), intorno.get(i).getX());
			assertEquals(intornoTest.get(i).getY(), intorno.get(i).getY());
		}
	}
}
