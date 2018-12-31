package Utils;

import java.io.Serializable;

public class Request implements Serializable {
    RequestCode requestCode;
    int requesterId;

    public RequestCode getRequestCode() {
        return requestCode;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public Request (RequestCode code, int requesterId){
        requestCode = code;
        this.requesterId = requesterId;

    }

}
