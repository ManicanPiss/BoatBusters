package c2f.boatbusters.factories;

import c2f.boatbusters.classes.*;


public class BoardFactory {

	public void createBoard(int i) {
		if (i == 1) {
			Board board1[][] = new Board[Board.fieldSizeX][Board.fieldSizeY];
		} else if (i == 2) {
			Board board2[][] = new Board[Board.fieldSizeX][Board.fieldSizeY];
		} else {
			// TODO error log here
		
		}
	}
}
