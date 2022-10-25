package TCPClient.GUI.views;

import TCPClient.ClientConnection;
import TCPClient.GUI.components.ChatCanvas;
import TCPClient.GUI.components.InputUser;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class MainView extends VBox {

    public static ClientConnection clientConnection;
    public ChatCanvas chatCanvas;

    /**
     * It's the component that first asks the user through 3 dialogs the information neeeded for connecting to the
     * server and second calls the setup method to create the UI of the cat
     */
    public MainView() {

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Welcome!");
        dialog.setHeaderText("Choose your nickname");
        dialog.setContentText("Name:");

        String clientName = "";

        do {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                clientName = result.get();
            } else
                throw new IllegalStateException("Client name not provided");
        } while(clientName.equals(""));

        String ipServer = "";
        dialog.setHeaderText("Choose the IP of the server, you want to connect to");
        dialog.setContentText("IP:");

        do {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                ipServer = result.get();
            } else
                throw new IllegalStateException("IP Server not provided");
        } while(ipServer.equals(""));

        int portServer = -1;
        dialog.setHeaderText("Connection to " + ipServer + ". Which port?");
        dialog.setContentText("Port:");

        do {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                try {
                    portServer = Integer.parseInt(result.get());
                } catch (NumberFormatException e) {
                    portServer = -1;
                }
            } else
                throw new IllegalStateException("Port not provided");
        } while(portServer == -1);

        clientConnection = new ClientConnection(clientName, ipServer, portServer);

        setUp();
    }

    /**
     * Set up the UI of the chat
     */
    public void setUp() {

        chatCanvas = new ChatCanvas();
        InputUser inputUser = new InputUser();

        getChildren().addAll(chatCanvas, inputUser);
    }
}
