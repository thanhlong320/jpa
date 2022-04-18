package com.axonactive.jpa.controller;

import com.axonactive.jpa.service.dto.ProjectDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProjectControllerTest {

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

    @ArquillianResource
    private URL deploymentURL;

    @Test
    @RunAsClient
    @Order(1)
    public void getAllProjectByDepartmentId(@ArquillianResteasyResource("api/departments/1/projects") ResteasyWebTarget webTarget)
    {
        //        Given
        final Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.acceptEncoding("UTF-8");
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        final Invocation invocation = invocationBuilder.buildGet();

        //        When
        final Response response = invocation.invoke();

        //        Then
        assertEquals(deploymentURL + "api/departments/1/projects", webTarget.getUri().toASCIIString());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    @RunAsClient
    @Order(2)
    public void addProject(@ArquillianResteasyResource("api/departments/1/projects") ResteasyWebTarget webTarget)
    {
        //        Given
        final Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.acceptEncoding("UTF-8");
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        final Invocation invocation = invocationBuilder.buildPost(Entity.entity("{\n" +
                "    \"area\": \"CAMBODIA\",\n" +
                "    \"name\": \"NEW PROJECT\"\n" +
                "}", MediaType.APPLICATION_JSON));

        //        When
        final Response response = invocation.invoke();
        ProjectDTO responseDTO = response.readEntity(ProjectDTO.class);

        //        Then
        assertEquals(deploymentURL + "api/departments/1/projects", webTarget.getUri().toASCIIString());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("CAMBODIA", responseDTO.getArea());
        assertEquals("NEW PROJECT", responseDTO.getName());
    }

    @Test
    @RunAsClient
    @Order(3)
    public void updateProject(@ArquillianResteasyResource("api/departments/1/projects/1") ResteasyWebTarget webTarget)
    {
        //        Given
        final Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.acceptEncoding("UTF-8");
        invocationBuilder.accept(MediaType.APPLICATION_JSON);
        final Invocation invocation = invocationBuilder.buildPut(Entity.entity("{\n" +
                "    \"area\": \"CAMBODIA UPDATED\",\n" +
                "    \"name\": \"NEW PROJECT UPDATED\"\n" +
                "}", MediaType.APPLICATION_JSON));

        //        When
        final Response response = invocation.invoke();
        ProjectDTO responseDTO = response.readEntity(ProjectDTO.class);

        //        Then
        assertEquals(deploymentURL + "api/departments/1/projects/1", webTarget.getUri().toASCIIString());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("CAMBODIA UPDATED", responseDTO.getArea());
        assertEquals("NEW PROJECT UPDATED", responseDTO.getName());
    }

    @Test
    @RunAsClient
    public void deleteProject(@ArquillianResteasyResource("api/departments/1/projects/21") ResteasyWebTarget webTarget)
    {
        //        Given
        final Invocation.Builder invocationBuilder = webTarget.request();
        final Invocation invocation = invocationBuilder.buildDelete();

        //        When
        final Response response = invocation.invoke();

        //        Then
        assertEquals(deploymentURL + "api/departments/1/projects/21", webTarget.getUri().toASCIIString());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}