package Host;

import Utils.Connection;

import java.util.HashMap;
import java.util.Map;

public class HostConnectionContainer {

    Map<Integer, Connection> connections;

    public HostConnectionContainer() {
        connections = new HashMap<>();
    }

    public void add(int hostId, Connection conn) {
        System.out.println(hostId + "    " +conn.toString() + "____________________________________________________");
        connections.putIfAbsent(hostId, conn);
    }

    public void remove(int hostId, Connection conn) {
        connections.remove(hostId,conn);
    }

    public Connection getConnection (int hostId){
        return connections.get(hostId);
    }

}
