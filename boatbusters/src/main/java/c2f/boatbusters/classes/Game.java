package c2f.boatbusters.classes;

import c2f.boatbusters.abstractClasses.Board;
import c2f.boatbusters.factories.*;
import c2f.boatbusters.interfaces.IPlayer;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
// import c2f.boatbusters.interfaces.WarShipInterface;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.util.Loader;

public class Game {
	
	public Player player1;
	public Player player2;
	public WarShip[][] board1;
	public WarShip[][] board2;
	Highscore highscore;
	
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
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public void setPlayer1(Player player){
		this.player1 = player;
	}
	
	public void setPlayer2(Player player){
		this.player2 = player;
	}
	
	public WarShip[][] getBoard1(){
		return board1;
	}
	
	public WarShip[][] getBoard2(){	
		return board2;
	}
	
	public void setBoard1(int x, int y){
		this.board1 = new WarShip[x][y];
	}
	
	public void setBoard2(int x, int y){
		this.board2 = new WarShip[x][y];
	}
	
	

	private boolean checkNumber (String s) {

		int i = 0;

		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			Main.getLogger().error("Invalid Input. Please type in a number.\n", e); //TODO log, nicht zu parsen
		}

		if (i > 0 && i <= Board.fieldSizeY) {
			return true;
		} else {
			return false;
		}
	}

	protected void startFiring(IPlayer player, WarShip[][] board, WarShip shooter, Game game) {

		Main.getLogger().info("Type in the field you want to shoot at: \n" + "X-Coordinate: \n");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		String input = scan.next();

		//Wenn der Spieler abbrechen moechte
		if (input.equals("m")) {
			game.showMenu(game);
		}

		if (checkNumber(input)) {
			int x = Integer.parseInt(input);
			Main.getLogger().info("Now the Y-Coordinate: \n");
			input = scan.next();

//			if (input.equals("m")) {
//				game.showMenu(game);
//			}

			if (checkNumber(input)) {
				int y = Integer.parseInt(input);
				increaseRound();
				//shooter.fire(x, y, board, player, shooter, game);
			} else {
				Main.getLogger().info("invalid input."); // TODO LOG!
				startFiring(player, board, shooter, game);
			}
		} else {
			Main.getLogger().info("invalid input."); // TODO LOG!
			startFiring(player, board, shooter, game);
		}
	}
	
	
	
	

