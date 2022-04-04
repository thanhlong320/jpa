package com.axonactive.jpa.service;

import com.axonactive.jpa.entity.Customer;
import com.axonactive.jpa.service.dto.DepartmentDTO;
import com.axonactive.jpa.service.dto.EmployeeDTO;
import com.axonactive.jpa.service.dto.ProjectDTO;
import com.axonactive.jpa.service.dto.ProjectDetailsDTO;

import java.util.List;

public interface CompanyService {
    List<DepartmentDTO> getDepartmentsProjects();

    List<ProjectDetailsDTO> getProjectsDetails();

    List<EmployeeDTO> getEmployeeNotInAnyProject();

    List<EmployeeDTO> getEmployeeInOtherDepartmentProject();

    List<Customer> getCustomers();
}
