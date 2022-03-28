package com.axonactive.jpa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectManagedDepartmentDTO {
    private ProjectDTO project;
    private DepartmentDTO department;
}
