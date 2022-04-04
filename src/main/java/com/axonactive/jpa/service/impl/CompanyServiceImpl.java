package com.axonactive.jpa.service.impl;

import com.axonactive.jpa.entity.Assignment;
import com.axonactive.jpa.entity.Employee;
import com.axonactive.jpa.entity.Project;
import com.axonactive.jpa.service.CompanyService;
import com.axonactive.jpa.service.dto.DepartmentDTO;
import com.axonactive.jpa.service.dto.EmployeeDTO;
import com.axonactive.jpa.service.dto.ProjectDetailsDTO;
import com.axonactive.jpa.service.mapper.EmployeeMapper;
import com.axonactive.jpa.service.mapper.ProjectMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService {

    @PersistenceContext(name = "jpa")
    EntityManager entityManager;

    @Inject
    private ProjectMapper projectMapper;

    @Inject
    private EmployeeMapper employeeMapper;


    @Override
    public List<DepartmentDTO> getDepartmentsProjects() {
        return entityManager.createQuery("FROM Project", Project.class).getResultList()
                .stream()
                .collect(Collectors.groupingBy(Project::getDepartment))
                .entrySet()
                .stream()
                .map(departmentListEntry -> {
                    DepartmentDTO departmentDTO = new DepartmentDTO();
                    departmentDTO.setProjects(projectMapper.ProjectsToProjectDtos(departmentListEntry.getValue()));
                    departmentDTO.setName(departmentListEntry.getKey().getName());
                    return departmentDTO;
                })
                .sorted(Comparator.comparing(DepartmentDTO::getName).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDetailsDTO> getProjectsDetails() {
        return entityManager.createQuery("FROM Assignment", Assignment.class).getResultList()
                .stream()
                .collect(Collectors.groupingBy(Assignment::getProject))
                .entrySet()
                .stream()
                .filter(projectListEntry -> projectListEntry.getKey().getArea().equals("Vietnam"))
                .map(projectListEntry -> {
                    ProjectDetailsDTO projectDetailsDTO = new ProjectDetailsDTO();
                    projectDetailsDTO.setName(projectListEntry.getKey().getName());
                    projectDetailsDTO.setArea(projectListEntry.getKey().getArea());
                    projectDetailsDTO.setTotalEmployee((int) projectListEntry.getValue().stream().map(Assignment::getEmployee).distinct().count());
                    projectDetailsDTO.setTotalHour(projectListEntry.getValue().stream().mapToDouble(Assignment::getNumberOfHour).sum());
                    projectDetailsDTO.setTotalSalary(projectListEntry.getValue().stream().mapToDouble(assignment -> assignment.getNumberOfHour() * (assignment.getEmployee().getSalary() / 160)).sum());
                    return projectDetailsDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeNotInAnyProject() {
        List<Employee> employeeInProject = new ArrayList<>(entityManager.createQuery("FROM Assignment", Assignment.class).getResultList()
                .stream()
                .collect(Collectors.groupingBy(Assignment::getEmployee))
                .keySet());
        return employeeMapper.EmployeesToEmployeeDtos(entityManager.createQuery("FROM Employee", Employee.class).getResultList()
                .stream()
                .filter(employee -> !employeeInProject.contains(employee))
                .collect(Collectors.toList()));
    }

    @Override
    public List<EmployeeDTO> getEmployeeInOtherDepartmentProject() {
        return employeeMapper.EmployeesToEmployeeDtos(entityManager.createQuery("FROM Assignment", Assignment.class).getResultList()
                .stream()
                .collect(Collectors.groupingBy(Assignment::getEmployee))
                .entrySet()
                .stream()
                .filter(employeeListEntry ->
                        employeeListEntry.getValue()
                                .stream()
                                .map(assignment -> assignment.getEmployee().getDepartment().getId()).count() > 1
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }
}
