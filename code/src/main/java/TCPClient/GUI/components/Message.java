package TCPClient.GUI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Message extends VBox {

    public Message(String message, String user) {
        Label userLabel = new Label(user);
        Label messageLabel = new Label(message);

        this.getChildren().addAll(userLabel, messageLabel);
    }


}
