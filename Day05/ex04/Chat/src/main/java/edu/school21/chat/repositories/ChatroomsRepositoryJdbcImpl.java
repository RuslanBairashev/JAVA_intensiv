package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomsRepositoryJdbcImpl implements ChatroomRepository {
    private static Connection connection;

    static {
    try {
        connection = DataSource.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public ChatroomsRepositoryJdbcImpl() {
    }

    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException() {
            System.out.println("Sub-entity identifiers are specified incorrectly!");
        }
    }

    @Override
    public Optional<Chatroom> findById(Long id) {
        Chatroom room = new Chatroom();

        String sql = "SELECT * FROM chatroom WHERE chatroom_id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setLong(1, id);

        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }
        room.setChatroom_id(rs.getLong("chatroom_id"));
        room.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.ofNullable(room);
    }
}
