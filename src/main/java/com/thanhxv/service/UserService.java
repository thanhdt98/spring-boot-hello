package com.thanhxv.service;

import com.thanhxv.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    User getUser(Long id);

    List<User> getUsers();
}
