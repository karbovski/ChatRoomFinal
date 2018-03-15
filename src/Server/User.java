package Server;

import java.util.ArrayList;

public class User {

    // TODO synchronized??
    static ArrayList<User> userDatabase = new ArrayList<>();

    String login;
    String password;

    private User(String login, String password) {
        this.login = login;
        this.password = password;
        userDatabase.add(this);
    }

    public static User findUserInDatabase(String login, String password) {

        User userToReturn = null;
        for (User user : userDatabase){
            if (user.login.equals(login) && user.password.equals(password)) userToReturn = user;
        }
        return userToReturn;
    }

    public static User registerNewUserInDatabase(String login, String password){
        return new User(login,password);
    }
}
