package Host;

import Utils.Connection;
import Utils.Introduction;
import Utils.Request;

import java.net.Socket;

public class Host {
    private int id;
    private CommandLine commandLine;
    private HostConnectionContainer connectionContainer;
    private ConnectionListener connectionListener;


    public Host(int id) {
        this.id = id;
        commandLine = new CommandLine(this);
        connectionContainer = new HostConnectionContainer();
        connectionListener = new ConnectionListener(id, connectionContainer);

        Thread t1 = new Thread(connectionListener);
        Thread t2 = new Thread(commandLine);
        t1.start();
        t2.start();
    }

    public void establishConn(int id) {
        try {
            Socket s = new Socket("localhost", id + 10000);
            Connection conn = new Connection(s);
            Object recv = conn.receive();
            if (recv instanceof Request) {
                conn.send(new Introduction(this.id));
                Request r = (Request) recv;
                connectionContainer.add(r.getRequesterId(), conn);
            }
        } catch (Exception e) {
        }
    }
}
