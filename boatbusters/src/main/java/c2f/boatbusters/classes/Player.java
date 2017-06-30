package c2f.boatbusters.classes;

import java.util.Scanner;


import c2f.boatbusters.abstractClasses.Board;
import c2f.boatbusters.abstractClasses.Ship;
import c2f.boatbusters.factories.ShipFactory;
import c2f.boatbusters.interfaces.IPlayer;
import javafx.scene.text.Text;

public class Player implements IPlayer {

	private int score = 0;
	private int choice;
	private int countSmall = 2;
	private int countMiddle = 3;
	private int countBig = 2;
	private int shipsCount = countSmall + countMiddle + countBig;
	private boolean shipsLeftToPut = (shipsCount > 0);
    private boolean shipSetMode;

	
	private boolean secondClick = false;
	private int xfirst;
	private int xlast;
	private int yfirst;
	private int ylast;
	
	private boolean ready = false;
	private boolean hit = false;
	private boolean missed = false;
	

	
	
	// GETTER AND SETTER
	
	public int getXfirst(){ return xfirst;}
	
	public int getXlast(){ return xlast;}
	
	public int getYfirst(){ return xfirst;}
	
	public int getYlast(){ return ylast;}
	
	
	public void setXfirst(int xfirst) { this.xfirst = xfirst;}
	
	public void setXlast(int xlast) { this.xlast = xlast;}
	
	public void setYfirst(int yfirst) { this.yfirst = yfirst;}
	
	public void setYlast(int ylast) { this.ylast = ylast;}
	
	
	public boolean getSecondClick(){ return secondClick;}
	
	public void setSecondClick(boolean value){ this.secondClick = value;}
	
	public void setReady(boolean ready){
		this.ready = ready;
	}
	
	public void setHit(boolean hit){
		this.hit = hit;
	}
	
	public void setMissed(boolean missed){
		this.missed = missed;
	}
	
	public boolean getHit(){
		return hit;
	}
	public boolean getMissed(){
		return missed;
	}
	
	public boolean getReady(){
		return ready;
	}
	
	public boolean getShipSetMode(){
		return shipSetMode;
	}
	
	public void setShipSetMode(boolean ShipSetMode){
		this.shipSetMode = ShipSetMode;
	}
	
	

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getScore()
	 */
	@Override
	public int getScore() { return score; }
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#increaseScore()
	 */
	
	
	
	@Override
	public void increaseScore() { score += 1; }

	//-------------------------------

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getCountSmall()
	 */
	@Override
	public int getCountSmall() { return countSmall; }
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#decreaseCountSmall()
	 */
	
	public void setCountSmall(int countSmall){ this.countSmall = countSmall;}
	
	@Override
	public void decreaseCountSmall() { countSmall -= 1; }

	//-------------------------------

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getCountMiddle()
	 */
	@Override
	public int getCountMiddle() { return countMiddle; }
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#decreaseCountMiddle()
	 */
	
	public void setCountMiddle(int countMiddle){ this.countMiddle = countMiddle; }
	

	@Override
	public void decreaseCountMiddle() { countMiddle -= 1; }

	//-------------------------------

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getCountBig()
	 */
	@Override
	public int getCountBig() { return countBig; }
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#decreaseCountBig()
	 */
	
	public void setCountBig(int countBig) { this.countBig = countBig; }
	
	
	@Override
	public void decreaseCountBig() { countBig -= 1; }

	//-------------------------------

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getShipsCount()
	 */
	@Override
	public int getShipsCount() { return shipsCount;}

	//-------------------------------

	// Aktualisiert den boolean jedes Mal aufs Neue
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#areShipsLeftToPut()
	 */
	
	@Override
	public boolean areShipsLeftToPut(Player player) { 
		shipsLeftToPut = (shipsCount > 0);
		return shipsLeftToPut; 
	}

	//-------------------------------

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getChoice()
	 */
	@Override
	public int getChoice() { return choice; }
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#setChoice(int)
	 */
	@Override
	public void setChoice(int choice) { this.choice = choice; }


	// CHECKER 
	//-------------------------------

	// Checkt ob die Schiffswahlzahl passt (muss zwischen 1 und 3 sein) 
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkChoiceNr(int)
	 */
	@Override
	public boolean checkChoiceNr (int choice) {
		return (choice > 0 && choice < 4);
	}

	// schiffsauswahl korrekt?
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkIfValidInput(java.lang.String)
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkIfShipAvailable(int, c2f.boatbusters.classes.IPlayer)
	 */
	@Override
	public boolean checkIfShipAvailable (int i, IPlayer player) {
		if (i == 2) {
			return (player.getCountSmall() > 0);
		} else if (i == 3) {
			return (player.getCountMiddle() > 0);
		} else if (i == 4) {
			return (player.getCountBig() > 0);
		} else {
			//  TODO error log
			return false;
		}
	}
	

