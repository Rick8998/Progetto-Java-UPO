package upo.battleship.cecciTragno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.battleship.cecciTragno.BattleshipModel;

class BattleshipModelTest {
	
	static BattleshipModel modello;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		modello = new BattleshipModel();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testReset() {
		modello.setFase(3);
		modello.setTurno(1);
		modello.reset();
		
		assertEquals(1, modello.getFase());
		assertEquals(0, modello.getTurno());
	}

}