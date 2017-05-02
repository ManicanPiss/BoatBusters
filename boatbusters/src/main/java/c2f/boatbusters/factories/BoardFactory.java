package c2f.boatbusters.factories;

import c2f.boatbusters.classes.*;


public class BoardFactory {

	public Board[][] createBoard(int i) {
		if (i == 1) {
			Board board1[][] = new Board[Board.fieldSizeX][Board.fieldSizeY];
			return board1;
		}else if (i == 2) {
			Board board2[][] = new Board[Board.fieldSizeX][Board.fieldSizeY];
			return board2;
		}

		return null;
	}
}