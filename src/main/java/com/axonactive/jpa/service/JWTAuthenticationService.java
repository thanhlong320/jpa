package com.axonactive.jpa.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axonactive.jpa.entity.User;
import org.jboss.security.auth.login.Token;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

public class JWTAuthenticationService {
    @Inject
    private AuthService authService;

    public String getToken(User user){
        authService.checkValidUser(user);
        String token = null;
        String secretKey = AppConfigService.getSecretKey();
        String issuer = AppConfigService.getIssuer();
        Integer timeToLive = AppConfigService.getTimeTolive();
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(this.setTimeToLive(timeToLive))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new WebApplicationException(Response.status(400).entity(e.getMessage()).build());
        }
        return token;
    }

    private Date setTimeToLive(Integer timeToLive) {
        long millis = System.currentTimeMillis();
        return new Date(millis + timeToLive);
    }

    public void checkAuthorized(String authorization) {
        String[] parts = authorization.split("\\s+");
        if (parts.length < 2 || !"Bearer".equals(parts[0])){
            throw new WebApplicationException(Response.status(400).entity("Invalid request").build());
        }
        String token = parts[1];

        String secretKey = AppConfigService.getSecretKey();
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(AppConfigService.getIssuer())
                    .build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException | UnsupportedEncodingException e) {
            throw new WebApplicationException(Response.status(400).entity(e.getMessage()).build());
        }

    }
}
