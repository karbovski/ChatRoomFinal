package Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ClientConnection.establishServerSocket();
        ClientConnection.waitForNewClient();
    }
}
