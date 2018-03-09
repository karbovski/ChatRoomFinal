package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection implements Runnable{

    static int portNumber = 1400;

    static ServerSocket serverSocket;

    static ArrayList<ClientConnection> connectedClients = new ArrayList<>();

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private User user = null;
    private boolean isInLoginState = true;

    public ClientConnection(Socket clientSocket) throws IOException {
        socket = clientSocket;
        connectedClients.add(this);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        try {
            while(!socket.isClosed()){
                String inputFromClient = in.readLine();
                if (isInLoginState == true && user == null) {
                    processAsCommand(inputFromClient);
                } else
                    processAsMessage(inputFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public  void processAsMessage(String input){
        Message message = new Message(input);

        // iterate through array and send message to clients
        for (ClientConnection client : connectedClients)
            client.sendMessage(message);

    }
    public void sendMessage(Message message){
        this.out.println(message.toString());
    }

    static void establishServerSocket(){
        try {
            // create new server socket
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server socket is established now!");

        } catch (IOException e) {
            System.out.println("Error! Server socket could not be established on port " + portNumber);
        }
    }

    static void waitForNewClient() throws IOException {
        while (true) {
            System.out.println("Listening on port: " + portNumber + " [waiting for new connection]");

            // wait for new client, here will execution be blocked until new client will connect
            Socket newClientSocket = serverSocket.accept();

            // create new clientConnection object which is a Runnable object
            ClientConnection clientConnection = new ClientConnection(newClientSocket);

            // create new thread and put a runnable object inside
            new Thread(clientConnection).start();

            // print info to console that new client is connected
            System.out.println("New client is connected from " + newClientSocket.getLocalAddress());
        }
    }

    void processAsCommand(String command){
        if (command.contains("#") && command.indexOf("#") == 0){

            // expected login command is on form #login(nickname,password)
            if (command.contains("login")) {
                int first = command.indexOf("(");
                int last = command.indexOf(")");
                int comma = command.indexOf(",");

                // just boring substring operations, trying to get login and password from login string
                String login = command.substring(first + 1,  comma);
                String password = command.substring(comma + 1, last);

                // findUserInDatabase returns null if not found
                User checkThisUser = User.findUserInDatabase(login, password);

                // if found
                if (checkThisUser != null)
                    user = checkThisUser;
                else this.out.println("Login was not successful, try again!"); // if not found
            }

            else if (command.contains("register")){

                int first = command.indexOf("(");
                int last = command.indexOf(")");
                int comma = command.indexOf(",");

                // just boring substring operations, trying to get login and password from login string
                String login = command.substring(first + 1,  comma);
                String password = command.substring(comma + 1, last);

                user = User.registerNewUserInDatabase(login,password);
            }

            else {
                this.out.println("System not recognised your system-command, try again!");
            }

        }
    }
}