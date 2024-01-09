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

public class NewGuiServer extends Application {
	
	TextField s1,s2,s3,s4, c1;
	Button serverChoice,clientChoice,b1;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;
	Client clientConnection;
	
	ListView<String> listItems, listItems2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
				Parent root = FXMLLoader.load(getClass().getResource("/FXML/ClientSign.fxml"));
				
				primaryStage.setTitle("Server GUI");
			Scene s1 = new Scene(root);
			//s1.getStylesheets().add("/styles/style1.css");
			primaryStage.setScene(s1);
			primaryStage.show();
			
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
	}

}
