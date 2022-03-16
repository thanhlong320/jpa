package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.AssignmentRequest;
import com.axonactive.jpa.entity.Assignment;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.persistence.AbstractCRUDBean;
import com.axonactive.jpa.persistence.PersistenceService;
import com.axonactive.jpa.service.AssignmentService;
import com.axonactive.jpa.service.EmployeeService;
import com.axonactive.jpa.service.dto.AssignmentDTO;
import com.axonactive.jpa.service.mapper.AssignmentMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Transactional
public class AssignmentServiceImpl extends AbstractCRUDBean<Assignment> implements AssignmentService{

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Inject
    AssignmentMapper assignmentMapper;

    @Inject
    private PersistenceService<Assignment> assignmentPersistenceService;

    @Override
    protected PersistenceService<Assignment> getPersistenceService() {
        return this.assignmentPersistenceService;
    }

    @Override
    public List<AssignmentDTO> getAssignments() {
        TypedQuery<Assignment> namedQuery = entityManager.createNamedQuery(Assignment.GET_ALL, Assignment.class);
        return assignmentMapper.AssignmentsToAssignmentDtos(namedQuery.getResultList());
    }

    @Override
    public AssignmentDTO getAssignmentById(int assignmentId) {
        TypedQuery<Assignment> namedQuery = entityManager.createNamedQuery(Assignment.GET_ASSIGNMENT_BY_ID, Assignment.class);
        namedQuery.setParameter("assignmentId", assignmentId);
        return assignmentMapper.AssignmentToAssignmentDto(namedQuery.getSingleResult());
    }

    @Override
    public AssignmentDTO addAssignment(AssignmentRequest assignmentRequest) {
        Employee employee = entityManager.createQuery("from Employee e where e.id = :employeeId", Employee.class).setParameter("employeeId", assignmentRequest.getEmployeeId()).getSingleResult();
        Project project = entityManager.createQuery("from Project p where p.id = :projectId", Project.class).setParameter("projectId", assignmentRequest.getProjectId()).getSingleResult();

        Assignment assignment = new Assignment();
        if (Objects.nonNull(employee) && Objects.nonNull(project)) {
            assignment.setEmployee(employee);
            assignment.setProject(project);
            save(assignment);
        }
        return assignmentMapper.AssignmentToAssignmentDto(assignment);
    }

    @Override
    public void deleteAssignment(int assignmentId) {
        Assignment assignment = entityManager.find(Assignment.class, assignmentId);
        if (Objects.nonNull(assignment)) {
            removeEntity(assignment);
        }
    }

    @Override
    public AssignmentDTO updateAssignment(int assignmentId, AssignmentRequest assignmentRequest) {
        Employee employee = entityManager.createQuery("from Employee e where e.id = :employeeId", Employee.class).setParameter("employeeId", assignmentRequest.getEmployeeId()).getSingleResult();
        Project project = entityManager.createQuery("from Project p where p.id = :projectId", Project.class).setParameter("projectId", assignmentRequest.getProjectId()).getSingleResult();

        Assignment assignment = entityManager.find(Assignment.class, assignmentId);
        Assignment newAssignment = new Assignment();
        if (Objects.nonNull(assignment)) {
            newAssignment.setEmployee(employee);
            newAssignment.setProject(project);
            update(assignment);
        }
        return assignmentMapper.AssignmentToAssignmentDto(newAssignment);
    }

}
