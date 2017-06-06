package c2f.boatbusters.gui;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import c2f.boatbusters.classes.Game;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {

	final int WINDOW_SIZE_X = 1280;
	final int WINDOW_SIZE_Y = 720;
	final int translateX = 200;
	final int translateY = 300;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		root.setPrefSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

		InputStream is = Files.newInputStream(Paths.get("src/main/resources/ShipBg.jpg"));
		Image img = new Image(is);
		is.close();

		GameMenu gameMenu = new GameMenu();
		GameField gameField = new GameField();

		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(WINDOW_SIZE_X + 20);
		imgView.setFitHeight(WINDOW_SIZE_Y + 20);

		Scene scene = new Scene(root);
		root.getChildren().addAll(imgView, gameMenu, gameField);

		// scene.setOnKeyPressed(event -> { // menu mit Q ein und ausblenden
		// if (event.getCode() == KeyCode.Q) {
		// if (!gameMenu.isVisible()) {
		// FadeTransition ft = new FadeTransition(Duration.seconds(0.5),
		// gameMenu);
		// ft.setFromValue(0);
		// ft.setToValue(1);
		//
		// gameMenu.setVisible(true);
		// ft.play();
		// } else {
		// FadeTransition ft = new FadeTransition(Duration.seconds(0.5),
		// gameMenu);
		// ft.setFromValue(1);
		// ft.setToValue(0);
		// ft.setOnFinished(evt -> gameMenu.setVisible(false));
		// ft.play();
		// }
		// }
		// });

		primaryStage.setTitle("Boatbusters");
		// primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(250, 30);
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

		public CellButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont());
			text.setFill(Color.BLACK);

			Rectangle bg = new Rectangle(30, 30); // größe der Cell

			bg.setFill(Color.GREY);

			getChildren().addAll(bg, text);

			// wenn maus über menupoint
			setOnMouseEntered(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.BEIGE);

			});
			// wenn maus menupoint verlässt
			setOnMouseExited(event -> {
				bg.setFill(Color.GREY);
				text.setFill(Color.BLACK);
			});
		}

		public void setOnAction(EventHandler<ActionEvent> eventHandler) {
			// TODO Auto-generated method stub

		}
	}

	public class GameMenu extends Parent {

		public GameMenu() throws Exception {

			Game game = new Game(0);

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

				getChildren().add(menuPlayer1);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				t1.setToX(mainMenu.getTranslateX() - offset);

				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				t2.setToX(mainMenu.getTranslateX());

				t1.play();
				t2.play();

				t1.setOnFinished(evt -> {
					getChildren().remove(mainMenu);
				});
			});

			///// HIGHSCORE BUTTON MAIN MENU /////
			MenuButton btnScore = new MenuButton("HIGHSCORE"); // Highscore
																// Button
																// Mainmenu
			btnScore.setOnMouseClicked(event -> {
				getChildren().add(scoreMenu);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				t1.setToX(mainMenu.getTranslateX() - offset);

				TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), scoreMenu);
				t2.setToX(mainMenu.getTranslateX());

				t1.play();
				t2.play();

				t1.setOnFinished(evt -> {
					getChildren().remove(mainMenu);
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

					getChildren().add(menuPlayer2);

					TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
					t1.setToX(menuPlayer1.getTranslateX() - offset);

					TranslateTransition t2 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
					t2.setToX(menuPlayer1.getTranslateX());

					t1.play();
					t2.play();

					t1.setOnFinished(evt -> {
						getChildren().remove(menuPlayer1);

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
						getChildren().remove(menuPlayer2);

					});

					try {
						GameField gameField = new GameField();
						gameField.GameField();
					} catch (Exception e) {
						e.printStackTrace();
					}

					// game.startGame(game, scan, namePlayer1, namePlayer2);

				}
			});

			///// Login Player 1 bestätigen mit Maus /////
			MenuButton btnLoginPlayer1 = new MenuButton("OK");
			btnLoginPlayer1.setOnMouseClicked(event -> {

				getChildren().add(menuPlayer2);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() - offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer2);
				tt1.setToX(menuPlayer1.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuPlayer1);

				});
			});

			///// Back to MainMenu from Login 1 /////
			MenuButton btnBackPlayer1 = new MenuButton("BACK");
			btnBackPlayer1.setOnMouseClicked(event -> {

				getChildren().add(mainMenu);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer1);
				tt.setToX(menuPlayer1.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
				tt1.setToX(menuPlayer1.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuPlayer1);

				});
			});

			///// Login Player 2 bestätigen mit Maus (Start Game) /////
			MenuButton btnLoginPlayer2 = new MenuButton("OK. Start game!");
			btnLoginPlayer2.setOnMouseClicked(event -> {

				String namePlayer1 = textfieldLoginPlayer1.getText();
				String namePlayer2 = textfieldLoginPlayer2.getText();
				// game.startGame(game, scan, namePlayer1, namePlayer2);

				TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				t1.setToX(menuPlayer2.getTranslateX() - offset);

				t1.play();

				t1.setOnFinished(evt -> {
					getChildren().remove(menuPlayer2);

				});

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

				getChildren().add(menuPlayer1);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuPlayer2);
				tt.setToX(menuPlayer2.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuPlayer1);
				tt1.setToX(menuPlayer2.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(menuPlayer2);

				});
			});

			///// Back to MainMenu from HIGHSCORE /////
			MenuButton btnBack2 = new MenuButton("BACK");
			btnBack2.setOnMouseClicked(event -> {

				getChildren().add(mainMenu);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), scoreMenu);
				tt.setToX(scoreMenu.getTranslateX() + offset);

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
				tt1.setToX(scoreMenu.getTranslateX());

				tt.play();
				tt1.play();

				tt.setOnFinished(evt -> {
					getChildren().remove(scoreMenu);

				});
			});

			mainMenu.getChildren().addAll(btnStart, btnScore, btnExit); // Hauptmenu
			scoreMenu.getChildren().addAll(highscoreText, btnBack2); // HighscoreMenu
			menuPlayer1.getChildren().addAll(loginTextPlayer1, textfieldLoginPlayer1, btnLoginPlayer1, btnBackPlayer1); // login1
			menuPlayer2.getChildren().addAll(loginTextPlayer2, textfieldLoginPlayer2, btnLoginPlayer2, btnBackPlayer2); // login2

			Rectangle bg = new Rectangle(1280, 720);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);

			getChildren().addAll(bg, mainMenu);

		}
	}

	public class GameField extends Parent {

		public void GameField(){

			BorderPane rootGame = new BorderPane();

			GridPane boardPane = new GridPane();

			boardPane.setTranslateX(translateX);
			boardPane.setTranslateY(translateY);

			VBox bottom = new VBox();
			Label message = new Label("TEST MESSAGE");
			bottom.getChildren().add(message);
			bottom.setPadding(new Insets(0, 0, 0, 20));

			for (int row = 0; row < 8; row++) {
				for (int column = 0; column < 8; column++) {
					CellButton button = new CellButton("X");

					int x = column;
					int y = row;

					button.setOnAction(event -> {

						System.out.println("button mit Cordinate: " + x + "/" + y);

					});
					boardPane.add(button, row, column);
				}

			}
			
			rootGame.setCenter(boardPane);
			rootGame.setBottom(bottom);

			getChildren().add(rootGame);
			

		}
	}

}
