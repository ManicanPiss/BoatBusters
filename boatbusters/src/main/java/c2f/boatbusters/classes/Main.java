package c2f.boatbusters.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main (String [] args) {
		
		File dataFile = new File("bestenliste.csv"); // Eingelesene Datei
		try (Scanner reader = new Scanner(dataFile).useDelimiter("\n")){ 
	
			while (reader.hasNext()) { // Einlesen der schon gespeicherten Spieler
				String[] dataArray = new String[2]; // Erstellt Array  (Zwischenspeicher)
				dataArray = reader.next().split(";", -1); // Teilen am ';'
				// Erstelle Spieler und füge sie der Liste hinzu
				Highscore.bestenliste.add(new Player(dataArray[0], dataArray[1])); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Game game = new Game(0);
		game.showMenu(game);
		
	}
}
