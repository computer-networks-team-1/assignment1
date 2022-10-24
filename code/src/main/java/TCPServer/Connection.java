package TCPServer;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Connection extends Thread {

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
                broadCast(clientSocketIp, clientName, data);
            }
        }
        catch(EOFException e) {
            TCPServer.clientsConnected.remove(this);
            broadCast(
                    this.clientSocket.getInetAddress().toString(),
                    this.getClientName(),
                    "Has left the chat."
            );
        }
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
            textToLog = time +" - " + ipAddress.substring(1) + " --> " + message;
        else
            textToLog = time + " - " + ipAddress.substring(1) + " --> " + clientName + ": " + message;

        try {
            String fileName = "src\\main\\resources\\logServer.txt";
            File serverFile = new File(fileName);
            if (serverFile.createNewFile()) {
                System.out.println("Logfile created: " + serverFile.getName());
            } else {
                System.out.println("Logfile already exists");
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
