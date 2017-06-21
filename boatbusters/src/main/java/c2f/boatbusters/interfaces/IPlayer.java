package c2f.boatbusters.interfaces;

import c2f.boatbusters.classes.Player;
import c2f.boatbusters.classes.WarShip;

public interface IPlayer {

	int smallCount = 4;
	int middleCount = 3;
	int bigCount = 2;
	int maxScore = 25;

	int getScore();

	void increaseScore();

	int getCountSmall();

	void decreaseCountSmall();

	int getCountMiddle();

	void decreaseCountMiddle();

	int getCountBig();

	void decreaseCountBig();

	int getShipsCount();

	// Aktualisiert den boolean jedes Mal aufs Neue
	boolean areShipsLeftToPut();

	int getChoice();

	void setChoice(int choice);

	// Checkt ob die Schiffswahlzahl passt (muss zwischen 1 und 3 sein) 
	boolean checkChoiceNr(int choice);

	// schiffsauswahl korrekt?
	boolean checkIfValidInput(String str);

	// noch ein schiff diesen Typs beim Spieler uebrig?
	boolean checkIfShipAvailable(int i, IPlayer player);

	// in der X-Koordinate ueberhaupt was frei?
	boolean checkXcoord(int i, WarShip[][] board);

	boolean checkNumber(String s);

	boolean checkValidNumber(int i);

	// wenn man ein schiff setzt, wird die anzahl der verfuegbaren schiffe diesen typs um 1 verringert
	void reduceShipCount(int i, IPlayer player);

	boolean checkIfInLine(int x1, int y1, int x2, int y2);

	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	boolean checkIfLineIsFreeAndLengthCorrect(int xfirst, int yfirst, int xlast, int ylast, int length,
			WarShip[][] board);

	Object getName();

	Object numberOfWins();

	//Ist in Einklang mit der printBestenliste Mehtode, es entsteht eine Tabelle im richtigen Format abhängig von der 
	//Länge des Spielernamens
	String toString();

	boolean areShipsLeftToPut(IPlayer player);

	void reduceShipCount(int i, Player player);


	int getShipCountCheck();

}