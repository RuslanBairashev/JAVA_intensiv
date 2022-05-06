package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSource();
        int page = 3;
        int size = 4;

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        usersRepository.findAll(3, 4);
    }
}
