package com.jason.example.service;

import com.jason.example.domain.User;

import java.util.List;

public interface UserService {
    List<User> getLists();
    Long save(User user);
}
