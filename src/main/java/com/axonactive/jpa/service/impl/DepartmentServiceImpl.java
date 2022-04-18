package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.DepartmentRequest;
import com.axonactive.jpa.entity.Department;
import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.service.DepartmentService;
import com.axonactive.jpa.service.dto.DepartmentDTO;
import com.axonactive.jpa.service.dto.DepartmentProjectsDTO;
import com.axonactive.jpa.service.dto.ProjectDTO;
import com.axonactive.jpa.service.mapper.*;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import java.util.stream.Collectors;

@RequestScoped
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Inject
    DepartmentMapper departmentMapper;

    @Inject
    ProjectMapper projectMapper;

    @Override
    public Department getDepartmentById(int id) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        Predicate condition = builder.equal(root.get("id"), id);
        query.select(root).where(condition);
        try {
            return session.createQuery(query).getSingleResult();
        } catch (Exception e){
            throw new WebApplicationException("Can not find department by id = " + id);
        }
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
        } else{
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
            throw new WebApplicationException("Can not update department by id = " + id);
        }
    }

    @Override
    public List<DepartmentProjectsDTO> getDepartmentWithProjects() {
        return entityManager.createQuery("FROM Project", Project.class).getResultList()
                .stream()
                .collect(Collectors.groupingBy(Project::getDepartment))
                .entrySet()
                .stream().map(entry ->{
                    DepartmentDTO departmentDTO = departmentMapper.toDTO(entry.getKey());
                    List<ProjectDTO> projectDTOS = projectMapper.ProjectsToProjectDtos(entry.getValue());
                    return new DepartmentProjectsDTO(departmentDTO, projectDTOS);
                }).collect(Collectors.toList());
    }
}
