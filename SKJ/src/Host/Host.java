package Host;

import Utils.*;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

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
        requestContainer = new RequestContainer();
        connectionRequestListener = new ConnectionRequestListener(id, connectionContainer, requestContainer);
        dirPath = "D:\\Torrent\\TORrent_" + this.id;
        initFiles(dirPath);
        Thread t1 = new Thread(connectionRequestListener);
        Thread t2 = new Thread(commandLine);

        t1.start();
        t2.start();
    }
    private void initFiles(String dirPath) {
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
            Object recv = conn.receive();
            if (recv instanceof Request) {
                conn.send(new Introduction(this.id));
                Request r = (Request) recv;
                connectionContainer.add(r.getRequesterId(), conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void execListCommand(int id) {
            try {
                Connection getconn = connectionContainer.getConnection(id);
                if(getconn != null){
                    dirPath = "D:\\Torrent\\TORrent_" + id;
                    initFiles(dirPath);
                    listFiles();
                }
                else {
                    System.out.println("Brak połączenia dla danego hosta");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void execPushCommand(int id,String[] pushArgs) {
        //dirPath = "D:\\Torrent\\TORrent_" + id;
        String fileName = pushArgs[0];
        Connection conn = connectionContainer.getConnection(id);
        System.out.println("Połączony zostałeś do hosta: " + id + " Połączenie:  " +conn.toString());
        if (conn != null) {
            Optional<File> fileOptional = getFile(fileName);
            System.out.println(fileOptional);
            if (fileOptional.isPresent()) {
                conn.send(fileOptional.get());
            }
        }
    }

    private Optional<File> getFile(String fileName) {
        for(FileData fileData : files) {
            if(fileData.getFile().getName().equals(fileName)) {
                return Optional.of(fileData.getFile());
            }
        }
        return Optional.empty();
    }
}


