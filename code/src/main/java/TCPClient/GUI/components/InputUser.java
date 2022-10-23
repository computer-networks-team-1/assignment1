package TCPClient.GUI.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class InputUser extends HBox {

    public InputUser() {
        TextArea input = new TextArea();
        input.getStyleClass().add("input");

        Button send = new Button("Send");
        send.getStyleClass().add("send");

        this.getStyleClass().add("input-user");
        this.setSpacing(10);
        this.getChildren().addAll(input, send);
    }

}
