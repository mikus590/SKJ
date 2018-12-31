package Host;

import Utils.Connection;
import Utils.Introduction;
import Utils.Request;
import Utils.RequestCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {
    private static int listenerPort = 10000;

    int ownerId;
    private ServerSocket serverSocket;
    private HostConnectionContainer connectionContainer;

    public ConnectionListener(int ownerId, HostConnectionContainer connectionContainer) {
        this.ownerId = ownerId;
        this.connectionContainer = connectionContainer;
        listenerPort += ownerId;
        createServerSock();
    }

    private void createServerSock() {
        try {
            serverSocket = new ServerSocket(listenerPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleNewConnection(Socket socket) {
        Connection newConnection = new Connection(socket);
        newConnection.send(new Request(RequestCode.INTRODUCTION, ownerId));
        Object recv = newConnection.receive();
        if (recv instanceof Introduction) {
            Introduction intro = (Introduction) recv;
            connectionContainer.add(intro.Id, newConnection);

        }
    }

    @Override
    public void run() {
        while(true) try {
            Socket sock = serverSocket.accept();
            handleNewConnection(sock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
