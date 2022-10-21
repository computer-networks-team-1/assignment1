import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    public static List<Connection> clientsConnected;

    public static void main (String args[]) {

        clientsConnected = new ArrayList<Connection>();

        try{

            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) { // it keeps listening for requests
                Socket clientSocket = listenSocket.accept();  // it receives request from the client and accepts it
                // --> handshake
                Connection c = new Connection(clientSocket); // it establishes a connection with the client
                clientsConnected.add(c);
            }

        } catch(IOException e) {System.out.println("Listen: " + e.getMessage());}
    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    private String clientName;

    public Connection (Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream()); //inward connection
            out = new DataOutputStream( clientSocket.getOutputStream()); //outward connection
            this.start(); // it executes the run()
        }
        catch(IOException e)
            {System.out.println("Connection: "+e.getMessage());}

    }

    public String getClientName(){
        if (clientName == null || clientName.isEmpty())
            throw new IllegalStateException("No nickname set for: " + clientSocket.getInetAddress().toString());
        else
            return this.clientName;

    }

    private void broadCast(String message){
        TCPServer.clientsConnected.forEach((Connection c)-> {
            try {
                c.out.writeUTF(getClientName() + ": " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void run(){
        try {
            clientName = in.readUTF();
            broadCast("Hey there, I joined the chat.");
            while(true) {
                String data = in.readUTF();
                if (data.equals("/quit")) { //Client want's to disconnect
                    broadCast(getClientName() + " left the chat.");
                    clientSocket.close();
                    TCPServer.clientsConnected.remove(this);
                    break;
                } else {
                    broadCast(data);
                }
            }
        }
        catch(EOFException e)
            {System.out.println("EOF: "+e.getMessage());}
        catch(IOException e) {System.out.println("IO:s a"+e.getMessage());
        } finally {
            try {clientSocket.close(); }
            catch (IOException e) {/*close failed*/}
        }
    }
}