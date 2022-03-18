package com.axonactive.jpa.service;

import com.axonactive.jpa.entity.User;

public interface AuthService {
    void checkValidUser(User user);
}
