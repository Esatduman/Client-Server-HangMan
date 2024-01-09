

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiController implements Initializable {
	
	@FXML
	Button serverButton, clientButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void clientMethod(ActionEvent e) {
        Stage stage = (Stage) clientButton.getScene().getWindow();
        try {
            openClientScene(stage);
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle the exception here
        }
    }

    private void openClientScene(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ClientSign.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Client Scene");
        stage.show();
    }
    
    public void ServerScene(ActionEvent e) {
    	
    	Stage stage2 = (Stage) serverButton.getScene().getWindow();
    	try {
    		openServerScene(stage2);
    	} catch (IOException ex1) {
    		ex1.printStackTrace();
    	}
    }
    
    
    private void openServerScene(Stage stage) throws IOException {
    	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/ClientSign.fxml"));
        Parent root2 = loader2.load();

        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.setTitle("Client Scene");
        stage.show();
    }
}
