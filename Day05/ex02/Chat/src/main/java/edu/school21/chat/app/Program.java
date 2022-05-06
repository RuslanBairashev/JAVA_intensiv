package edu.school21.chat.app;


import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.ChatroomsRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl();
        ChatroomsRepositoryJdbcImpl chatroomsRepositoryJdbc = new ChatroomsRepositoryJdbcImpl();

        User author = usersRepositoryJdbc.findById(1L).get();
        Chatroom room = chatroomsRepositoryJdbc.findById(1L).get();

        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl();
        messagesRepositoryJdbc.save(message);
        System.out.println(message.getId());

        author.setUser_id(50L);
        message = new Message(0L, author, room, "new text", LocalDateTime.now());
        messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl();
        messagesRepositoryJdbc.save(message);
    }
}
