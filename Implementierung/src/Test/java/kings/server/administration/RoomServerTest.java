package kings.server.administration;

import kings.client.IClient;
import kings.client.Player;
import kings.server.room.EntranceRoom;
import kings.server.room.GameRoom;
import kings.server.room.Lobby;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomServerTest {
    dbController db;
    Registry registry1;
    EntranceRoom entranceRoomroom1 = new EntranceRoom();
    Player player1 = new Player("player1", registry1);
    Player player2 = new Player("player2", registry1);
    Player player3 = new Player("player3", registry1);

   // ArrayList<GameRoom> gameRooms;
    RoomServer roomServer;
    RoomServer roomServer2;

    RoomServerTest() throws NotBoundException, RemoteException {
    }

    @Test
    void playerEntering(Player player1) {
    }

    @Test
    void leaveGameRoom(Player player) {
       GameRoom room1 = new GameRoom("room1", 3, 2);
       room1.enterRoom(this.player1);
       assert(room1.getPlayer().size() == 1);
       room1.enterRoom(this.player2);
       assert(room1.getPlayer().size() == 2);
       roomServer.gameRooms.add(room1);
       room1.leaveRoom(player1);
       assert(room1.getPlayer().size() == 1);
       room1.leaveRoom(player2);
       assert(room1.getPlayer().size() == 0);


    }

    @Test
    void createGameRoom() throws NotBoundException, RemoteException {
        roomServer2.createGameRoom(player3, "room1", 5, 2);
        assert(roomServer2.gameRooms.size() == 0);  // player 3 not in Lobby Test
        roomServer2.lobby.enterRoom(player3);
        roomServer2.createGameRoom(player3, "room2", 5, 2);
        assert(roomServer2.gameRooms.size() == 1);
        roomServer2.createGameRoom(player3, "room2", 5, 2);
        assert(roomServer2.gameRooms.size() == 1);  // "room2" name already exist test


    }

    @Test
    void enterGameRoom() throws NotBoundException, RemoteException {
        GameRoom groom = new GameRoom("room1", 5, 2) ;
        Player player4 = new Player("player4", registry1);
        roomServer.lobby.enterRoom(player4);
        roomServer.lobby.enterRoom(player2);
        roomServer.createGameRoom(player2, "room1", 5, 2); // create groom
        roomServer.enterGameRoom(player4, "room1");
        assertFalse(roomServer.lobby.getPlayer().contains(player4)); // player4 and 2 leave the lobby
        assertFalse(roomServer.lobby.getPlayer().contains(player2));
        assertTrue(roomServer.gameRooms.contains(groom)); ;
    }

    @Test
    void login() {


    }

    @Test
    void insertNewPlayer() {
     //   this.db.insertNewPlayer("player1", "pwd1");
    }

    @Test
    void deletePlayer() {

    }

    @Test
    void passwordCheck() {
    }
}



