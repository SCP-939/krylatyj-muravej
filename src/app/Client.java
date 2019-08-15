package app;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class Client {
    private Socket socket;

    private BufferedReader in;
    private BufferedWriter out;

    private BufferedReader console; // TODO: 2019-08-14 change name

    private Cipher cipher;

    public Client(InputStream input, String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        console = new BufferedReader(new InputStreamReader(input));

        generateKey();

        new ServerReader().start();
        new ServerWriter().start();
    }

    public void generateKey() throws IOException {
        BigInteger G = DiffeHellman.G;
        BigInteger P = DiffeHellman.P;

        String msg = G.toString() + " " + P.toString();
        out.write(msg + "\n");
        out.flush();

        BigInteger b = DiffeHellman.generate(300);

        BigInteger B = DiffeHellman.pow(G, b, P);
        out.write(B.toString() + "\n");
        out.flush();

        BigInteger A = new BigInteger(in.readLine());

        BigInteger k = DiffeHellman.pow(A, b, P);

        cipher = new Cipher(k);
    }

    public class ServerReader extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String message = cipher.decipher(in.readLine());

                    if (message.equals("!exit")) {
                        onExit();
                        break;
                    }

                    System.out.println(message);
                    System.out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ServerWriter extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String message = console.readLine();

                    if (message.equals("!exit")) {
                        out.write(cipher.encipher("!exit") + "\n");
                        out.flush();

                        onExit();
                        break;
                    }

                    out.write(cipher.encipher(message) + "\n");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
