package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static Connection connection;

    static {
    try {
        connection = DataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public MessagesRepositoryJdbcImpl() {
    }

    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException() {
            System.out.println("Sub-entity identifiers are specified incorrectly!");
        }

    }

    @Override
    public void save(Message message) throws SQLException {
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl();
        ChatroomsRepositoryJdbcImpl chatroomsRepositoryJdbc = new ChatroomsRepositoryJdbcImpl();

        usersRepositoryJdbc.findById(message.getAuthor().getId()).orElseThrow(NotSavedSubEntityException::new);
        chatroomsRepositoryJdbc.findById(message.getRoom().getId()).orElseThrow(NotSavedSubEntityException::new);

        String sql = "INSERT INTO message ( author, room, text, date_time) values(?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS);
            pst.setLong(1, message.getAuthor().getId());
            pst.setLong(2, message.getRoom().getId());
            pst.setString(3, message.getText());
            pst.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            message.setId(rs.getLong("message_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
