package com.axonactive.jpa.service;

import com.axonactive.jpa.entity.User;
import com.axonactive.jpa.service.dto.Token;

public interface JWTAuthenticationService {
    Token getToken(User user);
    void checkAuthorized(String authorization);
}
