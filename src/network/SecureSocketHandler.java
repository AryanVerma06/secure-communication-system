package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SecureSocketHandler {

    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public SecureSocketHandler(Socket socket) throws Exception {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendObject(Object obj) throws Exception {
        out.writeObject(obj);
        out.flush();
    }

    public Object receiveObject() throws Exception {
        return in.readObject();
    }
}