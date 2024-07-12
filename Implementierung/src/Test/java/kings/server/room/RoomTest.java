package kings.server.room;

import kings.client.IClient;
import kings.client.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    Room room1 = new Room();
    Room room2 = new Room();
    GameRoom room3 = new GameRoom("room3", 4,2 );
    IClient player1;
    IClient p1;
    IClient p2;
    IClient p3;



    @Test
    void enterRoom() {
        this.room1.enterRoom(player1);
        assert (room1.getPlayer().size() == 1);
    }

    @Test
    void leaveRoom() {
        this.room1.leaveRoom(player1);
        assert(room1.getPlayer().size() == 0);
    }

    @Test
    void getPlayer() {
        this.room2.enterRoom(this.p1);
        this.room2.enterRoom(this.p2);
        this.room2.enterRoom(this.p3);
      assert(room2.getPlayer().size() == 3);

    }

    @Test
    void getName() {
        assert(this.room3.getName() == "room3");
    }
}