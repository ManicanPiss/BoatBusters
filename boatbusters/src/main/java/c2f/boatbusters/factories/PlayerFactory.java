package c2f.boatbusters.factories;

import c2f.boatbusters.classes.Player;

public class PlayerFactory {

	public void createPlayer(int i) {
		if (i == 1) {
			Player player1 = new Player();
		} else if (i == 0) {
			Player player2 = new Player();
		} else {
			// TODO error log
		}
	}
}
