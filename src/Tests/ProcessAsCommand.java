package Tests;

import Server.Main;
import Server.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessAsCommand {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void  main(String[]args) throws IOException {
        while (true){
            String string = in.readLine();
            processAsCommand(string);
        }
    }

    static void processAsCommand(String command){
        if (command.contains("#") && command.indexOf("#") == 0){

            // expected login command is on form #login(nickname,password)
            if (command.contains("login")) {
                int first = command.indexOf("(");
                int last = command.indexOf(")");
                int comma = command.indexOf(",");

                // just boring substring operations, trying to get login and password from login string
                String login = command.substring(first + 1,  comma);
                String password = command.substring(comma + 1, last);

                System.out.println(login + " " + password);

                // findUserInDatabase returns null if not found
//                User checkThisUser = User.findUserInDatabase(login, password);

                // if found
//                if (checkThisUser != null)
//                    user = checkThisUser;
//                else this.out.println("Login was not successful, try again!"); // if not found
            }

            else if (command.contains("register")){

                int first = command.indexOf("(");
                int last = command.indexOf(")");
                int comma = command.indexOf(",");

                // just boring substring operations, trying to get login and password from login string
                String login = command.substring(first + 1,  comma);
                String password = command.substring(comma + 1, last);


                System.out.println(login + " " + password);


//                user = User.registerNewUserInDatabase(login,password);
            }

            else {
                System.out.println("System not recognised your system-command, try again!");
            }

        }
    }
}
