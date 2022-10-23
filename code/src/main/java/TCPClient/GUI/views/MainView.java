package TCPClient.GUI.views;

import TCPClient.GUI.components.ChatCanvas;
import TCPClient.GUI.components.InputUser;
import TCPClient.MessageInbox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class MainView extends VBox {

    public MainView() {

        Socket s = null;

        String host = "127.0.0.1";

        Scanner scan = new Scanner(System.in);

        try{
            int serverPort = 7896;

            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Welcome!");
            dialog.setHeaderText("Choose your nickname");
            dialog.setContentText("Name:");

            String clientName = "";

            do {
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    clientName = result.get();
                }
            } while(clientName.equals(""));

            s = new Socket(host, serverPort);

            DataInputStream in = new DataInputStream(s.getInputStream()); //inward connection
            DataOutputStream out = new DataOutputStream( s.getOutputStream()); //outward connection

            MessageInbox inbox = new MessageInbox(in);

            out.writeUTF(clientName); //First message is clients name

        } catch (UnknownHostException e){
            System.out.println("Sock: "+e.getMessage());
        } catch (EOFException e){
            System.out.println("EOF: "+e.getMessage());
        } catch (IOException e){
            System.out.println("IO: "+e.getMessage());
        } finally {if(s!=null)
            try { s.close();
            } catch (IOException e) {/*close failed*/}}


        setUp();
    }

    public void setUp() {

        ChatCanvas chatCanvas = new ChatCanvas();
        InputUser inputUser = new InputUser();

        getChildren().addAll(chatCanvas, inputUser);
    }
}
