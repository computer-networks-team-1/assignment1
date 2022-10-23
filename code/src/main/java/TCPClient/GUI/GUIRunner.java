package TCPClient.GUI;

import TCPClient.GUI.views.MainView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GUIRunner {

    private GUIRunner() {}

    /**
     * Initializes JavaFX scene and loads fxml file.
     *
     * @param primaryStage JavaFX primary stage.
     * @throws IOException when graphic.fxml is not found.
     */
    public static void run(Stage primaryStage) throws IOException {

        primaryStage.setTitle("TCPClient - Online Chat");

        Scene scene = new Scene(new MainView(), 850, 478);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage
                .getIcons()
                .add(new Image(new File("src/main/resources/assets/logo.PNG").toURI().toString()));

        primaryStage.setResizable(false);

        primaryStage.setMaximized(false);
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }

    /** Stops the GUI and the threads. */
    public static void stop() {
        if(MainView.clientConnection != null)
            MainView.clientConnection.closeCommunication();
        Platform.exit();
    }

}
