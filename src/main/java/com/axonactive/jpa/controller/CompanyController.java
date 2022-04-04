package com.axonactive.jpa.controller;

import com.axonactive.jpa.service.CompanyService;
import com.axonactive.jpa.service.EmployeeService;
import com.axonactive.jpa.service.RelativeService;


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
    CompanyService companyService;

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
    @Path("departmentsprojects")
    public Response getDepartmentsProjects(){
        return Response.ok(companyService.getDepartmentsProjects()).build();
    }

    @GET
    @Path("projectsdetails")
    public Response getProjectsDetails(){
        return Response.ok(companyService.getProjectsDetails()).build();
    }

    @GET
    @Path("employeenotinanyproject")
    public Response getEmployeeNotInAnyProject(){
        return Response.ok(companyService.getEmployeeNotInAnyProject()).build();
    }

    @GET
    @Path("employeeinotherdepartmentproject")
    public Response getEmployeeInOtherDepartmentProject(){
        return Response.ok(companyService.getEmployeeInOtherDepartmentProject()).build();
    }

    @GET
    @Path("customers")
    public Response getCustomerInfo(){
        return Response.ok(companyService.getCustomers()).build();
    }

}
