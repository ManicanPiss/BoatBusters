package c2f.boatbusters.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	public static void main (String [] args) {
		
		
		//File dataFile = new File("daten.csv"); // Eingelesene Datei
		//try (Scanner reader = new Scanner(dataFile).useDelimiter("\n")){ 
	
		//	while (reader.hasNext()) { // Einlesen der Personen
		//		String zwischenspeicher; // Erstellt Array  (Zwischenspeicher)
		//		zwischenspeicher = reader.next();
				// Erstelle String und füge sie der HashList hinzu
		//		Highscore.getBestenliste().add(zwischenspeicher); 
			
		//	}
		//} catch (FileNotFoundException e) {
		//	e.printStackTrace();
		//}
		
		
		
		
		Game game = new Game(0);
		game.showMenu();
		
		System.out.println(game.getRound());
		
		
	}
}
