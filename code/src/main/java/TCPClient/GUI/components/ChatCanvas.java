package TCPClient.GUI.components;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class ChatCanvas extends ScrollPane {

    public ChatCanvas() {
        VBox chat = new VBox();

        this.getStyleClass().add("chat-canvas");
        this.setContent(chat);
    }
}
