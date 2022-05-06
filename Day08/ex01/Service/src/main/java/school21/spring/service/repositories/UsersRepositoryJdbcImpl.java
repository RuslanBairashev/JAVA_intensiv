package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private static Connection connection;

    public UsersRepositoryJdbcImpl() {
    }

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                System.out.println("Connection is null!");
            } else {
                populateDb();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateDb() {
        String sql = "DROP TABLE IF EXISTS usr;";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE usr (id int, email varchar);";
        try {
            pst = connection.prepareStatement( sql );
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "INSERT INTO usr VALUES (1, 'yandex@yandex.ru'), " +
                "(2, 'mail@mail.ru')," +
                "(3, 'gmail@gmail.ru');";
        try {
            pst = connection.prepareStatement( sql );
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User tmp = new User();
        String sql = "SELECT * FROM usr WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();}
            tmp.setId(rs.getLong("id"));
            tmp.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(tmp);
    }

    @Override
    public List<User> findAll() {
        List<User> products = new ArrayList<>();
        String sql = "SELECT * FROM usr";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) return null;
            User tmp;
            do {
                tmp = new User();
                tmp.setId(rs.getLong("id"));
                tmp.setEmail(rs.getString("email"));
                products.add(tmp);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    @Override
    public void save(Object entity) {
        User tmp = (User) entity;
        String sql = "INSERT INTO usr ( id, email) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql);
            pst.setLong(1, tmp.getId());
            pst.setString(2, tmp.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object entity) {
        User tmp = (User) entity;
        String sql = "UPDATE usr SET email=? WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setString(1, tmp.getEmail());
            pst.setLong(2, tmp.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM usr WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User tmp = new User();
        String sql = "SELECT * FROM usr WHERE email=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();}
            tmp.setId(rs.getLong("id"));
            tmp.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(tmp);
    }
}
