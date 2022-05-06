package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    public UserServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    void userCorrectLoginTest() {
        User user = new User( 1L,"Sam", "123");
        Mockito.when(userRepository.findByLogin("Sam")).thenReturn(user);
        assertTrue(userService.authenticate("Sam", "123"));
    }

    @Test
    void userUpdateTest() {
        User user = new User( 1L,"Sam", "123");
        Mockito.when(userRepository.findByLogin("Sam")).thenReturn(user);
        userService.authenticate("Sam", "123");

        User updatedUser = new User( 1L,"Sam", "123");
        updatedUser.setStatus(true);
        assertEquals(updatedUser, user);
    }

    @Test
    void userAlreadyLoggedInTest() {
        User user = new User( 1L,"Sam", "123");
        user.setStatus(true);
        Mockito.when(userRepository.findByLogin("Sam")).thenReturn(user);
        assertThrows(AlreadyAuthenticatedException.class,
                () ->
                        userService.authenticate("Sam", "123"));
    }

    @Test
    void userIncorrectLoginTest() {
        User user = new User( 1L,"Sam", "123");
        Mockito.when(userRepository.findByLogin("Sam")).thenReturn(user);
        assertFalse(userService.authenticate("Spam", "123"));
    }

    @Test
    void userIncorrectPasswordTest() {
        User user = new User( 1L,"Sam", "123");
        Mockito.when(userRepository.findByLogin("Sam")).thenReturn(user);
        assertFalse(userService.authenticate("Sam", "0"));
    }


}
