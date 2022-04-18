package com.axonactive.jpa.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProjectDTO {
    @NotNull
    private String name;
    @NotNull
    private String area;

    private DepartmentDTO department;
}
