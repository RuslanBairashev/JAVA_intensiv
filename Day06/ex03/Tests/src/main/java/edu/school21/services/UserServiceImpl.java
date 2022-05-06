package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;

public class UserServiceImpl {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean authenticate(String login, String password) {

        if (login == null || userRepository.findByLogin(login) == null) {
            return false;
        }
        if (userRepository.findByLogin(login).isStatus()) {
            throw new AlreadyAuthenticatedException();
        } else {
            if ((password).equals(userRepository.findByLogin(login).getPassword())) {
                User user = userRepository.findByLogin(login);
                        user.setStatus(true);
                userRepository.update(user);
                return true;
            }
        }
        return false;
    }
}
