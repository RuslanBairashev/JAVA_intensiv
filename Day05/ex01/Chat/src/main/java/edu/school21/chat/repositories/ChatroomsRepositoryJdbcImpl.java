package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;

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

    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        Chatroom room = new Chatroom();

        String sql = "SELECT * FROM chatroom WHERE chatroom_id=?";
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
        room.setChatroom_id(rs.getLong("chatroom_id"));
        room.setName(rs.getString("name"));

        return Optional.ofNullable(room);
    }
}
