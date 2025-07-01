package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByLogin(String login);
    void saveUser(User user);

    // исправлено замечание с задания 2.3.1 заменить примитивный тип на обёртку
    User getUserById(Long id);
    void updateUser(User user);

    // исправлено замечание с задания 2.3.1 заменить примитивный тип на обёртку
    void deleteUser(Long id);
    List<User> listUsers();
}