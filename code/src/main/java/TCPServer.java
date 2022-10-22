import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> messages;

    public Connection (Socket aClientSocket) {

        try {
            messages = new ArrayList<>();
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

    private void broadCast(String ipAddress, String clientName, String message){
        recordThisMessage(ipAddress, clientName, message);
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

            String clientSocketIp = clientSocket.getInetAddress().toString();
            broadCast(clientSocketIp, clientName, "Hey there, I joined the chat.");

            while(true) {
                String data = in.readUTF();
                if (data.equals("/quit")) { //Client want's to disconnect
                    broadCast(clientSocketIp, clientName, getClientName() + " left the chat.");
                    clientSocket.close();
                    TCPServer.clientsConnected.remove(this);
                    break;
                } else {
                    broadCast(clientSocketIp, clientName, data);
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


    public void recordThisMessage(String ipAddress, String clientName, String message)
    {
        LocalDateTime now = LocalDateTime.now();
        String time =now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
        String textToLog;

        if (message.endsWith("left the chat."))
            textToLog = time + " - " + ipAddress + " --> " + message;
        else
            textToLog = time + " - " + ipAddress + " --> " + clientName + ": " + message;

        String fileName = "C:\\Users\\Alice\\Desktop\\assignment1-online-chat\\code\\src\\main\\resources\\" + "logServer.txt";

        try {
            File serverFile = new File(fileName);
            if (serverFile.createNewFile()) {
                System.out.println("File created: " + serverFile.getName());
            } else {
                System.out.println("File already exists");
            }

            FileWriter fw = new FileWriter(serverFile, true);
            fw.append(textToLog);
            fw.append('\n');
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}