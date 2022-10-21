import java.net.*;
import java.io.*;
import java.util.Scanner;

/*
	How to use it?

	for the server: 'javac TCPServer.java' and 'java TCPServer'
	for the client: 'javac TCPClient.java' and 'java TCPClient'
 */

public class TCPClient {

    public static void main (String args[]) {


        Socket s = null;

        String host = "127.0.0.1";

        Scanner scan = new Scanner(System.in);


        try{
            int serverPort = 7896;
            s = new Socket(host, serverPort);

            DataInputStream in = new DataInputStream(s.getInputStream()); //inward connection
            DataOutputStream out = new DataOutputStream( s.getOutputStream()); //outward connection

            MessagesInbox inbox = new MessagesInbox(in);

            while(true) {

                String message = scan.nextLine();
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

class MessagesInbox extends Thread {

    DataInputStream in;
    public MessagesInbox(DataInputStream in) {
        this.in = in;
        this.start();
    }

    public void run() {
        while(true) {
            String message = "";
            try {
                message = in.readUTF();
            } catch (IOException e) {
                message = "Error reading message. Info: " + e.getMessage();
            }
            System.out.println("Received: " + message);
        }
    }

}