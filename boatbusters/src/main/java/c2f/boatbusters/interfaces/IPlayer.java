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

	// noch ein schiff diesen Typs beim Spieler uebrig?
	boolean checkIfShipAvailable(int i, IPlayer player);


	// wenn man ein schiff setzt, wird die anzahl der verfuegbaren schiffe diesen typs um 1 verringert
	void reduceShipCount(int i, IPlayer player);


	// checkt, ob die Linie frei ist und ob die Distanz zwischen den beiden Punkte mit der Schifflänge übereinstimmen
	boolean checkIfLineIsFreeAndLengthCorrect(int xfirst, int yfirst, int xlast, int ylast, int length,
			WarShip[][] board);

	Object getName();

	Object numberOfWins();

	//Ist in Einklang mit der printBestenliste Mehtode, es entsteht eine Tabelle im richtigen Format abhängig von der 
	//Länge des Spielernamens
	String toString();


	void reduceShipCount(int i, Player player);


	int getShipCountCheck();

	boolean areShipsLeftToPut(Player player);



}