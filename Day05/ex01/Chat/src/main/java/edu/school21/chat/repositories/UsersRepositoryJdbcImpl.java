package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
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

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        User user = new User();

        String sql = "SELECT * FROM usr WHERE user_id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pst.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = pst.executeQuery();
        rs.next();
        user.setUser_id(rs.getLong("user_id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));

        return Optional.ofNullable(user);
    }
}
