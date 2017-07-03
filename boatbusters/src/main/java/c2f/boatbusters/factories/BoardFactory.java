package c2f.boatbusters.factories;

import c2f.boatbusters.abstractClasses.Board;
import c2f.boatbusters.classes.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardFactory {

	public WarShip[][] createBoard(int i) {

		if (i == 1) {
			WarShip board1[][] = new WarShip[Board.fieldSizeX][Board.fieldSizeY];

			return board1;

		} else if (i == 2) {
			WarShip board2[][] = new WarShip[Board.fieldSizeX][Board.fieldSizeY];
			return board2;
		}

		return null;
	}
}