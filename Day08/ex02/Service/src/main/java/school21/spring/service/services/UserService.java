package school21.spring.service.services;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public String signUp(String email);
}
