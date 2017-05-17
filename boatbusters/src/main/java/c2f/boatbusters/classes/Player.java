package c2f.boatbusters.classes;

import java.util.Scanner;

import c2f.boatbusters.abstractClasses.Ship;
import c2f.boatbusters.factories.ShipFactory;
import c2f.boatbusters.interfaces.WarShipInterface;

public class Player {

	private int score = 0;
	private int choice;
	private int countSmall = 4;
	private int countMiddle = 3;
	private int countBig = 2;
	private int shipsCount = countSmall + countMiddle + countBig;
	private boolean shipsLeftToPut = (shipsCount > 0);

	public static final int smallCount = 4;
	public static final int middleCount = 3;
	public static final int bigCount = 2;

	public static final int maxScore = 25;

	public int getScore() {
		return score;
	}
	public void increaseScore() {
		score += 1;
	}

	//-------------------------------

	public int getCountSmall() {
		return countSmall;
	}
	public void decreaseCountSmall() {
		countSmall -= 1;
	}

	//-------------------------------

	public int getCountMiddle() {
		return countMiddle;
	}
	public void decreaseCountMiddle() {
		countSmall -= 1;
	}

	//-------------------------------

	public int getCountBig() {
		return countBig;
	}
	public void decreaseCountBig() {
		countSmall -= 1;
	}

	//-------------------------------

	public int getShipsCount() {
		return shipsCount;
	}

	//-------------------------------

	public boolean areShipsLeftToPut() {
		return shipsLeftToPut;
	}

