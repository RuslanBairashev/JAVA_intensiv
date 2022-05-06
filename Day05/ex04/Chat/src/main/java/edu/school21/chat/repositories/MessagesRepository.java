package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.Optional;

public interface MessagesRepository {
    void update(Message message);
}
