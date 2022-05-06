package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;
    private static Connection connection;

    static {
    try {
        connection = DataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException() {
            System.out.println("Sub-entity identifiers are specified incorrectly!");
        }
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM usr OFFSET ?*? LIMIT ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, page);
            pst.setInt(2, size);
            pst.setInt(3, size);

            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User tmp = new User();
            do {
                tmp.setUser_id(rs.getLong("user_id"));
                tmp.setLogin(rs.getString("login"));
                tmp.setPassword(rs.getString("password"));
                System.out.println(tmp.toString());
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

}
