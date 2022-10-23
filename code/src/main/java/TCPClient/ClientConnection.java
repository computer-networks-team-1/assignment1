package TCPClient;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {

    private DataOutputStream out;
    private DataInputStream in;
    private Socket s;

    public ClientConnection(String clientName) {

        s = null;

        String host = "127.0.0.1";

        try{
            int serverPort = 7896;

            s = new Socket(host, serverPort);

            in = new DataInputStream(s.getInputStream()); //inward connection
            out = new DataOutputStream( s.getOutputStream()); //outward connection
//
//            MessageInbox inbox = new MessageInbox(in);

            out.writeUTF(clientName); //First message is clients name

        } catch (UnknownHostException e){
            System.out.println("Sock: "+e.getMessage());
        } catch (EOFException e){
            System.out.println("EOF: "+e.getMessage());
        } catch (IOException e){
            System.out.println("IO: "+e.getMessage());
        }


    }

    public void sendMessage (String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println("IO: "+e.getMessage());
        }
    }

    public String getMessage () {
        String message = "";
        try {
            message = in.readUTF();
        } catch (IOException e) {
            System.out.println("IO: "+e.getMessage());
        }
        return message;
    }

    public void closeCommunication () {
        if(s!=null)
        try {
            s.close();
        } catch (IOException e) {/*close failed*/}
    }
}
