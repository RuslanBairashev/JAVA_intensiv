package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long message_id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message() {
    }

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.message_id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Message: {\n" +
                "id=" + message_id + ",\n" +
                "author=" + author + ",\n" +
                "room=" + room +  ",\n" +
                "text='" + text + '\'' + ",\n" +
                "dateTime=" + dateTime +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getMessage_id().equals(message.getMessage_id()) && Objects.equals(getAuthor(), message.getAuthor()) && Objects.equals(getRoom(), message.getRoom()) && getText().equals(message.getText()) && Objects.equals(getDateTime(), message.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage_id(), getAuthor(), getRoom(), getText(), getDateTime());
    }

    public Long getId() {
        return message_id;
    }

    public void setId(Long id) {
        this.message_id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
