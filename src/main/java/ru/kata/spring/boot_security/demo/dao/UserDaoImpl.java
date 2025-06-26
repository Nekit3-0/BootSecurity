package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery(
                        "SELECT user FROM User user join fetch  user.roles WHERE user.login =:login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public void saveUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {

        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> listUsers() {

        return entityManager.createQuery("From User", User.class).getResultList();
    }
}
