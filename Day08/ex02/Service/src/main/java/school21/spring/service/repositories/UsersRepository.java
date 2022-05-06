package school21.spring.service.repositories;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UsersRepository<T> extends CrudRepository<T> {
    Optional<T> findByEmail(String email);
}
