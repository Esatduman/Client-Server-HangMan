
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class serverStartController implements Initializable{

    @FXML
    private Button connectButton;

    @FXML
    private TextField portSearch;

    @FXML
    private Button exitGui;
    
    @FXML
	private BorderPane pane;
    
    @FXML
    Server serverConnection;
	
    @FXML
    ListView<String> listItems, listItems2;
	
    @FXML
    Button serverChoice,clientChoice,b1;
    
    Button exit;
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
	    pane = new BorderPane();
	    
	}

    public void enterPort(ActionEvent event) {
        // Get the port number from the text field
        String portNumber = portSearch.getText();

        // Start the server using the specified port number
        try {
            // Create a ServerSocket object
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(Integer.parseInt(portNumber));

            // Create a client socket for each incoming connection
            while (true) {
                java.net.Socket clientSocket = serverSocket.accept();

                // Create input and output streams
                java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
                java.io.PrintWriter out = new java.io.PrintWriter(new java.io.OutputStreamWriter(clientSocket.getOutputStream()));

                // Receive a message from the client
                String message = in.readLine();
                System.out.println("Received message from client: " + message);

                // Send a message back to the client
                out.println("Hello from the server!");

                // Close the socket connection
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void exitGui(ActionEvent event) {
        // Close the current stage (window)
        Stage stage = (Stage) exitGui.getScene().getWindow();
        stage.close();
    }
    
    public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		exit = new Button("Shutdown Server");
		pane.setTop(exit);
		
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: lightgreen");
		
		pane.setCenter(listItems);
			
		return new Scene(pane, 600, 400);
		
		
	}
   
    public void startServer(ActionEvent event) throws IOException {
    	listItems = new ListView<String>();
  
        String text = "SERVER LOG:";
        listItems.getItems().add(text);
    	
    	serverConnection = new Server(data -> {
    		Platform.runLater(()->{
    			listItems.getItems().add(data.toString());
    		});

    	});

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Server Action Log");

    	Scene scene = createServerGui();
    	stage.setScene(scene);
    	
    	exit.setOnAction((ActionEvent) ->{
    		exitGame();
	    });
    	
    }
    
    public void exitGame() { 
		System.exit(0);
	}

}