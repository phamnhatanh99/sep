package kings.client;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IClient extends Remote {
    Map.Entry<String, String> receiveMsg(Map.Entry<String, String> msg) throws RemoteException;
    //void receiveMsg(String msg) throws RemoteException;

    void newMessage() throws RemoteException;

    void enterEntranceRoom() throws RemoteException;
    void connectToChatRoom(String currentRoom) throws RemoteException, NotBoundException;
    String getName() throws RemoteException;
}
