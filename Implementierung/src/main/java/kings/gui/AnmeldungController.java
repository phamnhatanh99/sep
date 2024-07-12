package kings.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import kings.client.Client;
import kings.client.Player;

import java.io.IOException;
import java.rmi.NotBoundException;

public class AnmeldungController {
    @FXML private Button anmelden;
    @FXML private Text infoText;
    @FXML private TextField benutzername;
    @FXML private PasswordField passwort;

    @FXML
    protected void onAnmeldenClicked() throws IOException, NotBoundException {
        String name = benutzername.getText();
        String pwd = passwort.getText();

        Client client = new Client();
        Player player = client.createNewPlayer(name);

        if (!player.login(pwd)) {
            infoText.setText("Not OK");
            infoText.setVisible(true);
        }

        else {
            PlayerHolder playerHolder = PlayerHolder.getInstance();
            playerHolder.setPlayer(player);
            switchToLobby();
        }
    }

    protected void switchToLobby() throws IOException {
        Main.switchScene(anmelden, "LobbyScene.fxml");
    }

}