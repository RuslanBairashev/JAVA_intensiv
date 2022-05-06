package edu.school21.repositories;

import edu.school21.models.User;

public interface UserRepository {
    User findByLogin(String login);
    void update(User user);
}
