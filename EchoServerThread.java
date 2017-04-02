import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * EchoServerThread class extends Thread to create new runnable so that more than one
 * client can interact with the server
 */
public class EchoServerThread extends Thread{
	
	//The socket of the client
	private Socket socket;
	
	/*
	 * constructor, each thread is created with the client socket
	 */
	public EchoServerThread(Socket socket){
		this.socket = socket;
		
	}
	
	/*
	 * This is what runs when start() is called, which creates a new thread and 
	 * runs run(). Handles the "Echo" in "EchoServer"
	 */
	public void run(){
		//getting and printing the client address
		String address = socket.getInetAddress().getHostAddress();
        System.out.printf("Client connected: %s%n", address);
        
        try{
        //the PrintWrite allows us to send a message to the client
        OutputStream os = socket.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        
        //BufferedReader allows us to read messages from the client
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
       
        //a confirmation message sent to the client - connect
        out.printf("Hi %s, thanks for connecting!%n", address);
          
        //the process by which we get the messages from the client, and then send them back
        String inLine = br.readLine();
        while(inLine != null){
        	out.println(inLine);
        	inLine = br.readLine();
        }
        
        }catch(IOException e){
        	e.printStackTrace();
        }
        //a confirmation message sent to the client - disconnect
        System.out.printf("Client disconnected: %s%n", address);
        try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}