//	public void startGameOld (Game game, String namePlayer1, String namePlayer2, Scanner scan) {
//
//
//		//Erstellung der Factories um Spieler und Bretter zu erstellen
//		PlayerFactory pf = new PlayerFactory();
//
//		
//		player1 = pf.createPlayer(namePlayer1, Highscore.checkIfArrayListContainsName(namePlayer1));
//		player2 = pf.createPlayer(namePlayer2, Highscore.checkIfArrayListContainsName(namePlayer2));
//		
//
//        if (Highscore.checkIfArrayListContainsName(namePlayer1).equals("0")){
//		Highscore.getBestenliste().add(player1);
//		}
//        if (Highscore.checkIfArrayListContainsName(namePlayer2).equals("0")){
//		Highscore.getBestenliste().add(player2);
//        }
//		
//
//		Highscore.sortArrayList();
//		
//
//		BoardFactory bf = new BoardFactory();
//
//		board1 = bf.createBoard(1);
//		board2 = bf.createBoard(2);
//
//		
//		// "Shooter" werden benoetigt, um auf die Methoden der WarShipKlasse zugreifen zu koennen, ohne diese static zu machen
//
//		WarShip shooterPlayer1 = new WarShip(1);
//		WarShip shooterPlayer2 = new WarShip(1);
//
//		setShipsBack (board1, board2);
//		
//		player1.setShip(player1, board1, game, scan);
//		startFiring(player2, board1, shooterPlayer2, game);
//
////		 Setzen der Schiffe solange noch Schiffe zu setzen sind, Spieler fuer Spieler
//		while (player1.areShipsLeftToPut()) {
//			player1.setShip(player1, board1, game, scan);
//		}
//
//		while (player2.areShipsLeftToPut()) {
//			player2.setShip(player2, board2, game, scan);
//		}
//
//		Main.getLogger().info("Let's bust some boats! \n"); //TODO log
//
//		//Solange kein Spieler gewonnen hat, wird weiter gespielt; Runden werden innerhalb der startFiring() Methode gezaehlt
//		while (player1.getScore() < IPlayer.maxScore && player2.getScore() < IPlayer.maxScore) {
//			startFiring(player1, board2, shooterPlayer1, game);
//			startFiring(player2, board1, shooterPlayer2, game);
//		}
//	}
//	
//	
	public void startGame (Game game, String namePlayer1, String namePlayer2) {

		
		//Erstellung der Factories um Spieler und Bretter zu erstellen
		PlayerFactory pf = new PlayerFactory();

		
		player1 = pf.createPlayer(namePlayer1, Highscore.checkIfArrayListContainsName(namePlayer1));
		player2 = pf.createPlayer(namePlayer2, Highscore.checkIfArrayListContainsName(namePlayer2));
		
		
		setPlayer1(player1);
		setPlayer2(player2);
		

        if (Highscore.checkIfArrayListContainsName(namePlayer1).equals("0")){
		Highscore.getBestenliste().add(player1);
		}
        if (Highscore.checkIfArrayListContainsName(namePlayer2).equals("0")){
        Highscore.getBestenliste().add(player2);
        }
		

		Highscore.sortArrayList();
		

		BoardFactory bf = new BoardFactory();
		ShipFactory sf = new ShipFactory();

		board1 = bf.createBoard(1);
		board2 = bf.createBoard(2);

		setShipsBack (board1, board2);
		
		Main.getLogger().info("startGame Methode erfolgreich ausgeführt");

		//Solange kein Spieler gewonnen hat, wird weiter gespielt; Runden werden innerhalb der startFiring() Methode gezaehlt
		//while (player1.getScore() < IPlayer.maxScore && player2.getScore() < IPlayer.maxScore) {
			//startFiring(player1, board2, shooterPlayer1, game);
			//startFiring(player2, board1, shooterPlayer2, game);
		}
	

	protected void showMenu(Game game) {
		Main.getLogger().info("Hauptmenu:\n\n Optionen:\n 1. Spiel Starten \n 2. Highscore anzeigen\n 3. Beenden\n\n"
				+ "You can always go back to this menu by entering 'm'!");

		Scanner scan = new Scanner(System.in);

		String str = scan.next();

		switch (str) {
		//case "1": startGame(game, scan);
		case "2": Highscore.printBestenliste();
		case "3": scan.close();
			quit();
		case "m": showMenu(game);

		default :
			Main.getLogger().info("Invalid input! Back to Main Menu"); //TODO log
			showMenu(game);
		}
	} 

	//private static void showHighscore (Game game) {

		//highscore.printBestenliste();

//		@SuppressWarnings("resource")
//		Scanner scan = new Scanner(System.in);
//		String str = scan.next();
		

		// Wenn der Spieler ins Menu zurueck moechte
//		if (str.equals("m")) {
//			game.showMenu(game);
//		}

		// TODO log System.out.println("You can always start over again with putting the ship in the game by entering 'b'! \n"
		//		+ "Set the X-Coordinate of the first Part of the Ship!\n");
	//}

	public void quit() {
		try {
			FileWriter fWriter = new FileWriter("bestenliste.csv");
			BufferedWriter writer = new BufferedWriter(fWriter);
			for (int i = 0; i < Highscore.getBestenliste().size(); i++) {
				writer.write(Highscore.getBestenliste().get(i).file()); //1 Person -> 1 Zeile
			}
			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.exit(0);
	}

	private void setShipsBack (WarShip board1[][], WarShip board2[][]) {
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board1[i][x] = null;
				board2[i][x] = null;
			}
		}
	}
	
	//Felder zurücksetzen für die Spieler, falls sie sich entscheiden, ihre Schiffe nochmal neu zu setzen
	public void setShipsBackBoard1 (WarShip board1[][]){
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board1[i][x] = null;
			}
		}
		player1.setCountSmall(2);
		player1.setCountMiddle(3);
		player1.setCountBig(2);
	}
	
	public void setShipsBackBoard2 (WarShip board2[][]){
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board2[i][x] = null;
			}
		}
		player2.setCountSmall(2);
		player2.setCountMiddle(3);
		player2.setCountBig(2);
	}

}
