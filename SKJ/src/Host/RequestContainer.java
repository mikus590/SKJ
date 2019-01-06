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
        System.out.println("Host: " + hostId + " Wartość requestu " + req.toString() + "Został dodany do kolejki");
        requests.putIfAbsent(hostId, req);
    }

    public void remove(int hostId, Request req) {
        requests.remove(hostId, req);
    }

    public Request getRequests (int hostId){
        return requests.get(hostId);
    }
}
