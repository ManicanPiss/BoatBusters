package c2f.boatbusters.gui;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import c2f.boatbusters.classes.Game;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
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

public class GUI extends Application  {

	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane();
		root.setPrefSize(1280,720);
		
		InputStream is = Files.newInputStream(Paths.get("src/main/resources/ShipBg.jpg"));
		Image img = new Image(is);
		is.close();
		
		GameMenu gameMenu = new GameMenu();
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(1300);
		imgView.setFitHeight(760);
		
		Scene scene = new Scene(root);
		root.getChildren().addAll(imgView, gameMenu);
		
		
		
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
			getChildren().addAll(bg, text); 
			
			// Maus betritt MenuButtonbereich
			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			
			});
			// Maus verlässt MenuButtonbereich
			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});
			
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
			
			///// START GAME BUTTON MAIN MENU /////
			MenuButton btnStart = new MenuButton("START GAME");
			btnStart.setOnMouseClicked(event ->{
				
				getChildren().add(menuPlayer1);
				
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menu0);
				t1.setToX(menu0.getTranslateX() - offset);
				
				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				t2.setToX(menu0.getTranslateX());
				
				t1.play();
				t2.play();
				
				t1.setOnFinished(evt ->{
					getChildren().remove(menu0);
				});
			});
			
			///// HIGHSCORE BUTTON MAIN MENU /////
			MenuButton btnScore = new MenuButton("HIGHSCORE");	//Highscore Button Mainmenu
			btnScore.setOnMouseClicked(event ->{
				getChildren().add(highscoreMenu1);
				
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menu0);
				t1.setToX(menu0.getTranslateX() - offset);
				
				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), highscoreMenu1);
				t2.setToX(menu0.getTranslateX());
				
				t1.play();
				t2.play();
				
				t1.setOnFinished(evt ->{
					getChildren().remove(menu0);
				});
				
				
			});
			///// EXIT BUTTON MAIN MENU /////
			MenuButton btnExit = new MenuButton("EXIT"); // EXIT Mainmenu
			btnExit.setOnMouseClicked(event ->{
				game.quit();
			});
			
			// Login Text Player1
			Text loginTextPlayer1 = new Text("Login: Player 1");
			loginTextPlayer1.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
			loginTextPlayer1.setFill(Color.WHITE);
			
			// Login Text Player2
			Text loginTextPlayer2 = new Text("Login: Player 2");
			loginTextPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
			loginTextPlayer2.setFill(Color.WHITE);
			
			Text highscoreText = new Text("HIGHSCORE");
			
			// Textfield Login Player 1
			TextField textfieldLoginPlayer1 = new TextField();
			textfieldLoginPlayer1.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
			textfieldLoginPlayer1.setPromptText("Username");
			
			// Login Player 1 Press-ENTER
			textfieldLoginPlayer1.setOnKeyPressed(event ->{				
				if(event.getCode() == KeyCode.ENTER){
					String namePlayer1 = textfieldLoginPlayer1.getText(); // vorrübergehend
					
					getChildren().add(menuPlayer2);
					
					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
					t1.setToX(menuPlayer1.getTranslateX() - offset);
					
					TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
					t2.setToX(menuPlayer1.getTranslateX());
					
					t1.play();
					t2.play();
					
					t1.setOnFinished(evt ->{
						getChildren().remove(menuPlayer1);
						
					});
				}
			});
			
			// Textfield Login Player 2
			TextField textfieldLoginPlayer2 = new TextField();
			textfieldLoginPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
			textfieldLoginPlayer2.setPromptText("Username");
			
			// Login Player 2 Press-ENTER (START GAME)
			textfieldLoginPlayer2.setOnKeyPressed(event ->{				
				if(event.getCode() == KeyCode.ENTER){
					
				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
				
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				t1.setToX(menuPlayer2.getTranslateX() - offset);
					
				
//					gameMenuStage.close();
//					try { // TODO:
//						
//					
//					
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					
			   //     game.startGame(game, scan, namePlayer1, namePlayer2);
				
				}		
			});
			
			
			///// Login Player 1 bestätigen mit Maus /////
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
			
			///// Back to MainMenu from Login 1 /////
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
			
			///// Login Player 2 bestätigen mit Maus (Start Game) ///// 
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!");
			btnLoginPlayer2.setOnMouseClicked(event ->{
				
				
				
				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
		   //     game.startGame(game, scan, namePlayer1, namePlayer2);
				
				
				
				
			});
			
			///// Back to Login 1 from Login 2 /////
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
			
			
			///// Back to MainMenu from HIGHSCORE /////
			MenuButton btnBack2 = new MenuButton("BACK");
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
			highscoreMenu1.getChildren().addAll(highscoreText, btnBack2);		// HighscoreMenu
			menuPlayer1.getChildren().addAll(loginTextPlayer1, textfieldLoginPlayer1, btnLoginPlayer1, btnBackPlayer1); // login1
			menuPlayer2.getChildren().addAll(loginTextPlayer2, textfieldLoginPlayer2, btnLoginPlayer2, btnBackPlayer2); // login2
			
			
			Rectangle bg = new Rectangle(1280,720);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);
			
			getChildren().addAll(bg, menu0);
			

}}} 
