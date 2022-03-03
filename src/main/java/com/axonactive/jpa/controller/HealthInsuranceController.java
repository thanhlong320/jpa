package com.axonactive.jpa.controller;

import com.axonactive.jpa.controller.request.HealthInsuranceRequest;
import com.axonactive.jpa.entity.HealthInsurance;
import com.axonactive.jpa.service.HealthInsuranceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees/{employeeId}/health-insurance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealthInsuranceController {
    @Inject
    private HealthInsuranceService healthInsuranceService;

    @GET
    public Response getHealthInsuranceByEmployeeId(@PathParam("employeeId") Integer employeeId){
        return Response.ok(healthInsuranceService.getHealthInsuranceByEmployeeId(employeeId)).build();
    }

    @POST
    public Response addHealthInsuranceByEmployeeId(@PathParam("employeeId") Integer employeeId, HealthInsuranceRequest healthInsuranceRequest){
        return Response.ok(healthInsuranceService.addHealthInsuranceByEmployeeId(employeeId, healthInsuranceRequest)).build();
    }

    @PUT
    public Response updateHealthInsuranceByEmployeeId(@PathParam("employeeId") Integer employeeId, HealthInsuranceRequest healthInsuranceRequest){
        return Response.ok(healthInsuranceService.updateHealthInsuranceByEmployeeId(employeeId, healthInsuranceRequest)).build();
    }

    @DELETE
    public Response deleteHealthInsuranceByEmployeeId(@PathParam("employeeId") Integer employeeId){
        healthInsuranceService.deleteHealthInsuranceByEmployeeId(employeeId);
        return Response.ok().build();
    }
}
