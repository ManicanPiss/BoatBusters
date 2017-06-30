package clemensfabianclemens.boatbusters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import c2f.boatbusters.classes.Player;
import c2f.boatbusters.classes.WarShip;
import c2f.boatbusters.factories.BoardFactory;


public class TestSetShipMethods {

	
	//Richtige Schiffslängen-Eingaben (2, 3, 4)
	@Test
	public void testCheckLength(){
		
		
        Player dummy = new Player("dummy", "0");
        //checkLength-Argumente (int xfirst, int yfirst, int xlast, int ylast)
		assertTrue(dummy.checkLength(1, 2, 1, 4) == 3);
		assertTrue(dummy.checkLength(3, 6, 3, 9) == 4);
		assertTrue(dummy.checkLength(6, 5, 3, 5) == 4);
		assertTrue(dummy.checkLength(9, 8, 9, 9) == 2);
		assertTrue(dummy.checkLength(7, 8, 4, 8) == 4);
		assertTrue(dummy.checkLength(6, 5, 6, 4) == 2);
		assertTrue(dummy.checkLength(3, 9, 1, 9) == 3);
		assertTrue(dummy.checkLength(2, 8, 2, 5) == 4);
	}
	
	public void testCheckIfLineIsFreeAndLengthCorrect (){
		
        Player dummy = new Player("dummy", "0");
        BoardFactory bf = new BoardFactory();
        WarShip[][] dummyBoard = bf.createBoard(1);
        
//		checkIfLineIsFreeAndLengthCorrect-Argumente: (int xfirst, int yfirst, int xlast,
//				int ylast, int length, WarShip[][] board)
        
        //Korrekte Länge und in einer Reihe
		assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(1, 2, 1, 4, 3, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(3, 6, 3, 9, 4, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(6, 5, 3, 5, 4, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(9, 8, 9, 9, 2, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(7, 8, 4, 8, 4, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(6, 5, 6, 4, 2, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(0, 3, 0, 1, 3, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(9, 5, 6, 5, 4, dummyBoard));
	    assertTrue(dummy.checkIfLineIsFreeAndLengthCorrect(2, 0, 1, 0, 2, dummyBoard));
	    
	    //Auch in einer Reihe, aber falsche Länge
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(1, 2, 1, 6, 5, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(3, 3, 3, 9, 7, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(9, 5, 2, 5, 8, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(9, 9, 9, 9, 1, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(0, 8, 4, 8, 5, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(6, 5, 6, 0, 6, dummyBoard));
		
		//Diagonal gesetzte oder völlig krumm und quer gesetzte Schiffe
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(3, 3, 4, 4, 0, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(9, 9, 5, 5, 0, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(2, 1, 5, 7, 0, dummyBoard));
		assertFalse(dummy.checkIfLineIsFreeAndLengthCorrect(3, 7, 9, 8, 0, dummyBoard));

	}
}
