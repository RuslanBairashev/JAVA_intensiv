package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.util.List;

@Component
public class UsersRepositoryImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

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
}
