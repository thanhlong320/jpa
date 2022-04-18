package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.entity.User;
import com.axonactive.jpa.service.JWTAuthenticationService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.io.File;
import java.util.ResourceBundle;

@RunWith(Arquillian.class)
public class ProjectServiceImplTest {
    @Deployment
    public static WebArchive createTestArchive() {
        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "JPA_Practice.war")
                .addAsLibraries(files)
                .addClass(ResourceBundle.class)
                .addPackages(true, "com.axonactive")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("META-INF/data.sql")
                .addAsResource("jwt.properties")
                .addAsResource("META-INF/beans.xml");
    }
    @Inject
    Greeter greeter;

    @Inject
    JWTAuthenticationService jwtAuthenticationService;

    @Test
    public void getProjectById() {
        Assert.assertEquals("Hello, Earthling!",
                greeter.createGreeting("Earthling"));
    }
    @Test
    public void checkAuthorized(){
        Assert.assertThrows(WebApplicationException.class,()->jwtAuthenticationService.checkAuthorized(null));
    }

    @Test
    public void getToken(){
        User user = new User();
        user.setUsername("long");
        user.setPassword("admin");
        Assert.assertNotNull(jwtAuthenticationService.getToken(user));
    }

}