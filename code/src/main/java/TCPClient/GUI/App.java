package TCPClient.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIRunner.run(primaryStage);

        primaryStage.setOnCloseRequest(e -> GUIRunner.stop());
    }

    /**
     * Start game main loop.
     *
     * @param args default args.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
