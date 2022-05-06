package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private static Connection connection;

    static {
    try {
        connection = DataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public UsersRepositoryJdbcImpl() {
    }

    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException() {
            System.out.println("Sub-entity identifiers are specified incorrectly!");
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = new User();

        String sql = "SELECT * FROM usr WHERE user_id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setLong(1, id);

        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }
        user.setUser_id(rs.getLong("user_id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }
}
