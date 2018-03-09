package Server;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeMap;

public class User {

    static ArrayList<User> userDatabase = new ArrayList<>();

    String login;
    String password;


    private User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    void registerNewUser(){

    }

    public static User findUserInDatabase(String login, String password) {

        User userToReturn = null;
        for (User user : userDatabase)
            if (user.login == login && user.password == password) userToReturn = user;
        return userToReturn;
    }

    public static User registerNewUserInDatabase(String login, String password){
        return new User(login,password);
    }
}
