package com.axonactive.jpa.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private String name;
    private List<ProjectDTO> projects;
}
