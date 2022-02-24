package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.controller.request.ProjectRequest;
import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.service.DepartmentService;
import com.axonactive.jpa.service.ProjectService;
import com.axonactive.jpa.service.dto.ProjectDTO;
import com.axonactive.jpa.service.mapper.ProjectMapper;

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
public class ProjectServiceImpl implements ProjectService {

    @PersistenceContext(unitName = "jpa")
    EntityManager entityManager;

    @Inject
    DepartmentService departmentService;

    @Inject
    ProjectMapper projectMapper;

    private Project getProjectByIdHelper(int departmentId, int projectId) {
        TypedQuery<Project> namedQuery = entityManager.createNamedQuery(Project.GET_PROJECT_BY_ID, Project.class);
        namedQuery.setParameter("departmentId", departmentId);
        namedQuery.setParameter("projectId", projectId);
        return namedQuery.getSingleResult();
    }


    @Override
    public ProjectDTO getProjectById(int departmentId, int projectId) {
        return projectMapper.ProjectToProjectDto(getProjectByIdHelper(departmentId, projectId));
    }

    @Override
    public List<ProjectDTO> getAllProjectByDepartment(int departmentId) {
        TypedQuery<Project> namedQuery = entityManager.createNamedQuery(Project.GET_ALL, Project.class);
        namedQuery.setParameter("departmentId", departmentId);
        List<Project> projectList = namedQuery.getResultList();

        return projectMapper.ProjectsToProjectDtos(projectList);
    }

    @Override
    public ProjectDTO addProject(int departmentId, ProjectRequest projectRequest) {
        Project project = new Project();
        project.setArea(projectRequest.getArea());
        project.setName(projectRequest.getName());
        project.setDepartment(departmentService.getDepartmentById(departmentId));
        entityManager.persist(project);
        return projectMapper.ProjectToProjectDto(project);
    }

    @Override
    public void deleteProject(int departmentId, int projectId) {
        Project project = getProjectByIdHelper(departmentId, projectId);
        if (Objects.nonNull(project)) {
            entityManager.remove(project);
        }
    }

    @Override
    public ProjectDTO updateProject(int departmentId, int projectId, ProjectRequest projectRequest) {
        Project project = getProjectByIdHelper(departmentId, projectId);
        project.setArea(projectRequest.getArea());
        project.setName(projectRequest.getName());
        entityManager.merge(project);

        return projectMapper.ProjectToProjectDto(project);
    }
}
