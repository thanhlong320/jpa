package com.axonactive.jpa.controller;

import com.axonactive.jpa.service.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/statistic")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {

    @Inject
    EmployeeService employeeService;

    @Inject
    RelativeService relativeService;

    @Inject
    ProjectService projectService;

    @Inject
    DepartmentService departmentService;

    @GET
    @Path("/employeegroupbydepartment")
    public Response getDepartmentStatistic() {
        return Response.ok(employeeService.getEmployeeGroupByDepartment()).build();
    }

    @GET
    @Path("/employeebybirthdaymonth/{month}")
    public Response getEmployeeByBirthDayMonth(@PathParam("month") int month) {
        return Response.ok(employeeService.getEmployeeByBirthDayMonth(month)).build();
    }
    @GET
    @Path("relativeofemployee")
    public Response getRelativeOfEmployee() {
        return Response.ok(relativeService.getRelativeOfEmployee()).build();
    }

    @GET
    @Path("employee-emergency")
    public Response getEmployeeEmergencyRelative(){
        return Response.ok(relativeService.getEmployeeEmergencyRelative()).build();
    }


    @GET
    @Path("projects")
    public Response getProjectWithManagedDepartment(){
        return Response.ok(projectService.getProjectWithManagedDepartment()).build();
    }

    @GET
    @Path("department-projects")
    public Response getDepartmentWithProjects(){
        return Response.ok(departmentService.getDepartmentWithProjects()).build();
    }

}
