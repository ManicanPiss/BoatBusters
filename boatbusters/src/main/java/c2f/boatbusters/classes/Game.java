package c2f.boatbusters.classes;

import java.util.Scanner;

public class Game {

	Scanner scan = new Scanner(System.in);

	private int round;

	public Game () {
		this.round = 0;
	}

	public int getRound() {
		return round;
	}

	public void increaseRound() {
		round += 1;
	}

	public static void startFiring() {

		game.increaseRound();

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

	public static void startGame () {

		Game.increaseRound();
		
		/*
		//Brett [][] boardPlayer1 = createBoard(fieldSizeX, fieldSizeY);
		//Brett [][] boardPlayer2 = createBoard(fieldSizeX, fieldSizeY);

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

		 */
	}

	void showMenu() {
		System.out.println("Hauptmenu:\n\n Optionen:\n 1. Spiel Starten \n 2. Highscore anzeigen\n 3. Beenden\n\n"
				+ "You can always go back to this menu by entering 'm'!");
		int i = Integer.parseInt(scan.next());

		switch (i) {
		case 1: startGame();
		// case 2: showHighscore();
		case 3: quit();

		default:
			System.out.println("Invalid input! Back to Main Menu");
			showMenu();
		} 
	}

	public static void quit() {
		System.exit(0);
	}
}
