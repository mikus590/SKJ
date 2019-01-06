package Host;

import Utils.Connection;

import java.util.HashMap;
import java.util.Map;

public class HostConnectionContainer implements Runnable {

    Map<Integer, Connection> connections;

    public HostConnectionContainer() {
        connections = new HashMap<>();
    }

    public void add(int hostId, Connection conn) {
        System.out.println("Połączony zostałeś do hosta: "+hostId + " Połączenie:  " +conn.toString());
        connections.putIfAbsent(hostId, conn);
    }

    public void remove(int hostId, Connection conn) {
        connections.remove(hostId,conn);
    }


    public Connection getConnection (int hostId){
        return connections.get(hostId);
    }

    @Override
    public void run() {
        while(true) {
            for(Map.Entry<Integer, Connection> entry : connections.entrySet()) {

            }
        }
    }
}
