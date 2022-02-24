package com.axonactive.jpa.controller;

import com.axonactive.jpa.controller.request.RelativeRequest;
import com.axonactive.jpa.service.RelativeService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees/{employeeId}/relatives")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class RelativeController {

    @Inject
    RelativeService relativeService;

    @GET
    public Response getRelatives(@PathParam("employeeId") int employeeId) {
        return Response.ok(relativeService.getRelatives(employeeId)).build();
    }

    @GET
    @Path("/{relativeId}")
    public Response getRelativesById(@PathParam("employeeId") int employeeId, @PathParam("relativeId") int relativeId) {
        return Response.ok(relativeService.getRelativeById(employeeId, relativeId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRelative(@PathParam("employeeId") int employeeId, RelativeRequest relativeRequest) {
        return Response.ok(relativeService.addRelative(employeeId, relativeRequest)).build();
    }

    @PUT
    @Path("{relativeId}")
    public Response updateRelative(@PathParam("employeeId") int employeeId, @PathParam("relativeId") int relativeId, RelativeRequest relativeRequest) {
        return Response.ok(relativeService.updateRelative(employeeId, relativeId, relativeRequest)).build();
    }

    @DELETE
    @Path("{relativeId}")
    public Response deleteRelative(@PathParam("employeeId") int employeeId, @PathParam("relativeId") int relativeId) {
        relativeService.deleteRelative(employeeId, relativeId);
        return Response.ok().build();
    }
}
