package c2f.boatbusters.classes;

import c2f.boatbusters.abstractClasses.*;
import c2f.boatbusters.interfaces.WarShipInterface;
import c2f.boatbusters.classes.*;
import c2f.boatbusters.factories.*;
import java.util.Scanner;

public class WarShip extends Ship implements WarShipInterface {

	static Scanner scan = new Scanner (System.in);

	private int shipSize;

	public WarShip (int i) {

		if (i == getShortShipLength()) {
			this.shipSize = i;
		} else if (i == getMiddleShipLength()) {
			this.shipSize = i;
		} else if (i == getBigShipLength()) {
			this.shipSize = i;
		} else {
			// error log, groesse falsch
		}
	}


	// CHECKER
	//-------------------------------------------------------

	//checkt, ob in der X-Koordinate überhaupt was frei ist
	public boolean checkXcoord (int i, Board board[][]) {
		//fährt jedes Feld in der X-koordinate ab und gibt true aus, falls eines 0 ist
		for (int a = 1; a <= Board.fieldSizeX; a++) {
			if (board [i][a] == null) {
				return true;
			}
		}
		return false;
	}

	public boolean checkNumber (String s) {
		int i = Integer.parseInt(s);

		if (i > 0 && i <= Board.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	//checkt, ob die Position frei ist
	public boolean checkFree (int x, int y, Board board[][]) {
		if (board [x][y] == null) {
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
	public boolean checkIfShipAvailable (int i, Player player) {
		if (i == 1) {
			return (player.getCountSmall() > 0);
		} else if (i == 2) {
			return (player.getCountMiddle() > 0);
		} else if (i == 3) {
			return (player.getCountBig() > 0);
		} else {
			//  TODO error log
			return false;
		}
	}

	//wenn man ein schiff setzt, wird die anzahl der verfügbaren schiffe diesen typs um 1 verringert
	public void reduceShipCount (int i, Player player) {
		switch (i) {
		case 1: player.decreaseCountSmall();
		break;
		case 2: player.decreaseCountMiddle();
		break;
		case 3: player.decreaseCountBig();
		break;

		default:
			// TODO error log ("Error: Zahl nicht zwischen 1 und 4!");
		}
	}

	public boolean checkIfInLine (int x1, int y1, int x2, int y2) {
		return (x1 == x2 && y1 != y2 || y1 == y2 && x1 != x2);
	}


	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	public boolean checkIfLineIsFreeAndLengthCorrect (int xfirst, int yfirst, int xlast, int ylast, int length, Board board[][]) {
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
				if (board[staticInt][i] != null){
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
				if (board[i][staticInt] != null){
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

	public void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast, Board board[][], int choice) {
		int staticInt, changingStart, changingEnd;

		ShipFactory fact = new ShipFactory();

		if (xfirst == xlast) { // wenn X statisch ist
			staticInt = xfirst;
			if (yfirst > ylast) { // checkt, welcher wert größer ist, damit die for-Schleife funktioniert
				changingStart = ylast;
				changingEnd = yfirst;
			} else {
				changingStart = yfirst;
				changingEnd = ylast;
			}

			// referenz wird gesetzt
			for (int i = changingStart; i <= changingEnd; i++) {
				WarShipInterface ship = ShipFactory.getType(choice);
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

			// referenz wird gesetzt
			for (int i = changingStart; i <= changingEnd; i++) {
				WarShipInterface ship = ShipFactory.getType(choice);
			}
		}
	}

	public void setShip (Player player, Board board[][]) {
		if (player.areShipsLeftToPut()) {

			//TODO sysout ERSETZEN!

			System.out.println("What kind of Ship do you want to put? Type: \n"
					+ "1 for a small Ship (" + player.getCountSmall() + " left to put) \n"
					+ "2 for a middle Ship (" + player.getCountMiddle() + " left to put) \n"
					+ "3 for a big Ship (" + player.getCountBig() + " left to put) \n");

			String wahl = scan.next();

			if (checkIfValidInput(wahl)) {
				int choice = Integer.parseInt(scan.next());

				if (checkChoiceNr(choice) && checkIfShipAvailable(choice, player)) {
					// Anzahl der verfügbaren schiffe des typs verringern und ersten Teil des Schiffs setzen!
					setShipParts(choice, board, player);
				} else {
					// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
					setShip(player, board);
				}
			} else {
				// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
				setShip(player, board);
			}
		} else {
			// TODO log System.out.println("All ships are put in the game. LET'S GO MARIO! \n");

			// wenn es keine schiffe mehr zu setzen gibt, wird abgebrochen
		}
	}

	public void setShipParts(int choice, Board board[][], Player player) {

		int xfirst, yfirst;

		// TODO log System.out.println("You can always start over again with putting the ship in the game by entering 'b'! \n"
		//		+ "Set the X-Coordinate of the first Part of the Ship!\n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);

		if (checkNumber(xcoord) && checkXcoord(x, board)) {
			// TODO log System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycoord = scan.next();
			int y = Integer.parseInt(ycoord);

			if (checkNumber(ycoord) && checkFree(x, y, board)) {
				xfirst = x;
				yfirst = y;
				// TODO log System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				xcoord = scan.next();
				x = Integer.parseInt(xcoord);

				// checkt wieder, ob alles passt UND ob die eingegebene koordinate mit einer Koordinate des 
				// ersten Punkts übereinstimmt (muss sie schließlich) 
				if (checkNumber(xcoord) && checkXcoord(x, board)) {
					// TODO log System.out.println("Now type in the Y-Coordinate of the last part of the Ship!\n");
					ycoord = scan.next();
					y = Integer.parseInt(ycoord);

					//checkt eingabe, freiheit des endfelds, freiheit der linie und länge des schiffs.
					//setzt erst dann die koordinaten auf 1
					if (checkNumber(ycoord) && checkFree(x, y, board) && checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, x, y, choice, board)) {
						// scan.close();
						setShipOnBoard(xfirst, yfirst, x, y, board, choice);
						reduceShipCount(choice, player);
					} else if (ycoord.equals("m")) {
						Game game = new Game(0);
						game.showMenu();
					} else if (ycoord.equals("b")) {
						setShipParts(choice, board, player);
					} else {
						// TODO log System.out.println("Invalid Input, we will start over again.\n");
						setShipParts(choice, board, player);
					}
				} else if (xcoord.equals("m")) {
					Game game = new Game(0);
					game.showMenu();
				} else if (xcoord.equals("b")) {
					setShipParts(choice, board, player);
				} else {
					// TODO log System.out.println("Invalid Input, we will start over again.\n");
					setShipParts(choice, board, player);
				}
			} else if (ycoord.equals("m")) {
				Game game = new Game(0);
				game.showMenu();
			} else if (ycoord.equals("b")) {
				setShipParts(choice, board, player);
			} else {
				// TODO log System.out.println("Invalid Input, we will start over again.\n");
				setShipParts(choice, board, player);
			}
		} else if (xcoord.equals("m")) {
			Game game = new Game(0);
			game.showMenu();
		} else if (xcoord.equals("b")) {
			setShipParts(choice, board, player);
		} else {
			// TODO log System.out.println("Invalid Input, we will start over again.\n");
			setShipParts(choice, board, player);
		}
	}

	public void fire(int x, int y, Board board[][], Player player) {
		// TODO wenn man nicht getroffen hat: Kreuz ins Feld zeichnen
		if (!checkFree(x, y, board)) {
			// TODO log System.out.println("HIT! \n");
			player.increaseScore();
			destroy(x, y, board);
		} else {
			// TODO log System.out.println("Missed! \n");
			// Feld kennzeichnen!
		}
	}

	// Alle Felder 0 setzen (falls wir das brauchen)
	public void setShipsBack (Board board1[][], Board board2[][]) {
		for (int i = 1; i <= Board.fieldSizeX; i++) {
			for (int x = 1; x <= Board.fieldSizeY; x++) {
				board1[i][x] = null;
				board2[i][x] = null;
			}
		}
	}

	public void destroy(int x, int y, Board board[][]) {
		board[x][y] = null;
	}
}


