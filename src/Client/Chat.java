package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {

    static boolean running = true;
    static Socket socket;

    static void startChat() throws IOException {
        // create new listener
        new Thread(new Listener(socket)).start();

        PrintWriter out;
        out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("You are now connected and chatting on " + socket.getLocalAddress() + socket.getLocalPort());

        while (!socket.isClosed()){
            while (running){
                String input = Main.inputConsole.readLine();
                if (input == "exit") {
                    running = false;
                    return;
                }
                else {
                    out.println(input);
                }
            }
        }
    }

    static void connectToServerWizard() {
        String serverIP = "";
        int portNumber;

        if (socket == null){
            try {
                // get ip address from user
                System.out.println("Type server IP you want connect to: ");
                serverIP = Main.inputConsole.readLine();

                // get port number from user
                System.out.println("Type port number: ");
                portNumber = Integer.parseInt(Main.inputConsole.readLine());

                System.out.println("Trying connect to " + serverIP);
                createSocket(serverIP, portNumber);

                System.out.println("Successfully connected to " + serverIP + ":" + portNumber);
                startChat();

            } catch (Throwable error) {
                System.out.println(error.getMessage());
                return;
            }

        } else System.out.println("You are already connected to server!");
    }

    static void createSocket(String IP, int portNumber){
        try {
            socket = new Socket(IP, portNumber);
        } catch (IOException error){
            error.getMessage();
        }
    }
}