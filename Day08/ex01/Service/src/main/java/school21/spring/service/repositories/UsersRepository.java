package school21.spring.service.repositories;

import java.util.Optional;

public interface UsersRepository<T> extends CrudRepository<T> {
    Optional<T> findByEmail(String email);
}
