package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
    private User sender;
    private String message;
    private LocalDateTime time;

    public Message() {
    }

    public Message(User sender, String message, LocalDateTime time) {
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
