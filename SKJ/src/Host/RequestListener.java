package Host;

import java.io.IOException;

public class RequestListener implements Runnable {

    int ownerId;
    private RequestContainer requestContainer;

    public RequestListener(int ownerId, RequestContainer connectionContainer) {
        this.ownerId = ownerId;
        this.requestContainer = connectionContainer;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("____");
        }
    }
}
