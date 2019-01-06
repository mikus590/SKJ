package Host;

import Utils.Connection;
import Utils.Introduction;
import Utils.Request;
import Utils.RequestCode;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class RequestHandler implements Runnable  {

    int ownerId;
    private ServerSocket serverSocket;
    private HostConnectionContainer connectionContainer;
    private RequestContainer requestContainer;
    private LinkedList<RequestContainer> requestBuffer;

    public RequestHandler(Host owner) {
        requestBuffer = new LinkedList<>();
       // this.owner = owner;
        //owner.listen(requestBuffer);
    }


    @Override
    public void run() {
        while(true)
            System.out.println("____");
    }
}
