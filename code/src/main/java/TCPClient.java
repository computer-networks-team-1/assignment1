import java.net.*;
import java.io.*;

/*
	How to use it?

	for the server: 'javac TCPServer.java' and 'java TCPServer'
	for the client: 'javac TCPClient.java' and 'java TCPClient'
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

            //eseguire un altro thread che stampa in continuazione e vede se ci sno nuovi messaggi
//                message = in.readUTF(); // it receives the message from the server
//                System.out.println("Received: " + message);

            while(true) {

                out.writeUTF(message); // it sends the message to the server
            }

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