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
import c2f.boatbusters.classes.WarShip;
import c2f.boatbusters.interfaces.IPlayer;
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
	final static String font14 = "-fx-font: italic 14px Verdana";
	final static String font20 = "-fx-font: italic 20px Verdana";
	final static String font30 = "-fx-font: italic 30px Verdana";

	String namePlayer1, namePlayer2;
	Player player1;
	Player player2;
	Game game;
	Highscore highscore;
	WarShip[][] board1;
	WarShip[][] board2;

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

		ImageView imgHome = new ImageView(img);
		imgHome.setFitWidth(WINDOW_SIZE_X + 20);
		imgHome.setFitHeight(WINDOW_SIZE_Y + 20);

		Rectangle bg = new Rectangle(MENUBUTTON_SIZE_X, MENUBUTTON_SIZE_Y);
		bg.setOpacity(0.3);
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
			bg.setOpacity(0.6);
			bg.setStroke(Color.TRANSPARENT);

			getChildren().addAll(bg);

			// wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setFill(Color.DIMGREY);
				bg.setOpacity(0.6);

			});
			// wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setFill(Color.GHOSTWHITE);
				bg.setOpacity(0.6);
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

			Rectangle bg = new Rectangle(SHIPBUTTON_SIZE_X - 10, SHIPBUTTON_SIZE_Y);
			bg.setFill(Color.DIMGRAY);
			bg.setOpacity(0.7);
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
			MenuButton btnScore = new MenuButton("HIGHSCORE");

			btnScore.setOnMouseClicked(event -> {
				rootMenu.getChildren().add(scoreMenu);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				t1.setToX(mainMenu.getTranslateX() - offset);

				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), scoreMenu);
				t2.setToX(mainMenu.getTranslateX());

				t1.play();
				t2.play();

				t1.setOnFinished(evt -> {
					rootMenu.getChildren().remove(mainMenu);
				});

			});

			//////////// EXIT BUTTON MAIN MENU ////////////
			MenuButton btnExit = new MenuButton("EXIT");
			btnExit.setOnMouseClicked(event -> {
				game.quit();
			});

			//////////// Login Text Player1 ////////////
			Text loginTextPlayer1 = new Text("Login: Player 1");
			loginTextPlayer1.setStyle(font30);
			loginTextPlayer1.setFill(Color.WHITE);

			//////////// Login Text Player2 ////////////
			Text loginTextPlayer2 = new Text("Login: Player 2");
			loginTextPlayer2.setStyle(font30);
			loginTextPlayer2.setFill(Color.WHITE);

			Text highscoreText = new Text("HIGHSCORE");

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

					System.out.println("asdasd");

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

					try {
						menuStage.close();
						GameField gameField = new GameField();
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
				System.out.println("asdasd");

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
			/////////////////////////////////////////////////////////

			mainMenu.getChildren().addAll(btnStart, btnScore, btnExit); // Hauptmenu
			scoreMenu.getChildren().addAll(highscoreText, btnBack2); // HighscoreMenu
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

	public class GameField extends Parent {

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

		private boolean player1Ready = false;
		private boolean player2Ready = false;

		public void GameField() throws Exception {

			Stage gameStage = new Stage();

			BorderPane rootGame = new BorderPane();

			InputStream is = Files.newInputStream(Paths.get("src/main/resources/bg2.jpg"));
			Image img = new Image(is);
			is.close();

			ImageView imgGameBG = new ImageView(img);
			imgGameBG.setFitWidth(WINDOW_SIZE_X);
			imgGameBG.setFitHeight(WINDOW_SIZE_Y);

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
			HBox textHBoxBottom = new HBox();

			gameBoardsHBox.getChildren().addAll(gamefieldLeft, gamefieldRight);

			//////////// Gamefield Right ////////////
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					CellButton button = new CellButton();
					int y = column;
					int x = row;

					button.setOnMouseClicked(event -> {
						Main.getLogger().info("LEFTSIDE: Button at " + x + "/" + y + " pressed");

						// if (player1.getSecondIteration() == false) {
						if (player1.getSecondClick() == false) {
							player1.setXfirst(x);
							player1.setYfirst(y);
							player1.setSecondClick(true);
						} else if (player1.getSecondClick() == true) {
							player1.setShipPartsGui(x, y, game.board1, player1);
							player1.setSecondClick(false);

							// TODO Eine Art update-Methode, in der der
							// Spielstand
							// aktualisiert und visualisiert wird;

							Main.getLogger().info("Player 1 Count Small Variable:" + player1.getCountSmall());
							Main.getLogger().info("Player 1 Count Middle Variable:" + player1.getCountMiddle());
							Main.getLogger().info("Player 1 Big Variable:" + player1.getCountBig());

							Main.getLogger().info(
									"Ist WarShip gesetzt auf Endkoordiante?\n" + "Speicheradresse: " + board1[x][y]);

							if (board1[x][y] == null) {
								Main.getLogger().info("Zelle mit Endkoordinaten hat Referenz null\n"
										+ "statt 'ship' oder 'warship', also anscheinend nein.");
							} else if (board1[x][y] != null) {
								Main.getLogger().info("#sotrue , Ja, hier ist ein Schiff");
							}
						}
						update(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);

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
						Main.getLogger().info("RIGHTSIDE: Button at " + x + "/" + y + " pressed");

						// if (player1.getSecondIteration() == false) {
						if (player2.getSecondClick() == false) {
							player2.setXfirst(x);
							player2.setYfirst(y);
							player2.setSecondClick(true);
						} else if (player2.getSecondClick() == true) {
							player2.setShipPartsGui(x, y, game.board2, player2);
							player2.setSecondClick(false);

							// TODO Eine Art update-Methode, in der der
							// Spielstand
							// aktualisiert und visualisiert wird;

							Main.getLogger().info("Player 2 Count Small Variable:" + player2.getCountSmall());
							Main.getLogger().info("Player 2  Middle Variable:" + player2.getCountMiddle());
							Main.getLogger().info("Player 2 Big Variable:" + player2.getCountBig());

							Main.getLogger().info(
									"Ist WarShip gesetzt auf Endkoordinate?\n" + "Speicheradresse: " + board2[x][y]);

							if (board2[x][y] == null) {
								Main.getLogger().info("Zelle mit Anfangskoordinaten hat Referenz null\n"
										+ "statt 'ship' oder 'warship', also anscheinend nein.");
							} else if (board2[x][y] != null) {
								Main.getLogger().info("#sotrue , Ja, hier ist ein Schiff");
							}

						}
						update(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);

					});

					gamefieldRight.add(button, row, column);

				}

			}

			//////////// TOP ////////////
			Rectangle bgTopBox = new Rectangle(600, 100);

			bgTopBox.setOpacity(0.4);
			bgTopBox.setFill(Color.DIMGRAY);
			bgTopBox.setStroke(Color.LIGHTGRAY);

			textVBoxTop.setTranslateX(WINDOW_SIZE_X / 2 - 180);
			textVBoxTop.setTranslateY(25);

			Text textInfo = new Text("Boat Busters");
			textInfo.setStyle(font30);
			textInfo.setFill(Color.ORANGE);

			Text textWaitTopP2 = new Text("wait for Player 2...");
			textWaitTopP2.setStyle(font14);
			textWaitTopP2.setFill(Color.WHITE);

			Text textWaitTopP1 = new Text("wait for Player 1...");
			textWaitTopP1.setStyle(font14);
			textWaitTopP1.setFill(Color.WHITE);

			Text textBeginTop = new Text("LET THE GAMES BEGIN!");
			textBeginTop.setStyle(font14);
			textBeginTop.setFill(Color.WHITE);

			textVBoxTop.getChildren().add(textInfo);
			topStackPane.getChildren().addAll(bgTopBox, textVBoxTop);
			//////////////////////////////////

			//////////// LEFTSIDE ////////////
			Rectangle bgLeftBox = new Rectangle(150, 500);
			bgLeftBox.setOpacity(0.4);
			bgLeftBox.setFill(Color.DIMGREY);
			bgLeftBox.setStroke(Color.LIGHTGRAY);

			textVBoxLeft.setSpacing(10);

			Text textLeft = new Text(" Press Q to Surrender");
			textLeft.setStyle(font14);
			textLeft.setFill(Color.WHITE);

			Text textPlayerLeft = new Text(" Player 1:");
			textPlayerLeft.setStyle(font20);
			textPlayerLeft.setFill(Color.MEDIUMSPRINGGREEN);

			GameButton btnResetLeft = new GameButton("reset Ships");
			btnResetLeft.setOnMouseClicked(event -> {
				game.setShipsBackBoard1(board1);
				update(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
			});

			GameButton btnReadyLeft = new GameButton("READY!");
			btnReadyLeft.setOnMouseClicked(event -> {
				if (player1.getShipCountCheck() == 0) {
					player1Ready = true;

					if (player1Ready == true && player2Ready == false) {
						textVBoxTop.getChildren().add(textWaitTopP2);
						textVBoxLeft.getChildren().removeAll(btnReadyLeft, btnResetLeft);
					}

					if (player2Ready == true && player1Ready == true) {
						textVBoxLeft.getChildren().removeAll(btnReadyLeft, btnResetLeft, getTextSmallLeft(),
								getTextMiddleLeft(), getTextBigLeft());

						textVBoxRight.getChildren().removeAll(getTextSmallRight(), getTextMiddleRight(),
								getTextBigRight());
						textVBoxTop.getChildren().remove(textWaitTopP1);
						textVBoxTop.getChildren().add(textBeginTop);
					}
				}
			});

			textVBoxLeft.getChildren().addAll(textLeft, textPlayerLeft, btnResetLeft, btnReadyLeft);
			leftStackPane.getChildren().addAll(bgLeftBox, textVBoxLeft);
			//////////////////////////////////////////

			//////////// RIGHTSIDE ////////////
			Rectangle bgRightBox = new Rectangle(150, 500);
			bgRightBox.setOpacity(0.4);
			bgRightBox.setFill(Color.DIMGREY);
			bgRightBox.setStroke(Color.LIGHTGRAY);

			textVBoxRight.setSpacing(10);

			Text textRight = new Text(" Press Q to Surrender");
			textRight.setStyle(font14);
			textRight.setFill(Color.WHITE);

			Text textPlayerRight = new Text(" Player 2:");
			textPlayerRight.setStyle(font20);
			textPlayerRight.setFill(Color.BLUE);

			GameButton btnResetRight = new GameButton("reset Ships");
			btnResetRight.setOnMouseClicked(event -> {
				game.setShipsBackBoard1(board2);
				update(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);
			});

			GameButton btnReadyRight = new GameButton("READY!");
			btnReadyRight.setOnMouseClicked(event -> {
				if (player2.getShipCountCheck() == 0) {
					player2Ready = true;

					if (player2Ready == true && player1Ready == false) {
						textVBoxTop.getChildren().add(textWaitTopP1);
						textVBoxRight.getChildren().removeAll(btnReadyRight, btnResetRight);
					}

					if (player2Ready == true && player1Ready == true) {
						textVBoxRight.getChildren().removeAll(btnReadyRight, btnResetRight, getTextSmallRight(),
								getTextMiddleRight(), getTextBigRight());

						textVBoxLeft.getChildren().removeAll(getTextSmallLeft(), getTextMiddleLeft(), getTextBigLeft());
						textVBoxTop.getChildren().remove(textWaitTopP2);
						textVBoxTop.getChildren().add(textBeginTop);
					}
				}
			});

			textVBoxRight.getChildren().addAll(textRight, textPlayerRight, btnResetRight, btnReadyRight);
			rightStackPane.getChildren().addAll(bgRightBox, textVBoxRight);
			//////////////////////////////////

			//////////// BOTTOM ////////////
			Rectangle bgBottomBox = new Rectangle(WINDOW_SIZE_X, 100);
			bgBottomBox.setOpacity(0.5);
			bgBottomBox.setFill(Color.DIMGRAY);
			bgBottomBox.setStroke(Color.LIGHTGRAY);

			textHBoxBottom.setPadding(new Insets(0, 0, 50, 0));
			textHBoxBottom.setSpacing(250);
			textHBoxBottom.setAlignment(Pos.BASELINE_CENTER);
			Text textPlayer1 = new Text("Player 1: " + player1.getName());
			textPlayer1.setStyle(font30);
			textPlayer1.setFill(Color.WHITE);

			Text textPlayer2 = new Text("Player 2: " + player2.getName());
			textPlayer2.setStyle(font30);
			textPlayer2.setFill(Color.WHITE);

			textHBoxBottom.getChildren().addAll(textPlayer1, textPlayer2);
			bottomStackPane.getChildren().addAll(bgBottomBox, textHBoxBottom);
			//////////////////////////////////

			rootGame.getChildren().add(imgGameBG);
			rootGame.setCenter(gameBoardsHBox);
			rootGame.setBottom(bottomStackPane);
			rootGame.setTop(topStackPane);
			rootGame.setLeft(leftStackPane);
			rootGame.setRight(rightStackPane);

			update(gamefieldLeft, gamefieldRight, textVBoxLeft, textVBoxRight);

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

		void update(GridPane feld1, GridPane feld2, VBox left, VBox right) {
			// Zellen, auf die eine Referenz gesetzt ist, werden eingefärbt
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (board1[i][j] != null) {
						feld1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: green;");
					}
					// Zellen, die die Referenz null haben, werden transparent
					// gemacht
					else if (board1[i][j] == null) {
						feld1.getChildren().get(i * 10 + j).setStyle("-fx-background-color: transparent;"); // TODO:
					}
				}
			}
			// Zellen, auf die eine Referenz gesetzt ist, werden eingefärbt
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (board2[i][j] != null) {
						feld2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: #6A5ACD;");
					}
					// Zellen, die die Referenz null haben, werden transparent
					// gemacht
					else if (board2[i][j] == null) {
						feld2.getChildren().get(i * 10 + j).setStyle("-fx-background-color: transparent;"); // TODO:
					}
				}
			}
			// Anzeigetexte in GUI, wie viele Schiffe noch gesetzt werden
			// müssen, werden aktualisiert (VBox links Player1)

			left.getChildren().removeAll(getTextSmallLeft(), getTextMiddleLeft(), getTextBigLeft());
			setTextSmallLeft();
			setTextMiddleLeft();
			setTextBigLeft();
			left.getChildren().addAll(getTextSmallLeft(), getTextMiddleLeft(), getTextBigLeft());

			// Anzeigetexte in GUI, wie viele Schiffe noch gesetzt werden
			// müssen, werden aktualisiert (VBox rechts Player2)

			right.getChildren().removeAll(getTextSmallRight(), getTextMiddleRight(), getTextBigRight());
			setTextSmallRight();
			setTextMiddleRight();
			setTextBigRight();
			right.getChildren().addAll(getTextSmallRight(), getTextMiddleRight(), getTextBigRight());

		}
	}
}
