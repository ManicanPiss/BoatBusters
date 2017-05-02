package c2f.boatbusters.classes;

import c2f.boatbusters.abstractClasses.Ship;
import c2f.boatbusters.interfaces.WarShipInterface;
import java.util.Scanner;

public class WarShip extends Ship implements WarShipInterface {

	static Scanner scan = new Scanner (System.in);
	
	private int shipSize;
	

	public WarShip (int i) {
		
		if (i == getShortShipLength()) {
			this.shipSize = i;
		} else if (i == getMiddleShipLength()) {
			this.shipSize = i;
		} else {
			// error log, groesse falsch
		}
	}

	
	// CHECKER
	//-------------------------------------------------------

	//checkt, ob in der X-Koordinate überhaupt was frei ist
	public boolean checkXcoord (int i) {
		//fährt jedes Feld in der X-koordinate ab und gibt true aus, falls eines 0 ist
		for (int a = 1; a <= Game.fieldSizeX; a++) {
			if (Brett.brett [i][a] == 0) {
				return true;
			} 
		}
		//sonst halt false
		return false;
	}

	public boolean checkNumber (String s) {
		int i = Integer.parseInt(s);

		//eingabe muss ja zwischen 1 und 10 sein
		if (i > 0 && i <= Game.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	//checkt, ob die Position frei ist
	public boolean checkFree (int x, int y) {
		if (Brett.brett [x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}

	// checkt ob die schiffswahlzahl passt (muss zwischen 1 und 4 sein) 
	public boolean checkChoiceNr (int choice) {
		if (choice > 0 && choice < 5) {
			return true;
		} else {
			return false;
		}
	}

	// checkt, ob noch ein schiff diesen Typs übrig ist
	public boolean checkIfShipAvailable (int i) {
		if (i == 1) {
			return (countSmall > 0);
		} else if (i == 2) {
			return (countMiddle > 0);
		} else if (i == 3) {
			return (countBig > 0);
		} else if (i == 4) {
			return (countDestroyer > 0);
		} else {
			System.out.println("Error: Zahl nicht zwischen 1 und 4! \n");
			return false;
		}
	}

	//checkt, ob überhaupt noch ein schiff übrig ist
	public boolean checkIfAnyShipLeft () {
		return (shipsCount > 0);
	}

	//wenn man ein schiff setzt, wird die anzahl der verfügbaren schiffe diesen typs um 1 verringert
	public void reduceShipCount (int i) {
		switch (i) {
		case 1: countSmall -= 1;
		break;
		case 2: countMiddle -= 1;
		break;
		case 3: countMiddle -= 1;
		break;
		case 4: countDestroyer -= 1;
		break;
		default:
			System.out.println("Error: Zahl nicht zwischen 1 und 4!");
		}
	}

	public boolean checkIfInLine (int x, int y, int checkThis) {
		return (x == checkThis || y == checkThis);
	}


	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	public boolean checkIfLineIsFreeAndLengthCorrect (int xfirst, int yfirst, int xlast, int ylast, int length) {
		int staticInt, changingStart, changingEnd;
		// hier wird festgelegt, welche Koordinate gleich bleibt und welche sich ändert,
		// damit anhand der sich ändernden koordinate die angepeilte linie auf dem Feld auf ihre Leere gecheckt werden kann.
		boolean isFree = true;


		if (xfirst == xlast) { // wenn X statisch ist
			staticInt = xfirst;
			if (yfirst > ylast) { // checkt, welcher wert größer ist, damit die for-Schleife funktioniert
				changingStart = ylast;
				changingEnd = yfirst;
			} else {
				changingStart = yfirst;
				changingEnd = ylast;
			}

			// das eigentliche abchecken findet hier statt
			for (int i = changingStart; i <= changingEnd; i++) {
				if (Brett.brett[staticInt][i] != 0){
					isFree = false;
				}
			}
		} else { // wenn Y statisch ist
			staticInt = yfirst;
			if (xfirst > xlast) { // checkt, welcher wert größer ist, damit die for-Schleife funktioniert
				changingStart = xlast;
				changingEnd = xfirst;
			} else {
				changingStart = xfirst;
				changingEnd = xlast;
			}

			// das eigentliche abchecken findet hier statt
			for (int i = changingStart; i <= changingEnd; i++) {
				if (Brett.brett[i][staticInt] != 0){
					isFree = false;
				}
			}
		}

		// checkt länge
		if (!(changingEnd - changingStart == length)) {
			isFree = false;
		} 

		return isFree;
	}

	public boolean checkIfValidInput(String str) {
		if (str.equals("1")) {
			return true;
		} else if (str.equals("2")) {
			return true;
		} else if (str.equals("3")) {
			return true;
		} else if (str.equals("4")) {
			return true;
		} else if (str.equals("m")) {
			return true;
		} else if (str.equals("b")) {
			return true;
		} else {
			return false;
		}
	}

	//-------------------------------------------------------------


	// SHIP SETTER 2
	//--------------------------------------------------------------

	public void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast) {
		int staticInt, changingStart, changingEnd;

		if (xfirst == xlast) { // wenn X statisch ist
			staticInt = xfirst;
			if (yfirst > ylast) { // checkt, welcher wert größer ist, damit die for-Schleife funktioniert
				changingStart = ylast;
				changingEnd = yfirst;
			} else {
				changingStart = yfirst;
				changingEnd = ylast;
			}

			// hier wird der Array auf 1 gesetzt
			for (int i = changingStart; i <= changingEnd; i++) {
				Brett.brett[staticInt][i] = 1;
			}
		} else { // wenn Y statisch ist
			staticInt = yfirst;
			if (xfirst > xlast) { // checkt, welcher wert größer ist, damit die for-Schleife funktioniert
				changingStart = xlast;
				changingEnd = xfirst;
			} else {
				changingStart = xfirst;
				changingEnd = xlast;
			}

			// hier wird der Array auf 1 gesetzt
			for (int i = changingStart; i <= changingEnd; i++) {
				Brett.brett[i][staticInt] = 1;
			}
		}
	}

	public void setShip () {
		if (checkIfAnyShipLeft()) {
			System.out.println("What kind of Ship do you want to put? Type: \n"
					+ "1 for a small Ship (" + countSmall + " left to put) \n"
					+ "2 for a middle Ship (" + countMiddle + " left to put) \n"
					+ "3 for a big Ship (" + countBig + " left to put) \n"
					+ "4 for your Destroyer (" + countDestroyer + " left to put) \n");

			//wahl des benutzers
			String wahl = scan.next();

			if (checkIfValidInput(wahl)) {
				int choice = Integer.parseInt(scan.next());

				if (checkChoiceNr(choice) && checkIfShipAvailable(choice)) {
					// Anzahl der verfügbaren schiffe des typs verringern und ersten Teil des Schiffs setzen!
					setShipParts(choice);
				} else {
					System.out.println("Invalid Input: We will start over again.\n\n");
					setShip();
				}
			} else {
				System.out.println("Invalid Input: We will start over again.\n\n");
				setShip();
			}
		} else {
			System.out.println("All ships are put in the game. LET'S GO MARIO! \n");

			// wenn es keine schiffe mehr zu setzen gibt, wird abgebrochen
			shipsLeftToPut = false;
		}
	}

	public void setShipParts(int i) {

		int xfirst, yfirst;

		System.out.println("You can always start over again with putting the ship in the game by entering 'b'! \n"
				+ "Set the X-Coordinate of the first Part of the Ship!\n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);

		if (checkNumber(xcoord) && checkXcoord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycoord = scan.next();
			int y = Integer.parseInt(ycoord);

			if (checkNumber(ycoord) && checkFree(x, y)) {
				xfirst = x;
				yfirst = y;
				System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				xcoord = scan.next();
				x = Integer.parseInt(xcoord);

				// checkt wieder, ob alles passt UND ob die eingegebene koordinate mit einer Koordinate des 
				// ersten Punkts übereinstimmt (muss sie schließlich) 
				if (checkNumber(xcoord) && checkXcoord(x) && checkIfInLine(xfirst, yfirst, x)) {
					System.out.println("Now type in the Y-Coordinate of the last part of the Ship!\n");
					ycoord = scan.next();
					y = Integer.parseInt(ycoord);

					//checkt eingabe, freiheit des endfelds, freiheit der linie und länge des schiffs.
					//setzt erst dann die koordinaten auf 1
					if (checkNumber(ycoord) && checkFree(x, y) && checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, x, y, i)) {
						// scan.close();
						setShipOnBoard(xfirst, yfirst, x, y);
						reduceShipCount(i);
					} else if (ycoord.equals("m")) {
						Game.showMenu();
					} else if (ycoord.equals("b")) {
						setShipParts(i);
					} else {
						System.out.println("Invalid Input, we will start over again.\n");
						setShipParts(i);
					}
				} else if (xcoord.equals("m")) {
					Game.showMenu();
				} else if (xcoord.equals("b")) {
					setShipParts(i);
				} else {
					System.out.println("Invalid Input, we will start over again.\n");
					setShipParts(i);
				}
			} else if (ycoord.equals("m")) {
				Game.showMenu();
			} else if (ycoord.equals("b")) {
				setShipParts(i);
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				setShipParts(i);
			}
		} else if (xcoord.equals("m")) {
			Game.showMenu();
		} else if (xcoord.equals("b")) {
			setShipParts(i);
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipParts(i);
		}
	}

	public void fire(int x, int y) {
		// TODO wenn man nicht getroffen hat: Kreuz ins Feld zeichnen

		//wenn man getroffen hat bzw nicht 
		if (!checkFree(x, y)) {
			System.out.println("HIT! \n");

			if (round % 2 == 0) {
				scorePlayer1 -= 1;
				destroy(x, y);

			} else {
				scorePlayer2 -= 1;
				destroy(x, y);
			}
		} else {
			System.out.println("Missed! \n");
			// Feld kennzeichnen!
		}
	}

	// Alle Felder 0 setzen (falls wir das brauchen)
	public void setShipsBack () {
		for (int i = 1; i <= fieldSizeX; i++) {
			for (int x = 1; x <= fieldSizeY; x++) {
				Brett.brett[i][x] = 0;
			}
		}
	}

	public void destroy(int x, int y) {
		
	}
}















}
