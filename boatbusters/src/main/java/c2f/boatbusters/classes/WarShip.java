package c2f.boatbusters.classes;

import c2f.boatbusters.abstractClasses.*;
import c2f.boatbusters.interfaces.IPlayer;
import c2f.boatbusters.interfaces.WarShipInterface;
import c2f.boatbusters.classes.*;
import c2f.boatbusters.factories.*;
import java.util.Scanner;

import org.apache.logging.log4j.core.Logger;

public class WarShip extends Ship {

	private int shipSize;


	public WarShip (int i) {

		if (i == getShortShipLength()) {
			this.shipSize = i;
		} else if (i == getMiddleShipLength()) {
			this.shipSize = i;
		} else if (i == getBigShipLength()) {
			this.shipSize = i;
		} else {
			// error log, groesse falsch
		}
	}
	
	// Alle Felder 0 setzen (falls wir das brauchen)
	public void setShipsBack (Board board1[][], Board board2[][]) {
		for (int i = 1; i <= Board.fieldSizeX; i++) {
			for (int x = 1; x <= Board.fieldSizeY; x++) {
				board1[i][x] = null;
				board2[i][x] = null;
			}
		}
	}

//Fire Methode ist jetzt in KlassePlayer, weil es so mit den shootern nicht funktioniert hat
	
//	@Override
//	public void fire(int x, int y, Player player, WarShip shooter, Game game) {
//		// TODO wenn man nicht getroffen hat: Kreuz ins Feld zeichnen
//		if (player == game.getPlayer1()) {
//			if (!player.checkFree(x, y, game.getBoard2())) {
//				// TODO log System.out.println("HIT! \n");
//				Main.getLogger().info("HIT! \n");
//				player.increaseScore();
//				destroy(x, y, game.getBoard2());
//			} else {
//				// TODO log System.out.println("Missed! \n");
//				Main.getLogger().info("Missed! \n");
//				// Feld kennzeichnen!
//			}
//		} else if (player == game.getPlayer2()) {
//
//			if (!player.checkFree(x, y, game.getBoard1())) {
//				// TODO log System.out.println("HIT! \n");
//				Main.getLogger().info("HIT! \n");
//				player.increaseScore();
//				destroy(x, y, game.getBoard1());
//			} else {
//				// TODO log System.out.println("Missed! \n");
//				Main.getLogger().info("Missed! \n");
//				// Feld kennzeichnen!
//			}
//		}
//	}
	

//	private void destroy(int x, int y, WarShip[][] board) {
//		board[x][y] = null;
//	}
}
