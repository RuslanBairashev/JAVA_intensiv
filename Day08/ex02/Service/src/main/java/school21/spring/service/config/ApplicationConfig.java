package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("school21.spring.service")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    @Value("${db.driver.name}")
    private String dbDriverName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource managerBean() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(dbDriverName);
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        return driverManagerDataSource;
    }

    @Bean
    public DataSource hikariBean() {
        HikariDataSource driverManagerDataSource = new HikariDataSource();
        driverManagerDataSource.setDriverClassName(dbDriverName);
        driverManagerDataSource.setJdbcUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        return driverManagerDataSource;
    }
}