	// in der X-Koordinate ueberhaupt was frei?
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkXcoord(int, c2f.boatbusters.classes.WarShip[][])
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkNumber(java.lang.String)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkValidNumber(int)
	 */
	@Override
	public boolean checkValidNumber (int i) {
		if (i > 0 && i <= Board.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	// Position frei?
	public boolean checkFree (int x, int y, WarShip[][] board) {
		return (board [x][y] == null);
	}

	// wenn man ein schiff setzt, wird die anzahl der verfuegbaren schiffe diesen typs um 1 verringert
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#reduceShipCount(int, c2f.boatbusters.classes.IPlayer)
	 */
	@Override
	public void reduceShipCount (int i, Player player) {
		switch (i) {
		case 2: player.decreaseCountSmall();
		break;
		case 3: player.decreaseCountMiddle();
		break;
		case 4: player.decreaseCountBig();
		break;

		default:
			// TODO error log ("Error: Zahl nicht zwischen 1 und 4!");
		}
	}

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkIfInLine(int, int, int, int)
	 */
	@Override
	public boolean checkIfInLine (int x1, int y1, int x2, int y2) {
		return (x1 == x2 && y1 != y2 || y1 == y2 && x1 != x2);
	}

	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#checkIfLineIsFreeAndLengthCorrect(int, int, int, int, int, c2f.boatbusters.classes.WarShip[][])
	 */
	@Override
	public boolean checkIfLineIsFreeAndLengthCorrect (int xfirst, int yfirst, int xlast,
			int ylast, int length, WarShip[][] board) {
		
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
				if (board[staticInt][i] != null){
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
				if (board[i][staticInt] != null){
					isFree = false;
				}
			}
		} else {
			// TODO Error Log: Die Eingaben sind falsch: Entweder die X- oder Y-Koordinaten muessen gleich sein!
			isFree = false;
			return isFree;
		}

		// Vergleicht Laenge des zu setzenden Schiffs mit der Laenge des Schifftyps
		if (!(changingEnd - changingStart + 1 == length)) {
			isFree = false;
		} 

		return isFree;
	}

	// SHIP SETTER
	//--------------------------------------------------------------

	// Anfangs- und Endteil des Schiffs setzen
	// Bei falschen Eingaben: Brechstange, fang von vorne an!
//	private void setShipParts(int choice, WarShip board[][], IPlayer player, Game game, Scanner scan) {
//
//		// X- und Y-Koordinaten der Anfangs- und Endpunkte der Schiffe
//		int xfirst, yfirst, xlast, ylast;
//
//		// TODO log System.out.println("type in the x coordinate of the first part!");
//		Main.getLogger().info("Type in the x coordinate of the first part!");
//		String input = scan.next();
//
//		// "m" -> ins Menu, "b" -> Abbrechen, Schiffsteile neu setzen
//		if (input.equals("m")) {
//			game.showMenu(game);
//		} else if (input.equals("b")) {
//			setShip(player, board, game, scan);
//		}
//
//		// Ist input eine Zahl? Ist auf der X-Koordiante noch was frei?
//		if (checkNumber(input) && checkXcoord(Integer.parseInt(input), board)) {
//			xfirst = Integer.parseInt(input);
//			// TODO log System.out.println("Now type in the Y-Coordinate and press Enter!\n");
//			Main.getLogger().info("Now type in the Y-Coordinate and press Enter!\n");
//			input = scan.next();
//
//			// Selber Ablauf fuer die folgenden Koordinaten-Eingaben
//			if (input.equals("m")) {
//				game.showMenu(game);
//			} else if (input.equals("b")) {
//				setShip(player, board, game, scan);
//			}
//
//			// input = Zahl? Position im Brett frei?
//			if (checkNumber(input) && checkFree(xfirst, Integer.parseInt(input), board)) {
//				yfirst = Integer.parseInt(input);
//				// TODO log System.out.println("Now type in the X-Coordinate of
//				// the last part of the Ship and press Enter!\n");
//				Main.getLogger().info("Now type in the X-Coordinate of the last part of the Ship and press Enter!\n");
//				input = scan.next();
//
//				if (input.equals("m")) {
//					game.showMenu(game);
//				} else if (input.equals("b")) {
//					setShip(player, board, game, scan);
//				}
//
//				if (checkNumber(input) && checkXcoord(xfirst, board)) {
//					xlast = Integer.parseInt(input);
//					// TODO log System.out.println("Now type in the Y-Coordinate
//					// of the last part of the Ship!\n");
//					Main.getLogger().info("Now type in the Y-Coordinate of the last part of the Ship!\n");
//					input = scan.next();
//
//					if (input.equals("m")) {
//						game.showMenu(game);
//					} else if (input.equals("b")) {
//						setShip(player, board, game, scan);
//					}
//
//					// Checkt input, Freiheit der Endposition, Freiheit der
//					// Linie und Laenge des Schiffs.
//					// Setzt erst dann die Referenzen in das Feld.
//					if (checkNumber(input) && checkFree(xlast, Integer.parseInt(input), board)
//							&& checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, xlast, Integer.parseInt(input), choice,
//									board)) {
//						ylast = Integer.parseInt(input);
//
//						// Schiff wird gesetzt, Anzahl der verfuegbaren Schiffe
//						// diesen Typs verringert
//						setShipOnBoard(xfirst, yfirst, xlast, ylast, board, choice);
//						reduceShipCount(choice, player);
//
//					} else {
//						// TODO log System.out.println("Invalid Input, we will
//						// start over again.\n");
//						setShipParts(choice, board, player, game, scan);
//					}
//				} else {
//					// TODO log System.out.println("Invalid Input, we will start
//					// over again.\n");
//					setShipParts(choice, board, player, game, scan);
//				}
//			} else {
//				// TODO log System.out.println("Invalid Input, we will start
//				// over again.\n");
//				setShipParts(choice, board, player, game, scan);
//			}
//		} else {
//			// TODO log System.out.println("Invalid Input, we will start over
//			// again.\n");
//			setShipParts(choice, board, player, game, scan);
//		}
//	}
	
	
	
	private int checkLength(int xfirst, int yfirst, int xlast, int ylast) {
		
		int lengthOfShip;
		
		if (xfirst == xlast && yfirst > ylast) {
			lengthOfShip = yfirst - ylast + 1;
			return lengthOfShip;
		} 
		
		else if (xfirst == xlast && yfirst < ylast) {
			lengthOfShip = ylast - yfirst + 1;
			return lengthOfShip;
		}

		else if (yfirst == ylast && xfirst > xlast) {
			lengthOfShip = xfirst - xlast + 1;
			return lengthOfShip;
		}

		else if (yfirst == ylast && xfirst < xlast) {
			lengthOfShip = xlast - xfirst + 1;
			return lengthOfShip;
		}

		else {return 0;}


	}
	

	
	
	// SHIP SETTER
	//--------------------------------------------------------------

	// Anfangs- und Endteil des Schiffs setzen
	// Bei falschen Eingaben: Brechstange, fang von vorne an!
	public void setShipPartsGui(int xlast, int ylast, WarShip[][] board,
			Player player) throws SetShipException {


		int lengthOfShip = checkLength(xfirst, yfirst, xlast, ylast);
		
		try {
		// Checkt input, Freiheit der Start- und Endposition, Freiheit der
		// Linie und Laenge des Schiffs. Setzt erst dann die Referenzen in das Feld.
		if (checkIfLineIsFreeAndLengthCorrect(xfirst, yfirst, xlast, ylast, lengthOfShip, board) &&
				checkFree(xfirst, yfirst, board) && checkFree(xlast, ylast, board)
				&& checkIfShipAvailable(lengthOfShip, player)){	
			// Schiff wird gesetzt, Anzahl der verfuegbaren Schiffe
			// diesen Typs verringert
			setShipOnBoard(xfirst, yfirst, xlast, ylast, board, lengthOfShip);
			reduceShipCount(lengthOfShip, this);
		}
		//Wenn das Schiff falsch gesetzt wurde, wird die SetShipException geworfen
		else { throw new SetShipException();
			
		}
		}catch(SetShipException e){ 
			//Error Nachricht an Benutzer, warum das Schiff nicht gesetzt werdeb konnte
			Main.getLogger().error("Achtung. falsche Koordinaten-Eingabe, "
					+ "Sie können ihr Schiff so nicht setzen!\nFolgendes kann "
					+ "schief gelaufen sein:\n1. Sie haben versucht, ihr Schiff "
					+ "diagonal oder quer zu setzen.\n2. Sie haben versucht, "
					+ "ihr Schiff auf Zellen / Koordinaten zu setzen, auf denen sich bereits "
					+ "Schiffe befinden.\n3. Sie haben ein zu langes oder zu kurzes Schiff "
					+ "gesetzt.\n4. Sie haben kein Schiff der gewählten Länge mehr verfügbar.");
		}	
	}


	
     
	

	private void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast, WarShip[][] board, int length) {
		int staticInt, changingStart, changingEnd;

		ShipFactory fact = new ShipFactory();


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
			for (int i = changingStart; i <= changingEnd; i++) {
				WarShip ship = fact.getType(length);
				board [staticInt][i] = ship;
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
			for (int i = changingStart; i <= changingEnd; i++) {
				WarShip ship = fact.getType(length);
				board [i][staticInt] = ship;
			}
		}
	}
	

