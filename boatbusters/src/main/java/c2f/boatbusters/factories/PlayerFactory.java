package c2f.boatbusters.factories;

import c2f.boatbusters.classes.Player;

public class PlayerFactory {


	public Player createPlayer() {
			return new Player();  //return new Player(name); mit Highscore Konstruktor würde Name abgefragt
	}
}
