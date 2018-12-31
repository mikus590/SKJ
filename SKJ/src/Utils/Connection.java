package Utils;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Object data) {
        try {
            outputStream.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object receive(){
        try{
            return inputStream.readObject();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return socket.getInetAddress().getHostName() + " " + socket.getPort();
    }

}
