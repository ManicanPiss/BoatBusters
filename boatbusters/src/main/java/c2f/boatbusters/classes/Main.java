package c2f.boatbusters.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main (String [] args) {
		launch(args);
		
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

	@Override
	
		public void start(Stage window) throws Exception {
			VBox root = new VBox();
			root.setAlignment(Pos.CENTER);
			
			
			Scene scene = new Scene(root, 300, 200, Color.DARKGRAY);
			
			Button btn1 = new Button("Spiel Starten");
			root.getChildren().add(btn1);
			btn1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("berechne krasse graphics..");
					System.out.println("Ihr PC kommt auf die krassen graphics nicht klar.. zurück zu menu");
					
				}
			});
			
			Button btn2 = new Button("Highscore");
			root.getChildren().add(btn2);
			btn2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("open highscore");
					
				}
			});
			
			Button btn3 = new Button("Beenden");
			root.getChildren().add(btn3);
			
			btn3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("Beenden");
					
				}
			});
			
			window.setTitle("Boatbusters");
			window.setScene(scene);
			window.show();
		
	}
	
	
}
