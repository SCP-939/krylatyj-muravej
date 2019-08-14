package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final int PORT = 25566;
    public static LinkedList<ClientListener> clientListeners = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = server.accept();

                try {
                    clientListeners.add(new ClientListener(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
