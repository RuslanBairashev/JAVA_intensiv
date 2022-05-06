package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(3L));
        System.out.println(usersRepository.findByEmail("mail@mail.ru"));
        usersRepository.save(new User(4L, "test@test.ru"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(4L);
        System.out.println(usersRepository.findAll());
        System.out.println("____________________");
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(3L));
        System.out.println(usersRepository.findByEmail("mail@mail.ru"));
        usersRepository.save(new User(4L, "test@test.ru"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(4L);
        System.out.println(usersRepository.findAll());
    }
}
