
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MyControllerClass implements Initializable {
	
	@FXML
	private Button bA, bB, bC, bD, bE, bF, bG, bH, bI, bJ, bK, // buttons that go from a to z
	bL, bM, bN, bO, bP, bQ, bR, bS, bT, bU, bV, bW, bX, bY, bZ;

	@FXML
	private Button uic_Button, suburbButton, programButton;
	
	 @FXML
	 private Circle head;
	
	 @FXML
	 private Line body, leftHand, rightHand, leftLeg, rightLeg;

	; // for the client to send back to the sevrer 
	 
	 private gameLogic hangmanLogic;
	 
	 private Client client;

	private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

	 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		hideAllBodyParts(); // Initially hide all body parts
        //incorrectGuesses = 0;
		initializeClient();
		
	
	}
	
	
	
	 private void hideAllBodyParts() {
	        head.setVisible(false);
	        body.setVisible(false);
	        leftHand.setVisible(false);
	        rightHand.setVisible(false);
	        leftLeg.setVisible(false);
	        rightLeg.setVisible(false);
	    }
	 
	 public void setSocket(Socket socket) {
	        this.socket = socket;
	        try {
	            this.out = new ObjectOutputStream(socket.getOutputStream());
	            this.in = new ObjectInputStream(socket.getInputStream());

	            initializeClient(); // Start the client after receiving the socket
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private void initializeClient() {
	        client = new Client(data -> {
	            System.out.println("Received from server: " + data);
	            receiveFromServer(data);
	        });
	        client.start();
	    }
	 
	  @FXML
	  public void letterButtonPressed(ActionEvent e) {
	       if (e.getSource() instanceof Button) {
	            Button button = (Button) e.getSource();
	            String letter = button.getText();

	            // Send the guessed letter to the server
	            
	            	String message = "Button '" + letter + "' was pressed.";
	                client.send(message); 
	            
	        }
	    }
	 
	 public void receiveFromServer(Serializable data) {
	        
	    }
	 
	 public void setClient(Client client) { // not used 
	        this.client = client;
	    }

	 
	@FXML
	public void exitGame(ActionEvent e) { // if the disconnect button is pressed exit game and remove client.
		
		System.exit(0);
	}



	 

}
