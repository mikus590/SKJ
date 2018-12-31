package Host;

import Utils.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestContainer {

    Map<Integer, Request> requests;

    public RequestContainer() {
        requests = new HashMap<>();
    }

    public void add(int hostId, Request req) {
        System.out.println(hostId + "    " +req.toString() + "dodany request");
        requests.putIfAbsent(hostId, req);
    }

    public void remove(int hostId) {
        requests.remove(hostId);
    }

    public Request getRequests (int hostId){
        return requests.get(hostId);
    }
}