	//-------------------------------

	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}

	// CHECKER
	//-------------------------------------------------------

	// checkt ob die schiffswahlzahl passt (muss zwischen 1 und 4 sein) 
	public boolean checkChoiceNr (int choice) {
		if (choice > 0 && choice < 5) {
			return true;
		} else {
			return false;
		}
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

	//checkt, ob in der X-Koordinate überhaupt was frei ist
	public boolean checkXcoord (int i, WarShip[][] board) {
		//fährt jedes Feld in der X-koordinate ab und gibt true aus, falls eines 0 ist
		for (int a = 0; a < Board.fieldSizeX; a++) {
			if (board [i - 1][a] == null) {
				return true;
			}
		}
		return false;
	}

	public boolean checkNumber (String s) {

		int i;

		try {
			i = Integer.parseInt(s);
			return checkValidNumber(i);
		} catch (Exception e) {
			// TODO ERROR LOG und das unter mir weg
			//i = Integer.parseInt(s);
		}

		return false;
		
	}
	
	public boolean checkValidNumber (int i) {
		if (i > 0 && i <= Board.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	//checkt, ob die Position frei ist
	public static boolean checkFree (int x, int y, WarShip[][] board) {
		if (board [x - 1][y - 1] == null) {
			return true;
		} else {
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
	public boolean checkIfLineIsFreeAndLengthCorrect (int xfirst, int yfirst, int xlast, int ylast, int length, WarShip[][] board) {
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
				if (board[staticInt - 1][i - 1] != null){
					isFree = false;
				}
			}
		} else if (yfirst == ylast) { // wenn Y statisch ist
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
				if (board[i - 1][staticInt - 1] != null){
					isFree = false;
				}
			}
		} else {
			isFree = false;
			return isFree;
		}

		// checkt länge
		if (!(changingEnd - changingStart == length)) {
			isFree = false;
		} 

		return isFree;
	}

	// SHIP SETTER 2
	//--------------------------------------------------------------

	public void setShipParts(int choice, WarShip board[][], Player player, Game game, Scanner scan) {

		int xfirst, yfirst, xlast, ylast;

		// TODO log System.out.println("type in the x coordinate of the first part!");
		System.out.println("type in the x coordinate of the first part!");
		// TODO hier ueberall waere ein try catch block gut! sonst gibt es eine fehlermeldung wenn der benutzer keinen parsebaren String eingibt 

		String input = scan.next();

		if (input.equals("m")) {
			game.showMenu(game);
		} else if (input.equals("b")) {
			setShip(player, board, game, scan);
		}

		if (checkNumber(input) && checkXcoord(Integer.parseInt(input), board)) {
			xfirst = Integer.parseInt(input);
			// TODO log System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			input = scan.next();

			if (input.equals("m")) {
				game.showMenu(game);
			} else if (input.equals("b")) {
				setShip(player, board, game, scan);
			}

			if (checkNumber(input) && checkFree(xfirst, Integer.parseInt(input), board)) {
				yfirst = Integer.parseInt(input);
				// TODO log System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				input = scan.next();

				// checkt wieder, ob alles passt UND ob die eingegebene koordinate mit einer Koordinate des 
				// ersten Punkts übereinstimmt (muss sie schließlich) 

				if (input.equals("m")) {
					game.showMenu(game);
				} else if (input.equals("b")) {
					setShip(player, board, game, scan);
				}

				if (checkNumber(input) && checkXcoord(xfirst, board)) {
					xlast = Integer.parseInt(input);
					// TODO log System.out.println("Now type in the Y-Coordinate of the last part of the Ship!\n");
					System.out.println("Now type in the Y-Coordinate of the last part of the Ship!\n");
					input = scan.next();

					if (input.equals("m")) {
						game.showMenu(game);
					} else if (input.equals("b")) {
						setShip(player, board, game, scan);
					}

					//checkt eingabe, freiheit des endfelds, freiheit der linie und länge des schiffs.
					//setzt erst dann die koordinaten auf 1
					if (checkNumber(input) && checkFree(xlast, Integer.parseInt(input), board) && checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, xlast, Integer.parseInt(input), choice, board)) {
						ylast = Integer.parseInt(input);

						setShipOnBoard(xfirst, yfirst, xlast, ylast, board, choice);
						reduceShipCount(choice, player);

					} else {
						// TODO log System.out.println("Invalid Input, we will start over again.\n");
						setShipParts(choice, board, player, game, scan);
					}
				} else {
					// TODO log System.out.println("Invalid Input, we will start over again.\n");
					setShipParts(choice, board, player, game, scan);
				}
			} else {
				// TODO log System.out.println("Invalid Input, we will start over again.\n");
				setShipParts(choice, board, player, game, scan);
			}
		} else {
			// TODO log System.out.println("Invalid Input, we will start over again.\n");
			setShipParts(choice, board, player, game, scan);
		}
	}

	public void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast, WarShip[][] board, int choice) {
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
			for (int i = changingStart - 1; i < changingEnd; i++) {
				createShip(1);
				
				//WarShipInterface ship = fact.getType(choice);
				
				board [staticInt-1][i] = createShip(1);
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
			for (int i = changingStart - 1; i < changingEnd; i++) {
				//WarShipInterface ship = fact.getType(choice);
				board [i][staticInt-1] = createShip(1);
			}
		}
	}

	public void setShip (Player player, WarShip[][] board1, Game game, Scanner scan) {

		//TODO sysout ERSETZEN!

		System.out.println("What kind of Ship do you want to put? Type: \n"
				+ "1 for a small Ship (" + player.getCountSmall() + " left to put) \n"
				+ "2 for a middle Ship (" + player.getCountMiddle() + " left to put) \n"
				+ "3 for a big Ship (" + player.getCountBig() + " left to put) \n");

		String wahl;

		wahl = scan.next();
		if (checkIfValidInput(wahl)) {
			//Wenn der Spieler abbrechen moechte
			if (wahl.equals("m")) {
				game.showMenu(game);
			} 

			int choice = Integer.parseInt(wahl);

			if (checkChoiceNr(choice) && checkIfShipAvailable(choice, player)) {
				// Anzahl der verfügbaren schiffe des typs verringern und ersten Teil des Schiffs setzen!
				setShipParts(choice, board1, player, game, scan);
			} else {
				// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
				setShip(player, board1, game, scan);
			}
		} else {
			// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
			setShip(player, board1, game, scan);
		}
	} 



	
	public WarShip createShip(int i) {
		return new WarShip(i);
	}

	//Sachen für Bestenliste/Highschore: Anfang:
	
	private Object name, numberOfWins;
	//Konstruktor: Spieler erhält vor dem Spiel einen Namen, sowie wenn er das erste Mal spielt, numberOfWins = 0
	public Player (Object name, Object numberOfWins){
		this.name = name;
		this.numberOfWins = numberOfWins;		
	}
	
	public Object getName(){
	    return name;
	}
	
	public Object numberOfWins(){
		return numberOfWins;
	}
	
	//Ist in Einklang mit der printBestenliste Mehtode, es entsteht eine Tabelle im richtigen Format abhängig von der 
	//Länge des Spielernamens
	public String toString(){
		if( name.toString().length() == 1){ return name + "                 |   " + numberOfWins;}
		else if( name.toString().length() == 2){ return name + "                |   " + numberOfWins;}
		else if( name.toString().length() == 3){ return name + "               |   " + numberOfWins;}
		else if( name.toString().length() == 4){ return name + "              |   " + numberOfWins;}
		else if( name.toString().length() == 5){ return name + "             |   " + numberOfWins;}
		else if( name.toString().length() == 6){ return name + "            |   " + numberOfWins;}
		else if( name.toString().length() == 7){ return name + "           |   " + numberOfWins;}
		else if( name.toString().length() == 8){ return name + "          |   " + numberOfWins;}
		else if( name.toString().length() == 9){ return name + "         |   " + numberOfWins;}
		else if( name.toString().length() == 10){ return name + "        |   " + numberOfWins;}
		else if( name.toString().length() == 11){ return name + "       |   " + numberOfWins;}
		else if( name.toString().length() == 12){ return name + "      |   " + numberOfWins;}
		else if( name.toString().length() == 13){ return name + "     |   " + numberOfWins;}
		else if( name.toString().length() == 14){ return name + "    |   " + numberOfWins;}
		else if( name.toString().length() == 15){ return name + "   |   " + numberOfWins;}
		else if( name.toString().length() == 16){ return name + "  |   " + numberOfWins;}
		else if( name.toString().length() == 17){ return name + " |   " + numberOfWins;}
		else if( name.toString().length() == 18){ return name + "|   " + numberOfWins;}
		else { return name + "      |   " + numberOfWins;}
	}

	/**
	 * Formatierung zum Abspeichern in .csv Datei
	 * @return Ein Eintrag -> Eine Zeile in Datei
	 */
	String file(){
		return name + ";" + numberOfWins + ";\n";
	}

	//Sachen für Bestenliste/ Highscore: Ende
	
} 





























