package app;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientListener extends Thread {
    private static Logger LOG = Logger.getLogger(ClientListener.class.getName());

    private Socket socket;

    private BufferedReader in;
    private BufferedWriter out;

    private Cipher cipher;

    public ClientListener(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

        try {
            generateKey();
        } catch (IOException e) {
            e.printStackTrace();
        }

        start();
    }

    private void generateKey() throws IOException {
        String[] s = in.readLine().split(" ");

        BigInteger G = new BigInteger(s[0]);
        BigInteger P = new BigInteger(s[1]);

        BigInteger a = DiffeHellman.generate(300);

        BigInteger A = DiffeHellman.pow(G, a, P);
        out.write(A.toString() + "\n");
        out.flush();

        BigInteger B = new BigInteger(in.readLine());

        BigInteger k = DiffeHellman.pow(B, a, P);

        cipher = new Cipher(k);
    }

    @Override
    public void run() {
        String message;
        try {
            while (true) {
                message = in.readLine();
                LOG.info("Ciphered - " + message);
                message = cipher.decipher(message);

                LOG.info("Got message - " + message);

                if (message.equals("!exit")) {
                    LOG.info("Exiting");
                    onExit();
                    break;
                }

                for (int i = 0; i < Server.clientListeners.size(); ++i) {
                    ClientListener listener = Server.clientListeners.get(i);

                    if (listener.isAlive()) {
                        LOG.info("Sending a message");
                        listener.send(message);
                    } else {
                        Server.clientListeners.remove(i--);
                        LOG.info("Clients size: " + Server.clientListeners.size());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String message) throws IOException {
        out.write(cipher.encipher(message) + "\n");
        out.flush();
    }

    private void onExit() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
