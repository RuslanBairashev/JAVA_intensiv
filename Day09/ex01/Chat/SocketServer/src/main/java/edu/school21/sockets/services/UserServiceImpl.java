package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService{
    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public String signUp(User user) {
        usersRepository.save(user);
        return user.getName();
    }

    @Override
    public boolean validateUser(String name) {
        if (usersRepository.findByName(name).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePassword(String name, String password) {
        return usersRepository.passwordIsValid(name, password);
    }

    @Override
    public void addMessageToDb(String message) {
        String[] strArray = message.split(": ");
        System.out.println(message);
        usersRepository.addMessage(strArray[0], strArray[1]);
    }
}
