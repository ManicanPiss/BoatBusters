package Klassen.Artifakt;

public class Schiffe extends Game {

	public static void setShips() {
		// TODO Objekte im 2-dimensionalen Array erstellen
		// Spieler für Spieler, mit Moeglichkeit, immer zurückzusetzen, bevor man nicht bestätigt hat.
		// DANN startFiring()
		
		startFiring();
	}
	
	public void fire() {
		// TODO checken, ob getroffen, wenn ja: destroy(), wenn nicht: Kreuz ins Feld zeichnen
	}
			
	public void setShipsBack () {
		// TODO alle erstellten Objekte im Array löschen -> Referenzen löschen
	}
	
	public void destroy() {
		// TODO Referenz auf getroffenes Objekt löschen
	}
	
	
	
}
