package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UserService;
import school21.spring.service.services.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ApplicationConfig.class
        );

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        userService.signUp("admin@mail.ru");
        System.out.println(usersRepository.findAll());

        System.out.println("____________________");

        usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        userService.signUp("root@yandex.ru");
        System.out.println(usersRepository.findAll());
    }
}
