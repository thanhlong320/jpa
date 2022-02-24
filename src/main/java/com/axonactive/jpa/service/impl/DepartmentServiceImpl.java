package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.service.DepartmentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        Predicate condition = builder.equal(root.get("id"), id);
        query.select(root).where(condition);
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public List<Department> getAllDepartment() {
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root);
        return session.createQuery(query).getResultList();
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
