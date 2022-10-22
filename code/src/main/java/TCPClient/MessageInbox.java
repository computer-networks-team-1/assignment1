package TCPClient;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageInbox extends Thread {

    DataInputStream in;

    public MessageInbox(DataInputStream in) {
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
                break;
            }
            System.out.println(message);
        }
    }



}
