package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

public interface UserService {
    public String signUp(User user);
    public boolean validateUser(String name);
    public boolean validatePassword(String name, String password);
    public void addMessageToDb(String message);
}
