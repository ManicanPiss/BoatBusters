package c2f.boatbusters.classes;

import c2f.boatbusters.factories.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Game {

	static Scanner scan = new Scanner(System.in);

	private int round;

	public Game (int i) {
		this.round = i;
	}

	public int getRound() {
		return round;
	}

	public void increaseRound() {
		round += 1;
	}

	public void startFiring() {

		increaseRound();

		Scanner scan = new Scanner (System.in);
        try{
		if (round % 2 == 0) {
			System.out.println("Type in the field you want to shoot at: \n"
					+ "X-Coordinate: \n");

			int x = Integer.parseInt(scan.next());
			

			System.out.println("Y-Coordinate: \n");

			int y = Integer.parseInt(scan.next());

			
			//scan.close(); Scanner schließen fliegt uns um die Ohren

			// Schiffe.fire(x, y);
			} else {
			System.out.println("Type in the field you want to shoot at: \n"
					+ "X-Coordinate: \n");

			int x = Integer.parseInt(scan.next());
			

			System.out.println("Y-Coordinate: \n");

			int y = Integer.parseInt(scan.next());
			}
			
            } catch (NumberFormatException e){
				  System.err.printf("Type in the field you want to shoot at: \n"
							+ "X-Coordinate: \n"); startFiring(); //Problem, wenn Invalid entry (z.B.Buchstaben,
							                        // die nicht zu Zahl konvertiert werden können), und Methode also
				  		                            // neu gestartet werden muss, wird Round erneut increased..
				  		                            
			}
			
			//scan.close(); Scanner schließen fliegt uns um die Ohren

			// Schiffe.fire(x, y);
		
	}

	public void startGame () {

		PlayerFactory factP = new PlayerFactory();
		
		System.out.println("Please type in the name of player 1: \n");
		String name1 = scan.next();
		System.out.println("And now please type in the name of player 2: \n");
		String name2 = scan.next();
		
		Player player1 = factP.createPlayer(name1);
		Player player2 = factP.createPlayer(name2);
		
		
		Highscore.putPlayersInHighscoreList(name1, name2); //Siehe Klasse Highscore

		
		BoardFactory factB = new BoardFactory();
		
		WarShip board1[][] = factB.createBoard(1);
		WarShip board2[][] = factB.createBoard(2);
		
		
		
		

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

	public void showMenu() {
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

	public void quit() {
		System.exit(0);
	}
}
