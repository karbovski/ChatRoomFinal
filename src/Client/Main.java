package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader inputConsole;
    static boolean running = true;

    public static void main(String[] args) throws IOException {

        initializing();
        showAvailableCommands();

        while (running) {
            System.out.print("> ");
            switch (inputConsole.readLine()){
                case "help": showAvailableCommands(); break;
                case "connect": Chat.connectToServerWizard(); break;
                case "exit": running = false; break;
                default: System.out.println("Unrecognized command, type 'help' for help"); break;
            }
        }
    }

    static void showAvailableCommands(){
        System.out.println("Available commands: connect, exit, help");
    }

    static void initializing(){
        System.out.println("Initializing...");
        System.out.println("Welcome to ChatRoom Client!");
        inputConsole = new BufferedReader(new InputStreamReader(System.in));
    }

}
