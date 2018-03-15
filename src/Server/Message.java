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

    public Message(String messageText, String userName) {
        this.messageText = "[" + userName + "]" + ": " + messageText;
        this.messageTime = LocalTime.now();
    }

    @Override
    public String toString() {
        return messageTime.format(DateTimeFormatter.ofPattern("HH:mm:ss ")) + messageText;
    }
    
}
