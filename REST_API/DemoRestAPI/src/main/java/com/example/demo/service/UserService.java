package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User findByEmail(String email);
    User create(User user);
    User update(User user);
    void delete(User user);
}
