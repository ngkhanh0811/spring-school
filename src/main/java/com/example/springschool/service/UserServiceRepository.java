package com.example.springschool.service;

import com.example.springschool.entity.User;

public interface UserServiceRepository {
    User findById(long id);
    boolean add(User user);
    User getByName(String username);
    boolean checkLogin(User user);
}
