package com.axonactive.jpa.controller;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Department", tags = "ABCDEFGH")
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;

    @GET
    @ApiOperation(value = "Get all department",notes = "Get list of department")
    @ApiResponses( value = {@ApiResponse(code = 200, message = "Success"),@ApiResponse(code = 404,message = "Not found")})
    public Response getAllDepartments(){
        return Response.ok(departmentService.getAllDepartment()).build();
    }


    @GET
    @Path("/{id}")
    public Response getDepartmentById(@PathParam("id") int id){
        return Response.ok(departmentService.getDepartmentById(id)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDepartment(DepartmentRequest departmentRequest){
        return Response.ok(departmentService.addDepartment(departmentRequest)).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") int id){
        departmentService.deleteDepartment(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDepartment(@PathParam("id") int id, DepartmentRequest departmentRequest){
        return Response.ok(departmentService.updateDepartment(id,departmentRequest)).build();
    }



}
