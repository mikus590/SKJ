package Host;

import Utils.Connection;
import Utils.Introduction;
import Utils.Request;
import Utils.RequestCode;

import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable  {

    int ownerId;
    private ServerSocket serverSocket;
    private HostConnectionContainer connectionContainer;
    private RequestContainer requestContainer;

    //private void handleNewConnection(Socket socket) {
    //  Connection newConnection = new Connection(socket);
    //  newConnection.send(new Request(RequestCode.INTRODUCTION, ownerId));
    //  Object recv = newConnection.receive();
    //  if (recv instanceof Introduction) {
    //      Introduction intro = (Introduction) recv;
    //      connectionContainer.add(intro.Id, newConnection);
    //      requestContainer.add(intro.Id, newConnection);

    //  }
    //}

    @Override
    public void run() {
        while(true)
            System.out.println("____");
    }
}
