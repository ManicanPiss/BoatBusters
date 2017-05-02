package c2f.boatbusters.interfaces;

import c2f.boatbusters.classes.Board;
import c2f.boatbusters.classes.Game;
import c2f.boatbusters.classes.Player;

public interface WarShipInterface {

	void fire(int x, int y, Board board[][], Player player);
}
