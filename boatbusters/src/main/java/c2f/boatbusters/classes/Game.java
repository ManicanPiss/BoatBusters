package c2f.boatbusters.classes;

import c2f.boatbusters.abstractClasses.Board;
import c2f.boatbusters.factories.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {

	public Player player1;
	public Player player2;
	public WarShip[][] board1;
	public WarShip[][] board2;
	PlayerFactory pf;
	public Lock lock = new ReentrantLock();

	private int round = 1;

	public Game(int i) {
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

	public void setPlayer1(Player player) {
		this.player1 = player;
	}

	public void setPlayer2(Player player) {
		this.player2 = player;
	}

	public WarShip[][] getBoard1() {
		return board1;
	}

	public WarShip[][] getBoard2() {
		return board2;
	}



	public void startGame(Game game, String namePlayer1, String namePlayer2) {

		// Erstellung der Factories um Spieler und Bretter zu erstellen
		PlayerFactory pf = new PlayerFactory();

		player1 = pf.createPlayer(namePlayer1, Main.getHighscore().checkIfArrayListContainsName(namePlayer1));
		player2 = pf.createPlayer(namePlayer2, Main.getHighscore().checkIfArrayListContainsName(namePlayer2));

		setPlayer1(player1);
		setPlayer2(player2);

		if (Main.getHighscore().checkIfArrayListContainsName(namePlayer1) == 0) {
			Main.getHighscore().getBestenliste().add(player1);
		}
		if (Main.getHighscore().checkIfArrayListContainsName(namePlayer2) == 0) {
			Main.getHighscore().getBestenliste().add(player2);
		}

		Main.getHighscore().sortBestenlisteStream();

		BoardFactory bf = new BoardFactory();

		board1 = bf.createBoard(1);
		board2 = bf.createBoard(2);

		setShipsBack(board1, board2);
	}
	

	
	public void quit(Highscore highscore) {
		try {
			FileWriter fWriter = new FileWriter("bestenliste.csv");
			BufferedWriter writer = new BufferedWriter(fWriter);
			for (int i = 0; i < highscore.getBestenliste().size(); i++) {
				writer.write(highscore.getBestenliste().get(i).file());														
			}
			writer.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.exit(0);
	}

	private void setShipsBack(WarShip board1[][], WarShip board2[][]) {
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board1[i][x] = null;
				board2[i][x] = null;
			}
		}
	}

	// Felder zurücksetzen für die Spieler, falls sie sich entscheiden, ihre
	// Schiffe nochmal neu zu setzen
	public void setShipsBackBoard1(WarShip board1[][]) {
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board1[i][x] = null;
			}
		}
		player1.setCountSmall(2);
		player1.setCountMiddle(3);
		player1.setCountBig(2);
	}

	public void setShipsBackBoard2(WarShip board2[][]) {
		for (int i = 0; i < Board.fieldSizeX; i++) {
			for (int x = 0; x < Board.fieldSizeY; x++) {
				board2[i][x] = null;
			}
		}
		player2.setCountSmall(2);
		player2.setCountMiddle(3);
		player2.setCountBig(2);
	}

	public String textWhosNext() {
		String player1 = "Player 1";
		String player2 = "Player 2";
		int round = getRound();
		if (round % 2 == 0) {
			return player1;
		} // auf Player 1 wird geschossen
		else {
			return player2;
		} // auf Player 2 wird geschossen
	}

	public int intWhosNext() {
		int round = getRound();
		if (round % 2 == 0) {
			return 2;
		} // Player 2 ist dran
		else {
			return 1;
		} // Player 1 ist dran
	}
	

	// Boolean Methode, die prüft, ob ein Spieler das Spiel gewonnen hat,
	// und einen entsprechenden boolean zurückgibt
	public boolean gameOver() {
		if (player1.checkIfPlayerWins() || player2.checkIfPlayerWins()) {
			return true;
		}
		return false;
	}

}
