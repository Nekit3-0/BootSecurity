package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    User getUserById(Long id);

    User getUserByLogin(String login);

    void updateUserById(User user);

    void removeUserById(Long id);

    List<User> allUsers();
}
