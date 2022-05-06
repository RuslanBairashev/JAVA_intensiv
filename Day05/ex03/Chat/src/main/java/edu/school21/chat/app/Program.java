package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl();
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl();
        Optional<Message> messageOptional = messagesRepository.findById(1L);
        boolean flag = false;
        if ( (flag = messageOptional.isPresent()) == true) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDateTime(null);
            messagesRepository.update(message);
            System.out.println(message.toString());

            User user = new User();
            user.setUser_id(5L);
            message.setAuthor(user);
            message.setText("Change user");
            System.out.println(user.toString());
            messagesRepository.update(message);
            System.out.println(message.toString());

            user.setUser_id(500L);
            message.setText("Wrong");
            message.setAuthor(user);
            System.out.println(user.toString());
            messagesRepository.update(message);
            System.out.println(message.toString());
        }
    }
}
