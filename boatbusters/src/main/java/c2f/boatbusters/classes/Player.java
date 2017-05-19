package c2f.boatbusters.classes;

import java.util.Scanner;

import c2f.boatbusters.abstractClasses.Board;
import c2f.boatbusters.factories.ShipFactory;

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

	// GETTER AND SETTER

	public int getScore() { return score; }
	public void increaseScore() { score += 1; }

	//-------------------------------

	public int getCountSmall() { return countSmall; }
	public void decreaseCountSmall() { countSmall -= 1; }

	//-------------------------------

	public int getCountMiddle() { return countMiddle; }
	public void decreaseCountMiddle() { countMiddle -= 1; }

	//-------------------------------

	public int getCountBig() { return countBig; }
	public void decreaseCountBig() { countBig -= 1; }

	//-------------------------------

	public int getShipsCount() { return shipsCount;}

	//-------------------------------

	// Aktualisiert den boolean jedes Mal aufs Neue
	public boolean areShipsLeftToPut() { 
		shipsLeftToPut = (shipsCount > 0);
		return shipsLeftToPut; 
	}

	//-------------------------------

	public int getChoice() { return choice; }
	public void setChoice(int choice) { this.choice = choice; }


	// CHECKER 
	//-------------------------------

	// Checkt ob die Schiffswahlzahl passt (muss zwischen 1 und 3 sein) 
	public boolean checkChoiceNr (int choice) {
		return (choice > 0 && choice < 4);
	}

	// schiffsauswahl korrekt?
	public boolean checkIfValidInput(String str) {
		switch (str) {
		case "1": return true;
		case "2": return true;
		case "3": return true;
		case "m": return true;

		default: return false;
		}
	}

	// noch ein schiff diesen Typs beim Spieler uebrig?
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

	// in der X-Koordinate ueberhaupt was frei?
	public boolean checkXcoord (int i, WarShip[][] board) {
		//faehrt jedes Feld in der X-koordinate ab und gibt aus, ob etwas frei ist oder nicht
		boolean free = false;
		for (int a = 0; a < Board.fieldSizeX; a++) {
			if (board [i - 1][a] == null) {
				free = true;
			}
		}
		return free;
	}

	public boolean checkNumber (String s) {
		int i;
		try {
			i = Integer.parseInt(s);
			return checkValidNumber(i);
		} catch (Exception e) {
			// TODO ERROR LOG!
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

	// Position frei?
	public static boolean checkFree (int x, int y, WarShip[][] board) {
		return (board [x - 1][y - 1] == null);
	}

	// wenn man ein schiff setzt, wird die anzahl der verfuegbaren schiffe diesen typs um 1 verringert
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
		// Hier wird festgelegt, welche Koordinate gleich bleibt und welche sich aendert,
		// damit anhand der sich aendernden Koordinate die angepeilte Linie auf dem Feld auf ihre Leere gecheckt werden kann.
		boolean isFree = true;

		if (xfirst == xlast) { // Wenn X statisch ist
			staticInt = xfirst;
			if (yfirst > ylast) { // Checkt, welcher Wert groeßer ist, damit die for-Schleife funktioniert
				changingStart = ylast;
				changingEnd = yfirst;
			} else {
				changingStart = yfirst;
				changingEnd = ylast;
			}

			// Das eigentliche Abchecken findet hier statt
			for (int i = changingStart; i <= changingEnd; i++) {
				if (board[staticInt - 1][i - 1] != null){
					isFree = false;
				}
			}
		} else if (yfirst == ylast) { // Wenn Y statisch ist
			staticInt = yfirst;
			if (xfirst > xlast) { // Checkt, welcher Wert groeßer ist, damit die for-Schleife funktioniert
				changingStart = xlast;
				changingEnd = xfirst;
			} else {
				changingStart = xfirst;
				changingEnd = xlast;
			}

			// Das eigentliche Abchecken findet hier statt
			for (int i = changingStart; i <= changingEnd; i++) {
				if (board[i - 1][staticInt - 1] != null){
					isFree = false;
				}
			}
		} else {
			// TODO Error Log: Die Eingaben sind falsch: Entweder die X- oder Y-Koordinaten muessen gleich sein!
			isFree = false;
			return isFree;
		}

		// Vergleicht Laenge des zu setzenden Schiffs mit der Laenge des Schifftyps
		if (!(changingEnd - changingStart == length)) {
			isFree = false;
		} 
		return isFree;
	}

	// SHIP SETTER
	//--------------------------------------------------------------

	// Anfangs- und Endteil des Schiffs setzen
	// Bei falschen Eingaben: Brechstange, fang von vorne an!
	private void setShipParts(int choice, WarShip board[][], Player player, Game game, Scanner scan) {

		// X- und Y-Koordinaten der Anfangs- und Endpunkte der Schiffe
		int xfirst, yfirst, xlast, ylast;

		// TODO log System.out.println("type in the x coordinate of the first part!");
		System.out.println("Type in the x coordinate of the first part!");
		String input = scan.next();

		// "m" -> ins Menu, "b" -> Abbrechen, Schiffsteile neu setzen
		if (input.equals("m")) {
			game.showMenu(game);
		} else if (input.equals("b")) {
			setShip(player, board, game, scan);
		}

		// Ist input eine Zahl? Ist auf der X-Koordiante noch was frei?
		if (checkNumber(input) && checkXcoord(Integer.parseInt(input), board)) {
			xfirst = Integer.parseInt(input);
			// TODO log System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			input = scan.next();

			// Selber Ablauf fuer die folgenden Koordinaten-Eingaben
			if (input.equals("m")) {
				game.showMenu(game);
			} else if (input.equals("b")) {
				setShip(player, board, game, scan);
			}

			// input = Zahl? Position im Brett frei? 
			if (checkNumber(input) && checkFree(xfirst, Integer.parseInt(input), board)) {
				yfirst = Integer.parseInt(input);
				// TODO log System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				System.out.println("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
				input = scan.next();

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

					// Checkt input, Freiheit der Endposition, Freiheit der Linie und Laenge des Schiffs.
					// Setzt erst dann die Referenzen in das Feld.
					if (checkNumber(input) && checkFree(xlast, Integer.parseInt(input), board) && checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, xlast, Integer.parseInt(input), choice, board)) {
						ylast = Integer.parseInt(input);

						// Schiff wird gesetzt, Anzahl der verfuegbaren Schiffe diesen Typs verringert
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

	private void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast, WarShip[][] board, int choice) {
		int staticInt, changingStart, changingEnd;

		ShipFactory fact = new ShipFactory();
		WarShip ship = (WarShip) fact.getType(choice);

		if (xfirst == xlast) { // Wenn X statisch ist
			staticInt = xfirst;
			if (yfirst > ylast) { // Checkt, welcher Wert groeßer ist, damit die for-Schleife funktioniert
				changingStart = ylast;
				changingEnd = yfirst;
			} else {
				changingStart = yfirst;
				changingEnd = ylast;
			}

			// Referenz wird gesetzt
			for (int i = changingStart - 1; i < changingEnd; i++) {
				board [staticInt-1][i] = ship;
			}
		} else { // Wenn X statisch ist
			staticInt = yfirst;
			if (xfirst > xlast) { // Checkt, welcher Wert groeßer ist, damit die for-Schleife funktioniert
				changingStart = xlast;
				changingEnd = xfirst;
			} else {
				changingStart = xfirst;
				changingEnd = xlast;
			}

			// Referenz wird gesetzt
			for (int i = changingStart - 1; i < changingEnd; i++) {
				board [i][staticInt-1] = ship;
			}
		}
	}

	protected void setShip (Player player, WarShip[][] board1, Game game, Scanner scan) {

		// TODO sysout ERSETZEN!
		// Welcher Schiffstyp? Zeigt Anzahl der noch vefuegbaren Schiffe jeden Typs an
		System.out.println("What kind of Ship do you want to put? Type: \n"
				+ "1 for a small Ship (" + player.getCountSmall() + " left to put) \n"
				+ "2 for a middle Ship (" + player.getCountMiddle() + " left to put) \n"
				+ "3 for a big Ship (" + player.getCountBig() + " left to put) \n");

		String wahl;

		wahl = scan.next();
		if (checkIfValidInput(wahl)) {
			// Wenn der Spieler ins Menu zurueck will
			if (wahl.equals("m")) {
				game.showMenu(game);
			} 

			int choice = Integer.parseInt(wahl);

			// Wenn noch ein Schiff diesen Typs verfuegbar ist 
			if (checkIfShipAvailable(choice, player)) {
				setShipParts(choice, board1, player, game, scan);
			} else {
				// TODO log System.out.println("Sorry, there's no ship of this type left!\n\n");
				setShip(player, board1, game, scan);
			}
		} else {
			// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
			setShip(player, board1, game, scan);
		}
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
