
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;

/*
 * The EchoClient class is what is used to communicate with the EchoServer
 */
public final class EchoClient {

	/*
	 * main function contains all code that interacts with the server and the user
	 */
    public static void main(String[] args) throws Exception {
        //gets where we are connecting
    	try (Socket socket = new Socket("localhost", 22222)) {
            
    		//BufferedReader allows us to get messages from the server
    		InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            //PrintWriter allows us to send messages to the server
            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            System.out.println(br.readLine());
            
            //this BufferedReader is for the user to enter their messages
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //input string from user
            String inLine = "";
            //echo string from the server
            String ret = "";
            
            //process by which a message is sent and received
            while(!inLine.equals("exit")){
            	System.out.print("Client> ");
            	inLine = in.readLine();
            	out.println(inLine);
            	out.flush();
            	if(!(ret = br.readLine()).equals("exit")){
            		System.out.print("Server> ");
            		System.out.println(ret);
            	}
            }
        }
    }
}















