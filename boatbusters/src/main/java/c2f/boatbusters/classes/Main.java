package c2f.boatbusters.classes;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
		
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(event ->{
			
		
		if(event.getCode() == KeyCode.Q){
			if(!gameMenu.isVisible()){
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
				ft.setFromValue(0);
				ft.setToValue(1);
				
				gameMenu.setVisible(true);
				ft.play();
			}
			else{
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished(evt -> gameMenu.setVisible(false));
				ft.play();
			}
		}
		});
		
		primaryStage.setTitle("Boatbusters");
	//	primaryStage.setResizable(false);
		primaryStage.setScene(scene);
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
			
			VBox menu0 = new VBox(10); // main menu
			VBox highscoreMenu1 = new VBox(10); // highscore menu
			
			VBox menuPlayer1 = new VBox(10); //player 1 sub menu
			VBox menuPlayer2 = new VBox(10); //player 2 sub menu
			
			menu0.setTranslateX(200);
			menu0.setTranslateY(300);
			
			highscoreMenu1.setTranslateX(200);
			highscoreMenu1.setTranslateY(300);
			
			menuPlayer1.setTranslateX(200);
			menuPlayer1.setTranslateY(300);
			
			menuPlayer2.setTranslateX(200);
			menuPlayer2.setTranslateY(300);
			
			
			
			final int offset = 400;
			
			highscoreMenu1.setTranslateX(offset);
			menuPlayer1.setTranslateX(offset);
			menuPlayer2.setTranslateX(offset);
			
			
			
			MenuButton btnStart = new MenuButton("START GAME"); 		//START Button Mainmenu
			btnStart.setOnMouseClicked(event ->{
//				game.startGame(game, scan);
				
				getChildren().add(menuPlayer1);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
				tt.setToX(menu0.getTranslateX() - offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				tt1.setToX(menu0.getTranslateX());
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt ->{
					getChildren().remove(menu0);
				});
			});
			
			MenuButton btnScore = new MenuButton("HIGHSCORE");	//Highscore Button Mainmenu
			btnScore.setOnMouseClicked(event ->{
				getChildren().add(highscoreMenu1);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
				tt.setToX(menu0.getTranslateX() - offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), highscoreMenu1);
				tt1.setToX(menu0.getTranslateX());
				
				tt.play();
				tt1.play();
				
				tt.setOnFinished(evt ->{
					getChildren().remove(menu0);
				});
				
				Highscore.printBestenliste();
			});
				
			MenuButton btnExit = new MenuButton("EXIT"); // EXIT Mainmenu
			btnExit.setOnMouseClicked(event ->{
				game.quit();
			});
			
			Text loginTextPlayer1 = new Text("Login: Player 1");
			loginTextPlayer1.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
			loginTextPlayer1.setFill(Color.WHITE);
			
			Text loginTextPlayer2 = new Text("Login: Player 2");
			loginTextPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
			loginTextPlayer2.setFill(Color.WHITE);
			
			Text highscoreText = new Text("Fabi is the best");
			
			
			TextField textfieldLoginPlayer1 = new TextField();
			textfieldLoginPlayer1.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
			
			textfieldLoginPlayer1.setOnKeyPressed(event ->{				// LoginName wird mit Enter Bestätigt
				if(event.getCode() == KeyCode.ENTER){
					System.out.println(textfieldLoginPlayer1.getText()); // vorrübergehend
					
					getChildren().add(menuPlayer2);
					
					TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
					tt.setToX(menuPlayer1.getTranslateX() - offset);
					
					TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
					tt1.setToX(menuPlayer1.getTranslateX());
					
					tt.play();
					tt1.play();
					
					
					tt.setOnFinished(evt ->{
						getChildren().remove(menuPlayer1);
						
					});
				}
			});
			
			
			TextField textfieldLoginPlayer2 = new TextField();
			textfieldLoginPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
			
			textfieldLoginPlayer2.setOnKeyPressed(event ->{				// LoginName wird mit Enter Bestätigt
				if(event.getCode() == KeyCode.ENTER){
					System.out.println(textfieldLoginPlayer2.getText()); // vorrübergehend
				}
			});
			
			
			
			MenuButton btnLoginPlayer1 = new MenuButton("OK");
			btnLoginPlayer1.setOnMouseClicked(event ->{
				
				getChildren().add(menuPlayer2);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() - offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
				tt1.setToX(menuPlayer1.getTranslateX());
				
				tt.play();
				tt1.play();
				
				
				tt.setOnFinished(evt ->{
					getChildren().remove(menuPlayer1);
					
				});
            });
			
			MenuButton btnBackPlayer1 = new MenuButton("BACK");
			btnBackPlayer1.setOnMouseClicked(event ->{
				
				getChildren().add(menu0);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() + offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
				tt1.setToX(menuPlayer1.getTranslateX());
				
				tt.play();
				tt1.play();
				
				
				tt.setOnFinished(evt ->{
					getChildren().remove(menuPlayer1);
					
				});
			});
			
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!");
			btnLoginPlayer2.setOnMouseClicked(event ->{
				
				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
		        game.startGame(game, scan, namePlayer1, namePlayer2);
				
			});

			MenuButton btnBackPlayer2 = new MenuButton("BACK");
			btnBackPlayer2.setOnMouseClicked(event ->{
				
				getChildren().add(menuPlayer1);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				tt.setToX(menuPlayer2.getTranslateX() + offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				tt1.setToX(menuPlayer2.getTranslateX());
				
				tt.play();
				tt1.play();
				
				
				tt.setOnFinished(evt ->{
					getChildren().remove(menuPlayer2);
					
				});
			});
			
			MenuButton btnBack2 = new MenuButton("BACK"); // Highscore back-Button
			btnBack2.setOnMouseClicked(event ->{
				
				getChildren().add(menu0);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), highscoreMenu1);
				tt.setToX(highscoreMenu1.getTranslateX() + offset);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
				tt1.setToX(highscoreMenu1.getTranslateX());
				
				tt.play();
				tt1.play();
				
				
				tt.setOnFinished(evt ->{
					getChildren().remove(highscoreMenu1);
					
				});
			});
			
			menu0.getChildren().addAll(btnStart, btnScore, btnExit); // Hauptmenu
			highscoreMenu1.getChildren().addAll(highscoreText, btnBack2);		// Highscore
			menuPlayer1.getChildren().addAll(loginTextPlayer1, textfieldLoginPlayer1, btnLoginPlayer1, btnBackPlayer1); // login1
			menuPlayer2.getChildren().addAll(loginTextPlayer2, textfieldLoginPlayer2, btnLoginPlayer2, btnBackPlayer2); // login2
			
			
			Rectangle bg = new Rectangle(1280,720);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);
			
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




