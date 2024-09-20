package com.ead.authuser.domain.application.service;

import com.ead.authuser.core.repository.IUserRepository;
import com.ead.authuser.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;
}
