package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String role) {
        /*return em.createQuery(
                "SELECT r from Role r where r.role=:role", Role.class
        ).setParameter("role", role).getSingleResult(); */
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.role = :role", Role.class);
        query.setParameter("role", role);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void save(Role role) {
        em.persist(role);
    }

    @Override
    public List<Role> getSetOfRoles(String[] roles) {
        List<Role> listOfRoles = new ArrayList<>();
        for (String role : roles) {
            listOfRoles.add(getRoleByName(role));
        }
        return listOfRoles;
    }
}
