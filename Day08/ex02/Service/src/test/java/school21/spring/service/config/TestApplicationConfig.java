package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UserServiceImpl;

@Configuration
public class TestApplicationConfig {

    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(embeddedDatabase());
    }

    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(embeddedDatabase());
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(usersRepositoryJdbc());
    }

    @Bean
    public UserServiceImpl userServiceTemplate() {
        return new UserServiceImpl(usersRepositoryJdbcTemplate());
    }

}
