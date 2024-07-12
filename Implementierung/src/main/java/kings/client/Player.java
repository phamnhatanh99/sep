package kings.client;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import kings.server.chat.IChat;
import kings.server.administration.IRoomServer;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// @TODO this is not the final Player class, but is needed for easier testing
public class Player implements IClient, Serializable {
    Registry registry;
    IRoomServer roomServer;
    //IDbController db;
    IChat chat;
    String name;
    private final BooleanProperty newMessage = new SimpleBooleanProperty(false);

    java.util.List<java.util.Map.Entry<String, String>> messages;

    /**
     * creates a remote object, so that the server can use methods of the player (currently receiveMsg)
     * @param name
     * @throws RemoteException
     */
    public Player(String name, Registry registry) throws RemoteException, NotBoundException {
        this.name = name;
        this.registry = registry;

        // access methods from the db
        //db = (IDbController) registry.lookup("Database");

        // access methods from the correct room
        roomServer = (IRoomServer) registry.lookup("RoomServer");



        messages = new ArrayList<>();
        messages.add(new AbstractMap.SimpleEntry<String, String>("", ""));
        UnicastRemoteObject.exportObject(this, 0);
    }

    public void enterEntranceRoom() throws RemoteException {
        // enter the enteranceRoom
        roomServer.playerEntering(this);
    }

    /**
     * recieves messages from the currently entered chat room
     * is remote executed by the server
     * @return
     */
    public Map.Entry<String, String> receiveMsg(Map.Entry<String, String> msg) {
        System.out.println(msg);
        messages.add(msg);
        return msg;
    }

    /**
     * sets the correct chat room for the player
     * is executed by the room server
     * @param currentRoom
     * @throws RemoteException
     * @throws NotBoundException
     */
    public void connectToChatRoom(String currentRoom) throws RemoteException, NotBoundException {
        // get messages from the correct chat
        // @TODO chat may not be necessary, because it can be accessed via room
        chat = (IChat) registry.lookup("Chat"+currentRoom);
    }

    public String getName() {
        return name;
    }

    // New code from here //
    public void sendMsg(String msg) throws RemoteException {
        //chat.storeMessage(msg, name);
        chat.storeMessage(msg, this);

    }

    public boolean login(String pwd) throws NotBoundException, RemoteException {
        return roomServer.login(this, this.name, pwd);
    }

    @Override
    public void newMessage() throws RemoteException {
        newMessage.set(true);
    }

    public Map.Entry<String, String> getNewMessage() {
        return messages.get(messages.size() - 1);
    }

    public BooleanProperty newMessageProperty() {
        return newMessage;
    }

    public void setNewMessage(boolean value) {
        newMessage.set(value);
    }
}
