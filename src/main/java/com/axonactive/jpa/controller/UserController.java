package com.axonactive.jpa.controller;

import com.axonactive.jpa.entity.User;
import com.axonactive.jpa.service.impl.JWTAuthenticationServiceImpl;
import com.axonactive.jpa.service.dto.Token;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class UserController {

    @Inject
    private JWTAuthenticationServiceImpl jwtAuthenticationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user){
        Token token = jwtAuthenticationService.getToken(user);
        return Response.ok(token).build();
    }
}
