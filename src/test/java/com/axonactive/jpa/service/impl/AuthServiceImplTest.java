//package com.axonactive.jpa.service.impl;
//
//import com.axonactive.jpa.entity.User;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Test;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//
//import static org.junit.Assert.*;
//
//public class AuthServiceImplTest {
//    @Inject
//    AuthServiceImpl authService;
//
//    @Deployment
//    public static WebArchive createTestArchive() {
//        return ShrinkWrap.create(WebArchive.class, "JPA_Practice.war")
//                .addClasses(AuthServiceImpl.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "META-INF/beans.xml");
//    }
//
//    @Test
//    public void checkValidUser() {
//        User user = new User();
//        user.setUsername("long");
//        user.setPassword("admin");
//        authService.checkValidUser(user);
//    }
//}