package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getList();
    User getById(long id);
    User getByUsername(String username);
    void store(User user);
    void update(User user);
    void delete(long id);
}
