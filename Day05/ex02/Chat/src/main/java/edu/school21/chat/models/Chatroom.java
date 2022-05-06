package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long chatroom_id;
    private String name;
    private User owner;
    private List<Message> messageList;

    public Chatroom() {
    }

    public Chatroom(Long id, String name, User owner, List<Message> messageList) {
        this.chatroom_id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "{" +
                "chatroom_id=" + chatroom_id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messageList=" + messageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatroom_id.equals(chatroom.chatroom_id) && getName().equals(chatroom.getName()) && Objects.equals(getOwner(), chatroom.getOwner()) && Objects.equals(getMessageList(), chatroom.getMessageList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroom_id, getName(), getOwner(), getMessageList());
    }

    public long getId() {
        return chatroom_id;
    }

    public void setChatroom_id(Long chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
