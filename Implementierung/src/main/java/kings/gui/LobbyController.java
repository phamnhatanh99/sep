package kings.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import kings.client.Player;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {

    @FXML private ScrollPane scroll_pane;
    @FXML private VBox chat_box;
    @FXML private Button send_button;
    @FXML private TextField message_box;
    private Player player = PlayerHolder.getInstance().getPlayer();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scroll_pane.setFitToWidth(true);
        chat_box.heightProperty().addListener((observable, oldValue, newValue) -> scroll_pane.setVvalue((Double) newValue));

        player.newMessageProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue && newValue) {
                String name = player.getNewMessage().getValue();
                String msg = player.getNewMessage().getKey();
                handleIncomingMessage(msg, name);
                player.setNewMessage(false);
            }
        });

        send_button.setOnAction(event -> {
            String msg = message_box.getText();
            if (!msg.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);

                Text text = new Text(player.getName() + ": " + msg);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(239, 242, 255)");
                textFlow.setStyle("-fx-background-color: rgb(15, 125, 242)");
                text.setFill(Color.color(0.934, 0.945, 0.996));

                hBox.getChildren().add(textFlow);
                chat_box.getChildren().add(hBox);

                try {
                    player.sendMsg(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                message_box.clear();
            }
        });
    }

    public void handleIncomingMessage(String msg, String name) {
        if (!msg.isEmpty()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);

            Text text = new Text(name + ": " + msg);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(233, 233, 235)");

            hBox.getChildren().add(textFlow);
            Platform.runLater(() -> chat_box.getChildren().add(hBox));
        }
    }

}
