package com.axonactive.jpa.service.mapper;

import com.axonactive.jpa.controller.request.EmployeeRequest;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.service.dto.EmployeeDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Mapping(source = "department.name", target = "departmentName")
    EmployeeDTO EmployeeToEmployeeDto(Employee Employee);

    List<EmployeeDTO> EmployeesToEmployeeDtos(List<Employee> employeeList);

    Employee EmployeeRequestToEmployee(EmployeeRequest employeeRequest);

    @AfterMapping
    default void getFullName(Employee employee, @MappingTarget EmployeeDTO employeeDTO) {
        employeeDTO.setFullName(employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
    }
}
