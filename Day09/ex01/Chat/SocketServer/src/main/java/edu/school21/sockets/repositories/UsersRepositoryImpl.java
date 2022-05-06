package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersRepositoryImpl() {
    }

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM usr",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(Object entity) {
        User tmp = (User) entity;
        jdbcTemplate.update("INSERT INTO usr ( name, password) values(?,?)", tmp.getName(), tmp.getPassword());
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM usr WHERE name=?",
                        new Object[]{name}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }

    @Override
    public boolean passwordIsValid(String name, String password) {
        User user = findByName(name).get();
        return  passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void addMessage(String username, String message) {
        jdbcTemplate.update("INSERT INTO message values(?,?,?)", username, message, LocalDateTime.now());
    }
}
