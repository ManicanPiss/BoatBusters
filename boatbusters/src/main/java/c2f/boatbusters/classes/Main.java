package c2f.boatbusters.classes;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;



public class Main extends Application{
	
	static final Scanner scan = new Scanner(System.in);
	
	private final static Logger logger = LogManager.getRootLogger();

	private GameMenu gameMenu;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		Pane root = new Pane();
		root.setPrefSize(1280,720);
		
		InputStream is = Files.newInputStream(Paths.get("src/main/resources/ShipBg.jpg"));
		Image img = new Image(is);
		is.close();
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(1300);
		imgView.setFitHeight(760);
		
		gameMenu = new GameMenu();
		
		root.getChildren().addAll(imgView, gameMenu);
		
		Scene sceneMain = new Scene(root);
		
		
		primaryStage.setTitle("Boatbusters");
	//	primaryStage.setResizable(false);
		primaryStage.setScene(sceneMain);
		primaryStage.show();	

	}
	
	
	private static class MenuButton extends StackPane{
		private Text text;
		
		public MenuButton(String name){
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.WHITE);
			
			Rectangle bg = new Rectangle(250,30);		
			bg.setOpacity(0.5);							
			bg.setFill(Color.BLACK);
			
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text); // text steht über hintergrund
			
			//wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			
			});
			//wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});
			
		}
	}
	
	private class GameMenu extends Parent{
		public GameMenu(){
			
			Game game = new Game(0);
			
			VBox menu0 = new VBox(10);
//			VBox menu1 = new VBox(10); //sub menu
			
			menu0.setTranslateX(200);
			menu0.setTranslateY(300);
			
//			menu1.setTranslateX(100);
//			menu1.setTranslateY(200);
			
			MenuButton btnStart = new MenuButton("START GAME");
			btnStart.setOnMouseClicked(event ->{
				game.startGame(game, scan);
				
				
			});
			MenuButton btnScore = new MenuButton("HIGHSCORE");
			btnScore.setOnMouseClicked(event ->{
				Highscore.printBestenliste();
			});
			
			MenuButton btnExit = new MenuButton("Exit");
			btnExit.setOnMouseClicked(event ->{
				game.quit();
			});
			
			menu0.getChildren().addAll(btnStart, btnScore, btnExit);
			
			Rectangle bg = new Rectangle(1280,720);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.3);
			
			getChildren().addAll(bg, menu0);
		}
	}
	
	

	
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
		
		launch(args);
	
  }
	
	
	public static Logger getLogger(){
		return logger;
	}
}




