package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@ComponentScan("edu.school21.sockets")
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {

    @Value("${db.driver.name}")
    private String dbDriverName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

//    @Bean
//    public ServerSocket serverSocket() throws IOException {
//        return new ServerSocket(Integer.parseInt(port));
//    }

    @Bean
    public DataSource hikariBean() {
        HikariDataSource driverManagerDataSource = new HikariDataSource();
        driverManagerDataSource.setDriverClassName(dbDriverName);
        driverManagerDataSource.setJdbcUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        return driverManagerDataSource;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
