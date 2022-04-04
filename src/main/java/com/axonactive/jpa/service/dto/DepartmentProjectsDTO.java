package com.axonactive.jpa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DepartmentProjectsDTO {
    private DepartmentDTO department;
    private List<ProjectDTO> projects;
}
