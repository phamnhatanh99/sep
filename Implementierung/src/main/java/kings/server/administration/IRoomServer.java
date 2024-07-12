package kings.server.administration;

import kings.client.Client;
import kings.client.IClient;
import kings.client.Player;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

public interface IRoomServer extends Remote {/*
    Registry registry = null;
    Lobby lobby = null;
    EntranceRoom entranceRoom = null;
*/
    /*
    public static void setRegistry(Registry registry) throws RemoteException {
        RoomServer.registry = registry;
    }

    public static void setRooms(EntranceRoom eRoom, Lobby lobby) throws RemoteException {
        RoomServer.lobby = lobby;
        RoomServer.entranceRoom = eRoom;
    }

    public static void join(Player player) {
        // move the player in the entrance room
        RoomServer.entranceRoom.enterRoom(player);
    }
 */
    //void setRegistry(Registry registry) throws RemoteException;
    //void setRooms(EntranceRoom eRoom, Lobby lobby) throws RemoteException;
    void playerEntering(IClient player) throws RemoteException;

    /*
    public static void changeRoom(Player player, Room toRoom) throws RemoteException {
        // never executed from GameRoom

        if (RoomServer.lobby.getPlayer().contains(player)) {
            RoomServer.lobby.leaveRoom(player);
        }
        else if (RoomServer.entranceRoom.getPlayer().contains(player)) {
            RoomServer.entranceRoom.leaveRoom(player);
        }
        else {
            // @TODO throw error
            System.out.println("ATTENTION, player is not in the lobby or entrance room => no move possible");
            return;
        }

        // @TODO is the player now in the class he should be or in the Room class?
        toRoom.enterRoom(player);
    }
    public static void changeRoom(ArrayList<Player> player, Room toRoom, Room fromRoom) {
        // only executed from GameRoom

        Iterator<Player> it = player.iterator();
        Player p;

        while (it.hasNext()) {
            p = it.next();
            toRoom.leaveRoom(p);
            toRoom.enterRoom(p);
        }
    }

     */
    /*
    void changeRoom(IClient player, String toRoom) throws RemoteException;
    void changeRoom(IClient player, Room toRoom, Room fromRoom) throws RemoteException, NotBoundException;

     */

    //void changeRoom(IClient player, String toRoom);
    //void changeRoom(IClient player, Room toRoom, Room fromRoom) throws NotBoundException, RemoteException;
    void createGameRoom(IClient player, String name, int capacity, int amountBots) throws RemoteException, NotBoundException;
    boolean login(IClient player, String name, String pwd) throws NotBoundException, RemoteException;
    void enterGameRoom(IClient player, String name) throws RemoteException, NotBoundException;
    void leaveGameRoom(IClient player) throws NotBoundException, RemoteException;
    void insertNewPlayer(String name, String pass) throws RemoteException;
    void deletePlayer(String name) throws RemoteException;
    boolean passwordCheck(String username, String password) throws RemoteException;
}
