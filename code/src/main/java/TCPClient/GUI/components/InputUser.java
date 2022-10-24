package TCPClient.GUI.components;

import TCPClient.GUI.GUIRunner;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.util.Objects;

import static TCPClient.GUI.views.MainView.clientConnection;

public class InputUser extends HBox {

    private final TextArea input;
    private final Button send;

    public InputUser() {
        input = new TextArea();
        input.getStyleClass().add("input");

        send = new Button("Send");
        send.getStyleClass().add("send");
        send.setOnAction(this::sendInput);

        this.getStyleClass().add("input-user");
        this.setSpacing(10);
        this.getChildren().addAll(input, send);
    }


    private void sendInput(ActionEvent event) {
        Objects.requireNonNull(event);

        if(!input.getText().equals("")) {
            if(input.getText().equals("/quit")){
                GUIRunner.stop(); //Triggers a disconnection message
            } else { //Normal input
                clientConnection.sendMessage(input.getText());
                input.setText("");
            }
        }

    }

}
