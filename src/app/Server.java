package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final int PORT = 25566;
    public static LinkedList<ClientListener> clientListeners = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        int id = 1;

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = server.accept();

                try {
                    clientListeners.add(new ClientListener(socket, id++));

                    if(id > 999) {
                        id %= 1000;
                        ++id;
                    }
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
