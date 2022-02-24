package com.axonactive.jpa.service;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Department getDepartmentById (int id);
    Department addDepartment(DepartmentRequest departmentRequest);
    void deleteDepartment(int id);
    Department updateDepartment(int id, DepartmentRequest departmentRequest);


}
