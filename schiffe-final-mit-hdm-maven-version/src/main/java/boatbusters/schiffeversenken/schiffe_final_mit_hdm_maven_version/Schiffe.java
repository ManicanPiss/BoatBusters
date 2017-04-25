package boatbusters.schiffeversenken.schiffe_final_mit_hdm_maven_version;



import java.util.Scanner;


public class Schiffe extends Game {

	// default Constructor, müssen wir noch ändern, wenn die Objekte Attribute brauchen (eigentlich nicht, oder?)
	// Update: würde gar nicht mit objekten arbeiten, sondern die positionen einfach =1 setzen!
	public Schiffe () {}

	// CHECKER
	//-------------------------------------------------------

	//checkt, ob in der X-Koordinate überhaupt was frei ist
	public static boolean checkXcoord (int i) {
		//fährt jedes Feld in der X-koordinate ab und gibt true aus, falls eines 0 ist
		for (int a = 1; a <= Game.fieldSizeX; a++) {
			if (Brett.brett [i][a] == 0) {
				return true;
			} 
		}
		//sonst halt false
		return false;
	}

	public static boolean checkNumber (String s) {
		int i = Integer.parseInt(s);

		//eingabe muss ja zwischen 1 und 10 sein
		if (i > 0 && i <= Game.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	//checkt, ob die Position frei ist
	public static boolean checkFree (int x, int y) {
		if (Brett.brett [x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}

	// checkt ob die schiffswahlzahl passt (muss zwischen 1 und 4 sein) 
	public static boolean checkChoiceNr (int i) {
		if (i > 0 && i < 5) {
			return true;
		} else {
			return false;
		}
	}

	// checkt, ob noch ein schiff diesen Typs übrig ist
	public static boolean checkIfShipAvailable (int i) {
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
	public static boolean checkIfAnyShipLeft () {
		return (shipsCount > 0);
	}

	//wenn man ein schiff setzt, wird die anzahl der verfügbaren schiffe diesen typs um 1 verringert
	public static void reduceShipCount (int i) {
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

	/*
	// checkt, wie gross das schiff ist, um zu sehen, ob man noch ein weiteres Teil setzen darf
	public static void checkSizeAfterSecondPart () {
		switch (choice) {
		case 1: System.out.println("Done, on it goes! \n");
		setShip();
		case 2: setShipThirdPart();
		case 3: setShipThirdPart();
		case 4: setShipThirdPart();
		default:
			System.out.println("Something went wrong!");
		}
	}
	public static void checkSizeAfterThirdPart () {
		switch (choice) {
		case 2: System.out.println("Done, on it goes! \n");
		setShip();
		case 3: setShipFourthPart();
		case 4: setShipFourthPart();
		default:
			System.out.println("Something went wrong!");
		}
	}
	public static void checkSizeAfterFourthPart () {
		switch (choice) {
		case 3: System.out.println("Done, on it goes! \n");
		setShip();
		case 4: setShipFifthPart();
		default:
			System.out.println("Something went wrong!");
		}
	}
	*/

	public static boolean checkIfInLine (int x, int y, int checkThis) {
		return (x == checkThis || y == checkThis);
	}

	
	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	public static boolean checkIfLineIsFreeAndLengthCorrect (int xfirst, int yfirst, int xlast, int ylast, int length) {
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
	
	

	//-------------------------------------------------------------


	// SHIP SETTER 2
	//--------------------------------------------------------------

	public static void setShipOnBoard(int xfirst, int yfirst, int xlast, int ylast) {
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
	
	public static void setShip () {
		if (checkIfAnyShipLeft()) {
			Scanner scan = new Scanner (System.in);
			System.out.println("What kind of Ship do you want to put? Type: \n"
					+ "1 for a small Ship (" + countSmall + " left to put) \n"
					+ "2 for a middle Ship (" + countMiddle + " left to put) \n"
					+ "3 for a big Ship (" + countBig + " left to put) \n"
					+ "4 for your Destroyer (" + countDestroyer + " left to put) \n");

			//wahl des benutzers
			choice = Integer.parseInt(scan.next());
			// Oder choice = scan.nextInt(); ???

			//scan.close();

			if (checkChoiceNr(choice) && checkIfShipAvailable(choice)) {
				// Anzahl der verfügbaren schiffe des typs verringern und ersten Teil des Schiffs setzen!

				reduceShipCount(choice);
				System.out.println("Set the X-Coordinate of the first Part of the Ship!\n");
				setShipParts(choice+1); //choice + 1, weil dieser übergebene Parameter die Länge des Schiffes sein soll,
			} else {                    //mit der in der Methode gearbeitet wird. Diese ist bei Small Ship z.B. 1+1=2 - Clemens F.
				System.out.println("Invalid Input: We will start over again.\n\n");
				setShip();
			}
		} else {
			System.out.println("All ships are put in the game. LET'S GO MARIO! \n");

			// wenn es keine schiffe mehr zu setzen gibt, wird abgebrochen
			shipsLeftToPut = false;
		}
	}

	public static void setShipParts(int i) {

		Scanner scan = new Scanner(System.in);
		int xfirst, yfirst;

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
						scan.close();
						setShipOnBoard(xfirst, yfirst, x, y);
					} else {
						System.out.println("Invalid Input, we will start over again.\n");
						setShipParts(i);
					}
				} else {
					System.out.println("Invalid Input, we will start over again.\n");
					setShipParts(i);
				}
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				setShipParts(i);
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipParts(i);
		}
	}

	//SHIP SETTER
	//-----------------------------------------------------------------

	/*
	//main methode
	public static void setShip () {
		if (checkIfAnyShipLeft()) {
			Scanner scan = new Scanner (System.in);
			System.out.println("What kind of Ship do you want to put? Type: \n"
					+ "1 for a small Ship (" + countSmall + " left to put) \n"
					+ "2 for a middle Ship (" + countMiddle + " left to put) \n"
					+ "3 for a big Ship (" + countBig + " left to put) \n"
					+ "4 for your Destroyer (" + countDestroyer + " left to put) \n");
			//wahl des benutzers
			choice = Integer.parseInt(scan.next());
			scan.close();
			if (checkChoiceNr(choice) && checkIfShipAvailable(choice)) {
				// Anzahl der verfügbaren schiffe des typs verringern und ersten Teil des Schiffs setzen!
				reduceShipCount(choice);
				setShipFirstPart();
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
	
	// Methode zum Setzen des ersten Teils der Schiffe
	public static void setShipFirstPart () {
		Scanner scan = new Scanner(System.in);
		System.out.println("Put the first part of the ship in place!\n"
				+ "Type in the X-coordinate and press Enter: \n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);
		//checkt, ob eingegebenes Zeichen eine Zahl zwischen 1 und 10 ist 
		//und ob auf der X-koordinate überhaupt eine Y-kooridnate frei ist.
		//wenn nicht, fangen wir nochmal von vorne an.
		if (checkNumber(xcoord) && checkXcord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycoord = scan.next();
			int y = Integer.parseInt(ycoord);
			//setzt die Position im Array auf 1
			if (checkNumber(ycoord) && checkFree(x, y)) {
				Brett.brett[x][y] = 1;
				scan.close();
				setShipSecondPart();
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				scan.close();
				setShipFirstPart();
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipFirstPart ();
		}
	}
	//Methode zum Setzen des zweiten Teils des Schiffs
	public static void setShipSecondPart () {
		Scanner scan = new Scanner (System.in);
		System.out.println("Put the second Part of the Ship in place!\n"
				+ "Type in the X-coordinate and press Enter! \n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);
		scan.close();
		//checkt, ob eingegebenes Zeichen eine Zahl zwischen 1 und 10 ist 
		//und ob auf der X-koordinate überhaupt eine Y-kooridnate frei ist.
		//wenn nicht, fangen wir nochmal von vorne an.
		if (checkNumber(xcoord) && checkXcord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycord = scan.next();
			int y = Integer.parseInt(ycord);
			//setzt die Position im Array auf 1
			if (checkNumber(ycord) && checkFree(x, y)) {
				Brett.brett[x][y] = 1;
				checkSizeAfterSecondPart();
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				setShipSecondPart();
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipSecondPart ();
		}
	}
	public static void setShipThirdPart () {
		Scanner scan = new Scanner (System.in);
		System.out.println("Put the third Part of the Ship in place!\n"
				+ "Type in the X-coordinate and press Enter! \n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);
		//checkt, ob eingegebenes Zeichen eine Zahl zwischen 1 und 10 ist 
		//und ob auf der X-koordinate überhaupt eine Y-kooridnate frei ist.
		//wenn nicht, fangen wir nochmal von vorne an.
		if (checkNumber(xcoord) && checkXcord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycord = scan.next();
			int y = Integer.parseInt(ycord);
			//setzt die Position im Array auf 1
			if (checkNumber(ycord) && checkFree(x, y)) {
				Brett.brett[x][y] = 1;
				scan.close();
				checkSizeAfterThirdPart();
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				scan.close();
				setShipThirdPart();
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipThirdPart ();
		}
	}
	public static void setShipFourthPart () {
		Scanner scan = new Scanner (System.in);
		System.out.println("Put the fourth Part of the Ship in place!\n"
				+ "Type in the X-coordinate and press Enter! \n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);
		//checkt, ob eingegebenes Zeichen eine Zahl zwischen 1 und 10 ist 
		//und ob auf der X-koordinate überhaupt eine Y-kooridnate frei ist.
		//wenn nicht, fangen wir nochmal von vorne an.
		if (checkNumber(xcoord) && checkXcord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycord = scan.next();
			int y = Integer.parseInt(ycord);
			//setzt die Position im Array auf 1
			if (checkNumber(ycord) && checkFree(x, y)) {
				Brett.brett[x][y] = 1;
				scan.close();
				checkSizeAfterFourthPart();
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				scan.close();
				setShipFourthPart();
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipFourthPart ();
		}
	}
	public static void setShipFifthPart () {
		Scanner scan = new Scanner (System.in);
		System.out.println("Put the second Part of the Ship in place!\n"
				+ "Type in the X-coordinate and press Enter! \n");
		String xcoord = scan.next();
		int x = Integer.parseInt(xcoord);
		//checkt, ob eingegebenes Zeichen eine Zahl zwischen 1 und 10 ist 
		//und ob auf der X-koordinate überhaupt eine Y-kooridnate frei ist.
		//wenn nicht, fangen wir nochmal von vorne an.
		if (checkNumber(xcoord) && checkXcord(x)) {
			System.out.println("Now type in the Y-Coordinate and press Enter!\n");
			String ycord = scan.next();
			int y = Integer.parseInt(ycord);
			//setzt die Position im Array auf 1
			if (checkNumber(ycord) && checkFree(x, y)) {
				Brett.brett[x][y] = 1;
				scan.close();
				// hier gehts nicht weiter, da 5 die maximale Laenge ist
			} else {
				System.out.println("Invalid Input, we will start over again.\n");
				scan.close();
				setShipFifthPart();
			}
		} else {
			System.out.println("Invalid Input, we will start over again.\n");
			setShipFifthPart ();
		}
	}
	
	*/

	public static void fire(int x, int y) {
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

	public static void destroy(int x, int y) {
		Brett.brett[x][y] = 0;
	}
}

