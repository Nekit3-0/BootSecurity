package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    Role getRoleByName(String role);

    List<Role> getAllRoles();

    List<Role> getSetOfRoles(String[] roles);

}