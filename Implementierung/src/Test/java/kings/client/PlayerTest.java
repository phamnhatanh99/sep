package kings.client;

import kings.server.administration.IRoomServer;
import kings.server.chat.IChat;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Registry registry;
    IRoomServer iRoomServer;



    @Test
    void enterEntranceRoom() throws NotBoundException, RemoteException {
        Player player = new Player("player", registry );
        player.enterEntranceRoom();


    }

    @Test
    void receiveMsg() throws NotBoundException, RemoteException {
        Player player = new Player("player", registry );
        HashMap<String, String> msg = new HashMap<String, String>();
        msg.put("player", "first msg");
        player.receiveMsg((Map.Entry<String, String>) msg);
        assert(player.messages.size() == 1);
        assert (player.getNewMessage() == msg);
    }

    @Test
    void connectToChatRoom() {

    }

    @Test
    void getName() throws NotBoundException, RemoteException {
        Player player = new Player("player", registry );
        assert(player.getName() == "player");
    }



    @Test
    void sendMsg() throws NotBoundException, RemoteException {

        Player player = new Player("player", registry );
        player.sendMsg("My message");
        HashMap<String, String> msg = new HashMap<String, String>();
        msg.put("player", "My message");
        assert(player.messages.contains(msg));
    }

    @Test
    void login() {
    }

    @Test
    void newMessage() throws NotBoundException, RemoteException {
        Player player = new Player("player", registry );
        HashMap<String, String> msg = new HashMap<String, String>();
        msg.put("player", "first msg");
        HashMap<String, String> msg1 = new HashMap<String, String>();
        msg1.put("player", "second msg");
        assert(player.messages.get((player.messages.size() - 1)) == msg1);

    }


}