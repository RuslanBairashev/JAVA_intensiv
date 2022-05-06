package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static Connection connection;
    private HikariDataSource dataSource;

    static {
    try {
        connection = DataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl();
        ChatroomsRepositoryJdbcImpl chatroomsRepositoryJdbc = new ChatroomsRepositoryJdbcImpl();

        Message message = new Message();
        User user = new User();
        Chatroom room = new Chatroom();

        String sql = "SELECT * FROM message WHERE message_id=?";
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
        message.setId(rs.getLong("message_id"));
        message.setAuthor(usersRepositoryJdbc.findById(rs.getLong("author")).get());
        message.setRoom(chatroomsRepositoryJdbc.findById(rs.getLong("room")).get());
        message.setText(rs.getString("text"));
        message.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());

        System.out.println(message.toString());

        return Optional.empty();
    }
}