	public void fire(int x, int y, Player player, Game game) {

		if (player == game.getPlayer1()) {
			if (!player.checkFree(x, y, game.getBoard2())) {
				// TODO log System.out.println("HIT! \n");
				Main.getLogger().info("HIT! \n");
				player.increaseScore();
				setHit(true);
				setMissed(false);
				destroy(game.getBoard2()[x][y]);
			} else {
				Main.getLogger().info("Missed! \n");
				setMissed(true);
				setHit(false);
				ShipFactory sf = new ShipFactory();
				WarShip empty = sf.getType(0);
				game.board2[x][y] = empty;
				empty.setEmpty(true);
			}
		} else if (player == game.getPlayer2()) {

			if (!player.checkFree(x, y, game.getBoard1())) {
				// TODO log System.out.println("HIT! \n");
				setHit(true);
				setMissed(false);
				Main.getLogger().info("HIT! \n");
				player.increaseScore();
				destroy(game.getBoard1()[x][y]);
			} else {
				Main.getLogger().info("Missed! \n");
				setMissed(true);
				setHit(false);
				ShipFactory sf = new ShipFactory();
				WarShip empty = sf.getType(0);
				game.board1[x][y] = empty;
				empty.setEmpty(true);
			}
		}
	}

	private void destroy(WarShip warship) {
		warship.setShipDestroyed(true);
	}
	
	

//	protected void setShip (IPlayer player, WarShip[][] board1, Game game, Scanner scan) {
//
//		// TODO sysout ERSETZEN!
//		// Welcher Schiffstyp? Zeigt Anzahl der noch vefuegbaren Schiffe jeden Typs an
//		Main.getLogger().info("What kind of Ship do you want to put? Type: \n"
//				+ "1 for a small Ship (" + player.getCountSmall() + " left to put) \n"
//				+ "2 for a middle Ship (" + player.getCountMiddle() + " left to put) \n"
//				+ "3 for a big Ship (" + player.getCountBig() + " left to put) \n");
//
//		String wahl;
//
//		wahl = scan.next();
//		if (checkIfValidInput(wahl)) {
//			// Wenn der Spieler ins Menu zurueck will
//			if (wahl.equals("m")) {
//				game.showMenu(game);
//			} 
//
//			int choice = Integer.parseInt(wahl);
//
//			// Wenn noch ein Schiff diesen Typs verfuegbar ist 
//			if (checkIfShipAvailable(choice, player)) {
//				setShipParts(choice, board1, player, game, scan);
//			} else {
//				// TODO log System.out.println("Sorry, there's no ship of this type left!\n\n");
//				setShip(player, board1, game, scan);
//			}
//		} else {
//			// TODO log System.out.println("Invalid Input: We will start over again.\n\n");
//			setShip(player, board1, game, scan);
//		}
//	} 


