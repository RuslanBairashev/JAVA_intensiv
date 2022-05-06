package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl() {
    }

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
            jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM usr WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM usr",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(Object entity) {
        User tmp = (User) entity;
        jdbcTemplate.update("INSERT INTO usr ( id, email) values(?,?)", tmp.getId(), tmp.getEmail());
    }

    @Override
    public void update(Object entity) {
        User tmp = (User) entity;
        jdbcTemplate.update("UPDATE usr SET email=? WHERE id=?", tmp.getEmail(), tmp.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM usr WHERE id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM usr WHERE email=?",
                        new Object[]{email}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }
}
