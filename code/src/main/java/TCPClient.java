import java.net.*;
import java.io.*;

/*
	How to use it?

	for the server: 'javac TCPServer.java' and 'java TCPServer'
	for the client: 'javac TCPClient.java' and 'java TCPClient hello 127.0.0.1'
 */

public class TCPClient {

    public static void main (String args[]) {


        Socket s = null;

        String message = "hello";
        String host = "127.0.0.1";


        try{
            int serverPort = 7896;
            s = new Socket(host, serverPort);

            DataInputStream in = new DataInputStream(s.getInputStream()); //inward connection
            DataOutputStream out = new DataOutputStream( s.getOutputStream()); //outward connection

            out.writeUTF(message);
            String data = in.readUTF();
            System.out.println("Received: "+ data);
        } catch (UnknownHostException e){
            System.out.println("Sock: "+e.getMessage());
        } catch (EOFException e){
            System.out.println("EOF: "+e.getMessage());
        } catch (IOException e){
            System.out.println("IO: "+e.getMessage());
        } finally {if(s!=null)
            try { s.close();
            } catch (IOException e) {/*close failed*/}}
    }
}