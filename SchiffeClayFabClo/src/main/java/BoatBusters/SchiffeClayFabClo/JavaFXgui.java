package BoatBusters.SchiffeClayFabClo;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



@SuppressWarnings("restriction")
public class JavaFXgui extends Application implements EventHandler<ActionEvent>  {

	Stage window;
	Button button;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("thenewboston - JavaFX");
		button = new Button("Schiff Stortebecker");
		
		button.setOnAction(this);

		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 300, 250);

		window.setScene(scene);
		window.show();
	}

	public void handle(ActionEvent event) {
            if (event.getSource()==button){
            	System.out.println("Schiff anbumst");
            	
            }
	}



	
	





	
	  

}
