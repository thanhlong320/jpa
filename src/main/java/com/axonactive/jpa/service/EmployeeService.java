package com.axonactive.jpa.service;

import com.axonactive.jpa.controller.request.EmployeeRequest;
import com.axonactive.jpa.service.dto.DepartmentStatisticDTO;
import com.axonactive.jpa.service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployeeByDepartment(int departmentId);

    EmployeeDTO getEmployeeById(int departmentId, int employeeId);

    EmployeeDTO addEmployee(int departmentId, EmployeeRequest employeeRequest);

    void deleteEmployee(int departmentId, int employeeId);

    EmployeeDTO updateEmployee(int departmentId, int employeeId, EmployeeRequest employeeRequest);

    List<DepartmentStatisticDTO> getEmployeeGroupByDepartment();

    List<EmployeeDTO> getEmployeeByBirthDayMonth(int month);
}
