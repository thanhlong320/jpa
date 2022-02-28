package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestScoped
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Override
    public Department getDepartmentById(int id) {
        logger.info("Get departmen by id = " + id + " ....");
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        Predicate condition = builder.equal(root.get("id"), id);
        query.select(root).where(condition);
        try {
            return session.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.warn("Get department by id = " + id + " failed");
            throw new WebApplicationException("Can not find department by id = " + id);
        }
    }

    @Override
    public List<Department> getAllDepartment() {
        logger.info("Get all department....");
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
        } else{
            logger.warn("Delete department by id = " + id + " failed");
            throw new WebApplicationException("Can not delete department by id = " + id);
        }
    }

    @Override
    public Department updateDepartment(int id, DepartmentRequest departmentRequest) {
        try {
            Department department = getDepartmentById(id);
            department.setName(departmentRequest.getName());
            department.setStartDate(departmentRequest.getStartDate());
            entityManager.merge(department);
            return department;
        } catch (Exception e ){
            logger.warn("Update department by id = " + id + " failed");
            throw new WebApplicationException("Can not update department by id = " + id);
        }
    }


}
