package Utils;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

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

    public void send(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            System.out.println("sending file");
            for (byte a : fileContent) {
                System.out.print(a);
            }
            System.out.println();
            outputStream.writeObject(fileContent);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("Failed to send a file: " + file.getName());
        }
    }
    public Object receiveData(){
        try {
                Object recv = inputStream.readObject();
                FileOutputStream fos = new FileOutputStream();
            fos.write((byte[]) recv);
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
