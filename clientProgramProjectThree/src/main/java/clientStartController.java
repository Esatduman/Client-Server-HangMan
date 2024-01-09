

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class clientStartController implements Initializable{

	
	@FXML
	Button connectButton, exitGui;
	
	@FXML
	TextField portSearch;
		 
	private Socket socketClient;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Client client;

    private gameScreenController gameController;
    
	ListView<String> listItems, listItems2;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void enterPort(ActionEvent e) {
		String portText = portSearch.getText();

        try {
            int portNumber = Integer.parseInt(portText);
            
            client = new Client(data->{
				Platform.runLater(()->{listItems2.getItems().add(data.toString());
				});
			});
            
            // Handle transition to the game scene
            goToGameScene(portNumber);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("Invalid port number.");
        }   
    }
 	
	private void goToGameScene(int portNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/hangmanDesign.fxml"));
            Parent root = loader.load();
            gameController = loader.getController();
            root.getStylesheets().add("/CSS/styles.css");

            Stage stage = (Stage) connectButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("It's Time to Play Hangman!");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the game scene.");
        }
    }
	
	
	@FXML
    public void exitGui(ActionEvent event) {
        // Close the current stage (window)
        Stage stage = (Stage) exitGui.getScene().getWindow();
        stage.close();
    }
}
