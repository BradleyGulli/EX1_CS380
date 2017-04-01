
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inLine = "";
            String ret = "";
            while(!inLine.equals("exit")){
            	System.out.print("Client> ");
            	inLine = in.readLine();
            	out.println(inLine);
            	if(!(ret = br.readLine()).equals("exit")){
            		System.out.print("Server> ");
            		System.out.println(ret);
            	}
            	
            }
            
            
           
            
        }
    }
}















