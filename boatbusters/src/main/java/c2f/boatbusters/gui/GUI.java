package c2f.boatbusters.gui;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import c2f.boatbusters.classes.Game;
import c2f.boatbusters.classes.GameLog;
import c2f.boatbusters.classes.Highscore;
import c2f.boatbusters.classes.Main;
import c2f.boatbusters.classes.Player;
import c2f.boatbusters.classes.SetShipException;
import c2f.boatbusters.classes.WarShip;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.effect.*;

public class GUI extends Application {

	final int WINDOW_SIZE_X = 1280;
	final int WINDOW_SIZE_Y = 720;
	final int translateX = 200;
	final int translateY = 300;
	final static int MENUBUTTON_SIZE_X = 250;
	final static int MENUBUTTON_SIZE_Y = 30;
	final static int CELLBUTTON_SIZE_X_Y = 30;
	final static int GAMEBUTTON_SIZE_X = 150;
	final static int GAMEBUTTON_SIZE_Y = 30;
	final static String font14 = "-fx-font: italic 14px Verdana";
	final static String font16 = "-fx-font: italic 16px Verdana";
	final static String font20 = "-fx-font: italic 20px Verdana";
	final static String font30 = "-fx-font: italic 30px Verdana";

	String namePlayer1, namePlayer2;
	Player player1;
	Player player2;
	Game game;
	WarShip[][] board1;
	WarShip[][] board2;
	WarShip shooterPlayer1;
	WarShip shooterPlayer2;

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane rootStart = new BorderPane();
		rootStart.setPrefSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

		GridPane startGridPane = new GridPane();
		StackPane startBox = new StackPane();

		startBox.setTranslateX(-400);
		startBox.setTranslateY(MENUBUTTON_SIZE_Y);

		startGridPane.setTranslateX((WINDOW_SIZE_X / 2));
		startGridPane.setTranslateY((WINDOW_SIZE_Y / 2) - MENUBUTTON_SIZE_Y);

		InputStream is = Files.newInputStream(Paths.get("src/main/resources/bg2.jpg"));
		Image img = new Image(is);
		is.close();

		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(0.1);
		colorAdjust.setHue(-0.05);
		colorAdjust.setBrightness(0.1);
		colorAdjust.setSaturation(0.2);

		ImageView imgHome = new ImageView(img);
		imgHome.setFitWidth(WINDOW_SIZE_X + 20);
		imgHome.setFitHeight(WINDOW_SIZE_Y + 20);
		imgHome.setEffect(colorAdjust);

		Rectangle bg = new Rectangle(MENUBUTTON_SIZE_X, MENUBUTTON_SIZE_Y);
		bg.setOpacity(0.4);
		bg.setFill(Color.DIMGREY);
		bg.setStroke(Color.WHITE);

