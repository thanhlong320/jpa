package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.service.DepartmentService;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Override
    public Department getDepartmentById(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> getAllDepartment() {
        return entityManager.createQuery("from Department", Department.class).getResultList();
    }

    @Override
    public Department addDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setStartDate(departmentRequest.getStartDate());
        entityManager.persist(department);
        return department;
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = getDepartmentById(id);
        if (Objects.nonNull(department)) {
            entityManager.remove(department);
        }
    }

    @Override
    public Department updateDepartment(int id, DepartmentRequest departmentRequest) {
        Department department = getDepartmentById(id);
        department.setName(departmentRequest.getName());
        department.setStartDate(departmentRequest.getStartDate());
        entityManager.merge(department);
        return department;
    }


}
