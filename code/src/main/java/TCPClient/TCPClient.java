package TCPClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static void main (String[] args) {

        Socket s = null;

        String host = "127.0.0.1";

        Scanner scan = new Scanner(System.in);


        try{
            int serverPort = 7896;

            System.out.print("Choose a Nickname: ");
            String clientName = scan.nextLine();

            s = new Socket(host, serverPort);

            DataInputStream in = new DataInputStream(s.getInputStream()); //inward connection
            DataOutputStream out = new DataOutputStream( s.getOutputStream()); //outward connection

            MessageInbox inbox = new MessageInbox(in);

            out.writeUTF(clientName); //First message is clients name

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
