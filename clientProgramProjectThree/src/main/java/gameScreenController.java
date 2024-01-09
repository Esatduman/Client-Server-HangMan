
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

public class gameScreenController implements Initializable {
	
	@FXML
	private Button bA, bB, bC, bD, bE, bF, bG, bH, bI, bJ, bK, // buttons that go from a to z
	bL, bM, bN, bO, bP, bQ, bR, bS, bT, bU, bV, bW, bX, bY, bZ, 
	restart, suburbs, progLangs, profs;

	@FXML
	 private Circle head;
	
	 @FXML
	 private Line body, leftHand, rightHand, leftLeg, rightLeg;
	 
	 @FXML 
	 private TextField length, correct, guess, correctOrNot;
	 
	 private Socket socket;
	 private ObjectOutputStream out;
	 private ObjectInputStream in;
	 private Client client;
	 
	 gameLogic GameLogic = new gameLogic();
	 gameScreenController controller;

	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		hideAllBodyParts(); // Initially hide all body parts
		disableLetters();
		guess.setText("Remaining Guesses : " + String.valueOf(GameLogic.getRemainingGuesses()));
		initializeClient();
       //incorrectGuesses = 0;
		
	}
	
	private void hideAllBodyParts() {
        head.setVisible(false);
        body.setVisible(false);
        leftHand.setVisible(false);
        rightHand.setVisible(false);
        leftLeg.setVisible(false);
        rightLeg.setVisible(false);
    }
	
	public void disableLetters() {
		bA.setDisable(true);
    	bB.setDisable(true);
    	bC.setDisable(true);
    	bD.setDisable(true);
    	bE.setDisable(true);
    	bF.setDisable(true);
    	bG.setDisable(true);
    	bH.setDisable(true);
    	bI.setDisable(true);
    	bJ.setDisable(true);
    	bK.setDisable(true);
    	bL.setDisable(true);
    	bM.setDisable(true);
    	bN.setDisable(true);
    	bO.setDisable(true);
    	bP.setDisable(true);
    	bQ.setDisable(true);
    	bR.setDisable(true);
    	bS.setDisable(true);
    	bT.setDisable(true);
    	bU.setDisable(true);
    	bV.setDisable(true);
    	bW.setDisable(true);
    	bX.setDisable(true);
    	bY.setDisable(true);
    	bZ.setDisable(true);
	}
	
	public void enableLetters() {
        bA.setDisable(false);
    	bB.setDisable(false);
    	bC.setDisable(false);
    	bD.setDisable(false);
    	bE.setDisable(false);
    	bF.setDisable(false);
    	bG.setDisable(false);
    	bH.setDisable(false);
    	bI.setDisable(false);
    	bJ.setDisable(false);
    	bK.setDisable(false);
    	bL.setDisable(false);
    	bM.setDisable(false);
    	bN.setDisable(false);
    	bO.setDisable(false);
    	bP.setDisable(false);
    	bQ.setDisable(false);
    	bR.setDisable(false);
    	bS.setDisable(false);
    	bT.setDisable(false);
    	bU.setDisable(false);
    	bV.setDisable(false);
    	bW.setDisable(false);
    	bX.setDisable(false);
    	bY.setDisable(false);
    	bZ.setDisable(false);
	}
	
	public void displayHangman() {
		int numGuesses = GameLogic.getRemainingGuesses();
		
		if (numGuesses == 6) {
			hideAllBodyParts();
		} else if (numGuesses == 5) {
			head.setVisible(true);
		} else if (numGuesses == 4) {
			leftHand.setVisible(true);
		} else if (numGuesses == 3) {
			rightHand.setVisible(true);
		} else if (numGuesses == 2) {
			body.setVisible(true);
		} else if (numGuesses == 1) {
			leftLeg.setVisible(true);
		} else if (numGuesses == 0) {
			rightLeg.setVisible(true);
		} else {
			hideAllBodyParts();
		}
		
	}
	
	public void alphabetButtonsHandler(javafx.event.ActionEvent event) {
		
		Button source = (Button) event.getSource();
		char guessLetter = source.getText().charAt(0);
		
		boolean isGuessCorrect = GameLogic.checkUserGuess(guessLetter);
		boolean hasPlayerWon = GameLogic.hasUserWon();
		
		String message = "Button '" + guessLetter + "' was pressed.";
        client.send(message); 
		
		if (isGuessCorrect == true) {
			correct.setText("Correct Guesses " + GameLogic.getGuessedWord());
			correctOrNot.setText(guessLetter + " : CORRECT");
			
			if (hasPlayerWon == true){
				disableLetters();
				correct.setText("You guessed " + GameLogic.getCurrentWord() + " Correctly!");
				correctOrNot.setText("Restart to play again!");
				source.setDisable(false);
				restart.setVisible(true);
				restart.setDisable(false);
				
				String winner = "Client has won the game!";
		        client.send(message); 
			}
			
		} else {
			source.setDisable(true);
			correctOrNot.setText(guessLetter + " : INCORRECT");
			guess.setText("Remaining Guesses : " + String.valueOf(GameLogic.getRemainingGuesses()));
			
			displayHangman();
			
			if (GameLogic.getRemainingGuesses() <= 0) {
				correct.setText("YOU LOSE : RESTART TO TRY AGAIN");
				restart.setVisible(true);
				restart.setDisable(false);
				disableLetters();
				suburbs.setDisable(true);
				progLangs.setDisable(true);
				profs.setDisable(true);
				
				String loser = "Client has lost the game :(";
				client.send(loser);
			}
		}
		
        // -------------------- UNCOMMENT FOR CONSOLE DEBUGGIN PURPOSES
		
//		System.out.println("Guessed letter: " + guessLetter);
//	    System.out.println("Current word: " + GameLogic.getCurrentWord());
//	    System.out.println("Guessed word: " + GameLogic.getGuessedWord());
//	    System.out.println("Is guess correct? " + isGuessCorrect);
//        System.out.println("Remaining guesses: " + GameLogic.getRemainingGuesses());
//	    System.out.println("\n");
	    
        // -------------------- UNCOMMENT FOR CONSOLE DEBUGGIN PURPOSES
	} 
	
	public void programmingLangButton(ActionEvent event) {
        handleCategoryButton("Programming Languages");
        length.setText("Length of Word : " + String.valueOf(GameLogic.getLength())); 
        
    }

    @FXML
    public void chicagoSuburbsButton(ActionEvent event) {
        handleCategoryButton("Chicago Suburbs");
        length.setText("Length of Word : " + String.valueOf(GameLogic.getLength()));
    }

    @FXML
    public void uicCSProfsButton(ActionEvent event) {
        handleCategoryButton("UIC CS Professors");
        length.setText("Length of Word : " + String.valueOf(GameLogic.getLength()));
    }
    
    private void handleCategoryButton(String category) {
        GameLogic.selectCategory(category);
        correct.setText("You chose " + category);
        correctOrNot.setText("Begin Guessing!");
        
        suburbs.setDisable(true);
		progLangs.setDisable(true);
		profs.setDisable(true);
		
		
		String message = "Button '" + category + "' was pressed.";
        client.send(message); 
        
        enableLetters();
        
        // -------------------- UNCOMMENT FOR CONSOLE DEBUGGIN PURPOSES
        
      // System.out.println("Category selected: " + category);
      // System.out.println("Current word: " + GameLogic.getCurrentWord() + "\n");
      // System.out.println("Guessed word: " + GameLogic.getGuessedWord());
      // System.out.println("Remaining guesses: " + GameLogic.getRemainingGuesses());
        
        // -------------------- UNCOMMENT FOR CONSOLE DEBUGGIN PURPOSES
        
    }
    
    public void setClient(Client client) { // not used 
        this.client = client;
    }
    
    public void restartGame() {
    	restart.setVisible(false);
    	GameLogic.winCondition = false;
    	GameLogic.resetGame();
    	correct.setText("Correct Guess : ");
    	length.setText("Length of Word : ");
    	guess.setText("Remaining Guesses : " + String.valueOf(GameLogic.getRemainingGuesses()));
    	correctOrNot.setText("Select a Category");
    	suburbs.setDisable(false);
		progLangs.setDisable(false);
		profs.setDisable(false);
    	hideAllBodyParts();
		disableLetters();
		
		String message = "Button '" + restart.getText() + "' was pressed. Client playing again.";
        client.send(message); 
    }
    
	public void exitGame(ActionEvent e) { // if the disconnect button is pressed exit game and remove client.
		String message = "Client has disconnected from the server.";
        client.send(message); 
		System.exit(0);
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
	        });
	        client.start();
	    }
}
