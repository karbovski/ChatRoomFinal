package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener implements Runnable {

    Socket socket;
    BufferedReader in;

    public Listener (Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while(!socket.isClosed()){
            try {
                String inputFromServer = in.readLine();
                if (inputFromServer != null){
                    System.out.println(inputFromServer);
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
}
