package com.pobeguts.repository;

import com.pobeguts.model.User;

import java.util.List;

/**
 * Created by Pobeguts on 30.03.2018.
 */
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
