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



public class Main extends Application{
	
	static final Scanner scan = new Scanner(System.in);
	
	private final static Logger logger = LogManager.getRootLogger();
	
	private GameMenu gameMenu;
	private GameField gameField;
	
	
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
		gameField = new GameField();
		
		Scene scene = new Scene(root);
		
		root.getChildren().addAll(imgView, gameMenu, gameField);
		
		scene.setOnKeyPressed(event ->{ 												// menu mit Q ein und ausblenden
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
	
	
	public static class MenuButton extends StackPane{
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
	
	public class GameMenu extends Parent{
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
//				
				
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
			textfieldLoginPlayer1.setPromptText("Username");
			
			textfieldLoginPlayer1.setOnKeyPressed(event ->{				// LoginName1 wird mit Enter Bestätigt
				if(event.getCode() == KeyCode.ENTER){
					String namePlayer1 = textfieldLoginPlayer1.getText(); // vorrübergehend
					
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
			textfieldLoginPlayer2.setPromptText("Username");
			
			textfieldLoginPlayer2.setOnKeyPressed(event ->{				// Start Game mit Enter
				if(event.getCode() == KeyCode.ENTER){
					String namePlayer1 = textfieldLoginPlayer1.getText();
					String namePlayer2 = textfieldLoginPlayer2.getText();
					
					
			   //     game.startGame(game, scan, namePlayer1, namePlayer2);
				
					getChildren().addAll(gameField.row1, gameField.row2, gameField.row3);
					
					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
					t1.setToX(menuPlayer2.getTranslateX() - offset);
					
					TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), gameField.row1);
					t2.setToX(menuPlayer2.getTranslateX());
					t2.setToY(300);
					
					TranslateTransition t3 = new TranslateTransition(Duration.seconds(0.5), gameField.row2);
					t3.setToX(menuPlayer2.getTranslateX());
					t3.setToY(menuPlayer2.getTranslateY() + 31); // 31 entstehen durch größe der Cell +1
					
					TranslateTransition t4 = new TranslateTransition(Duration.seconds(0.5), gameField.row3);
					t4.setToX(menuPlayer2.getTranslateX());
					t4.setToY(menuPlayer2.getTranslateY() + 62);
					
					t1.play();
					t2.play();
					t3.play();
					t4.play();
					
					
					t2.setOnFinished(evt ->{
						getChildren().removeAll(menuPlayer2);
						
					});
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
			
			
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!"); // starte spiel
			btnLoginPlayer2.setOnMouseClicked(event ->{
				
				
				
				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
		   //     game.startGame(game, scan, namePlayer1, namePlayer2);
				
				getChildren().addAll(gameField.row1, gameField.row2, gameField.row3);
				
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				t1.setToX(menuPlayer2.getTranslateX() - offset);
				
				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), gameField.row1);
				t2.setToX(menuPlayer2.getTranslateX());
				t2.setToY(300);
				
				TranslateTransition t3 = new TranslateTransition(Duration.seconds(0.5), gameField.row2);
				t3.setToX(menuPlayer2.getTranslateX());
				t3.setToY(menuPlayer2.getTranslateY() + 31); // 31 entstehen durch größe der Cell(30) +1
				
				TranslateTransition t4 = new TranslateTransition(Duration.seconds(0.5), gameField.row3);
				t4.setToX(menuPlayer2.getTranslateX());
				t4.setToY(menuPlayer2.getTranslateY() + 62);
				
				t1.play();
				t2.play();
				t3.play();
				t4.play();
				
				t2.setOnFinished(evt ->{
					getChildren().remove(menuPlayer2);
					
				});
				
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
	public class CellButton extends GridPane{
		Text text;
		public CellButton(String name){
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.BLACK);
			
			
			Rectangle bg = new Rectangle(30,30);		// größe der Cell
										
			bg.setFill(Color.GREY);
			
			getChildren().addAll(bg,text); 
			
			//wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.BEIGE);
			
			});
			//wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.BLACK);
			});
		}
	}
	
	public class GameField extends Parent{
		HBox row1 = new HBox(1); // die 1 ist der abstand zwischen den cells
		HBox row2 = new HBox(1);	
		HBox row3 = new HBox(1);
		HBox row4 = new HBox(1);
		HBox row5 = new HBox(1);
		HBox row6 = new HBox(1);
		HBox row7 = new HBox(1);
		HBox row8 = new HBox(1);
		HBox row9 = new HBox(1);
		HBox row10 = new HBox(1);
		VBox field = new VBox(10);
//		VBox rows = new VBox(row1, row2, row3, row4, row5, row6, row7);
		
		
//		public GameField(boolean test){
////			EventHandler<? super MouseEvent> handler
//			if(test == true){
//			for (int y = 0; y <2; y++){
//				HBox row = new HBox();
//				
//				for(int x = 0; x <2; x++){
//					CellButton c = new CellButton(x, y);
//					row.getChildren().add(c);
//			}
//			rows.getChildren().add(row);	
//		}
//		getChildren().add(rows);
//	}}
		public GameField(){
		CellButton a1 = new CellButton("A1");
		CellButton a2 = new CellButton("A2");
		CellButton a3 = new CellButton("A3");
		CellButton a4 = new CellButton("A4");
		CellButton a5 = new CellButton("A5");
		CellButton a6 = new CellButton("A6");
		CellButton a7 = new CellButton("A7");
		CellButton a8 = new CellButton("A8");
		CellButton a9 = new CellButton("A9");
		CellButton a10 = new CellButton("A10");
		
		
		CellButton b1 = new CellButton("B1");
		CellButton b2 = new CellButton("B2");
		CellButton b3 = new CellButton("B3");
		CellButton b4 = new CellButton("B4");
		CellButton b5 = new CellButton("B5");
		CellButton b6 = new CellButton("B6");
		CellButton b7 = new CellButton("B7");
		CellButton b8 = new CellButton("B8");
		CellButton b9 = new CellButton("B9");
		CellButton b10 = new CellButton("B10");

		
		CellButton c1 = new CellButton("C1");
		CellButton c2 = new CellButton("C2");
		CellButton c3 = new CellButton("C3");
		CellButton c4 = new CellButton("C4");
		CellButton c5 = new CellButton("C5");
		CellButton c6 = new CellButton("C6");
		CellButton c7 = new CellButton("C7");
		CellButton c8 = new CellButton("C8");
		CellButton c9 = new CellButton("C9");
		CellButton c10 = new CellButton("C10");

		CellButton d1 = new CellButton("D1");
		CellButton d2 = new CellButton("D2");
		CellButton d3 = new CellButton("D3");
		CellButton d4 = new CellButton("D4");
		CellButton d5 = new CellButton("D5");
		CellButton d6 = new CellButton("D6");
		CellButton d7 = new CellButton("D7");
		CellButton d8 = new CellButton("D8");
		CellButton d9 = new CellButton("D9");
		CellButton d10 = new CellButton("D10");
		
		CellButton f1 = new CellButton("F1");
		CellButton f2 = new CellButton("F2");
		CellButton f3 = new CellButton("F3");
		CellButton f4 = new CellButton("F4");
		CellButton f5 = new CellButton("F5");
		CellButton f6 = new CellButton("F6");
		CellButton f7 = new CellButton("F7");
		CellButton f8 = new CellButton("F8");
		CellButton f9 = new CellButton("F9");
		CellButton f10 = new CellButton("F10");
		


		
		
		
		a4.setOnMouseClicked(event ->{
			row1.getChildren().removeAll(a1, a2, a3, a4);
		});
		c1.setOnMouseClicked(event ->{
			row1.getChildren().addAll(a1, a2, a3, a4);
		});
		c4.setOnMouseClicked(event ->{
			row2.getChildren().addAll(b1, b2, b3, b4 );
		});
		b2.setOnMouseClicked(event ->{
			row2.getChildren().removeAll(b1, b2, b3, b4);
		});

		d3.setOnMouseClicked(event ->{
			row2.getChildren().removeAll(b1, b2, b3, b4);
		});
		
		row1.getChildren().addAll(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
		row2.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
		row3.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
		row4.getChildren().addAll(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
		row5.getChildren().addAll(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
		
		field.getChildren().addAll(row1, row2, row3, row4, row5);
		
		
		
	}}
	
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




