import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class serverGUI extends Application{
	
	Server serverConnection;
	ListView<String> listItems, listItems2;
	Button serverChoice,clientChoice,b1;
	
	serverStartController controller;
	
	@FXML
    private Button connectButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
		
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/startServer.fxml"));
		primaryStage.setTitle("Sever Start-up Screen");
		Scene homeScreen = new Scene(root, 300, 300);
		homeScreen.getStylesheets().add("/CSS/styles.css");

//		homeScreen.getStylesheets().add("/styles/styles.css");
		primaryStage.setScene(homeScreen);
		primaryStage.show();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
}


