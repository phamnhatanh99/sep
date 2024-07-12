package kings.server.room;

import kings.server.chat.Chat;

public class GameRoom extends Room{
    int capacity;
    int amountBots = 0;

    Chat chat;

    /**
     * creates a new gameRoom with a new chat
     * @param name
     * @param capacity
     * @param amountOfBots
     */
    public GameRoom(String name, int capacity, int amountOfBots) {
        this.name = name;
        this.capacity = capacity;
        //addBots(amountOfBots);

        chat = new Chat(name, this);
    }

    public int getCapacity() {
        return capacity;
    }
/*
    public void deleteGameRoom() {
        // move all players to the lobby
        RoomServer.changeRoom(players, "Lobby", name);

        gameServer.remove(this);
    }

    public void addBots(int amount) {
        amountBots += amount;
        /* @TODO
        for (int i = 0; i < amountOfBots; i++) {
            Bot bot = new Bot(uniqueID)
            players.append(bot)
        }
         *//*
    }
    */
}
