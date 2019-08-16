package app;

import java.io.IOException;

public class Main {
    private static final String IP = "46.21.65.194";

    public static void main(String[] args) throws IOException {
        new Client(System.in, "localhost", 25566); // TODO: 2019-08-15
    }
}
