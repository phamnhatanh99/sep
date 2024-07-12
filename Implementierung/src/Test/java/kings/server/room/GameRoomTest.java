package kings.server.room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRoomTest {

    private String name;
    private int capacity;
    private int bots;

    GameRoom gameroom = new GameRoom("gameroom", 4, 1);

    @Test
    void getCapacity() {
        assert (gameroom.getCapacity() == 4);
    }
}