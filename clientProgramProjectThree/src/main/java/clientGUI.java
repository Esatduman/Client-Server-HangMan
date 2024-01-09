import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class clientGUI extends Application {
	
	Client clientConnection;
	ListView<String> listItems, listItems2;
	Button serverChoice,clientChoice,b1;
	TextField c1;
	VBox clientBox;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ClientSign.fxml"));
		primaryStage.setTitle("Client Sign-in Page");
		Scene homeScreen = new Scene(root, 300, 300);
		homeScreen.getStylesheets().add("/CSS/styles.css");
		primaryStage.setScene(homeScreen);
		primaryStage.show();
		
		clientConnection = new Client(data->{
			Platform.runLater(()->{
				listItems2.getItems().add(data.toString());
			});
		});
	}

}
