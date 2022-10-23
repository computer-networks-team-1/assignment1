package TCPClient.GUI.tasks;

import TCPClient.GUI.GUIRunner;
import javafx.concurrent.Task;

public class MessageInboxTask extends Task<Void> {


    private final int REFRESH_INTERVAL = 1;


    /**
     * Called by the Task when the thread that contains this task is started every 500ms it updates
     * the progress (@updateProgress) of the currency.
     *
     * @return Void placeholder for void.
     * @throws Exception if the thread was stopped.
     */
    @Override
    protected Void call() throws Exception {
        while (true) {
            Thread.sleep(REFRESH_INTERVAL);
            updateProgress(GUIRunner.mainView.clientConnection.getMessage());
        }
    }

    protected void updateProgress(String v) {
        updateMessage(GUIRunner.mainView.chatCanvas.getChat() + "\n" + v);
        super.updateProgress(0,0);
    }

}
