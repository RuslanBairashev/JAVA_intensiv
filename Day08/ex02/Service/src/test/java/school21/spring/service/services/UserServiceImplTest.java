package school21.spring.service.services;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class UserServiceImplTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        TestApplicationConfig.class);

    @Test
    void signUpTest() {
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        String tmp = null;
        assertNotNull(tmp = userService.signUp("test@test.ru"));
    }

    @Test
    void signUpTemplateTest() {
        UserService userService = context.getBean("userServiceTemplate", UserServiceImpl.class);
        String tmp = null;
        assertNotNull(tmp = userService.signUp("test@test.ru"));
    }
}
