package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;

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
    public void update(Message message) {

        String sql = "UPDATE message SET text=?, date_time=? WHERE message_id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setString(1, message.getText());
            System.out.println("text="+ message.getText());
            pst.setTimestamp(2, Timestamp.valueOf(message.getDateTime()));
            pst.setLong(3, message.getMessage_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
