package c2f.boatbusters.classes;

import c2f.boatbusters.factories.*; 
// import c2f.boatbusters.interfaces.WarShipInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;



public class Game {

	private int round = 1;

	public Game (int i) {
		this.round = i;
	}

	public int getRound() {
		return round;
	}

	public void increaseRound() {
		round += 1;
	}

	public boolean checkNumber (String s) {

		int i = 0;

		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Invalid Input.\n"); //TODO log, nicht zu parsen
		}

		if (i > 0 && i <= Board.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	public void startFiring(Player player, WarShip[][] board, WarShip shooter, Game game) {

		System.out.println("Type in the field you want to shoot at: \n"
				+ "X-Coordinate: \n");
		Scanner scan = new Scanner(System.in);
		
		String input = scan.next();

		//Wenn der Spieler abbrechen moechte
		if (input.equals("m")) {
			game.showMenu(game);
		}

		if (checkNumber(input)) {
			int x = Integer.parseInt(input);
			System.out.println("Now the Y-Coordinate: \n");
			input = scan.next();

			//Wenn der Spieler abbrechen moechte
			if (input.equals("m")) {
				game.showMenu(game);
			}

			if (checkNumber(input)) {
				int y = Integer.parseInt(input);
				increaseRound();
				shooter.fire(x, y, board, player, shooter, game);
			} else {
				System.out.println("invalid input."); // TODO LOG!
				startFiring(player, board, shooter, game);
			}
		} else {
			System.out.println("invalid input."); // TODO LOG!
			startFiring(player, board, shooter, game);
		}
	}

	public void startGame (Game game, Scanner scan) {


		//Erstellung der Factories um Spieler und Bretter zu erstellen

		PlayerFactory pf = new PlayerFactory();
		
		//Abfrage der Namen der Spieler
		System.out.println("Player 1, please type in your name: ");
		String namePlayer1 = scan.next();
		System.out.println("Now type in your number of wins: ");
		String numberOfWinsPlayer1 = scan.next();
		System.out.println("Player 2, please type in your name: ");
		String namePlayer2 = scan.next();
		System.out.println("Now type in your number of wins: ");
		String numberOfWinsPlayer2 = scan.next();
		
		
		Player player1 = pf.createPlayer(namePlayer1, numberOfWinsPlayer1);
		Player player2 = pf.createPlayer(namePlayer2, numberOfWinsPlayer2);
		
		Highscore.bestenliste.add(player1);
		Highscore.bestenliste.add(player2);
		
		Highscore.sortArrayList();

		BoardFactory bf = new BoardFactory();

		WarShip board1[][] = bf.createBoard(1);
		WarShip board2[][] = bf.createBoard(2);

		// ShipFactory sf = new ShipFactory();

		//"Shooter" werden benoetigt, um auf die methoden der WarShipKlasse zugreifen zu koennen, ohne sie static zu machen

		WarShip shooterPlayer1 = new WarShip(1);
		WarShip shooterPlayer2 = new WarShip(1);


		PlayerFactory factP = new PlayerFactory();
		
		
		

		// 
		setShipsBack (board1, board2);
		
		player1.setShip(player1, board1, game, scan);
		startFiring(player2, board1, shooterPlayer2, game);
		
		//Setzen der Schiffe solange noch Schiffe zu setzen sind, Spieler fuer Spieler
		
		while (player1.areShipsLeftToPut()) {
			player1.setShip(player1, board1, game, scan);
		}

		while (player2.areShipsLeftToPut()) {
			player2.setShip(player2, board2, game, scan);
		}

		System.out.println("Let's bust some boats! \n"); //TODO log

		//Solange kein Spieler gewonnen hat, wird weiter gespielt

		while (player1.getScore() < Player.maxScore && player2.getScore() < Player.maxScore) {
			startFiring(player1, board2, shooterPlayer1, game);
			startFiring(player2, board1, shooterPlayer2, game);
		}
	}

	public void showMenu(Game game) {
		System.out.println("Hauptmenu:\n\n Optionen:\n 1. Spiel Starten \n 2. Highscore anzeigen\n 3. Beenden\n\n"
				+ "You can always go back to this menu by entering 'm'!");

		Scanner scan = new Scanner(System.in);
		
		String str = scan.next();

		switch (str) {
		case "1": startGame(game, scan);
		case "2": showHighscore(game);
		case "3": quit();
		case "m": showMenu(game);

		default :
			System.out.println("Invalid input! Back to Main Menu"); //TODO log
			showMenu(game);
		}
	} 


	public static void showHighscore (Game game) {
		// TODO Datenbank implementieren!
		System.out.println("Hallo! Ich werde mal eine Datenbank.\n");
        
		Highscore.printBestenliste();
		
		Scanner scan = new Scanner(System.in);
		
		String str = scan.next();

		//Wenn der Spieler abbrechen moechte
		if (str.equals("m")) {
			game.showMenu(game);
		}

		// TODO log System.out.println("You can always start over again with putting the ship in the game by entering 'b'! \n"
		//		+ "Set the X-Coordinate of the first Part of the Ship!\n");

	}

	public void quit() {
		try {
			FileWriter fWriter = new FileWriter("bestenliste.csv");
			BufferedWriter writer = new BufferedWriter(fWriter);
			for (int i = 0; i < Highscore.bestenliste.size(); i++) {
				writer.write(Highscore.bestenliste.get(i).file()); //1 Person -> 1 Zeile
			}
			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.exit(0);
	}
	
	
	public void setShipsBack (WarShip board1[][], WarShip board2[][]) {
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board1[i][x] = null;
				board2[i][x] = null;
			}
		}
	}
}
