package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getList();
    User getById(long id);
    User getByUsername(String username);
    void store(User user);
    void update(User user);
    void delete(long id);
}
