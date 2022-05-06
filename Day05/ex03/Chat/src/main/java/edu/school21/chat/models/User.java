package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long user_id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;

    public User() {
    }

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializedRooms) {
        this.user_id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializedRooms = socializedRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && getLogin().equals(user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCreatedRooms(), user.getCreatedRooms()) && Objects.equals(getSocializedRooms(), user.getSocializedRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getCreatedRooms(), getSocializedRooms());
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + user_id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }

    public long getId() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
    }
}
