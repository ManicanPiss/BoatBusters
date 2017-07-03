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

public class Main {

	static final Scanner scan = new Scanner(System.in);

	private final static Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) {

		File dataFile = new File("bestenliste.csv"); // Eingelesene Datei

		try (Scanner reader = new Scanner(dataFile).useDelimiter("\n")) {

			while (reader.hasNext()) { // Einlesen der schon gespeicherten
										// Spieler
				String[] dataArray = new String[2]; // Erstellt Array
													// (Zwischenspeicher)
				dataArray = reader.next().split(";", -1); // Teilen am ';'
				// Erstelle Spieler und füge sie der Liste hinzu
				Highscore.getBestenliste().add(new Player(dataArray[0], dataArray[1]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Highscore.sortArrayList();

		logger.trace("Configuration File Defined To Be :: " + System.getProperty("log4j.configurationFile"));

		GUI javaFX = new GUI();
		Application.launch(javaFX.getClass(), args);

	}

	public static Logger getLogger() {
		return logger;
	}
}
