import java.net.ServerSocket;
import java.net.Socket;

/*
 * EchoServer class. Starts the connection, and creates a new thread for the client to
 * interact with the server 
 */
public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try {
                	//makes connection
                	Socket socket = serverSocket.accept();
                	//creates a new thread for the echo server
                	EchoServerThread t = new EchoServerThread(socket);
                	t.start();
	
        			} catch (Exception e){
        				e.printStackTrace();
        			}
                }
            }
        }
    }

