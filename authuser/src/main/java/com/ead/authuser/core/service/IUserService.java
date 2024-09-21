package com.ead.authuser.core.service;

import com.ead.authuser.core.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(UUID userId);

    void delete(User userOptional);

    void save(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
