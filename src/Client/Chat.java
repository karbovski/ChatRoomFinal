package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

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

    static void connectToServerWizard() throws IOException {
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
                createSocket(serverIP, portNumber, "dupa", "dupa");


            } catch (Throwable error) {
                System.out.println(error.getMessage());
                return;
            }

            // try to establish connection with ip and port number provided by user

            try {
                // try create new socket
                socket = new Socket(serverIP, portNumber);
                System.out.println("Successfully connected to " + serverIP + ":" + portNumber);

                startChat();

            } catch (UnknownHostException e) {
                System.out.println("Unknown host error! Could not connect to " + serverIP + ":" + portNumber);
            } catch (ConnectException e){
                System.out.println("Connect error! Could not connect to " + serverIP + ":" + portNumber);
            }

        } else System.out.println("You are already connected to server!");

    }

    static void loginWizard() throws IOException {
        System.out.println("Do you already have account on this chat? [yes/no]");

        switch (Main.inputConsole.readLine()){
            case "yes": {

            }
            case "no": {

            }
                default: System.out.println("Unrecognized answer!"); break;
        }

    }

    static void createSocket(String IP, int portNumber, String login, String password){
        try {
            socket = new Socket(IP, portNumber);
        } catch (IOException error){
            error.getMessage();
        }


    }

}
