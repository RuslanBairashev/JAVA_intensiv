package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Random;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService{
    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("usersRepositoryJdbcImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        Random random = new Random();
        Integer tmp = random.nextInt(99999 - 11111) + 11111;
        String password = tmp.toString();
        usersRepository.save(new User(5L, email, password));
        return password;
    }
}
