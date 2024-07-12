package kings.server.room;

import kings.client.Client;
import kings.client.IClient;
import kings.client.Player;

import java.util.ArrayList;

public class Room {
    ArrayList<IClient> player = new ArrayList<>();
    String name;


    /**
     * inserts a player into the player ArrayList
     * player joins a room => add player to the ArrayList
     * @param player
     */
    public void enterRoom(IClient player) {
        this.player.add(player);
    }

    /**
     * removes a player from the player ArrayList
     * player leaves a room => remove player from the ArrayList
     * @param player
     */
    public void leaveRoom(IClient player) {
        this.player.remove(player);
    }

    public ArrayList<IClient> getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
}
