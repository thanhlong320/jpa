package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.entity.User;
import com.axonactive.jpa.service.AuthService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class AuthServiceImpl implements AuthService {
    @PersistenceContext(unitName = "jpa")
    private EntityManager entityManager;

    @Override
    public boolean checkValidUser(User user) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password =:password", User.class);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        User userEntity = query.getSingleResult();
        if(Objects.nonNull(userEntity)){
            return true;
        }
        throw new WebApplicationException(Response.status(400).entity("Username and password not corrected").build());
    }
}
