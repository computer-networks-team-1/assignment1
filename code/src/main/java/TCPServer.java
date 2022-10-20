import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main (String args[]) {
        try{

            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) { // it keeps listening for requests
                Socket clientSocket = listenSocket.accept();  // it receives request from the client and accepts it
                // --> handshake
                Connection c = new Connection(clientSocket); // it establishes a connection with the client
            }

        } catch(IOException e) {System.out.println("Listen: " + e.getMessage());}
    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;

    Socket clientSocket;

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

    public void run(){
        try {
            String data = in.readUTF();  //it reads the data sent from the client
            out.writeUTF(data.toUpperCase()); //it transforms the data in uppercase
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