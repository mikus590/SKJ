package Host;

import Utils.*;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;

public class Host {
    private int id;
    private CommandLine commandLine;
    private HostConnectionContainer connectionContainer;
    private RequestContainer requestContainer;
    private String dirPath;
    private ArrayList<FileData> files;

    private ConnectionRequestListener connectionRequestListener;

    public Host(int id) {
        this.id = id;
        commandLine = new CommandLine(this);
        connectionContainer = new HostConnectionContainer();
        connectionRequestListener = new ConnectionRequestListener(id, connectionContainer, requestContainer);
        dirPath = "D:\\Torrent\\TORrent_" + this.id;
        System.out.println(dirPath);
        Thread t1 = new Thread(connectionRequestListener);
        Thread t2 = new Thread(commandLine);

        t1.start();
        t2.start();
    }
    private void initFiles() {
        files = new ArrayList<>();
        File[] directory = new File(dirPath).listFiles();
        if (directory != null) {
            for (File file : directory) {
                files.add(new FileData(file));
            }
        }
    }
    private void listFiles() {
        System.out.println("Files:");
        for (FileData fileData : files) {
            System.out.println(fileData);
        }
    }

    public void establishConn(int id) {
        try {
            Socket s = new Socket("localhost", id + 10000);
            Connection conn = new Connection(s);
            Request req = new Request(RequestCode.INTRODUCTION,this.id);
            Object recv = conn.receive();
            if (recv instanceof Request) {
                conn.send(new Introduction(this.id));
                Request r = (Request) recv;
                connectionContainer.add(r.getRequesterId(), conn);
                requestContainer.add(r.getRequesterId(), req);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void execListCommand(int id) {
            try {
                initFiles();
                listFiles();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
