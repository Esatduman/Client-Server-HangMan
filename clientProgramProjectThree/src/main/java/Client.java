import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Client extends Thread{

	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){	
		callback = call;
	}
	
	// 
	
	public void run() 
	{	
		try {
			
			socketClient= new Socket("127.0.0.1",5555);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
		    		    
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		while(true) {
			
			try {
				
				String message = in.readObject().toString();
				callback.accept(message);
			}
			
			catch(Exception e) {}
		}
    } // end of run()
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
