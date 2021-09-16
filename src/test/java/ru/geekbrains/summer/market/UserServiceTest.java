package ru.geekbrains.summer.market;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.summer.market.model.User;
import ru.geekbrains.summer.market.repositories.UserRepository;
import ru.geekbrains.summer.market.services.UserService;

import java.util.Optional;

@SpringBootTest (classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findUserTest(){
        User userTest = new User();
        userTest.setUsername("user");
        userTest.setEmail("bob_johnson@gmail.com");

        Mockito.doReturn(Optional.of(userTest)).when(userRepository).findByUsername("user");
    }
}
