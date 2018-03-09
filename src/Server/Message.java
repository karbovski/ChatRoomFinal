package Server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {

    String messageText = null;
    LocalTime messageTime = null;
    User user = null;


    public Message(String messageText) {
        this.messageText = messageText;
        this.messageTime = LocalTime.now();
    }

    @Override
    public String toString() {
        return messageTime.format(DateTimeFormatter.ofPattern("HH:mm:ss ")) + messageText;
    }

    public static Message loginRequiredMessage(){
        return new Message("You have to log in before you can start chatting. Type: login [nickname] [password]");
    }
}
