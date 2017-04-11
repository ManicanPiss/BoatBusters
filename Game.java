package Klassen.Artifakt;

import java.util.Scanner;

public class Game {

	public static int[][] first;
	public static int [][] last;
	public static int round = 0;
	public static int choice;
	public static int countSmall = 4;
	public static int countMiddle = 3;
	public static int countBig = 2;
	public static int countDestroyer = 1;
	public static int shipsCount = countSmall + countMiddle + countBig + countDestroyer; // Anzahl schiffe
	/*public static final int smallValue = 1;
	public static final int midlleValue = 2;
	public static final int bigValue = 3;
	public static final int destroyerValue = 4;*/
	public static final int shipsValue = 1;
	public static final int fieldSizeX = 10;
	public static final int fieldSizeY = 10;
	public static boolean shipsLeftToPut = true;
	
	public static int scorePlayer1 = countSmall * 2 + countMiddle * 3 + countBig * 4 + countDestroyer * 5; //summe der anzahl der von den Schiffen beanspruchten felder
	public static int scorePlayer2 = scorePlayer1; //hiervon wird pro versenktem Schiffsteil 1 abgezogen -> niederlage wenn = 0
	
	
	public static void showHighscore () {
		// TODO Datenbank implementieren!
	}
	
	public static void showMenu () {
		// TODO huebsches Menu einbauen!
		Scanner scan = new Scanner (System.in);
		System.out.println("Hauptmenu:\n\n Optionen:\n 1. Spiel Starten \n 2. Highscore anzeigen\n 3. Beenden");
		int i = Integer.parseInt(scan.next());
		// scan.close();
		
		switch (i) {
		case 1: startGame();
		case 2: showHighscore();
		case 3: quit();
		
		default:
			System.out.println("Invalid input! Back to Main Menu");
			showMenu();
		}
	}
	
	public static void startGame () {
		// TODO 
		// solange noch schiffe übrig sind, muss eingegeben werden
		while (shipsLeftToPut) {
			Schiffe.setShip();
		}
		
		System.out.println("Let's bust some boats! \n");
				
		while (scorePlayer1 != 0 && scorePlayer2 != 0) {
			startFiring();
		}
		
		if (scorePlayer1 == 0) {
			System.out.println("Congratulations Player 2, you won! \n");
		} else if (scorePlayer2 == 0) {
			System.out.println("Congratulations Player 1, you won! \n");
		}
	}
	
	public static void startFiring() {
		
		round++;
		
		Scanner scan = new Scanner (System.in);
		
		if (round % 2 == 0) {
			System.out.println("Type in the field you want to shoot at: \n"
					+ "X-Coordinate: \n");
			int x = Integer.parseInt(scan.next());
			
			System.out.println("Y-Coordinate: \n");
			int y = Integer.parseInt(scan.next());
			scan.close();
			
			Schiffe.fire(x, y);
		} else {
			System.out.println("Type in the field you want to shoot at: \n"
					+ "X-Coordinate: \n");
			int x = Integer.parseInt(scan.next());
			
			System.out.println("Y-Coordinate: \n");
			int y = Integer.parseInt(scan.next());
			scan.close();
			
			Schiffe.fire(x, y);
		}
	}
	
	public static void quit() {
		System.exit(0);
	}
}