		Text welcomeText = new Text("PRESS ANY KEY TO CONTINUE");
		welcomeText.setStyle(font14);
		welcomeText.setFill(Color.WHITE);
		welcomeText.setOnMouseClicked(event -> {

			primaryStage.close();
			try {
				GameMenu gameMenu = new GameMenu();
				gameMenu.GameMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		startBox.setOnMouseEntered(event -> {
			bg.setOpacity(0.6);
		});
		startBox.setOnMousePressed(event -> {
			welcomeText.setFill(Color.ROYALBLUE);
			bg.setFill(Color.WHITE);
		});
		startBox.setOnMouseReleased(event -> {
			welcomeText.setFill(Color.WHITE);
			bg.setFill(Color.DIMGREY);
		});
		startBox.setOnMouseExited(event -> {
			bg.setOpacity(0.3);
			welcomeText.setFill(Color.WHITE);
		});

		Text welcomeMessage0 = new Text("WELCOME TO BOATBUSTERS");
		welcomeMessage0.setStyle(font30);
		welcomeMessage0.setFill(Color.WHITE);
		welcomeMessage0.setTranslateY(-50);
		welcomeMessage0.setTranslateX(-400);

		startBox.getChildren().addAll(bg, welcomeText);
		startGridPane.getChildren().addAll(welcomeMessage0, startBox);
		rootStart.getChildren().addAll(imgHome, startGridPane);

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
			text.setStyle(font14);
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(MENUBUTTON_SIZE_X, MENUBUTTON_SIZE_Y);
			bg.setOpacity(0.5);
			bg.setFill(Color.BLACK);
			bg.setStroke(Color.ROYALBLUE);
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);

			// Maus betritt MenuButtonbereich
			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setStroke(Color.TRANSPARENT);
				bg.setFill(Color.ROYALBLUE);

			});
			setOnMousePressed(event -> {
				bg.setOpacity(0.3);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
				bg.setStroke(Color.ROYALBLUE);
			});
			setOnMouseReleased(event -> {
				bg.setOpacity(0.5);
				bg.setFill(Color.ROYALBLUE);
				text.setFill(Color.WHITE);
			});
			// Maus verlässt MenuButtonbereich
			setOnMouseExited(event -> {
				bg.setOpacity(0.5);
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setStroke(Color.ROYALBLUE);
				bg.setFill(Color.BLACK);

			});

		}
	}

	public class CellButton extends GridPane {
		Text text;

		public CellButton() {
			// text = new Text(name);
			// text.setFont(text.getFont());
			// text.setFill(Color.BLACK);

			Rectangle bg = new Rectangle(CELLBUTTON_SIZE_X_Y, CELLBUTTON_SIZE_X_Y);

			bg.setFill(Color.GHOSTWHITE);
			bg.setOpacity(0.4);
			bg.setStroke(Color.TRANSPARENT);

			getChildren().addAll(bg);

			// wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setFill(Color.DIMGREY);
				bg.setOpacity(0.4);

			});
			// wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setFill(Color.GHOSTWHITE);
				bg.setOpacity(0.4);
			});
		}

	}

	public class GameButton extends StackPane {
		Text text;

		public GameButton(String name) {
			text = new Text(name);
			text.setStyle(font14);
			text.setFill(Color.WHITE);
			text.setTextAlignment(TextAlignment.RIGHT);

			Rectangle bg = new Rectangle(GAMEBUTTON_SIZE_X - 10, GAMEBUTTON_SIZE_Y);
			bg.setFill(Color.DIMGRAY);
			bg.setOpacity(0.4);
			bg.setStroke(Color.BLACK);
			setAlignment(Pos.CENTER);

			setOnMousePressed(event -> {
				text.setFill(Color.ORANGE);
			});

			setOnMouseReleased(event -> {
				text.setFill(Color.WHITE);
			});

			setOnMouseEntered(event -> {
				bg.setStroke(Color.ORANGE);
			});

			setOnMouseExited(event -> {
				bg.setStroke(Color.BLACK);
			});

			getChildren().addAll(bg, text);
		}
	}

	public class GameMenu {

		public void GameMenu() throws Exception {

			game = new Game(1);

			Stage menuStage = new Stage();
			Pane rootMenu = new Pane();

			InputStream is = Files.newInputStream(Paths.get("src/main/resources/ShipBg.jpg"));
			Image imgShip = new Image(is);
			is.close();

			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setContrast(0.001);
			colorAdjust.setBrightness(0.001);

			ImageView imgView = new ImageView(imgShip);
			imgView.setFitWidth(WINDOW_SIZE_X + 20);
			imgView.setFitHeight(WINDOW_SIZE_Y + 20);
			imgView.setEffect(colorAdjust);

			VBox mainMenu = new VBox(10); // main menu
			VBox scoreMenu = new VBox(10); // highscore menu

			VBox menuPlayer1 = new VBox(10); // player 1 sub menu
			VBox menuPlayer2 = new VBox(10); // player 2 sub menu

			mainMenu.setTranslateX(translateX);
			mainMenu.setTranslateY(translateY);

			scoreMenu.setTranslateX(translateX);
			scoreMenu.setTranslateY(200);

			menuPlayer1.setTranslateX(translateX);
			menuPlayer1.setTranslateY(translateY);

			menuPlayer2.setTranslateX(translateX);
			menuPlayer2.setTranslateY(translateY);

			final int offset = 400;

			scoreMenu.setTranslateX(offset);
			menuPlayer1.setTranslateX(offset);
			menuPlayer2.setTranslateX(offset);

			//////////// START GAME BUTTON MAIN MENU ////////////
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

			//////////// HIGHSCORE BUTTON MAIN MENU ////////////
			MenuButton btnScore = new MenuButton("HIGHSCORE");//TODO:
			MenuButton btnBack2 = new MenuButton("BACK");

			btnScore.setOnMouseClicked(event -> {
				
				rootMenu.getChildren().add(scoreMenu);
				
				
//				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
//				t1.setToX(mainMenu.getTranslateX() - offset);
//
//				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), scoreMenu);
//				t2.setToX(mainMenu.getTranslateX());
//
//				t1.play();
//				t2.play();

//				t1.setOnFinished(evt -> {
					
					rootMenu.getChildren().remove(mainMenu);
					updateHighscore(scoreMenu, btnBack2);
//				});

			});

			//////////// EXIT BUTTON MAIN MENU ////////////
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				game.quit(Main.getHighscore());
			});

			//////////// Login Text Player1 ////////////
			Text loginTextPlayer1 = new Text("Login: Player 1");
			loginTextPlayer1.setStyle(font30);
			loginTextPlayer1.setFill(Color.WHITE);

			//////////// Login Text Player2 ////////////
			Text loginTextPlayer2 = new Text("Login: Player 2");
			loginTextPlayer2.setStyle(font30);
			loginTextPlayer2.setFill(Color.WHITE);

			
			//////////// Textfield Login Player 1 ////////////
			TextField textfieldLoginPlayer1 = new TextField();
			textfieldLoginPlayer1.setStyle(font20);
			textfieldLoginPlayer1.setPromptText("Username");

			//////////// Login Player 1 ENTER ////////////
			textfieldLoginPlayer1.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {

					namePlayer1 = textfieldLoginPlayer1.getText();

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

			//////////// Textfield Login Player 2 ////////////
			TextField textfieldLoginPlayer2 = new TextField();
			textfieldLoginPlayer2.setStyle(font20);
			textfieldLoginPlayer2.setPromptText("Username");

			//////////// Login Player 2 ENTER (START GAME) ////////
			textfieldLoginPlayer2.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {

					namePlayer1 = textfieldLoginPlayer1.getText();
					namePlayer2 = textfieldLoginPlayer2.getText();
					game.startGame(game, namePlayer1, namePlayer2);

					player1 = game.getPlayer1();
					player2 = game.getPlayer2();

					board1 = game.board1;
					board2 = game.board2;

					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
					t1.setToX(menuPlayer2.getTranslateX() - offset);

					t1.play();

					t1.setOnFinished(evt -> {
						rootMenu.getChildren().remove(menuPlayer2);

					});

					menuStage.close();
					GameField gameField = new GameField();
					try {
						gameField.GameField();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});

			//////////// Login Player 1 MAUS ////////////
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
			////////////////////////////////////////////////////////

			//////////// Back to MainMenu from Login 1 ////////////
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
			//////////////////////////////////////////////////////////

			//////////// Login Player 2 MAUS (Start Game) ////////////
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!");
			btnLoginPlayer2.setOnMouseClicked(event -> {

				namePlayer1 = textfieldLoginPlayer1.getText();
				namePlayer2 = textfieldLoginPlayer2.getText();

				game.startGame(game, namePlayer1, namePlayer2);

				player1 = game.getPlayer1();
				player2 = game.getPlayer2();

				board1 = game.board1;
				board2 = game.board2;

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
			//////////////////////////////////////////////////////

			//////////// Back to Login 1 from Login 2 ////////////
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
			/////////////////////////////////////////////////////////

			//////////// Back to MainMenu from HIGHSCORE ////////////
			
			btnBack2.setOnMouseClicked(event -> {

				rootMenu.getChildren().add(mainMenu);

//				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), scoreMenu);
//				tt.setToX(scoreMenu.getTranslateX() + offset);
//
//				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
//				tt1.setToX(scoreMenu.getTranslateX());
//				tt1.setToY(scoreMenu.getTranslateY() + 100);
//
//				tt.play();
//				tt1.play();
//
//				tt.setOnFinished(evt -> {
					rootMenu.getChildren().remove(scoreMenu);

//				});
			});
			/////////////////////////////////////////////////////////

			mainMenu.getChildren().addAll(btnStart, btnScore, btnExit); // Hauptmenu
			menuPlayer1.getChildren().addAll(loginTextPlayer1, textfieldLoginPlayer1, btnLoginPlayer1, btnBackPlayer1); // login1
			menuPlayer2.getChildren().addAll(loginTextPlayer2, textfieldLoginPlayer2, btnLoginPlayer2, btnBackPlayer2); // login2

			rootMenu.getChildren().addAll(imgView, mainMenu);

			Scene menuScene = new Scene(rootMenu);
			menuStage.setTitle("BoatBusters");
			menuStage.setWidth(WINDOW_SIZE_X);
			menuStage.setHeight(WINDOW_SIZE_Y);
			menuStage.setScene(menuScene);
			menuStage.show();

		}

		
	}
	
	private void updateHighscore(VBox scoreMenu, MenuButton back){
		//TODO:
		TableView table = new TableView();
		
		
		TableColumn firstCol = new TableColumn("NAME");
		firstCol.setMinWidth(250);
		TableColumn secCol = new TableColumn("SCORE");
		secCol.setMinWidth(250);
		table.setPlaceholder(new Label("Sorry, kein Zugriff auf bestenliste.csv.\n"
				+ "Ein Restart ihres Computers, sollte das Problem beheben.\n"
				+ "Bei weiteren Fragen wenden Sie sich bitte an das Entwicklerteam."));
		
		table.getColumns().addAll(firstCol, secCol);
		scoreMenu.getChildren().addAll(table, back);
	}

	public class GameField extends Parent {

		Text textQuit = new Text();

		// Anzeigetexte für linke Spielhälfte, d.h. Spieler 1, wieviele Schiffe
		// er/sie noch setzen muss
		Text textSmallLeft = new Text();
		Text textMiddleLeft = new Text();
		Text textBigLeft = new Text();

		// Anzeigetexte für rechte Spielhälfte, d.h. Spieler 2, wieviele Schiffe
		// er/sie noch setzen muss
		Text textSmallRight = new Text();
		Text textMiddleRight = new Text();
		Text textBigRight = new Text();

		Text textTurnTop = new Text();
		Text textWhosNextTop = new Text();
		Text textHitTop = new Text();
		Text textMissedTop = new Text();
		Text textWinner = new Text();

		Text textP1Name = new Text();
		Text textP2Name = new Text();

		public void setTextWinner() {

			this.textWinner = new Text("Congratulations, you have won the game!");
			textWinner.setStyle(font14);
			textWinner.setFill(Color.WHITE);
		}


		public void setTextQuit() {
			this.textQuit = new Text(" Press Q to Quit");
			textQuit.setStyle(font14);
			textQuit.setFill(Color.WHITE);
		}

		public void setTextHitTop() {
			this.textHitTop = new Text("HIT!");
			textHitTop.setStyle(font16);
			textHitTop.setFill(Color.LIGHTCORAL);
		}

		public void setTextMissedTop() {
			this.textMissedTop = new Text("You Missed!");
			textMissedTop.setStyle(font16);
			textMissedTop.setFill(Color.ROYALBLUE);
		}

		public void setTextTurnTop() {
			this.textTurnTop = new Text("Turn: " + game.getRound());
			textTurnTop.setStyle(font14);
			textTurnTop.setFill(Color.WHITE);
		}

		public void setTextWhosNextTop() {
			this.textWhosNextTop = new Text("Your Turn: " + game.textWhosNext());
			textWhosNextTop.setStyle(font14);
			textWhosNextTop.setFill(Color.WHITE);
		}

		// Getter und Setter der Texte, wie viele Schiffe noch gesetzt werden
		// müssen
		// für linke Spielhälfte, d.h. die von Spieler 1
		public void setTextSmallLeft() {
			this.textSmallLeft = new Text(" " + player1.getCountSmall() + " small Ships left");
			textSmallLeft.setStyle(font14);
			textSmallLeft.setFill(Color.WHITE);
		}

		public void setTextMiddleLeft() {
			this.textMiddleLeft = new Text(" " + player1.getCountMiddle() + " middle Ships left");
			textMiddleLeft.setStyle(font14);
			textMiddleLeft.setFill(Color.WHITE);
		}

		public void setTextBigLeft() {
			this.textBigLeft = new Text(" " + player1.getCountBig() + " big Ships left");
			textBigLeft.setStyle(font14);
			textBigLeft.setFill(Color.WHITE);
		}

		public void setTextP1NameColor() {
			this.textP1Name = new Text("Player 1 wins!");
			textP1Name.setStyle(font20);
			textP1Name.setFill(Color.ORANGE);
		}

		public void setTextP2NameColor() {
			this.textP2Name = new Text("Player 2 wins!");
			textP2Name.setStyle(font20);
			textP2Name.setFill(Color.ORANGE);
		}

		public Text getTextQuit() {
			return textQuit;
		}

		public Text getTextWinner() {
			return textWinner;
		}

		public Text getP1NameColor() {
			return textP1Name;
		}

		public Text getP2NameColor() {
			return textP2Name;
		}

		public Text getTextTurnTop() {
			return textTurnTop;
		}

		public Text getTextWhosNextTop() {
			return textWhosNextTop;
		}

		public Text getTextHitTop() {
			return textHitTop;
		}

		public Text getTextMissedTop() {
			return textMissedTop;
		}

		public Text getTextSmallLeft() {
			return textSmallLeft;
		}

		public Text getTextMiddleLeft() {
			return textMiddleLeft;
		}

		public Text getTextBigLeft() {
			return textBigLeft;
		}

		// Getter und Setter der Texte, wie viele Schiffe noch gesetzt werden
		// müssen
		// für rechte Spielhälfte, d.h. die von Spieler 2
		public void setTextSmallRight() {
			this.textSmallRight = new Text(" " + player2.getCountSmall() + " small Ships left");
			textSmallRight.setStyle(font14);
			textSmallRight.setFill(Color.WHITE);
		}

		public void setTextMiddleRight() {
			this.textMiddleRight = new Text(" " + player2.getCountMiddle() + " middle Ships left");
			textMiddleRight.setStyle(font14);
			textMiddleRight.setFill(Color.WHITE);
		}

		public void setTextBigRight() {
			this.textBigRight = new Text(" " + player2.getCountBig() + " big Ships left");
			textBigRight.setStyle(font14);
			textBigRight.setFill(Color.WHITE);
		}

		public Text getTextSmallRight() {
			return textSmallRight;
		}

		public Text getTextMiddleRight() {
			return textMiddleRight;
		}

		public Text getTextBigRight() {
			return textBigRight;
		}

		public void GameField() throws Exception {

			Stage gameStage = new Stage();

			BorderPane rootGame = new BorderPane();

			InputStream is = Files.newInputStream(Paths.get("src/main/resources/bg2.jpg"));
			Image img = new Image(is);
			is.close();

			// ColorAdjust colorAdjust = new ColorAdjust();
			// colorAdjust.setContrast(0.001);
			// colorAdjust.setHue(-0.05);
			// colorAdjust.setBrightness(0.001);
			// colorAdjust.setSaturation(0.2);

			ImageView imgGameBG = new ImageView(img);
			imgGameBG.setFitWidth(WINDOW_SIZE_X);
			imgGameBG.setFitHeight(WINDOW_SIZE_Y);
			// imgGameBG.setEffect(colorAdjust);

			HBox gameBoardsHBox = new HBox();
			gameBoardsHBox.setSpacing(100);
			gameBoardsHBox.setPadding(new Insets(100, 0, 0, 0));

			GridPane gamefieldLeft = new GridPane();
			GridPane gamefieldRight = new GridPane();
			gameBoardsHBox.setAlignment(Pos.BASELINE_CENTER);

			StackPane leftStackPane = new StackPane();
			VBox textVBoxRight = new VBox();

			StackPane rightStackPane = new StackPane();
			VBox textVBoxLeft = new VBox();

			StackPane topStackPane = new StackPane();
			VBox textVBoxTop = new VBox();

			StackPane bottomStackPane = new StackPane();
			VBox textVBoxBottom = new VBox();
			HBox textHBoxBottom = new HBox();

			setTextQuit();
			gameBoardsHBox.getChildren().addAll(gamefieldLeft, gamefieldRight);

			//////////// Gamefield Right ////////////
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					CellButton button = new CellButton();
					int y = column;
					int x = row;

					button.setOnMouseClicked(event -> {
						// Main.getLogger().info("LEFTSIDE: Button at " + x +
						// "/" + y + " pressed");

						// Wenn die Spieler noch nicht alle Schiffe gesetzt
						// haben sowie noch nicht auf ready geklickt haben
						if (player1.getReady() == true && player2.getReady() == false) {
							Main.getLogger().info("It's the turn of " + namePlayer2 + " to set his Ships.\n"
									+ namePlayer1 + ", please wait until the fire mode begins");
						}
						// Zunächst ist Spieler 1 mit dem Setzen seiner Schiffe
						// (auf sein eigenes Feld) dran
						else if (player1.getReady() == false) {
							// Die Schiffe werden gesetzt durch Klick auf die
							// Anfangskoordinate und einen Klick
							// auf die Endkoordinate. Beim Klick auf die
							// Anfangskoordinate werden diese zunächst
							// zwischengespeichert in den Instanzvariablen
							// xfirst und yfirst des jeweiligen Spielers
							if (player1.getSecondClick() == false) {
								player1.setXfirst(x);
								player1.setYfirst(y);
								player1.setSecondClick(true);
								// Beim zweiten Klick, werden die Endkoordinaten
								// zusammen mit dem Board von Spieler 2
								// sowie mit Spieler 2 selbst an die
								// setShipPartsGui Methode übergeben
							} else if (player1.getSecondClick() == true) {
								// der try-catch-Block fängt die eigens kreierte
								// SetShipException ab, die geworfen wird,
								// wenn jemand sein Schiff falsch setzt. (Genaue
								// Erklärung des falschen Setzens
								// in der Error Log Nachricht unten)
								try {
									player1.setShipPartsGui(x, y, game.board1, player1);
								} catch (SetShipException e) {
									// Error Nachricht an Spieler, warum das
									// Schiff nicht gesetzt werdeb konnte
									Main.getLogger().error("Achtung. falsche Koordinaten-Eingabe, "
											+ "Sie können ihr Schiff so nicht setzen!\nFolgendes kann "
											+ "schief gelaufen sein:\n1. Sie haben versucht, ihr Schiff "
											+ "diagonal oder quer zu setzen.\n2. Sie haben versucht, "
											+ "ihr Schiff auf Zellen / Koordinaten zu setzen, auf denen sich bereits "
											+ "Schiffe befinden.\n3. Sie haben ein zu langes oder zu kurzes Schiff "
											+ "gesetzt.\n4. Sie haben kein Schiff der gewählten Länge mehr verfügbar.");
								}
								player1.setSecondClick(false);
								updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
								gameUpdate(textVBoxTop);

							}
						}
						// Wenn beide Spieler alle Schiffe gesetzt haben und
						// Spieler 2 mit feuern dran ist (Runde 1, 3, 5, 7 ...)
						if (player2.getReady() == true && player1.getReady() == true && (game.getRound() % 2) != 0
								&& !game.gameOver()) {

							player2.fire(x, y, player2, game);
							updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
							gameUpdate(textVBoxTop);

							// Es wird überpüft, ob der
							// Spieler mit dem aktuellen Mausklick / Feuern das
							// letzte Schiff des Gegners zerstört hat,
							// ob er also den Score von 21 Punkten erreicht und
							// das Spiel gewonnen hat
							if (player2.checkIfPlayerWins()) {
								Main.getLogger().info("Herzlichen Glückwunsch " + player2.getName() + ", du hast "
										+ "das Spiel gewonnen und grenzenlose Ehre erworben!");
								// game.updateBestenliste();
							}
							game.increaseRound();

						} else if (player2.getReady() && player1.getReady() && game.getRound() % 2 == 0
								&& !game.gameOver()) {
							Main.getLogger().info("It's the turn of " + namePlayer1 + " to fire.\n" + namePlayer2
									+ ", please wait until you it's your turn");
						}

					});

					gamefieldLeft.add(button, row, column);
				}

			}
			////////////////////////////////////////

			//////////// GameField Left ////////////
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					CellButton button = new CellButton();

					int y = column;
					int x = row;

					button.setOnMouseClicked(event -> {
						// Main.getLogger().info("RIGHTSIDE: Button at " + x +
						// "/" + y + " pressed");

						// Wenn die Spieler noch nicht alle Schiffe gesetzt
						// haben sowie noch nicht auf ready geklickt haben
						if (player1.getReady() == false && player2.getReady() == false) {
							Main.getLogger().info("It's the turn of " + namePlayer1 + " to set his Ships.\n"
									+ namePlayer2 + ", please wait until you it's your turn");
						}
						// Wenn Spieler 1 seine Schiffe gesetzt hat und auf den
						// Ready Button geklickt hat,
						// ist Spieler 2 dran mit Schiffe setztn
						else if (player1.getReady() && player2.getReady() == false) {
							// Die Schiffe werden gesetzt durch Klick auf die
							// Anfangskoordinate und einen Klick
							// auf die Endkoordinate. Beim Klick auf die
							// Anfangskoordinate werden diese zunächst
							// zwischengespeichert in den Instanzvariablen
							// xfirst und yfirst des jeweiligen Spielers
							if (player2.getSecondClick() == false) {
								player2.setXfirst(x);
								player2.setYfirst(y);
								player2.setSecondClick(true);
								// Beim zweiten Klick, werden die Endkoordinaten
								// zusammen mit dem Board von Spieler 2
								// sowie mit Spieler 2 selbst an die
								// setShipPartsGui Methode übergeben
							} else if (player2.getSecondClick() == true) {
								// der try-catch-Block fängt die eigens kreierte
								// SetShipException ab, die geworfen wird,
								// wenn jemand sein Schiff falsch setzt. (Genaue
								// Erklärung des falschen Setzens
								// in der Error Log Nachricht unten)
								try {
									player2.setShipPartsGui(x, y, game.board2, player2);
								} catch (SetShipException e) {
									// Error Nachricht an Benutzer, warum das
									// Schiff nicht gesetzt werdeb konnte
									Main.getLogger().error("Achtung. falsche Koordinaten-Eingabe, "
											+ "Sie können ihr Schiff so nicht setzen!\nFolgendes kann "
											+ "schief gelaufen sein:\n1. Sie haben versucht, ihr Schiff "
											+ "diagonal oder quer zu setzen.\n2. Sie haben versucht, "
											+ "ihr Schiff auf Zellen / Koordinaten zu setzen, auf denen sich bereits "
											+ "Schiffe befinden.\n3. Sie haben ein zu langes oder zu kurzes Schiff "
											+ "gesetzt.\n4. Sie haben kein Schiff der gewählten Länge mehr verfügbar.");
								}
								player2.setSecondClick(false);
								updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
								gameUpdate(textVBoxTop);

							}
						}
						// Wenn beide Spieler alle Schiffe gesetzt haben und
						// Spieler 1 mit feuern dran ist (Runde 2, 4, 6, 8, ...)
						if (player2.getReady() && player1.getReady() && game.getRound() % 2 == 0 && !game.gameOver()) {

							player1.fire(x, y, player1, game);
							updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
							gameUpdate(textVBoxTop);

							// Es wird überpüft, ob der
							// Spieler mit dem aktuellen Mausklick / Feuern das
							// letzte Schiff des Gegners zerstört hat,
							// ob er also den Score von 21 Punkten erreicht und
							// das Spiel gewonnen hat
							if (player1.checkIfPlayerWins()) {
								Main.getLogger().info("Herzlichen Glückwunsch " + player1.getName() + ", du hast "
										+ "das Spiel gewonnen und grenzenlose Ehre erworben!");
								 Main.getHighscore().updateBestenliste(player1);
							}
							game.increaseRound();

						} else if (player2.getReady() && player1.getReady() && game.getRound() % 2 != 0
								&& !game.gameOver()) {
							Main.getLogger().info("It's the turn of " + namePlayer2 + " to fire.\n" + namePlayer1
									+ ", please wait until you it's your turn");
						}

					});

					gamefieldRight.add(button, row, column);

				}

			}

			//////////// TOP ////////////
			Rectangle bgTopBox1 = new Rectangle(500, 100);
			Rectangle bgTopBox2 = new Rectangle(800, 120);

			bgTopBox1.setOpacity(0.5);
			bgTopBox1.setFill(Color.DIMGREY);
			bgTopBox1.setStroke(Color.LIGHTGRAY);

			bgTopBox2.setOpacity(0.5);
			bgTopBox2.setFill(Color.DIMGRAY);
			bgTopBox2.setStroke(Color.ROYALBLUE);

			// textVBoxTop.setTranslateX(WINDOW_SIZE_X / 2);
			// textVBoxTop.setTranslateY(25);
			textVBoxTop.setAlignment(Pos.CENTER);

			Text textInfoTop = new Text("Boat Busters");
			textInfoTop.setStyle(font30);
			textInfoTop.setFill(Color.ORANGE);

			Text textP1Set = new Text("Player 1: set your Ships and press READY!");
			textP1Set.setStyle(font16);
			textP1Set.setFill(Color.WHITE);

			Text textP2Set = new Text("Player 2: set your Ships and press READY!");
			textP2Set.setStyle(font16);
			textP2Set.setFill(Color.WHITE);

			textVBoxTop.getChildren().addAll(textInfoTop, textP1Set);
			topStackPane.getChildren().addAll(bgTopBox1, textVBoxTop);
			//////////////////////////////////

			//////////// LEFTSIDE ////////////
			Rectangle bgLeftBox = new Rectangle(150, 500);
			bgLeftBox.setOpacity(0.4);
			bgLeftBox.setFill(Color.DIMGREY);
			bgLeftBox.setStroke(Color.LIGHTGRAY);

			textVBoxLeft.setSpacing(10);

			Text textPlayerLeft = new Text(" Player 1:");
			textPlayerLeft.setStyle(font20);
			textPlayerLeft.setFill(Color.MEDIUMSPRINGGREEN);

			GameButton btnResetLeft = new GameButton("reset Ships");
			btnResetLeft.setOnMouseClicked(event -> {
				game.setShipsBackBoard1(board1);
				updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
			});

			GameButton btnReadyLeft = new GameButton("READY!");
			btnReadyLeft.setOnMouseClicked(event -> {

				if (player1.getShipCountCheck() == 0) {
					player1.setReady(true);

					if (player1.getReady() == true && player2.getReady() == false) {

						textVBoxLeft.getChildren().removeAll(btnReadyLeft, btnResetLeft);
						textVBoxTop.getChildren().removeAll(textP1Set);
						textVBoxTop.getChildren().add(textP2Set);
					}
					darkenField(gamefieldLeft);
				}

			});

			textVBoxLeft.getChildren().addAll(textPlayerLeft, btnResetLeft, btnReadyLeft);
			leftStackPane.getChildren().addAll(bgLeftBox, textVBoxLeft);
			//////////////////////////////////////////

			//////////// RIGHTSIDE ////////////
			Rectangle bgRightBox = new Rectangle(150, 500);
			bgRightBox.setOpacity(0.4);
			bgRightBox.setFill(Color.DIMGREY);
			bgRightBox.setStroke(Color.LIGHTGRAY);

			textVBoxRight.setSpacing(10);

			Text textPlayerRight = new Text(" Player 2:");
			textPlayerRight.setStyle(font20);
			textPlayerRight.setFill(Color.BLUE);

			GameButton btnResetRight = new GameButton("reset Ships");
			btnResetRight.setOnMouseClicked(event -> {
				game.setShipsBackBoard2(board2);
				updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
			});

			GameButton btnReadyRight = new GameButton("READY!");
			btnReadyRight.setOnMouseClicked(event -> {
				if (player2.getShipCountCheck() == 0) {
					player2.setReady(true);

					if (player2.getReady() == true && player1.getReady() == true) {
						textVBoxRight.getChildren().removeAll(btnReadyRight, btnResetRight, getTextSmallRight(),
								getTextMiddleRight(), getTextBigRight());
						rightStackPane.getChildren().removeAll(textVBoxRight, bgRightBox);
						leftStackPane.getChildren().removeAll(textVBoxLeft, bgLeftBox);
						topStackPane.getChildren().removeAll(bgTopBox1, textVBoxTop);
						topStackPane.getChildren().addAll(bgTopBox2, textVBoxTop);

						textVBoxTop.getChildren().remove(textP2Set);
						textVBoxTop.getChildren().addAll(getTextWhosNextTop());
						gameUpdate(textVBoxTop);
						// Thread wird erstellt, der durch Logs den aktuellen
						// Spielstand, d.h. die Scores von Spieler 1
						// und von Spieler 2 sowie die aktuelle Runde ausgibt
						Thread log = new Thread(new GameLog(game));
						log.start();
					}
					if (player1.getReady()) {
						darkenField(gamefieldRight);
					}
				}

			});

			textVBoxRight.getChildren().addAll(textPlayerRight, btnResetRight, btnReadyRight);
			rightStackPane.getChildren().addAll(bgRightBox, textVBoxRight);
			//////////////////////////////////

			//////////// BOTTOM ////////////
			Rectangle bgBottomBox = new Rectangle(WINDOW_SIZE_X, 100);
			bgBottomBox.setOpacity(0.5);
			bgBottomBox.setFill(Color.DIMGRAY);
			bgBottomBox.setStroke(Color.LIGHTGRAY);

			textHBoxBottom.setSpacing(250);
			textHBoxBottom.setAlignment(Pos.BASELINE_CENTER);
			Text textPlayer1 = new Text("Player 1: " + player1.getName());
			textPlayer1.setStyle(font30);
			textPlayer1.setFill(Color.WHITE);

			Text textPlayer2 = new Text("Player 2: " + player2.getName());
			textPlayer2.setStyle(font30);
			textPlayer2.setFill(Color.WHITE);

			textHBoxBottom.getChildren().addAll(textPlayer1, textPlayer2);
			textVBoxBottom.getChildren().addAll(textHBoxBottom, getTextQuit());
			bottomStackPane.getChildren().addAll(bgBottomBox, textVBoxBottom);
			//////////////////////////////////

			rootGame.getChildren().add(imgGameBG);
			rootGame.setCenter(gameBoardsHBox);
			rootGame.setBottom(bottomStackPane);
			rootGame.setTop(topStackPane);
			rootGame.setLeft(leftStackPane);
			rootGame.setRight(rightStackPane);

			updateFields(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
			gameUpdate(textVBoxTop);

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

		void updateFields(GridPane field1, GridPane field2, VBox left, VBox right) {
			// Zellen, auf die eine Referenz gesetzt ist, werden eingefärbt
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					// Zellen, auf denen noch unversehrte Schiffe sind, werden
					// blau dargestellt
					if (board1[i][j] != null && board1[i][j].getShipDestroyed() == false
							&& player1.getReady() == false) {
						field1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: green;");

					}
					// Zellen, auf denen ein Schiff ist, das aber versenkt, also
					// getroffen wurde, werden rot dargestellt
					else if (board1[i][j] != null && board1[i][j].getShipDestroyed() == true) {
						field1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: red;");
					}
					// Zellen, die die Referenz null haben, werden transparent
					// gemacht
					else if (board2[i][j] == null && player1.getReady() == false && player2.getReady() == false) {
						field1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: transparent;"); // TODO:
					} else if (board1[i][j] != null && board1[i][j].getEmpty()) {

						field1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: blue;");
					}
				}
			}
			// Zellen, auf die eine Referenz gesetzt ist, werden eingefärbt
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					// Zellen, auf denen noch unversehrte Schiffe sind, werden
					// blau dargestellt
					if (board2[i][j] != null && board2[i][j].getShipDestroyed() == false
							&& player2.getReady() == false) {
						field2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: green;");

					}
					// Zellen, auf denen ein Schiff ist, das aber versenkt, also
					// getroffen wurde, werden rot dargestellt
					else if (board2[i][j] != null && board2[i][j].getShipDestroyed() == true) {
						field2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: red;");

					}
					// Zellen, die die Referenz null haben, werden transparent
					// gemacht
					else if (board2[i][j] == null && player1.getReady() == true && player2.getReady() == false) {
						field2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: transparent;"); // TODO:
					} else if (board2[i][j] != null && board2[i][j].getEmpty()) {
						field2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: blue;");
					}
				}
			}
			// Anzeigetexte in GUI, wie viele Schiffe noch gesetzt werden
			// müssen, werden aktualisiert (VBox links Player1)
			if (player1.getReady() == false) {
				left.getChildren().removeAll(getTextSmallLeft(), getTextMiddleLeft(), getTextBigLeft());
				setTextSmallLeft();
				setTextMiddleLeft();
				setTextBigLeft();
				left.getChildren().addAll(getTextSmallLeft(), getTextMiddleLeft(), getTextBigLeft());
			}
			// Anzeigetexte in GUI, wie viele Schiffe noch gesetzt werden
			// müssen, werden aktualisiert (VBox rechts Player2)
			if (player2.getReady() == false) {
				right.getChildren().removeAll(getTextSmallRight(), getTextMiddleRight(), getTextBigRight());
				setTextSmallRight();
				setTextMiddleRight();
				setTextBigRight();
				right.getChildren().addAll(getTextSmallRight(), getTextMiddleRight(), getTextBigRight());
			}
			// if (player1.checkIfPlayerWins()){
			// gameField.getChildren().removeAll(field1, field2);
			//
			// }
			// if(player2.checkIfPlayerWins()){
			// gameField.getChildren().removeAll(field1, field2);
			// }
		}

		void darkenField(GridPane feld) {
			// Feld 2 wird komplett schwarz gefärbt, da die Spieler im
			// Feuer/Kampf Modus nicht sehen sollen
			// wo die Schiffe des anderen sind
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					feld.getChildren().get(i * 10 + j).setStyle("-fx-background-color: black;");
				}
			}
		}

		void gameUpdate(VBox top) {
			if (player1.getReady() == true && player2.getReady() == true) {

				top.getChildren().removeAll(getTextWhosNextTop(), getTextTurnTop(), getTextHitTop(),
						getTextMissedTop());
				if ((player1.getHit() == true && game.intWhosNext() != 1)
						|| (player2.getHit() == true && game.intWhosNext() != 2)) {
					setTextHitTop();
					top.getChildren().remove(getTextMissedTop());
					top.getChildren().add(getTextHitTop());
				}
				if ((player1.getMissed() == true && game.intWhosNext() != 1)
						|| (player2.getMissed() == true && game.intWhosNext() != 2)) {
					setTextMissedTop();
					top.getChildren().remove(getTextHitTop());
					top.getChildren().add(getTextMissedTop());
				}
				setTextTurnTop();
				setTextWhosNextTop();
				top.getChildren().addAll(getTextTurnTop(), getTextWhosNextTop());

			}

			if (player1.checkIfPlayerWins()) {
				setTextP1NameColor();
				setTextWinner();

				top.getChildren().removeAll(getTextTurnTop(), getTextWhosNextTop(), getTextHitTop());
				top.getChildren().addAll(getP1NameColor(), getTextWinner());
			}
			if (player2.checkIfPlayerWins()) {
				setTextP2NameColor();
				setTextWinner();

				top.getChildren().removeAll(getTextTurnTop(), getTextWhosNextTop(), getTextHitTop());
				top.getChildren().addAll(getP2NameColor(), getTextWinner());
			}

		}
		
	}
	
}
