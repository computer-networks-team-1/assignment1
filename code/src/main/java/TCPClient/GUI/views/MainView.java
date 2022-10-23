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
            }
        } while(clientName.equals(""));

        clientConnection = new ClientConnection(clientName);

        setUp();
    }

    public void setUp() {

        chatCanvas = new ChatCanvas();
        InputUser inputUser = new InputUser();

        getChildren().addAll(chatCanvas, inputUser);
    }
}
