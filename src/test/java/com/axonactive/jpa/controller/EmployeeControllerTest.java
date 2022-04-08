//package com.axonactive.jpa.controller;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.container.test.api.RunAsClient;
//import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.test.api.ArquillianResource;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import javax.inject.Inject;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.File;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import static org.junit.Assert.*;
//@RunWith(Arquillian.class)
//public class EmployeeControllerTest {
//
//    @Deployment
//    public static WebArchive createTestArchive() {
//        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
//                .importRuntimeDependencies().resolve().withTransitivity().asFile();
//        return ShrinkWrap.create(WebArchive.class, "JPA_Practice.war")
//                .addAsLibraries(files)
//                .addClass(ResourceBundle.class)
//                .addPackages(true, "com.axonactive")
//                .addAsResource("META-INF/persistence.xml")
//                .addAsResource("META-INF/data.sql")
//                .addAsResource("jwt.properties")
//                .addAsResource("META-INF/beans.xml");
//    }
//
//    @Inject
//    DepartmentController departmentController;
//
//    @ArquillianResource
//    private URL deploymentURL;
//
//    @Test
//    @RunAsClient
//    public void getallDepartments(@ArquillianResteasyResource("api/departments") ResteasyWebTarget webTarget)
//    {
//        //        Given
//        final Invocation.Builder invocationBuilder = webTarget.request();
//        invocationBuilder.acceptEncoding("UTF-8");
//        invocationBuilder.accept(MediaType.APPLICATION_JSON);
//        final Invocation invocation = invocationBuilder.buildGet();
//
//        //        When
//        final Response response = invocation.invoke();
//        System.out.println(response.getEntity());
//        //        Then
//        assertEquals(deploymentURL + "api/departments", webTarget.getUri().toASCIIString());
//        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }
//}