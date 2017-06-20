package c2f.boatbusters.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import c2f.boatbusters.classes.Game;
import c2f.boatbusters.classes.Highscore;
import c2f.boatbusters.classes.Main;
import c2f.boatbusters.classes.Player;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class GUI extends Application {

	final int WINDOW_SIZE_X = 1280;
	final int WINDOW_SIZE_Y = 720;
	final int translateX = 200;
	final int translateY = 300;
	final static int MENUBUTTON_SIZE_X = 250;
	final static int MENUBUTTON_SIZE_Y = 30;
	final static int CELLBUTTON_SIZE_X_Y = 30;
	final static int SHIPBUTTON_SIZE_X = 150;
	final static int SHIPBUTTON_SIZE_Y = 30;
	
	Player player1;
	Player player2;
	Game game;
	Highscore highscore;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		

		Pane rootStart = new Pane();
		rootStart.setPrefSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

		VBox startBox = new VBox(10);

		startBox.setTranslateX((WINDOW_SIZE_X / 2) - 350);
		startBox.setTranslateY((WINDOW_SIZE_Y / 2) - MENUBUTTON_SIZE_Y);

		InputStream is = Files.newInputStream(Paths.get("src/main/resources/bg2.jpg"));
		Image img = new Image(is);
		is.close();

		ImageView imgHome = new ImageView(img);
		imgHome.setFitWidth(WINDOW_SIZE_X + 20);
		imgHome.setFitHeight(WINDOW_SIZE_Y + 20);

		MenuButton welcomeBtn = new MenuButton("PRESS ANY KEY TO CONTINUE");

		welcomeBtn.setOnMousePressed(event -> {

			primaryStage.close();
			try {
				GameMenu gameMenu = new GameMenu();
				gameMenu.GameMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Text welcomeMessage0 = new Text("WELCOME TO BOATBUSTERS");
		welcomeMessage0.setStyle("-fx-font: normal bold 35px 'serif' ");
		welcomeMessage0.setFill(Color.WHITE);
		welcomeMessage0.setTranslateY(-50);
		welcomeMessage0.setTranslateX(-400);

		TranslateTransition t1 = new TranslateTransition(Duration.seconds(1.0), welcomeMessage0);
		t1.setToX(welcomeMessage0.getTranslateX() + 400);
		t1.play();

		startBox.getChildren().addAll(welcomeMessage0, welcomeBtn);
		rootStart.getChildren().addAll(imgHome, startBox);

		Scene sceneStart = new Scene(rootStart);

		sceneStart.setOnKeyPressed(event -> {
			// if (event.getCode() == KeyCode.ENTER) {
			primaryStage.close();

			try {
				GameMenu gameMenu = new GameMenu();
				gameMenu.GameMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// }
		});

		primaryStage.setTitle("Boatbusters");
		// primaryStage.setResizable(false);
		primaryStage.setScene(sceneStart);
		primaryStage.show();

	}

	public static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(MENUBUTTON_SIZE_X, MENUBUTTON_SIZE_Y);
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

	public class CellButton extends GridPane {
		Text text;

		public CellButton() {
			// text = new Text(name);
			// text.setFont(text.getFont());
			// text.setFill(Color.BLACK);

			Rectangle bg = new Rectangle(CELLBUTTON_SIZE_X_Y, CELLBUTTON_SIZE_X_Y); // größe
																					// der
																					// Cell

			bg.setFill(Color.GHOSTWHITE);
			bg.setOpacity(0.6);
			bg.setStroke(Color.TRANSPARENT);

			getChildren().addAll(bg);

			// wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setFill(Color.ORANGE);
				bg.setOpacity(0.7);

				// text.setFill(Color.TURQUOISE);

			});
			// wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setFill(Color.GHOSTWHITE);
				bg.setOpacity(0.6);
				// text.setFill(Color.BLACK);
			});
		}

		public void setOnAction(EventHandler<ActionEvent> eventHandler) { // TODO:
			// TODO Auto-generated method stub

		}

	}

	public class GameButton extends StackPane {
		Text text;
		public GameButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.WHITE);
			text.setTextAlignment(TextAlignment.RIGHT);
			
			Rectangle bg = new Rectangle(SHIPBUTTON_SIZE_X-10, SHIPBUTTON_SIZE_Y); // größe
																				// der
			bg.setFill(Color.DIMGRAY);
			bg.setOpacity(0.7);
			bg.setStroke(Color.BLACK);
			setAlignment(Pos.CENTER);

			getChildren().addAll(bg, text);
			setOnMousePressed(event ->{
				text.setFill(Color.ORANGE);
			});
			
			setOnMouseReleased(event ->{
				text.setFill(Color.WHITE);
			});
			
			setOnMouseEntered(event -> {
//				text.setFill(Color.ORANGE);
				bg.setStroke(Color.ORANGE);

			});
			
			setOnMouseExited(event -> {
//				text.setFill(Color.WHITE);
				bg.setStroke(Color.BLACK);
			});
		}
	}

	public class GameMenu {

		public void GameMenu() throws Exception {

			game = new Game(0);

			Stage menuStage = new Stage();
			Pane rootMenu = new Pane();

			InputStream is = Files.newInputStream(Paths.get("src/main/resources/ShipBg.jpg"));
			Image imgShip = new Image(is);
			is.close();

			ImageView imgView = new ImageView(imgShip);
			imgView.setFitWidth(WINDOW_SIZE_X + 20);
			imgView.setFitHeight(WINDOW_SIZE_Y + 20);

			VBox mainMenu = new VBox(10); // main menu
			VBox scoreMenu = new VBox(10); // highscore menu

			VBox menuPlayer1 = new VBox(10); // player 1 sub menu
			VBox menuPlayer2 = new VBox(10); // player 2 sub menu

			mainMenu.setTranslateX(translateX);
			mainMenu.setTranslateY(translateY);

			scoreMenu.setTranslateX(translateX);
			scoreMenu.setTranslateY(translateY);

			menuPlayer1.setTranslateX(translateX);
			menuPlayer1.setTranslateY(translateY);

			menuPlayer2.setTranslateX(translateX);
			menuPlayer2.setTranslateY(translateY);

			final int offset = 400;

			scoreMenu.setTranslateX(offset);
			menuPlayer1.setTranslateX(offset);
			menuPlayer2.setTranslateX(offset);

			///// START GAME BUTTON MAIN MENU /////
			MenuButton btnStart = new MenuButton("START GAME");
			btnStart.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(menuPlayer1);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				t1.setToX(mainMenu.getTranslateX() - offset);

				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				t2.setToX(mainMenu.getTranslateX());

				t1.play();
				t2.play();

				t1.setOnFinished(evt -> {
					rootMenu.getChildren().remove(mainMenu);
				});
			});

			///// HIGHSCORE BUTTON MAIN MENU /////
			MenuButton btnScore = new MenuButton("HIGHSCORE");

			btnScore.setOnMouseClicked(event -> {
				rootMenu.getChildren().add(scoreMenu);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				t1.setToX(mainMenu.getTranslateX() - offset);

				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), scoreMenu);
				t2.setToX(mainMenu.getTranslateX());

				t1.play();
				t2.play();
				
				Highscore.printBestenliste();

				t1.setOnFinished(evt -> {
					rootMenu.getChildren().remove(mainMenu);
				});

			});
			///// EXIT BUTTON MAIN MENU /////
			MenuButton btnExit = new MenuButton("EXIT"); // EXIT Mainmenu
			btnExit.setOnMouseClicked(event -> {
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
			textfieldLoginPlayer1.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					String namePlayer1 = textfieldLoginPlayer1.getText(); // TODO:vorrübergehend

					rootMenu.getChildren().add(menuPlayer2);

					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
					t1.setToX(menuPlayer1.getTranslateX() - offset);

					TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
					t2.setToX(menuPlayer1.getTranslateX());

					t1.play();
					t2.play();

					t1.setOnFinished(evt -> {
						rootMenu.getChildren().remove(menuPlayer1);

					});
				}
			});

			// Textfield Login Player 2
			TextField textfieldLoginPlayer2 = new TextField();
			textfieldLoginPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
			textfieldLoginPlayer2.setPromptText("Username");

			// Login Player 2 Press-ENTER (START GAME)
			textfieldLoginPlayer2.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {

					String namePlayer1 = textfieldLoginPlayer1.getText();
					String namePlayer2 = textfieldLoginPlayer2.getText();

					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
					t1.setToX(menuPlayer2.getTranslateX() - offset);

					t1.play();

					t1.setOnFinished(evt -> {
						rootMenu.getChildren().remove(menuPlayer2);

					});

					try {
						menuStage.close();
						GameField gameField = new GameField();
						gameField.GameField();
					} catch (Exception e) {
						e.printStackTrace();
					}

					 game.startGame(game, namePlayer1, namePlayer2);
					 player1 = game.getPlayer1();
					 player2 = game.getPlayer2();
				}
			});

			///// Login Player 1 bestätigen mit Maus /////
			MenuButton btnLoginPlayer1 = new MenuButton("OK");
			btnLoginPlayer1.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(menuPlayer2);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
				tt1.setToX(menuPlayer1.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					rootMenu.getChildren().remove(menuPlayer1);

				});
			});

			///// Back to MainMenu from Login 1 /////
			MenuButton btnBackPlayer1 = new MenuButton("BACK");
			btnBackPlayer1.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(mainMenu);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
				tt1.setToX(menuPlayer1.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					rootMenu.getChildren().remove(menuPlayer1);

				});
			});

			///// Login Player 2 bestätigen mit Maus (Start Game) /////
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!");
			btnLoginPlayer2.setOnMouseClicked(event -> {

				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
				game.startGame(game, namePlayer1, namePlayer2);
				
				
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				t1.setToX(menuPlayer2.getTranslateX() - offset);

				t1.play();

				t1.setOnFinished(evt -> {
					rootMenu.getChildren().remove(menuPlayer2);

				});

				menuStage.close();
				try {

					GameField gameField = new GameField();
					gameField.GameField();

				} catch (Exception e) {
					e.printStackTrace();
				}

			});

			///// Back to Login 1 from Login 2 /////
			MenuButton btnBackPlayer2 = new MenuButton("BACK");
			btnBackPlayer2.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(menuPlayer1);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				tt.setToX(menuPlayer2.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				tt1.setToX(menuPlayer2.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					rootMenu.getChildren().remove(menuPlayer2);

				});
			});

			///// Back to MainMenu from HIGHSCORE /////
			MenuButton btnBack2 = new MenuButton("BACK");
			btnBack2.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(mainMenu);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), scoreMenu);
				tt.setToX(scoreMenu.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
				tt1.setToX(scoreMenu.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					rootMenu.getChildren().remove(scoreMenu);

				});
			});

			mainMenu.getChildren().addAll(btnStart, btnScore, btnExit); // Hauptmenu
			scoreMenu.getChildren().addAll(highscoreText, btnBack2); // HighscoreMenu
			menuPlayer1.getChildren().addAll(loginTextPlayer1, textfieldLoginPlayer1, btnLoginPlayer1, btnBackPlayer1); // login1
			menuPlayer2.getChildren().addAll(loginTextPlayer2, textfieldLoginPlayer2, btnLoginPlayer2, btnBackPlayer2); // login2

			// getChildren().addAll(bg, mainMenu);
			rootMenu.getChildren().addAll(imgView, mainMenu);

			Scene menuScene = new Scene(rootMenu);
			menuStage.setTitle("BoatBusters");
			menuStage.setWidth(WINDOW_SIZE_X);
			menuStage.setHeight(WINDOW_SIZE_Y);
			menuStage.setScene(menuScene);
			menuStage.show();

		}
	}

	public class GameField extends Parent {

		public void GameField() throws Exception {
			Stage gameStage = new Stage();

			BorderPane rootGame = new BorderPane();

			InputStream is = Files.newInputStream(Paths.get("src/main/resources/bg2.jpg"));
			Image img = new Image(is);
			is.close();

			ImageView imgGameBG = new ImageView(img);
			imgGameBG.setFitWidth(WINDOW_SIZE_X);
			imgGameBG.setFitHeight(WINDOW_SIZE_Y);

			HBox gameBoards = new HBox();
			gameBoards.setSpacing(100);
			gameBoards.setPadding(new Insets(100, 0, 0, 0));

			/// TOP ///
			StackPane top = new StackPane();
			top.setAlignment(Pos.CENTER);
			Rectangle bgTopBox = new Rectangle(600, 100);

			bgTopBox.setOpacity(0.4);
			bgTopBox.setFill(Color.DIMGRAY);
			bgTopBox.setStroke(Color.LIGHTGRAY);

			VBox gameTextTop = new VBox();
			gameTextTop.setTranslateX(WINDOW_SIZE_X / 2 - 180);
			gameTextTop.setTranslateY(25);

			Text textInfo = new Text("Infonachricht: Du bist scheiße!");
			textInfo.setTextAlignment(TextAlignment.CENTER);
			textInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
			textInfo.setFill(Color.ORANGE);
			gameTextTop.getChildren().add(textInfo);
			top.getChildren().addAll(bgTopBox, gameTextTop);
			///////

			// LEFTSIDE//
			StackPane left = new StackPane();
			Rectangle bgLeftBox = new Rectangle(150, 500);
			bgLeftBox.setOpacity(0.4);
			bgLeftBox.setFill(Color.DIMGREY);
			bgLeftBox.setStroke(Color.LIGHTGRAY);

			VBox gameLeft = new VBox();
			gameLeft.setSpacing(5);
			GameButton smallLeft = new GameButton("small Ship");
			smallLeft.setOnMouseClicked(event->{
				
			});
			GameButton middleLeft = new GameButton("middle Ship");
			middleLeft.setOnMouseClicked(event->{
				
			});
			GameButton bigLeft = new GameButton("big Ship");
			bigLeft.setOnMouseClicked(event->{
				
			});
			
			
			Text textLeft = new Text(" Press Q to Surrender");
			textLeft.setTextAlignment(TextAlignment.CENTER);
			textLeft.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
			textLeft.setFill(Color.BEIGE);
			
			Text textPlayerLeft = new Text(" Player 1:");
			textPlayerLeft.setTextAlignment(TextAlignment.CENTER);
			textPlayerLeft.setFont(Font.font("Verdana", FontPosture.ITALIC, 14));
			textPlayerLeft.setFill(Color.BEIGE);
			gameLeft.getChildren().addAll(textLeft, textPlayerLeft, smallLeft, middleLeft ,bigLeft);
			
//			if(alle schiffe gesetzt){ TODO:
//				gameLeft.getChildren().remove(smallLeft);
//				gameLeft.getChildren().remove(middleLeft);
//				gameLeft.getChildren().remove(bigLeft);
//			}
			
			left.getChildren().addAll(bgLeftBox, gameLeft);
			/////

			/// RIGHTSIDE ///
			StackPane right = new StackPane();
			right.setAlignment(Pos.CENTER_RIGHT);
			Rectangle bgRightBox = new Rectangle(150, 500);
			bgRightBox.setOpacity(0.4);
			bgRightBox.setFill(Color.DIMGREY);
			bgRightBox.setStroke(Color.LIGHTGRAY);

			VBox gameRight = new VBox();
			gameRight.setSpacing(5);
			GameButton smallRight = new GameButton("small Ship");
			smallRight.setOnMouseClicked(event->{
				
			});
			GameButton middleRight = new GameButton("middle Ship");
			middleRight.setOnMouseClicked(event->{
				
			});
			GameButton bigRight = new GameButton("big Ship");
			bigRight.setOnMouseClicked(event->{
				
			});
			
			Text textRight = new Text(" Press Q to Surrender");
			textRight.setTextAlignment(TextAlignment.CENTER);
			textRight.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
			textRight.setFill(Color.DARKSEAGREEN);
			Text textPlayerRight = new Text(" Player 2:");
			textPlayerRight.setTextAlignment(TextAlignment.CENTER);
			textPlayerRight.setFont(Font.font("Verdana", FontPosture.ITALIC, 14));
			textPlayerRight.setFill(Color.DARKSEAGREEN);
			gameRight.getChildren().addAll(textRight, textPlayerRight, smallRight, middleRight, bigRight);
			
//			if(alle schiffe gesetzt){ TODO:
//				gameRight.getChildren().remove(smallRight);
//				gameRight.getChildren().remove(middleRight);
//				gameRight.getChildren().remove(bigRight);
//			}
			right.getChildren().addAll(bgRightBox, gameRight);
			/////

			///// BOTTOM ///////
			StackPane bottom = new StackPane();
			Rectangle bgBottomBox = new Rectangle(WINDOW_SIZE_X, 100);
			bgBottomBox.setOpacity(0.4);
			bgBottomBox.setFill(Color.DIMGRAY);
			bgBottomBox.setStroke(Color.LIGHTGRAY);

			HBox playerNames = new HBox();
			playerNames.setPadding(new Insets(0, 0, 50, 0));
			playerNames.setSpacing(100);
			playerNames.setAlignment(Pos.CENTER);
			Text textPlayer1 = new Text("Player 1: skat3r_B0Y_2001");
			textPlayer1.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
			textPlayer1.setFill(Color.BEIGE);

			Text textPlayer2 = new Text("Player 2: lil_gaengster23");
			textPlayer2.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
			textPlayer2.setFill(Color.DARKSEAGREEN);

			playerNames.getChildren().addAll(textPlayer1, textPlayer2);
			bottom.getChildren().addAll(bgBottomBox, playerNames);
			

			GridPane gameFieldLEFT = new GridPane();
			GridPane gameFieldRIGHT = new GridPane();
			gameBoards.setAlignment(Pos.BASELINE_CENTER);

			gameBoards.getChildren().addAll(gameFieldLEFT, gameFieldRIGHT);
			
			
			//TODO Text Feld machen mit dieser Anzeige eine pro Spieler, 
			//also wie viele Schiffe jeder Spieler noch zu setzen hat:
			//Aber eher erst die Anzeige über erstem Feld anzeigen lassen,
			//solange Spieler 1 dran ist, und dann über zweiten Feld, solang Spieler 2 dran ist
			
//			Main.getLogger().info("Please put your ships on the field!\n"
//			+ "You have left:"
//			+ player.getCountSmall() +  "small ship(s) (3 cells long),\n"
//			+ player.getCountMiddle() +  "middle ship(s) (4 cells long)\n"
//			+ player.getCountBig() +  "big ship(s) (5 cells long)");

			// Gamefield eastside //
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					CellButton button = new CellButton();
					int x = column;
					int y = row;

					button.setOnMouseClicked(event -> {
						//Main.getLogger().info("LEFTSIDE: Button at " + x + "/" + y + " pressed");
						Main.getLogger().info(highscore.getBestenliste().get(1));
						
 
					});
					
					button.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							
							if(player1.getSecondIteration() == false) {
								player1.setXfirst(x);
								player1.setYfirst(y);
								
							}
							
							player1.setShipPartsGui( x, y, game.board1);
							player1.setSecondIterationOnFalse();
//							//Eine Art update-Methode, in der der Spielstand aktualisiert und visualisiert wird;
//							Main.getLogger().info("anzahl schiffe noch zu setzen:" + player1.getShipsCount());
				    	}
					});
					
					gameFieldLEFT.add(button, row, column);
				}

			}
			// GameField westside //
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					CellButton button = new CellButton();

					int x = column;
					int y = row;

					button.setOnMouseClicked(event -> {

						Main.getLogger().info("RIGHTSIDE: Button at " + x + "/" + y + " pressed");

					});
					
					button.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							
							if(player2.getSecondIteration() == false) {
								player2.setXfirst(x);
								player2.setYfirst(y);
								return;
							}
							
							player2.setShipPartsGui( x, y, game.board2);
							player2.setSecondIterationOnFalse();
							//Eine Art update-Methode, in der der Spielstand aktualisiert und visualisiert wird;
						}
					});

					gameFieldRIGHT.add(button, row, column);

				}

			}

			rootGame.getChildren().add(imgGameBG);
			// rootGame.setTop(new ToolBar());
			rootGame.setCenter(gameBoards);
			rootGame.setBottom(bottom);
			rootGame.setTop(top);
			rootGame.setLeft(left);
			rootGame.setRight(right);

			Scene gameScene = new Scene(rootGame);
			gameScene.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.Q) {
					gameStage.close();

					try {
						GameMenu gameMenu = new GameMenu();
						gameMenu.GameMenu();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			});
			gameStage.setTitle("BoatBusters");
			gameStage.setWidth(WINDOW_SIZE_X);
			gameStage.setHeight(WINDOW_SIZE_Y);
			gameStage.setScene(gameScene);
			// gameStage.setResizable(false);
			gameStage.show();

		}
	}

}
