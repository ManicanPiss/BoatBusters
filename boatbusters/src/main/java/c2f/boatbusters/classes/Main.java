package c2f.boatbusters.classes;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import c2f.boatbusters.gui.GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;



public class Main{
	
	static final Scanner scan = new Scanner(System.in);
	
	private final static Logger logger = LogManager.getRootLogger();
	
//	
//	public class GameField extends Parent{
//		HBox row1 = new HBox(1); // die 1 ist der abstand zwischen den cells
//		HBox row2 = new HBox(1);	
//		HBox row3 = new HBox(1);
//		HBox row4 = new HBox(1);
//		HBox row5 = new HBox(1);
//		HBox row6 = new HBox(1);
//		HBox row7 = new HBox(1);
//		HBox row8 = new HBox(1);
//		HBox row9 = new HBox(1);
//		HBox row10 = new HBox(1);
//		VBox field = new VBox(10);
////		VBox rows = new VBox(row1, row2, row3, row4, row5, row6, row7);
//		
//		
////		public GameField(boolean test){
//////			EventHandler<? super MouseEvent> handler
////			if(test == true){
////			for (int y = 0; y <2; y++){
////				HBox row = new HBox();
////				
////				for(int x = 0; x <2; x++){
////					CellButton c = new CellButton(x, y);
////					row.getChildren().add(c);
////			}
////			rows.getChildren().add(row);	
////		}
////		getChildren().add(rows);
////	}}
//		public GameField(){
//		CellButton a1 = new CellButton("A1");
//		CellButton a2 = new CellButton("A2");
//		CellButton a3 = new CellButton("A3");
//		CellButton a4 = new CellButton("A4");
//		CellButton a5 = new CellButton("A5");
//		CellButton a6 = new CellButton("A6");
//		CellButton a7 = new CellButton("A7");
//		CellButton a8 = new CellButton("A8");
//		CellButton a9 = new CellButton("A9");
//		CellButton a10 = new CellButton("A10");
//		
//		
//		CellButton b1 = new CellButton("B1");
//		CellButton b2 = new CellButton("B2");
//		CellButton b3 = new CellButton("B3");
//		CellButton b4 = new CellButton("B4");
//		CellButton b5 = new CellButton("B5");
//		CellButton b6 = new CellButton("B6");
//		CellButton b7 = new CellButton("B7");
//		CellButton b8 = new CellButton("B8");
//		CellButton b9 = new CellButton("B9");
//		CellButton b10 = new CellButton("B10");
//
//		
//		CellButton c1 = new CellButton("C1");
//		CellButton c2 = new CellButton("C2");
//		CellButton c3 = new CellButton("C3");
//		CellButton c4 = new CellButton("C4");
//		CellButton c5 = new CellButton("C5");
//		CellButton c6 = new CellButton("C6");
//		CellButton c7 = new CellButton("C7");
//		CellButton c8 = new CellButton("C8");
//		CellButton c9 = new CellButton("C9");
//		CellButton c10 = new CellButton("C10");
//
//		CellButton d1 = new CellButton("D1");
//		CellButton d2 = new CellButton("D2");
//		CellButton d3 = new CellButton("D3");
//		CellButton d4 = new CellButton("D4");
//		CellButton d5 = new CellButton("D5");
//		CellButton d6 = new CellButton("D6");
//		CellButton d7 = new CellButton("D7");
//		CellButton d8 = new CellButton("D8");
//		CellButton d9 = new CellButton("D9");
//		CellButton d10 = new CellButton("D10");
//		
//		CellButton f1 = new CellButton("F1");
//		CellButton f2 = new CellButton("F2");
//		CellButton f3 = new CellButton("F3");
//		CellButton f4 = new CellButton("F4");
//		CellButton f5 = new CellButton("F5");
//		CellButton f6 = new CellButton("F6");
//		CellButton f7 = new CellButton("F7");
//		CellButton f8 = new CellButton("F8");
//		CellButton f9 = new CellButton("F9");
//		CellButton f10 = new CellButton("F10");
//		
//
//
//		
//		
//		
//		a4.setOnMouseClicked(event ->{
//			row1.getChildren().removeAll(a1, a2, a3, a4);
//		});
//		c1.setOnMouseClicked(event ->{
//			row1.getChildren().addAll(a1, a2, a3, a4);
//		});
//		c4.setOnMouseClicked(event ->{
//			row2.getChildren().addAll(b1, b2, b3, b4 );
//		});
//		b2.setOnMouseClicked(event ->{
//			row2.getChildren().removeAll(b1, b2, b3, b4);
//		});
//
//		d3.setOnMouseClicked(event ->{
//			row2.getChildren().removeAll(b1, b2, b3, b4);
//		});
//		
//		row1.getChildren().addAll(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
//		row2.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
//		row3.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
//		row4.getChildren().addAll(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
//		row5.getChildren().addAll(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
//		
//		field.getChildren().addAll(row1, row2, row3, row4, row5);
//		
//	}}
//	
	@SuppressWarnings("restriction")
	public static void main (String [] args) {
		
		logger.trace("Configuration File Defined To Be :: " + System.getProperty("log4j.configurationFile"));
		
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

		Highscore.sortArrayList();
		
		GUI javaFX = new GUI();
		Application.launch(javaFX.getClass(), args);
	
  }
	public static Logger getLogger(){
		return logger;
	}
}




