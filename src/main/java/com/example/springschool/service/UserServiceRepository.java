package com.example.springschool.service;

import com.example.springschool.entity.User;

import java.util.List;

public interface UserServiceRepository {
    User findById(long id);
    boolean add(User user);
    User getByName(String username);
    boolean checkLogin(User user);
    List<User> getAll();
}
