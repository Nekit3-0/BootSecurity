package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    User findUserById(Long id);

    User findUserByLogin(String login);

    void editUserById(User user);

    void removeUserById(Long id);

    List<User> allUsers();
}