	//Sachen für Bestenliste/Highschore: Anfang:

	private Object name, numberOfWins;
	//Konstruktor: Spieler erhält vor dem Spiel einen Namen, sowie wenn er das erste Mal spielt, numberOfWins = 0
	public Player (Object name, Object numberOfWins){
		this.name = name;
		this.numberOfWins = numberOfWins;		
	}

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#getName()
	 */
	@Override
	public String getName(){
		return (String) name;
	}

	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#numberOfWins()
	 */
	@Override
	public Object numberOfWins(){
		return numberOfWins;
	}

	//Ist in Einklang mit der printBestenliste Mehtode, es entsteht eine Tabelle im richtigen Format abhängig von der 
	//Länge des Spielernamens
	/* (non-Javadoc)
	 * @see c2f.boatbusters.classes.IPlayer#toString()
	 */
	
	public String toString2(){
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
		else if( name.toString().length() == 0){ return name + "                  |   " + numberOfWins;}
		else { return name + "      |   " + numberOfWins;}
	}

	/**
	 * Formatierung zum Abspeichern in .csv Datei
	 * @return Ein Eintrag -> Eine Zeile in Datei
	 */
	String file(){
		return name + ";" + numberOfWins + ";\n";
	}
	@Override
	public int getShipCountCheck() {
		int ShipsLeft = getCountBig() + getCountMiddle() + getCountSmall();
		
		return ShipsLeft;
	}
	@Override
	public void reduceShipCount(int i, IPlayer player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean areShipsLeftToPut() {
		shipsLeftToPut = (shipsCount > 0);
		return shipsLeftToPut; 
	}


	//Sachen für Bestenliste/ Highscore: Ende

} 
