package Klassen.Artifakt;

public class Game extends Main {

	public void showHighscore () {
		// TODO Datenbank implementieren!
	}
	
	public static void showMenu () {
		// TODO huebsches Menu einbauen!
		System.out.println("Hauptmenu:\n\n Optionen:\n 1. Spiel Starten \n 2. Highscore anzeigen\n 3. Beenden");
	}
	
	public void startGame () {
		// TODO
		Schiffe.setShips();
	}
	
	public static void startFiring() {
		// TODO anfangen zu feuern
	}
	
	public void quit() {
		// TODO 
		System.exit(0);
	}
	
}
