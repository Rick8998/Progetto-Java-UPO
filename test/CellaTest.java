package upo.battleship.cecciTragno;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import upo.battleship.cecciTragno.Cella;
import upo.battleship.cecciTragno.Nave;

class CellaTest {
	
	static Cella cella1, cella2, cella3;
	static Cella griglia[][];
	static Nave nave;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		griglia = new Cella[10][10];
		nave = new Nave(3, "naveDaTre");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++ ) {
				griglia[i][j] = new Cella(i, j);
			}
		}
	}
 
	@Test
	void testAssegnaNave() {
		assertTrue(griglia[1][1].assegnaNave(nave));
		assertTrue(griglia[1][2].assegnaNave(nave));
		assertTrue(griglia[1][3].assegnaNave(nave));
		
		assertFalse(griglia[1][4].assegnaNave(nave));
		assertFalse(griglia[1][1].assegnaNave(nave));
	}

	@Test
	void testColpisci(){
		assertEquals(1, griglia[1][1].colpisci());
		assertEquals(1, griglia[1][2].colpisci());
		assertEquals(2, griglia[1][3].colpisci());
	}

}
