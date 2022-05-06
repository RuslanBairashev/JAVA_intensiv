package edu.school21.sockets.repositories;

import java.util.Optional;

public interface UsersRepository<T> extends CrudRepository<T> {
    Optional<T> findByName(String name);
    boolean passwordIsValid(String name, String password);
    public void addMessage(String username, String message);
}
